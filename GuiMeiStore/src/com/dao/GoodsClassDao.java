package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.GoodsClass;
import com.util.JDBCUtil;


public class GoodsClassDao {
	GoodsClass good=null;
	List<GoodsClass> list=new ArrayList<GoodsClass>();
	//添加商品大类目
	public boolean add(GoodsClass good){
		String sql="insert into goodsclass values(null,?)";
		Object[] obj={good.getClass_name()};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		return false;
	}
	//查找大类目所有信息
	public List<GoodsClass> checkAll(){
		String sql="select * from goodsclass";
		Object[] obj={};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				good=new GoodsClass(rs.getString(1),rs.getString(2));
				list.add(good);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//类目信息
	public List<GoodsClass> checkInf(int pagenow,int pagesize){
		String sql="select * from goodsclass limit ?,?";
		Object[] obj={pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				good=new GoodsClass(rs.getString(1),rs.getString(2));
				list.add(good);
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
		String sql="select count(*) from goodsclass";
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
	//根据ID查找大类目信息
	public GoodsClass checkById(String class_id){
		int id=Integer.parseInt(class_id);
		String sql="select * from goodsclass where class_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				good=new GoodsClass(rs.getString(1),rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return good;
	}
	//根据大类目名查找大类目信息
	public boolean checkByName(String class_name){
		String sql="select * from goodsclass where class_name=?";
		Object[] obj={class_name};
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
	//删除大类目信息
	public boolean drop(String class_id){
		int id=Integer.parseInt(class_id);
		String sql="delete from goodsclass where class_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		else return false;
	}
	//修改大类目信息
	public boolean update(GoodsClass goods){
		int id=Integer.parseInt(goods.getClass_id());
		String sql="update goodsclass set class_name=? where class_id=?";
		Object[] obj={goods.getClass_name(),id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>-1)return true;
		else return false;
	}
}
