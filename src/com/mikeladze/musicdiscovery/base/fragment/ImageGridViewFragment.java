
package com.mikeladze.musicdiscovery.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.view.gridview.ImageGridAdapter;
import com.origamilabs.library.views.StaggeredGridView;
import com.origamilabs.library.views.StaggeredGridView.OnItemClickListener;

/**
 * The ImageGridViewFragment. This is extended by image grid fragments.
 * 
 * @author Tim Mikeladze
 */
public abstract class ImageGridViewFragment extends BaseTabFragment implements OnItemClickListener {
	
	protected StaggeredGridView gridView;
	protected ImageGridAdapter gridAdapter;
	
	@Override
	/**
	 * Creates the view.
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_image_grid, container, false);
		
		gridAdapter = new ImageGridAdapter(getActivity(), 0);
		gridView = (StaggeredGridView) v.findViewById(R.id.gridview);
		
		int margin = getResources().getDimensionPixelSize(R.dimen.margin);
		gridView.setItemMargin(margin);
		gridView.setPadding(margin, 0, margin, 0);
		gridView.setOnItemClickListener(this);
		
		gridView.setAdapter(gridAdapter);
		
		initialize();
		
		return v;
	}
	
	@Override
	/**
	 * Handle on click events, should be defined in child classes.
	 */
	public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
	}
	
	/**
	 * Called as the last step of fragment creation.
	 */
	public void initialize() {
		
	}
	
}
