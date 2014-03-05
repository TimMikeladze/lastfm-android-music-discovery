
package com.mikeladze.musicdiscovery.topartists.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikeladze.musicdiscovery.fragments.ImageGridViewFragment;
import com.mikeladze.musicdiscovery.http.RestClient;

import fm.last.charts.Chart;

public class TopArtistsFragment extends ImageGridViewFragment {

	private static final String TITLE = "Popular";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
    public void initialize() {
	    loadTopArtists();
	}

	private void loadTopArtists() {
	    Log.d("debug", Chart.<String>getTopArtists(0, 50));
	    RestClient.get(Chart.<String>getTopArtists(0, 50), new JsonHttpResponseHandler() {
	        @Override
	        public void onSuccess(JSONObject response) {
	            Log.d("debug", "loaded");
	            try {
                    JSONObject artists = response.getJSONObject("artists");
                    Log.d("debug", artists.toString());
                }
                catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
	            Toast.makeText(getActivity(), "loaded", Toast.LENGTH_SHORT).show();
	        }
	    });
	}


	@Override
	public String getTitle() {
		return TITLE;
	}
}
