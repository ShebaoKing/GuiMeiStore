package com.entity;

public class Page{
	private String num;
	private int pagenow;
	private int pagesize;
	private int count;
	private int pagecount;
	private  int pagestart;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		if(num == null){
			num="1";
		}
		this.num = num;
	}
	public int getPagenow() {
		return pagenow;
	}
	public void setPagenow() {
		pagenow=Integer.parseInt(num);
		if(pagenow<=0){
			pagenow=1;
		}
		if(pagenow>pagecount){
			pagenow=pagecount;
		}
		if(pagenow<1){
			pagenow=1;
		}
		
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount() {
		this.pagecount = (count%pagesize==0)?(count/pagesize):(count/pagesize+1);
	}
	public int getPagestart() {
		return pagestart;
	}
	public void setPagestart() {
		this.pagestart = (pagenow-1)*pagesize;
	}
	public Page() {
		super();
	}
	public Page(String num, int pagesize, int count) {
		setNum(num);
		this.pagesize = pagesize;
		this.count = count;
		setPagecount();
		setPagenow();
		setPagestart();
	}
}
