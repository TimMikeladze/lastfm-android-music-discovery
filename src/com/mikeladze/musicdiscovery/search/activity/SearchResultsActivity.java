
package com.mikeladze.musicdiscovery.search.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.album.activity.AlbumActivity;
import com.mikeladze.musicdiscovery.artist.activity.ArtistActivity;
import com.mikeladze.musicdiscovery.base.activity.BaseFragmentActivity;
import com.mikeladze.musicdiscovery.http.LastFMResult;
import com.mikeladze.musicdiscovery.http.RestClient;
import com.mikeladze.musicdiscovery.search.adapter.SearchResultsAdapter;

import fm.last.album.Album;
import fm.last.artist.Artist;

/**
 * This activity displays search results
 * 
 * @author Tim Mikeladze
 */
public class SearchResultsActivity extends BaseFragmentActivity implements OnChildClickListener {
	
	private static final String ACTION_BAR_TITLE = "Search Results";
	private static final int NUMBER_OF_SEARCH_RESULTS = 10;
	private SearchResultsAdapter listAdapter;
	private ExpandableListView listView;
	
	private ArrayList<String> headers;
	private HashMap<String, List<LastFMResult>> items;
	private String query;
	
	@Override
	/**
	 * Creates the activity
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		actionBar.setTitle(ACTION_BAR_TITLE);
		
		if (savedInstanceState != null) {
			query = savedInstanceState.getString("query");
		}
		
		handleIntent(getIntent());
		
		listView = (ExpandableListView) findViewById(R.id.view_search_results);
		listView.setOnChildClickListener(this);
	}
	
	@Override
	/**
	 * Stores the received search query for device orientation changed purposes
	 */
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putString("query", query);
	}
	
	/**
	 * Loads and displays the search results for artists and albums.
	 */
	private void loadSearchResults() {
		headers = new ArrayList<String>();
		items = new HashMap<String, List<LastFMResult>>();
		
		RestClient.get(Artist.<String> search(query, 0, NUMBER_OF_SEARCH_RESULTS), new JsonHttpResponseHandler() {
			
			@Override
			public void onStart() {
				super.onStart();
				setProgressBarIndeterminateVisibility(true);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				
				setProgressBarIndeterminateVisibility(false);
				try {
					JSONArray artists = response.getJSONObject("results")
												.getJSONObject("artistmatches")
												.getJSONArray("artist");
					ArrayList<LastFMResult> artistResults = new ArrayList<LastFMResult>();
					for (int i = 0; i < artists.length(); i++) {
						String image = artists.getJSONObject(i)
												.getJSONArray("image")
												.getJSONObject(3)
												.getString("#text");
						String title = artists.getJSONObject(i)
												.getString("name");
						String mbid = artists.getJSONObject(i)
												.getString("mbid");
						artistResults.add(new LastFMResult(image, title, mbid));
					}
					
					headers.add("Artists");
					items.put(headers.get(0), artistResults);
					
					RestClient.get(Album.<String> search(query, 0, 10), new JsonHttpResponseHandler() {
						
						@Override
						public void onSuccess(JSONObject response) {
							try {
								JSONArray albums = response.getJSONObject("results")
															.getJSONObject("albummatches")
															.getJSONArray("album");
								ArrayList<LastFMResult> albumResults = new ArrayList<LastFMResult>();
								for (int i = 0; i < albums.length(); i++) {
									String image = albums.getJSONObject(i)
															.getJSONArray("image")
															.getJSONObject(3)
															.getString("#text");
									String title = albums.getJSONObject(i)
															.getString("name") + " - " + albums.getJSONObject(i)
																								.getString("artist");
									String mbid = albums.getJSONObject(i)
														.getString("mbid");
									
									String artist = albums.getJSONObject(i)
															.getString("artist");
									
									albumResults.add(new LastFMResult(image, title, artist, mbid));
								}
								
								headers.add("Albums");
								items.put(headers.get(1), albumResults);
								
								listAdapter = new SearchResultsAdapter(SearchResultsActivity.this, headers, items);
								listView.setAdapter(listAdapter);
								
								listView.expandGroup(0);
								
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					});
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	@Override
	/**
	 * Receives an intent 
	 */
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}
	
	/**
	 * Handles receiving the search intent and intiaites a search
	 * 
	 * @param intent the intent
	 */
	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			query = intent.getStringExtra(SearchManager.QUERY);
			loadSearchResults();
		}
		
	}
	
	@Override
	/**
	 * Handles click events to children in the search results list, starting appropriate activity based on item.
	 */
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		Bundle b = new Bundle();
		Class<?> c = null;
		switch (groupPosition) {
			case 0:
				c = ArtistActivity.class;
				break;
			case 1:
				c = AlbumActivity.class;
				b.putString("artist", items.get(headers.get(groupPosition))
											.get(childPosition)
											.getArtist());
				break;
			default:
				break;
		}
		
		Intent intent = new Intent(this, c);
		b.putString("name", items.get(headers.get(groupPosition))
									.get(childPosition)
									.getTitle()
									.split("-")[0].trim());
		b.putString("mbid", items.get(headers.get(groupPosition))
									.get(childPosition)
									.getMBID());
		intent.putExtras(b);
		startActivity(intent);
		return false;
	}
}
