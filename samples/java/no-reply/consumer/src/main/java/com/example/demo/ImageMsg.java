package com.example.demo;

public class ImageMsg {
    private String imageUrl;

	public ImageMsg() {}
	public ImageMsg(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "<ImageMsg: <imageUrl: " + imageUrl + ">>";
	}
}
