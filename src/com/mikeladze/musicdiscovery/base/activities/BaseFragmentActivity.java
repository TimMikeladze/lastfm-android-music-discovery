
package com.mikeladze.musicdiscovery.base.activities;

import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.mikeladze.musicdiscovery.R;

public abstract class BaseFragmentActivity extends FragmentActivity {
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
