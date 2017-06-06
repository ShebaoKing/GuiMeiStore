package com.entity;

import com.dao.StoreDao;

public class User {
	private String user_id;
	private String user_name;
	private String user_login;
	private String user_pwd;
	private String user_phone;
	private String user_email;
	private String user_sex;
	private String user_photo;
	private String user_hobby;
	private String user_birthday;
	private String user_birthday1;
	private String user_birthday2;
	private String user_birthday3;
	private String user_type;
	//private String user_type1;
	private Store store;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String user_id, String user_name, String user_login,
			String user_pwd,String user_phone,String user_email, String user_sex,
			String user_photo, String user_hobby, String user_birthday,String user_type) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_login = user_login;
		this.user_pwd = user_pwd;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_sex = user_sex;
		this.user_photo = user_photo;
		this.user_hobby = user_hobby;
		this.user_birthday = user_birthday;
		this.setUser_birthday1(user_birthday.substring(0,4));
		this.setUser_birthday2(user_birthday.substring(5,7));
		this.setUser_birthday3(user_birthday.substring(8,10));
		this.user_type=user_type;
		if(user_type.equals("1")){
			this.setStore(new StoreDao().checkByUser(user_id));
		}
		
	}
	public User(String user_name, String user_login, String user_pwd,
			String user_phone, String user_email, String user_sex, String user_photo,
			String user_hobby, String user_birthday,String user_type) {
		super();
		this.user_name = user_name;
		this.user_login = user_login;
		this.user_pwd = user_pwd;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_sex = user_sex;
		this.user_photo = user_photo;
		this.user_hobby = user_hobby;
		this.user_birthday = user_birthday;
		this.user_type=user_type;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_login() {
		return user_login;
	}
	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_photo() {
		return user_photo;
	}
	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}
	public String getUser_hobby() {
		return user_hobby;
	}
	public void setUser_hobby(String user_hobby) {
		this.user_hobby = user_hobby;
	}
	public String getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_birthday1() {
		return user_birthday1;
	}
	public void setUser_birthday1(String user_birthday1) {
		this.user_birthday1 = user_birthday1;
	}
	public String getUser_birthday2() {
		return user_birthday2;
	}
	public void setUser_birthday2(String user_birthday2) {
		this.user_birthday2 = user_birthday2;
	}
	public String getUser_birthday3() {
		return user_birthday3;
	}
	public void setUser_birthday3(String user_birthday3) {
		this.user_birthday3 = user_birthday3;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
	
}
