package com.entity;

import com.dao.CategoryDao;
import com.dao.StoreDao;

public class Goods {
	CategoryDao cd=new CategoryDao();
	
	private String good_id;
	private String good_name;
	private String cate_id;
	private String cate_name;
	private String class_id;
	private String class_name;
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private int good_count;
	private int sale_count;
	private double good_sale;
	private double sale_dz;
	private String describe;
	private String good_type;
	private String store_id;
	private Store store;
	
	public Goods() {
		// TODO Auto-generated constructor stub
	}
	public Goods(String good_id, String good_name, String cate_id, String pic1,
			String pic2, String pic3, String pic4, String good_count,String sale_count,
			String good_sale, String sale_dz, String describe,String good_type,String store_id) {
		super();
		
		this.cate_name=cd.checkById(cate_id).getCate_name();
		this.setClass_id(cd.checkById(cate_id).getClass_id());
		this.setClass_name(cd.checkById(cate_id).getClass_name());
		
		this.good_id = good_id;
		this.good_name = good_name;
		this.cate_id = cate_id;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.good_count = Integer.parseInt(good_count);
		this.sale_count = Integer.parseInt(sale_count);
		this.good_sale = Double.parseDouble(good_sale);
		this.sale_dz = Double.parseDouble(sale_dz);
		this.describe = describe;
		this.good_type = good_type;
		this.store_id=store_id;
		this.store=new StoreDao().checkById(store_id);
	}
	public Goods(String good_name, String cate_id, String pic1, String pic2,
			String pic3, String pic4, String good_count, String sale_count,
			String good_sale, String sale_dz, String describe,String good_type,String store_id) {
		super();
		
		this.cate_name=cd.checkById(cate_id).getCate_name();
		this.setClass_id(cd.checkById(cate_id).getClass_id());
		this.setClass_name(cd.checkById(cate_id).getClass_name());
		
		this.good_name = good_name;
		this.cate_id = cate_id;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.good_count = Integer.parseInt(good_count);
		this.sale_count = Integer.parseInt(sale_count);
		this.good_sale = Double.parseDouble(good_sale);
		this.sale_dz = Double.parseDouble(sale_dz);
		this.describe = describe;
		this.good_type = good_type;
		this.store_id=store_id;
		this.store=new StoreDao().checkById(store_id);
	}
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public String getCate_id() {
		return cate_id;
	}
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public String getPic3() {
		return pic3;
	}
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	public String getPic4() {
		return pic4;
	}
	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}
	public int getGood_count() {
		return good_count;
	}
	public void setGood_count(String good_count) {
		this.good_count = Integer.parseInt(good_count);
	}
	public int getSale_count() {
		return sale_count;
	}
	public void setSale_count(String sale_count) {
		this.sale_count = Integer.parseInt(sale_count);
	}
	public double getGood_sale() {
		return good_sale;
	}
	public void setGood_sale(String good_sale) {
		this.good_sale = Double.parseDouble(good_sale);
	}
	public double getSale_dz() {
		return sale_dz;
	}
	public void setSale_dz(String sale_dz) {
		this.sale_dz = Double.parseDouble(sale_dz);
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
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
	public String getGood_type() {
		return good_type;
	}
	public void setGood_type(String good_type) {
		this.good_type = good_type;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
}
