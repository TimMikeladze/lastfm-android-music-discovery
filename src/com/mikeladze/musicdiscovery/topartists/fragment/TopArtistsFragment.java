
package com.mikeladze.musicdiscovery.topartists.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikeladze.musicdiscovery.fragments.BaseTabFragment;

public class TopArtistsFragment extends BaseTabFragment {
	
	private static final String TITLE = "Popular";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
}
