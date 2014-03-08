
package com.mikeladze.musicdiscovery.base.fragment;

import android.support.v4.app.Fragment;

/**
 * This is base tab fragment used through out the app. It is required to guarantee that all
 * fragments provide a "Title" to be used for tabs.
 * 
 * @author Tim Mikeladze
 */
public abstract class BaseTabFragment extends Fragment {
	
	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public abstract String getTitle();
	
}
