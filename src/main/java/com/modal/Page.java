package com.modal;

public class Page {
	String title, filename,icon;

	public Page() {
		super();
	}

	public Page(String title, String filename,String  icon) {
		super();
		this.title = title;
		this.filename = filename;
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
