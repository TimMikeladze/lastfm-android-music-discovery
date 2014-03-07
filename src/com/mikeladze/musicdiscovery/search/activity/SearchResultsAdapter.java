
package com.mikeladze.musicdiscovery.search.activity;

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

public class SearchResultsAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	private ArrayList<String> headers;
	private HashMap<String, List<LastFMResult>> items;
	private ImageLoader imageLoader;
	
	public SearchResultsAdapter(Context context, ArrayList<String> headers, HashMap<String, List<LastFMResult>> items) {
		this.context = context;
		this.headers = headers;
		this.items = items;
		
		imageLoader = new ImageLoader(context);
		
	}
	
	@Override
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
	public LastFMResult getChild(int groupPosition, int childPosition) {
		return items.get(headers.get(groupPosition))
					.get(childPosition);
		
	}
	
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return items.get(headers.get(groupPosition))
					.size();
	}
	
	@Override
	public String getGroup(int groupPosition) {
		return headers.get(groupPosition);
	}
	
	@Override
	public int getGroupCount() {
		return headers.size();
	}
	
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	@Override
	public boolean hasStableIds() {
		return false;
	}
	
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
