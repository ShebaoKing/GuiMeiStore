package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Category;
import com.entity.GoodsClass;
import com.util.JDBCUtil;


public class CategoryDao {
	Category cate=null;
	List<Category> list=new ArrayList<Category>();
	//添加商品小类目
	public boolean add(Category cate){
		int class_id=Integer.parseInt(cate.getClass_id());
		String sql="insert into category values(null,?,?)";
		Object[] obj={cate.getCate_name(),class_id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		return false;
	}
	//查找大类目中的小类目
	public List<Category> check(String class_id){
		List<Category> list2=new ArrayList<Category>();
		int id=Integer.parseInt(class_id);
		String sql="select * from category where class_id=? limit 0,15";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				cate=new Category(rs.getString(1),rs.getString(2),rs.getString(3));
				list2.add(cate);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list2;
	}
	//查找小类目所有信息
	public List<Category> checkAll(){
		String sql="select * from category";
		Object[] obj={};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				cate=new Category(rs.getString(1),rs.getString(2),rs.getString(3));
				list.add(cate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//类目信息
	public List<Category> checkInf(int pagenow,int pagesize){
		String sql="select * from category order by class_id limit ?,?";
		Object[] obj={pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				String class_name=new GoodsClassDao().checkById(rs.getString(3)).getClass_name();
				cate=new Category(rs.getString(1),rs.getString(2),rs.getString(3),class_name);
				list.add(cate);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查询类目信息数量
	public int checkCount(){
		String sql="select count(*) from category";
		Object[] obj={};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		int count=0;
		try {
			if(rs.next()){
				count=rs.getInt(1);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return count;
	}
	//根据ID查找类目信息
	public Category checkById(String cate_id){
		int id=Integer.parseInt(cate_id);
		String sql="select * from category where cate_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				cate=new Category(rs.getString(1),rs.getString(2),rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return cate;
	}
	//根据小类目名查找小类目信息
	public boolean checkByName(String cate_name){
		String sql="select * from category where cate_name=?";
		Object[] obj={cate_name};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return true;
	}
	//删除小类目信息
	public boolean drop(String cate_id){
		int id=Integer.parseInt(cate_id);
		String sql="delete from category where cate_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>-1)return true;
		else return false;
	}
	//修改大类目信息
	public boolean update(Category cate){
		int cate_id=Integer.parseInt(cate.getCate_id());
		int class_id=Integer.parseInt(cate.getClass_id());
		String sql="update category set cate_name=?,class_id=? where cate_id=?";
		Object[] obj={cate.getCate_name(),class_id,cate_id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		else return false;
	}
}
