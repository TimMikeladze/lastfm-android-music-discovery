
package com.mikeladze.musicdiscovery.http;

public class LastFMResult {
	
	private String image;
	private String title;
	private String mbid;
	private String artist;
	
	public LastFMResult(String image, String title) {
		this(image, title, null, null);
	}
	
	public LastFMResult(String image, String title, String mbid) {
		this(image, title, null, mbid);
	}
	
	public LastFMResult(String image, String title, String artist, String mbid) {
		this.image = image;
		this.title = title;
		this.mbid = mbid;
		this.artist = artist;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getMBID() {
		return mbid;
	}
	
	public String getArtist() {
		return artist;
	}
	
	@Override
	public String toString() {
		return "LastFMResult [image=" + image + ", title=" + title + "]";
	}
}
