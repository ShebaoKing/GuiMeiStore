package com.entity;

import java.util.List;

public class GoodsClass {
	private String class_id;
	private String class_name;
	private List<Category> cate;
	public GoodsClass() {
		// TODO Auto-generated constructor stub
	}
	public GoodsClass(String class_id, String class_name) {
		super();
		this.class_id = class_id;
		this.class_name = class_name;
	}
	public GoodsClass(String class_id, String class_name, List<Category> cate) {
		super();
		this.class_id = class_id;
		this.class_name = class_name;
		this.cate = cate;
	}
	public GoodsClass(String class_name) {
		super();
		this.class_name = class_name;
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
	public List<Category> getCate() {
		return cate;
	}
	public void setCate(List<Category> cate) {
		this.cate = cate;
	}
}
