
package com.mikeladze.musicdiscovery.hypedartists.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikeladze.musicdiscovery.fragments.ImageGridViewFragment;
import com.mikeladze.musicdiscovery.http.RestClient;

import fm.last.charts.Chart;

public class HypedArtistsFragment extends ImageGridViewFragment {
	
	private static final int NUMBER_OF_ARTISTS = 100;
	private static final String TITLE = "Hyped";
	private JSONArray artists;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void initialize() {
		loadTopArtists();
	}
	
	private void loadTopArtists() {
		RestClient.get(Chart.<String> getHypedArtists(0, NUMBER_OF_ARTISTS), new JsonHttpResponseHandler() {
			
			@Override
			public void onSuccess(JSONObject response) {
				if (artists == null) {
					try {
						JSONArray artists = response.getJSONObject("artists")
													.getJSONArray("artist");
						for (int i = 0; i < artists.length(); i++) {
							gridAdapter.addImage(artists.getJSONObject(i)
														.getJSONArray("image")
														.getJSONObject(4)
														.getString("#text"));
						}
						gridAdapter.notifyDataSetChanged();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
}
