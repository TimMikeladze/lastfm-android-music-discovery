
package com.mikeladze.musicdiscovery.artist.fragment;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikeladze.musicdiscovery.artist.activity.ArtistActivity;
import com.mikeladze.musicdiscovery.base.fragment.ImageGridViewFragment;
import com.mikeladze.musicdiscovery.http.LastFMResult;
import com.origamilabs.library.views.StaggeredGridView;

public class SimilarArtistsFragment extends ImageGridViewFragment {
	
	private static final String TITLE = "Similar";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
	
	public void displaySimiliarArtists(JSONArray artists) {
		try {
			for (int i = 0; i < artists.length(); i++) {
				String image;
				image = artists.getJSONObject(i)
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
	
	@Override
	public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
		Intent intent = new Intent(getActivity(), ArtistActivity.class);
		Bundle b = new Bundle();
		b.putString("name", gridAdapter.getItem(position)
										.getTitle());
		intent.putExtras(b);
		startActivity(intent);
	}
}
