
package com.mikeladze.musicdiscovery.view.gridview;

import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.view.gridview.loader.ImageLoader;

public class ImageGridAdapter extends BaseAdapter {
	
	private Context context;
	private LinkedList<String> images;
	
	private ImageLoader imageLoader;
	
	public ImageGridAdapter(Context context) {
		this.context = context;
		images = new LinkedList<String>();
		imageLoader = new ImageLoader(context);
		
	}
	
	public void addImage(String url) {
		images.add(url);
	}
	
	@Override
	public int getCount() {
		return images.size();
	}
	
	@Override
	public Object getItem(int position) {
		return position;
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if (convertView == null) {
			LayoutInflater layoutInflator = LayoutInflater.from(context);
			convertView = layoutInflator.inflate(R.layout.view_image_grid_item, null);
			holder = new ViewHolder();
			holder.imageView = (DynamicImageView) convertView.findViewById(R.id.imageview);
			convertView.setTag(holder);
		}
		
		holder = (ViewHolder) convertView.getTag();
		
		if (!images.isEmpty()) {
			imageLoader.displayImage(images.get(position), holder.imageView);
			
		}
		
		return convertView;
	}
	
	static class ViewHolder {
		
		DynamicImageView imageView;
	}
}
