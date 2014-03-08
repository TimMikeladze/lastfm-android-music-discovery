
package com.mikeladze.musicdiscovery.album.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikeladze.musicdiscovery.R;

/**
 * A custom adapter for the track list.
 * 
 * @author Tim Mikeladze
 */
public class TrackListAdapter extends ArrayAdapter<LastFMTrack> {
	
	private Context context;
	private int layoutResourceID;
	private OnTrackClickListener onTrackClickListener;
	
	/**
	 * Instantiates a new track list adapter.
	 * 
	 * @param context the context
	 * @param layoutResourceID the layout resource id
	 * @param tracks the tracks
	 */
	public TrackListAdapter(Context context, int layoutResourceID, ArrayList<LastFMTrack> tracks) {
		super(context, layoutResourceID, tracks);
		this.layoutResourceID = layoutResourceID;
		this.context = context;
	}
	
	/**
	 * Sets the on track click listener.
	 * 
	 * @param onTrackClickListener the new on track click listener
	 */
	public void setOnTrackClickListener(OnTrackClickListener onTrackClickListener) {
		this.onTrackClickListener = onTrackClickListener;
	}
	
	@Override
	/**
	 * Gets the view
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		TrackHolder holder = null;
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceID, parent, false);
			
			holder = new TrackHolder();
			holder.title = (TextView) row.findViewById(R.id.textview_track_title);
			holder.image = (ImageView) row.findViewById(R.id.imageview_youtube);
			holder.image.setOnClickListener(new OnItemClickListener(position));
			
			row.setTag(holder);
		} else {
			holder = (TrackHolder) row.getTag();
		}
		
		LastFMTrack track = getItem(position);
		holder.title.setText(position + 1 + ". " + track.getTitle());
		
		return row;
	}
	
	/**
	 * A holder for list item data.
	 * 
	 * @author Tim Mikeladze
	 */
	private static class TrackHolder {
		
		private TextView title;
		private ImageView image;
		
	}
	
	/**
	 * Provides a callback to the OnTrackClickListener fragment
	 * 
	 * 
	 */
	private class OnItemClickListener implements OnClickListener {
		
		private int position;
		
		/**
		 * Instantiates a new on item click listener.
		 * 
		 * @param position the position
		 */
		public OnItemClickListener(int position) {
			this.position = position;
		}
		
		@Override
		public void onClick(View v) {
			if (onTrackClickListener != null) {
				onTrackClickListener.onTrackClick(position);
			}
		}
	}
}
