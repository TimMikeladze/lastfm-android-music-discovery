
package com.mikeladze.musicdiscovery.search.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.http.LastFMResult;
import com.mikeladze.musicdiscovery.view.gridview.loader.ImageLoader;

/**
 * The SearchResultsAdapter. A custom expandable list adapter for display artist and album
 * searches.
 * 
 * @author Tim Mikeladze
 */
public class SearchResultsAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	private ArrayList<String> headers;
	private HashMap<String, List<LastFMResult>> items;
	private ImageLoader imageLoader;
	
	/**
	 * Instantiates a new search results adapter.
	 * 
	 * @param context the context
	 * @param headers the headers
	 * @param items the items
	 */
	public SearchResultsAdapter(Context context, ArrayList<String> headers, HashMap<String, List<LastFMResult>> items) {
		this.context = context;
		this.headers = headers;
		this.items = items;
		
		imageLoader = new ImageLoader(context);
		
	}
	
	@Override
	/**
	 * Get the expandable group view. "Artist" or "Album"
	 */
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		String headerTitle = getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(R.layout.view_search_result_header, null);
		}
		
		TextView lblListHeader = (TextView) convertView.findViewById(R.id.view_search_result_header);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);
		
		return convertView;
	}
	
	@Override
	/**
	 * Get the child view, these are the search results.
	 */
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(R.layout.view_search_result_item, null);
		}
		
		TextView titleView = (TextView) convertView.findViewById(R.id.view_search_result_title);
		ImageView imageView = (ImageView) convertView.findViewById(R.id.view_search_result_image);
		
		if (headers != null && items != null) {
			LastFMResult result = getChild(groupPosition, childPosition);
			titleView.setText(result.getTitle());
			imageLoader.displayImage(result.getImage(), imageView);
		}
		
		return convertView;
	}
	
	@Override
	/**
	 * Get data at child position.
	 */
	public LastFMResult getChild(int groupPosition, int childPosition) {
		return items.get(headers.get(groupPosition))
					.get(childPosition);
		
	}
	
	@Override
	/**
	 * Get the child id.
	 */
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	
	@Override
	/**
	 * Get the child count.
	 */
	public int getChildrenCount(int groupPosition) {
		return items.get(headers.get(groupPosition))
					.size();
	}
	
	@Override
	/**
	 * Get title of group at a position.
	 */
	public String getGroup(int groupPosition) {
		return headers.get(groupPosition);
	}
	
	@Override
	/**
	 * Get number of groups.
	 */
	public int getGroupCount() {
		return headers.size();
	}
	
	@Override
	/**
	 * Get the id of a group at position.
	 */
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	@Override
	/**
	 * Checks if IDs in the adapter are stable.
	 */
	public boolean hasStableIds() {
		return false;
	}
	
	@Override
	/**
	 * Checks if given child is selectable.
	 */
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
