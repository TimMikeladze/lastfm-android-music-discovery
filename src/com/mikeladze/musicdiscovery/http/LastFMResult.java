
package com.mikeladze.musicdiscovery.http;

/**
 * A simple container for data used throughout the app.
 * 
 * @author Tim Mikeladze
 */
public class LastFMResult {
	
	private String image;
	private String title;
	private String mbid;
	private String artist;
	
	/**
	 * Instantiates a new last fm result.
	 * 
	 * @param image the image
	 * @param title the title
	 */
	public LastFMResult(String image, String title) {
		this(image, title, null, null);
	}
	
	/**
	 * Instantiates a new last fm result.
	 * 
	 * @param image the image
	 * @param title the title
	 * @param mbid the mbid
	 */
	public LastFMResult(String image, String title, String mbid) {
		this(image, title, null, mbid);
	}
	
	/**
	 * Instantiates a new last fm result.
	 * 
	 * @param image the image
	 * @param title the title
	 * @param artist the artist
	 * @param mbid the mbid
	 */
	public LastFMResult(String image, String title, String artist, String mbid) {
		this.image = image;
		this.title = title;
		this.mbid = mbid;
		this.artist = artist;
	}
	
	/**
	 * Gets the image.
	 * 
	 * @return the image
	 */
	public String getImage() {
		return image;
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
	 * Gets the mbid.
	 * 
	 * @return the mbid
	 */
	public String getMBID() {
		return mbid;
	}
	
	/**
	 * Gets the artist.
	 * 
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	
	@Override
	public String toString() {
		return "LastFMResult [image=" + image + ", title=" + title + "]";
	}
}
