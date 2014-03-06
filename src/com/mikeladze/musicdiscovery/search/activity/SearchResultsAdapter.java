
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
import android.widget.TextView;

import com.mikeladze.musicdiscovery.R;

public class SearchResultsAdapter<T> extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> headers;
    private HashMap<String, List<T>> items;

    public SearchResultsAdapter(Context context) {
        this.context = context;
        this.headers = new ArrayList<String>();
        this.items = new HashMap<String, List<T>>();
    }

    public void addHeader(String header) {
        headers.add(header);
    }

    public void addItem(String header, T t) {
        if(items.containsKey(header) && items.get(header) == null) {
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
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
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView,
            ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.view_search_result_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.view_search_result_title);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public T getChild(int groupPosition, int childPosititon) {
        return items.get(headers.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items.get(headers.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
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
