
package com.mikeladze.musicdiscovery.http;

public class LastFMResult {
	
	private String image;
	private String title;
	
	public LastFMResult(String image, String title) {
		this.image = image;
		this.title = title;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		return "LastFMResult [image=" + image + ", title=" + title + "]";
	}
}
