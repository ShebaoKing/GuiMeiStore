package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Order;
import com.entity.Store;
import com.util.JDBCUtil;


public class StoreDao {
	
	Store st;
	UserDao ud=new UserDao();
	List<Store> list=new ArrayList<Store>();
	
	//添加店铺信息
	public boolean add(Store store){
		String sql="insert into store values(null,?,?,?,?,?)";
		Object[] obj={store.getStore_user_id(),store.getStore_name(),store.getStore_photo(),
				store.getStore_card(),"2"};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0){
			boolean b=ud.updateType(store.getStore_user_id(),"1");
			if(b)return true;
		}
		return false;
	}
	//查找店铺名
	public Store checkByName(String name){
		String sql="select * from store where store_name=?";
		Object[] obj={name};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				st=new Store(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return st;
	}
	//查找店铺名
	public Store checkByUser(String user_id){
		int id=Integer.parseInt(user_id);
		String sql="select * from store where store_user_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				st=new Store(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return st;
	}
	//店铺名身份证查重
	public boolean checkByCard(String card){
		String sql="select * from store where store_card=?";
		Object[] obj={card};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return false;
	}
	//根据ID查找店铺
	public Store checkById(String store_id){
		int id=Integer.parseInt(store_id);
		String sql="select * from store where store_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				st=new Store(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return st;
	}
	//查询所有店铺信息
	public List<Store> checkInf(int pagenow,int pagesize){
		String sql="select * from store order by store_type limit ?,?";
		Object[] obj={pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				st=new Store(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6));
				list.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查询所有通过审核店铺信息
	public List<Store> checkInf2(int pagenow,int pagesize){
		String sql="select * from store where store_type=1 order by store_type limit ?,?";
		Object[] obj={pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				st=new Store(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6));
				list.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查询店铺数量
	public int checkCount(){
		JDBCUtil ju=new JDBCUtil();
		String sql="select count(*) from store";
		Object[] obj={};
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
	//查询通过审核店铺数量
	public int checkCount2(){
		JDBCUtil ju=new JDBCUtil();
		String sql="select count(*) from store where store_type=1";
		Object[] obj={};
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
	//修改店铺审核状态
	public boolean update(String store_id,String store_type){
		int id=Integer.parseInt(store_id);
		String sql="update store set store_type=? where store_id=?";
		Object[] obj={store_type,id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		else return false;
	}
	//删除店铺信息
	public boolean drop(String store_id){
		int id=Integer.parseInt(store_id);
		st=checkById(store_id);
		String user_id=st.getStore_user_id();
		String sql="delete from store where store_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0){
			ud.updateType(user_id,"0");
			return true;
		}
		return false;
	}


}
