package com.entity;

public class Admin {
	private String admin_id;
	private String admin_name;
	private String admin_user;
	private String admin_pwd;
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public Admin(String admin_id, String admin_name, String admin_user,
			String admin_pwd) {
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.admin_user = admin_user;
		this.admin_pwd = admin_pwd;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_user() {
		return admin_user;
	}
	public void setAdmin_user(String admin_user) {
		this.admin_user = admin_user;
	}
	public String getAdmin_pwd() {
		return admin_pwd;
	}
	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}

}
