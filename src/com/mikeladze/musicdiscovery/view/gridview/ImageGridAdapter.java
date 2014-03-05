
package com.mikeladze.musicdiscovery.view.gridview;

import java.util.LinkedList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageGridAdapter extends BaseAdapter {

    private Context context;
    private LinkedList<String> images;

    public ImageGridAdapter(Context context) {
        this.context = context;
        images = new LinkedList<String>();
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) { // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ImageGridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(com.mikeladze.musicdiscovery.R.drawable.ic_launcher);
        return imageView;
    }

}
