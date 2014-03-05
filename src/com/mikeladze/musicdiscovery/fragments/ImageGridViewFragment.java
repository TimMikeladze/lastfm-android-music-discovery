package com.mikeladze.musicdiscovery.fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikeladze.musicdiscovery.R;
import com.mikeladze.musicdiscovery.view.gridview.ImageGridAdapter;
import com.mikeladze.musicdiscovery.view.gridview.ImageGridView;

public abstract class ImageGridViewFragment extends BaseTabFragment {

    protected ImageGridView gridView;
    protected ImageGridAdapter gridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_grid, container, false);

        gridAdapter = new ImageGridAdapter(getActivity());

        gridView = (ImageGridView) v.findViewById(R.id.gridview);
        gridView.setAdapter(gridAdapter);

        initialize();

        return v;
    }

    public abstract void initialize();
}
