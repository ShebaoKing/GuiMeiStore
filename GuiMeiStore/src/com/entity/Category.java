package com.entity;

import com.dao.GoodsClassDao;

public class Category {
	GoodsClassDao gc=new GoodsClassDao();
	
	private String cate_id;
	private String cate_name;
	private String class_id;
	private String class_name;
	public Category() {
		// TODO Auto-generated constructor stub
	}
	public Category(String cate_id, String cate_name, String class_id) {
		super();
		this.class_name=gc.checkById(class_id).getClass_name();
		
		this.cate_id = cate_id;
		this.cate_name = cate_name;
		this.class_id = class_id;
	}
	public Category(String cate_name, String class_id) {
		super();
		this.class_name=gc.checkById(class_id).getClass_name();
		
		this.cate_name = cate_name;
		this.class_id = class_id;
	}
	public Category(String cate_id, String cate_name, String class_id,
			String class_name) {
		super();
		this.class_name=gc.checkById(class_id).getClass_name();
		
		this.cate_id = cate_id;
		this.cate_name = cate_name;
		this.class_id = class_id;
		this.class_name = class_name;
	}
	public String getCate_id() {
		return cate_id;
	}
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

}
