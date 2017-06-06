package com.entity;

import com.dao.GoodDao;

public class GoodsCar {
	GoodDao gd=new GoodDao();
	
	private String car_id;
	private String car_goods_id;
	private Goods goods;
	private String car_user_id;
	private int car_no;
	public GoodsCar() {
		// TODO Auto-generated constructor stub
	}
	public GoodsCar(String car_id, String car_goods_id, String car_user_id,String car_no) {
		super();
		
		this.goods=gd.checkById(car_goods_id);
		
		this.car_id = car_id;
		this.car_goods_id = car_goods_id;
		this.car_user_id = car_user_id;
		this.car_no = Integer.parseInt(car_no);
	}
	public GoodsCar(String car_goods_id, String car_user_id, String car_no) {
		super();
		this.car_goods_id = car_goods_id;
		this.car_user_id = car_user_id;
		this.car_no = Integer.parseInt(car_no);
	}
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public String getCar_goods_id() {
		return car_goods_id;
	}
	public void setCar_goods_id(String car_goods_id) {
		this.car_goods_id = car_goods_id;
	}
	public String getCar_user_id() {
		return car_user_id;
	}
	public void setCar_user_id(String car_user_id) {
		this.car_user_id = car_user_id;
	}
	public int getCar_no() {
		return car_no;
	}
	public void setCar_no(String car_no) {
		this.car_no = Integer.parseInt(car_no);
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
}
