
package com.mikeladze.musicdiscovery.album.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.album.fragment.AlbumInfoFragment;
import com.mikeladze.musicdiscovery.album.fragment.AlbumTracksFragment;
import com.mikeladze.musicdiscovery.base.activity.BaseFragmentActivity;
import com.mikeladze.musicdiscovery.base.adapter.SectionsPagerAdapter;
import com.mikeladze.musicdiscovery.http.RestClient;

import fm.last.album.Album;

/**
 * The AlbumActivity, contains the fragments for the album.
 * 
 * @author Tim Mikeladze
 */
public class AlbumActivity extends BaseFragmentActivity implements ActionBar.TabListener {
	
	private SectionsPagerAdapter sectionsPagerAdapter;
	private ViewPager viewPager;
	private AlbumInfoFragment albumInfoFragment;
	private AlbumTracksFragment albumTracksFragment;
	
	private String name;
	private String artist;
	private String mbid;
	
	@Override
	/**
	 * Creates the activity for the given album
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_album);
		
		if (savedInstanceState != null) {
			name = savedInstanceState.getString("name");
			artist = savedInstanceState.getString("artist");
			mbid = savedInstanceState.getString("mbid");
			
		} else {
			Bundle b = getIntent().getExtras();
			name = b.getString("name");
			artist = b.getString("artist");
			mbid = b.getString("mbid");
		}
		
		Log.d("info", name + " " + artist + " " + mbid);
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		albumInfoFragment = (AlbumInfoFragment) Fragment.instantiate(this, AlbumInfoFragment.class.getName());
		albumTracksFragment = (AlbumTracksFragment) Fragment.instantiate(this, AlbumTracksFragment.class.getName());
		
		sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
		
		sectionsPagerAdapter.addFragment(albumInfoFragment);
		sectionsPagerAdapter.addFragment(albumTracksFragment);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setOffscreenPageLimit(1);
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
		
		loadAlbum();
	}
	
	@Override
	/**
	 * Stores the received search query for device orientation changed purposes
	 */
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putString("name", name);
		savedInstanceState.putString("artist", artist);
		savedInstanceState.putString("mbid", mbid);
	}
	
	/**
	 * Load the album information and update underlying fragments.
	 */
	private void loadAlbum() {
		String url = Album.<String> getInfo(name, artist, true, mbid, null, null);
		RestClient.get(url, new JsonHttpResponseHandler() {
			
			@Override
			public void onStart() {
				setProgressBarIndeterminateVisibility(true);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				setProgressBarIndeterminateVisibility(false);
				try {
					actionBar.setTitle(response.getJSONObject("album")
												.getString("name") + " - " + response.getJSONObject("album")
																						.getString("artist"));
					albumInfoFragment.displayAlbumInfo(response.getJSONObject("album"));
					albumTracksFragment.displayAlbumTracks(response.getJSONObject("album")
																	.getJSONObject("tracks")
																	.getJSONArray("track"));
					
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