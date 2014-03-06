
package com.mikeladze.musicdiscovery.view.gridview;

public class GridItem {
	
	private String image;
	private String title;
	
	public GridItem(String image, String title) {
		this.image = image;
		this.title = title;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getTitle() {
		return title;
	}
}
