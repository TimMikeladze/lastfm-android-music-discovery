
package com.mikeladze.musicdiscovery.album.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.base.fragment.BaseTabFragment;

public class AlbumTracksFragment extends BaseTabFragment {
	
	private static final String TITLE = "Tracks";
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private ArrayList<String> tracksList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_generic_list, container, false);
		
		listView = (ListView) v.findViewById(R.id.view_list);
		tracksList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tracksList);
		
		listView.setAdapter(adapter);
		
		return v;
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
	
	public void displayAlbumTracks(JSONArray tracks) {
		try {
			for (int i = 0; i < tracks.length(); i++) {
				
				tracksList.add((i + 1) + ". " + tracks.getJSONObject(i)
														.getString("name"));
			}
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}