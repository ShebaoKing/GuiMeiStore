package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;
import com.util.JDBCUtil;


public class UserDao {
	//添加用户信息
	public boolean add(User user){
		String sql="insert into user values(null,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj={user.getUser_name(),user.getUser_login(),
					user.getUser_pwd(),user.getUser_phone(),user.getUser_email(),
					user.getUser_sex(),user.getUser_photo(),
					user.getUser_hobby(),user.getUser_birthday(),user.getUser_type()};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		return false;
	}
	//查找用户信息
	public User check(String name,String pwd){
		String sql="select * from user where user_login=? and user_pwd=?";
		Object[] obj={name,pwd};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		User user=null;
		try {
			if(rs.next()){
				user=new User(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),
						rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return user;
	}
	//查找所有用户信息
	public List<User> checkAll(int pagenow,int pagesize){
		String sql="select * from user limit ?,?";
		Object[] obj={pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		User user=null;
		List<User> list=new ArrayList<User>();
		try {
			while(rs.next()){
				user=new User(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
						rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
				list.add(user);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查询用户信息数量
	public int checkCount(){
		String sql="select count(*) from user";
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
	//根据ID查找用户信息
	public User checkById(String n){
		int id=Integer.parseInt(n);
		String sql="select * from user where user_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		User user=null;
		try {
			if(rs.next()){
				user=new User(n,rs.getString(2),rs.getString(3),
							rs.getString(4),rs.getString(5),
							rs.getString(6),rs.getString(7),
							rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return user;
	}
	//根据ID查找用户姓名
	public String checkById2(String n){
		int id=Integer.parseInt(n);
		String sql="select user_name from user where user_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return null;
	}
	//根据ID查找用户电话
	public String checkById3(String n){
		int id=Integer.parseInt(n);
		String sql="select user_phone from user where user_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return null;
	}
	//根据ID查找用户头像
	public String checkById4(String n) {
		int id=Integer.parseInt(n);
		String sql="select user_photo from user where user_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return null;
	}
	//根据用户名查找用户信息
	public int checkByName(String name){
		String sql="select user_id from user where user_name=?";
		Object[] obj={name};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		int id=0;
		try {
			if(rs.next()){
				id=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return id;
	}
	//根据登录名查找用户信息
	public boolean checkByLogin(String login){
		String sql="select * from user where user_login=?";
		Object[] obj={login};
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
	//根据邮箱查找用户信息
	public boolean checkByEmail(String email, String user_id){
		int id=Integer.parseInt(user_id);
		String sql="select * from user where user_email=? and user_id!=?";
		Object[] obj={email,id};
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
	//根据手机号码查找用户信息
	public boolean checkByPhone(String phone, String user_id){
		int id=Integer.parseInt(user_id);
		String sql="select * from user where user_phone=? and user_id!=?";
		Object[] obj={phone,id};
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
	//修改用户信息
	public boolean update(User user){
		String sql;
		JDBCUtil ju=new JDBCUtil();
		int i=0;
		sql="update user set user_name=?,user_login=?,user_pwd=?,user_phone=?,user_email=?,user_sex=?,";
		if(user.getUser_photo()!=null){
			sql+="user_photo='"+user.getUser_photo()+"',";
		}
		int id=Integer.parseInt(user.getUser_id());
		sql+="user_hobby=?,user_birthday=? where user_id=?";
		Object[] obj={user.getUser_name(),user.getUser_login(),user.getUser_pwd(),
				user.getUser_phone(),user.getUser_email(),user.getUser_sex(),
				user.getUser_hobby(),user.getUser_birthday(),id};
		i=ju.update(sql,obj);
		if(i>0) return true;
		return false;
	}
	//修改用户密码
	public boolean updatePwd(String user_login,String user_pwd){
		String sql;
		JDBCUtil ju=new JDBCUtil();
		int i=0;
		sql="update user set user_pwd=? where user_login=?";
		Object[] obj={user_pwd,user_login};
		i=ju.update(sql,obj);
		if(i>0) return true;
		return false;
	}
	//修改用户类型
	public boolean updateType(String user_id,String type){
		int id=Integer.parseInt(user_id);
		System.out.println("user_id"+id);
		System.out.println("type"+type);
		System.out.println("id"+id);
		JDBCUtil ju=new JDBCUtil();
		String sql="update user set user_type=? where user_id=?";
		Object[] obj={type,id};
		int i=ju.update(sql,obj);
		if(i>0) return true;
		return false;
	}
	//删除用户信息
	public boolean drop(String user_id){
		int id=Integer.parseInt(user_id);
		String sql="delete from user where user_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		else return false;
	}
	
}
