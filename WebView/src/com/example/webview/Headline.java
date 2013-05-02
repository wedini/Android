package com.example.webview;

import java.util.ArrayList;



public class Headline {
	String headline;
	String link;
	ArrayList<String> relatedLinks;
	
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public ArrayList<String> getRelatedLinks() {
		return relatedLinks;
	}
	public void setRelatedLinks(ArrayList<String> relatedLinks) {
		this.relatedLinks = relatedLinks;
	}
	
}
