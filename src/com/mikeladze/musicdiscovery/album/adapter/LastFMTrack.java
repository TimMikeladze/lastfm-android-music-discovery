
package com.mikeladze.musicdiscovery.album.adapter;

/**
 * A simple class used to hold data about a track.
 * 
 * @author Tim Mikeladze
 */
public class LastFMTrack {
	
	private String title;
	private String artist;
	
	/**
	 * Instantiates a new last fm track.
	 * 
	 * @param title the title
	 * @param artist the artist
	 */
	public LastFMTrack(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}
	
	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets the artist.
	 * 
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
}
