
package com.mikeladze.musicdiscovery.main.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikeladze.musicdiscovery.artist.activity.ArtistActivity;
import com.mikeladze.musicdiscovery.base.fragment.ImageGridViewFragment;
import com.mikeladze.musicdiscovery.http.LastFMResult;
import com.mikeladze.musicdiscovery.http.RestClient;
import com.origamilabs.library.views.StaggeredGridView;

import fm.last.charts.Chart;

public class HypedArtistsFragment extends ImageGridViewFragment {
	
	private static final int NUMBER_OF_ARTISTS = 100;
	private static final String TITLE = "Hyped";
	
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
			public void onStart() {
				super.onStart();
				HypedArtistsFragment.this.getActivity()
											.setProgressBarIndeterminateVisibility(true);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				HypedArtistsFragment.this.getActivity()
											.setProgressBarIndeterminateVisibility(false);
				try {
					JSONArray artists = response.getJSONObject("artists")
												.getJSONArray("artist");
					for (int i = 0; i < artists.length(); i++) {
						String image = artists.getJSONObject(i)
												.getJSONArray("image")
												.getJSONObject(4)
												.getString("#text");
						String title = artists.getJSONObject(i)
												.getString("name");
						gridAdapter.add(new LastFMResult(image, title));
					}
					gridAdapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
		Intent intent = new Intent(getActivity(), ArtistActivity.class);
		Bundle b = new Bundle();
		b.putString("name", gridAdapter.getItem(position)
										.getTitle());
		intent.putExtras(b);
		startActivity(intent);
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
}
