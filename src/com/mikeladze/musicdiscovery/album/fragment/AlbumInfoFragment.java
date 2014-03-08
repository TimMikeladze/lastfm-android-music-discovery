
package com.mikeladze.musicdiscovery.album.fragment;

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
 * This fragment displays the album image and information.
 * 
 * @author Tim Mikeladze
 */
public class AlbumInfoFragment extends BaseTabFragment {
	
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
	 * Display album info.
	 * 
	 * @param album the album
	 */
	public void displayAlbumInfo(JSONObject album) {
		String s = "No information";
		try {
			
			ImageLoader imageLoader = new ImageLoader(getActivity());
			imageLoader.displayImage(album.getJSONArray("image")
											.getJSONObject(4)
											.getString("#text"), imageView);
			
			s = album.getJSONObject("wiki")
						.getString("summary")
						.trim()
						.length() > album.getJSONObject("wiki")
											.getString("content")
											.trim()
											.length() ? album.getJSONObject("wiki")
																.getString("summary")
																.trim() : album.getJSONObject("wiki")
																				.getString("content")
																				.trim();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		textViewInfo.setText(Html.fromHtml(s));
		Linkify.addLinks(textViewInfo, Linkify.ALL);
	}
}