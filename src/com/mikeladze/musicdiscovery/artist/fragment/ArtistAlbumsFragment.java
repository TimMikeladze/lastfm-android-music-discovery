
package com.mikeladze.musicdiscovery.artist.fragment;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikeladze.musicdiscovery.album.activity.AlbumActivity;
import com.mikeladze.musicdiscovery.base.fragment.ImageGridViewFragment;
import com.mikeladze.musicdiscovery.http.LastFMResult;
import com.origamilabs.library.views.StaggeredGridView;

public class ArtistAlbumsFragment extends ImageGridViewFragment {
	
	private static final String TITLE = "Albums";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
	
	public void displayAlbums(JSONArray albums) {
		try {
			for (int i = 0; i < albums.length(); i++) {
				String image;
				image = albums.getJSONObject(i)
								.getJSONArray("image")
								.getJSONObject(3)
								.getString("#text");
				
				String title = albums.getJSONObject(i)
										.getString("name");
				String mbid = albums.getJSONObject(i)
									.getString("mbid");
				String artist = albums.getJSONObject(i)
										.getJSONObject("artist")
										.getString("name");
				gridAdapter.add(new LastFMResult(image, title, artist, mbid));
			}
			gridAdapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
		Intent intent = new Intent(getActivity(), AlbumActivity.class);
		Bundle b = new Bundle();
		b.putString("mbid", gridAdapter.getItem(position)
										.getMBID());
		b.putString("name", gridAdapter.getItem(position)
										.getTitle());
		b.putString("artist", gridAdapter.getItem(position)
											.getArtist());
		intent.putExtras(b);
		startActivity(intent);
	}
}