package com.entity;

import com.dao.GoodDao;
import com.dao.UserDao;

public class Order {
	
	UserDao ud=new UserDao();
	GoodDao gd=new GoodDao();
	User user;
	Goods good;
	
	private String order_id;
	private String order_goods_id;
	private String order_goods_name;
	private String order_cate_name;
	private String order_user_id;
	private String order_user_name;
	private String order_no;
	private String order_name;
	private String order_phone;
	private String order_address;
	private String order_status;
	private String order_date;
	private Store store;
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(String order_id, String order_goods_id, String order_user_id,
			String order_no, String order_name,String order_phone, String order_address,
			String order_status, String order_date) {
		super();
		
		user=ud.checkById(order_user_id);
		this.order_user_name=user.getUser_name();
		good=gd.checkById(order_goods_id);
		this.order_cate_name=good.getCate_name();
		this.order_goods_name=good.getGood_name();
		
		this.order_id = order_id;
		this.order_goods_id = order_goods_id;
		this.order_user_id = order_user_id;
		this.order_no = order_no;
		this.order_name = order_name;
		this.order_phone = order_phone;
		this.order_address = order_address;
		this.order_status = order_status;
		this.order_date = order_date;
		this.setStore(gd.checkById(order_goods_id).getStore());
	}
	public Order(String order_goods_id, String order_user_id, String order_no,
			String order_name,String order_phone, String order_address,
			String order_status, String order_date) {
		super();
		this.order_goods_id = order_goods_id;
		this.order_user_id = order_user_id;
		this.order_no = order_no;
		this.order_name = order_name;
		this.order_phone = order_phone;
		this.order_address = order_address;
		this.order_status = order_status;
		this.order_date = order_date;
		this.setStore(gd.checkById(order_goods_id).getStore());
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_goods_id() {
		return order_goods_id;
	}
	public void setOrder_goods_id(String order_goods_id) {
		this.order_goods_id = order_goods_id;
	}
	public String getOrder_user_id() {
		return order_user_id;
	}
	public void setOrder_user_id(String order_user_id) {
		this.order_user_id = order_user_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getOrder_address() {
		return order_address;
	}
	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOrder_user_name() {
		return order_user_name;
	}
	public void setOrder_user_name(String order_user_name) {
		this.setOrder_user_id(ud.checkByName(order_user_name)+"");
		this.order_user_name = order_user_name;
	}
	public String getOrder_cate_name() {
		return order_cate_name;
	}
	public void setOrder_cate_name(String order_cate_name) {
		this.order_cate_name = order_cate_name;
	}
	public String getOrder_phone() {
		return order_phone;
	}
	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public String getOrder_goods_name() {
		return order_goods_name;
	}
	public void setOrder_goods_name(String order_goods_name) {
		this.order_goods_name = order_goods_name;
	}
	
}
