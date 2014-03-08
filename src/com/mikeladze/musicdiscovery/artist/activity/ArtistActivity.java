
package com.mikeladze.musicdiscovery.artist.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.artist.fragment.ArtistAlbumsFragment;
import com.mikeladze.musicdiscovery.artist.fragment.ArtistInfoFragment;
import com.mikeladze.musicdiscovery.artist.fragment.SimilarArtistsFragment;
import com.mikeladze.musicdiscovery.base.activity.BaseFragmentActivity;
import com.mikeladze.musicdiscovery.base.adapter.SectionsPagerAdapter;
import com.mikeladze.musicdiscovery.http.RestClient;

import fm.last.artist.Artist;

/**
 * This activity holds the fragments for an artist.
 * 
 * @author Tim Mikeladze
 */
public class ArtistActivity extends BaseFragmentActivity implements ActionBar.TabListener {
	
	private SectionsPagerAdapter sectionsPagerAdapter;
	private ViewPager viewPager;
	private ArtistInfoFragment artistInfoFragment;
	private ArtistAlbumsFragment artistAlbumsFragment;
	private SimilarArtistsFragment similarArtistsFragment;
	
	private String name;
	private String mbid;
	
	@Override
	/**
	 * Creates the activity.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_artist);
		
		if (savedInstanceState != null && (savedInstanceState.containsKey("name") || savedInstanceState.containsKey("mbid"))) {
			name = savedInstanceState.getString("name");
			mbid = savedInstanceState.getString("mbid");
		} else {
			Bundle b = getIntent().getExtras();
			name = b.getString("name");
			mbid = b.getString("mbid");
		}
		
		actionBar.setTitle(name);
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		artistInfoFragment = (ArtistInfoFragment) Fragment.instantiate(this, ArtistInfoFragment.class.getName());
		artistAlbumsFragment = (ArtistAlbumsFragment) Fragment.instantiate(this, ArtistAlbumsFragment.class.getName());
		similarArtistsFragment = (SimilarArtistsFragment) Fragment.instantiate(this, SimilarArtistsFragment.class.getName());
		
		sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
		
		sectionsPagerAdapter.addFragment(artistInfoFragment);
		sectionsPagerAdapter.addFragment(artistAlbumsFragment);
		sectionsPagerAdapter.addFragment(similarArtistsFragment);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(sectionsPagerAdapter);
		
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
										.setText(sectionsPagerAdapter.getPageTitle(i))
										.setTabListener(this));
		}
		
		loadArtist();
	}
	
	@Override
	/**
	 * Stores the received bundle for device orientation changed purposes
	 */
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putString("name", name);
		savedInstanceState.putString("mbid", mbid);
	}
	
	/**
	 * Load artist and update the fragments.
	 */
	private void loadArtist() {
		RestClient.get(Artist.<String> getInfo(name, true, mbid, null, null), new JsonHttpResponseHandler() {
			
			@Override
			public void onStart() {
				setProgressBarIndeterminateVisibility(true);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				setProgressBarIndeterminateVisibility(false);
				try {
					similarArtistsFragment.displaySimilarArtists(response.getJSONObject("artist")
																			.getJSONObject("similar")
																			.getJSONArray("artist"));
					artistInfoFragment.displayArtistInfo(response.getJSONObject("artist"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		
		RestClient.get(Artist.<String> getTopAlbums(name, true, null, null, null), new JsonHttpResponseHandler() {
			
			@Override
			public void onStart() {
				setProgressBarIndeterminateVisibility(true);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				setProgressBarIndeterminateVisibility(false);
				
				try {
					artistAlbumsFragment.displayAlbums(response.getJSONObject("topalbums")
																.getJSONArray("album"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		viewPager.setCurrentItem(tab.getPosition());
	}
	
	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}
	
	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}
}