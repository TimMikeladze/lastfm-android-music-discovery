
package com.mikeladze.musicdiscovery.album.adapter;

/**
 * The listener interface for receiving onTrackClick events. The class that is interested in
 * processing a onTrackClick event implements this interface, and the object created with that
 * class is registered with a component using the component's
 * <code>addOnTrackClickListener<code> method. When
 * the onTrackClick event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see OnTrackClickEvent
 */
public interface OnTrackClickListener {
	
	/**
	 * On track click.
	 * 
	 * @param position the position
	 */
	void onTrackClick(int position);
}
