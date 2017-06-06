package com.entity;

import com.dao.UserDao;

public class Store {
	private String store_id;
	private String store_user_id;
	private String store_user_name;
	private String store_user_phone;
	private String store_user_photo;
	private String store_name;
	private String store_photo;
	private String store_card;
	private String store_type;
	public Store() {
		// TODO Auto-generated constructor stub
	}
	public Store(String store_id, String store_user_id, String store_name,
			String store_photo, String store_card, String store_type) {
		super();
		
		this.store_id = store_id;
		this.store_user_id = store_user_id;
		this.store_name = store_name;
		this.store_photo = store_photo;
		this.store_card = store_card;
		this.store_type = store_type;
		this.store_user_name=new UserDao().checkById2(store_user_id);
		this.store_user_phone=new UserDao().checkById3(store_user_id);
		this.store_user_photo=new UserDao().checkById4(store_user_id);
	}
	public Store(String store_user_id, String store_name, String store_photo,
			String store_card, String store_type) {
		super();
		this.store_user_id = store_user_id;
		this.store_name = store_name;
		this.store_photo = store_photo;
		this.store_card = store_card;
		this.store_type = store_type;
		this.store_user_name=new UserDao().checkById2(store_user_id);
		this.store_user_phone=new UserDao().checkById3(store_user_id);
		this.store_user_photo=new UserDao().checkById4(store_user_id);
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getStore_user_id() {
		return store_user_id;
	}
	public void setStore_user_id(String store_user_id) {
		this.store_user_id = store_user_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_photo() {
		return store_photo;
	}
	public void setStore_photo(String store_photo) {
		this.store_photo = store_photo;
	}
	public String getStore_card() {
		return store_card;
	}
	public void setStore_card(String store_card) {
		this.store_card = store_card;
	}
	public String getStore_type() {
		return store_type;
	}
	public void setStore_type(String store_type) {
		this.store_type = store_type;
	}
	public String getStore_user_name() {
		return store_user_name;
	}
	public void setStore_user_name(String store_user_name) {
		this.store_user_name = store_user_name;
	}
	public String getStore_user_phone() {
		return store_user_phone;
	}
	public void setStore_user_phone(String store_user_phone) {
		this.store_user_phone = store_user_phone;
	}
	public String getStore_user_photo() {
		return store_user_photo;
	}
	public void setStore_user_photo(String store_user_photo) {
		this.store_user_photo = store_user_photo;
	}
	
}
