
package com.mikeladze.musicdiscovery.artist.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.base.fragment.BaseTabFragment;
import com.mikeladze.musicdiscovery.view.gridview.loader.ImageLoader;

/**
 * This fragment displays the artist image and information.
 * 
 * @author Tim Mikeladze
 */
public class ArtistInfoFragment extends BaseTabFragment {
	
	private static final String TITLE = "Info";
	private View v;
	private ImageView imageView;
	private TextView textViewInfo;
	
	@Override
	/**
	 * Creates the view
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_generic_info, container, false);
		
		imageView = (ImageView) v.findViewById(R.id.image);
		textViewInfo = (TextView) v.findViewById(R.id.textview_info);
		
		return v;
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
	
	/**
	 * Display artist info.
	 * 
	 * @param artist the artist
	 */
	public void displayArtistInfo(JSONObject artist) {
		String s = "No information";
		try {
			
			ImageLoader imageLoader = new ImageLoader(getActivity());
			imageLoader.displayImage(artist.getJSONArray("image")
											.getJSONObject(4)
											.getString("#text"), imageView);
			
			s = artist.getJSONObject("bio")
						.getString("summary")
						.trim();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		textViewInfo.setText(Html.fromHtml(s));
		Linkify.addLinks(textViewInfo, Linkify.ALL);
	}
}
