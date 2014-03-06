
package com.mikeladze.musicdiscovery.view.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.http.LastFMResult;
import com.mikeladze.musicdiscovery.view.gridview.loader.ImageLoader;

public class ImageGridAdapter extends ArrayAdapter<LastFMResult> {
	
	private Context context;
	
	private ImageLoader imageLoader;
	
	public ImageGridAdapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		imageLoader = new ImageLoader(context);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if (convertView == null) {
			LayoutInflater layoutInflator = LayoutInflater.from(context);
			convertView = layoutInflator.inflate(R.layout.view_image_grid_item, null);
			holder = new ViewHolder();
			holder.image = (DynamicImageView) convertView.findViewById(R.id.imageview);
			holder.title = (TextView) convertView.findViewById(R.id.textview_title);
			
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();
		
		if (getCount() > 0) {
			LastFMResult gi = getItem(position);
			imageLoader.displayImage(gi.getImage(), holder.image);
			holder.title.setText(gi.getTitle());
			
		}
		
		return convertView;
	}
	
	static class ViewHolder {
		
		private DynamicImageView image;
		private TextView title;
	}
}
