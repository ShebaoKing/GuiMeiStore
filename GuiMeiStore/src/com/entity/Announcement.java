package com.entity;

public class Announcement {
	private String an_id;
	private String an_title;
	private String an_text;
	private String an_image;
	private String an_status;
	public Announcement() {
		// TODO Auto-generated constructor stub
	}
	public Announcement(String an_id, String an_title, String an_text,
			String an_image, String an_status) {
		super();
		this.an_id = an_id;
		this.an_title = an_title;
		this.an_text = an_text;
		this.an_image = an_image;
		this.an_status = an_status;
	}
	public Announcement(String an_title, String an_text, String an_image,String an_status) {
		super();
		this.an_title = an_title;
		this.an_text = an_text;
		this.an_image = an_image;
		this.an_status = an_status;
	}
	public String getAn_id() {
		return an_id;
	}
	public void setAn_id(String an_id) {
		this.an_id = an_id;
	}
	public String getAn_title() {
		return an_title;
	}
	public void setAn_title(String an_title) {
		this.an_title = an_title;
	}
	public String getAn_text() {
		return an_text;
	}
	public void setAn_text(String an_text) {
		this.an_text = an_text;
	}
	public String getAn_image() {
		return an_image;
	}
	public void setAn_image(String an_image) {
		this.an_image = an_image;
	}
	public String getAn_status() {
		return an_status;
	}
	public void setAn_status(String an_status) {
		this.an_status = an_status;
	}
}
