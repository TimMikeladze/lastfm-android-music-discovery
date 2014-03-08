
package com.mikeladze.musicdiscovery.base.adapter;

import java.util.LinkedList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mikeladze.musicdiscovery.base.fragment.BaseTabFragment;

/**
 * This is a custom adapter to hold fragments for tabs.
 * 
 * @author Tim Mikeladze
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
	
	private LinkedList<BaseTabFragment> fragments;
	
	/**
	 * Instantiates a new sections pager adapter.
	 * 
	 * @param context the context
	 * @param fragmentManager the fragment manager
	 */
	public SectionsPagerAdapter(Context context, FragmentManager fragmentManager) {
		super(fragmentManager);
		fragments = new LinkedList<BaseTabFragment>();
	}
	
	/**
	 * Adds the fragment.
	 * 
	 * @param f the f
	 */
	public void addFragment(Fragment f) {
		fragments.add((BaseTabFragment) f);
	}
	
	@Override
	/**
	 * Get the fragment at given position.
	 */
	public BaseTabFragment getItem(int position) {
		return fragments.get(position);
	}
	
	@Override
	/**
	 * Get title of the fragment.
	 */
	public CharSequence getPageTitle(int position) {
		return fragments.get(position)
						.getTitle();
	}
	
	@Override
	/**
	 * Get number of fragments in adapter.
	 */
	public int getCount() {
		return fragments.size();
	}
}