
package com.mikeladze.musicdiscovery.main.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.base.activity.BaseFragmentActivity;
import com.mikeladze.musicdiscovery.base.adapter.SectionsPagerAdapter;
import com.mikeladze.musicdiscovery.main.fragment.HypedArtistsFragment;
import com.mikeladze.musicdiscovery.main.fragment.TopArtistsFragment;

import fm.last.lastfm.LastFM;

public class MainActivity extends BaseFragmentActivity implements ActionBar.TabListener {
	
	private SectionsPagerAdapter sectionsPagerAdapter;
	private ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setTitle("Top Artists");
		
		intialize();
		
		setContentView(R.layout.activity_main);
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
		
		sectionsPagerAdapter.addFragment(Fragment.instantiate(this, TopArtistsFragment.class.getName()));
		sectionsPagerAdapter.addFragment(Fragment.instantiate(this, HypedArtistsFragment.class.getName()));
		
		viewPager = (ViewPager) findViewById(R.id.pager);
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
		
	}
	
	private void intialize() {
		LastFM.setApiKey("aa4b32f5ab59754424905e8bb3576143");
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return true;
	}
	
}
