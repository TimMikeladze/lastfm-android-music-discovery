
package com.mikeladze.musicdiscovery.album.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.album.adapter.LastFMTrack;
import com.mikeladze.musicdiscovery.album.adapter.OnTrackClickListener;
import com.mikeladze.musicdiscovery.album.adapter.TrackListAdapter;
import com.mikeladze.musicdiscovery.base.fragment.BaseTabFragment;
import com.mikeladze.musicdiscovery.util.IntentUtils;

/**
 * This fragment displays the tracks on the album in a listview
 * 
 * @author Tim Mikeladze
 */
public class AlbumTracksFragment extends BaseTabFragment implements OnTrackClickListener {
	
	private static final String YOUTUBE_SEARCH_URL = "https://www.youtube.com/results?search_query=";
	private static final String TITLE = "Tracks";
	private ListView listView;
	private TrackListAdapter adapter;
	private ArrayList<LastFMTrack> tracksList;
	
	@Override
	/**
	 * Create the view
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_generic_list, container, false);
		
		listView = (ListView) v.findViewById(R.id.view_list);
		tracksList = new ArrayList<LastFMTrack>();
		adapter = new TrackListAdapter(getActivity(), R.layout.view_track_list_item, tracksList);
		adapter.setOnTrackClickListener(this);
		
		return v;
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
	
	/**
	 * Display album tracks.
	 * 
	 * @param tracks the tracks
	 */
	public void displayAlbumTracks(String artist, JSONArray tracks) {
		try {
			for (int i = 0; i < tracks.length(); i++) {
				
				tracksList.add(new LastFMTrack(tracks.getJSONObject(i)
														.getString("name"), artist));
			}
			listView.setAdapter(adapter);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * Handles track clicking. Starts new intent in youtube or falls back to browser if youtube is not available to play the track.
	 */
	public void onTrackClick(int position) {
		String query = adapter.getItem(position)
								.getTitle() + " " + adapter.getItem(position)
															.getArtist();
		
		Intent intent = new Intent(Intent.ACTION_SEARCH);
		intent.setPackage("com.google.android.youtube");
		
		if (IntentUtils.isAvailable(getActivity(), intent)) {
			intent.putExtra("query", query);
		} else {
			intent = new Intent("android.intent.action.VIEW", Uri.parse(YOUTUBE_SEARCH_URL + query));
		}
		
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}