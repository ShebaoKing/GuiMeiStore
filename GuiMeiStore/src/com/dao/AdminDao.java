package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.entity.Admin;
import com.entity.User;
import com.util.JDBCUtil;
public class AdminDao {
	//添加用户信息
	public boolean add(Admin admin){
		String sql="insert into admin values(null,?,?,?)";
		Object[] obj={admin.getAdmin_name(),admin.getAdmin_user(),admin.getAdmin_pwd()};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		return false;
	}
	//查找用户信息
	public Admin check(String name,String pwd){
		String sql="select * from admin where login_user=? and login_pwd=?";
		Object[] obj={name,pwd};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		Admin admin=null;
		try {
			if(rs.next()){
				admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4));
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return admin;
	}
	//根据ID查找用户信息
	public Admin checkById(String n){
		int id=Integer.parseInt(n);
		String sql="select * from admin where id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		Admin admin=null;
		try {
			if(rs.next()){
				admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return admin;
	}
	//删除用户信息
	public boolean drop(String id){
		String sql="delete from admin where admin_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		else return false;
	}
}