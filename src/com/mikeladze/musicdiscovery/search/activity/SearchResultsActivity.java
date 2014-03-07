
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
import android.widget.ExpandableListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.base.activities.BaseFragmentActivity;
import com.mikeladze.musicdiscovery.http.LastFMResult;
import com.mikeladze.musicdiscovery.http.RestClient;

import fm.last.album.Album;
import fm.last.artist.Artist;

public class SearchResultsActivity extends BaseFragmentActivity {
	
	private static final int NUMBER_OF_SEARCH_RESULTS = 10;
	private SearchResultsAdapter listAdapter;
	private ExpandableListView listView;
	
	private ArrayList<String> headers;
	private HashMap<String, List<LastFMResult>> items;
	private String query;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		if (savedInstanceState != null) {
			query = savedInstanceState.getString("query");
		}
		
		handleIntent(getIntent());
		
		listView = (ExpandableListView) findViewById(R.id.view_search_results);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putString("query", query);
	}
	
	private void loadSearchResults() {
		headers = new ArrayList<String>();
		items = new HashMap<String, List<LastFMResult>>();
		
		RestClient.get(Artist.<String> search(query, 0, NUMBER_OF_SEARCH_RESULTS), new JsonHttpResponseHandler() {
			
			@Override
			public void onSuccess(JSONObject response) {
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
						artistResults.add(new LastFMResult(image, title));
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
									albumResults.add(new LastFMResult(image, title));
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
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}
	
	private void handleIntent(Intent intent) {
		
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			query = intent.getStringExtra(SearchManager.QUERY);
			loadSearchResults();
		}
		
	}
}
