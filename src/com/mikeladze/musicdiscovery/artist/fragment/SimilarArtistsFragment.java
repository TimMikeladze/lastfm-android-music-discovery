
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

/**
 * This fragment displays the similar artists related to an artist.
 * 
 * @author Tim Mikeladze
 */
public class SimilarArtistsFragment extends ImageGridViewFragment {
	
	private static final String TITLE = "Similar";
	
	@Override
	/**
	 * Creates the view.
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
	
	/**
	 * Display similar artists.
	 * 
	 * @param artists the artists
	 */
	public void displaySimilarArtists(JSONArray artists) {
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
	/**
	 * Handles click events. Opens corresponding ArtistActivity.
	 */
	public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
		Intent intent = new Intent(getActivity(), ArtistActivity.class);
		Bundle b = new Bundle();
		b.putString("name", gridAdapter.getItem(position)
										.getTitle());
		intent.putExtras(b);
		startActivity(intent);
	}
}
