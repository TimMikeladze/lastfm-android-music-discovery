
package com.mikeladze.musicdiscovery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.view.gridview.ImageGridAdapter;
import com.origamilabs.library.views.StaggeredGridView;

public abstract class ImageGridViewFragment extends BaseTabFragment {
	
	protected StaggeredGridView gridView;
	protected ImageGridAdapter gridAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_image_grid, container, false);
		
		gridAdapter = new ImageGridAdapter(getActivity(), 0);
		gridView = (StaggeredGridView) v.findViewById(R.id.gridview);
		
		int margin = getResources().getDimensionPixelSize(R.dimen.margin);
		gridView.setItemMargin(margin);
		gridView.setPadding(margin, 0, margin, 0);
		
		gridView.setAdapter(gridAdapter);
		
		initialize();
		
		return v;
	}
	
	public abstract void initialize();
	
}
