
package com.mikeladze.musicdiscovery.main.adapter;

import java.util.LinkedList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mikeladze.musicdiscovery.fragments.BaseTabFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
	
	private LinkedList<BaseTabFragment> fragments;
	
	public SectionsPagerAdapter(Context context, FragmentManager fragmentManager) {
		super(fragmentManager);
		fragments = new LinkedList<BaseTabFragment>();
	}
	
	public void addFragment(Fragment f) {
		fragments.add((BaseTabFragment) f);
	}
	
	@Override
	public BaseTabFragment getItem(int position) {
		return fragments.get(position);
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return fragments.get(position)
						.getTitle();
	}
	
	@Override
	public int getCount() {
		return fragments.size();
	}
}