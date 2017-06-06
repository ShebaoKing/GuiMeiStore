package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Announcement;
import com.entity.Goods;
import com.entity.User;
import com.util.JDBCUtil;


public class AnnouncementDao {
	List<Announcement> list=new ArrayList<Announcement>();
	Announcement an=null;
	//添加公告信息
	public boolean add(Announcement a){
		String sql="insert into announcement values(null,?,?,?,?)";
		Object[] obj={a.getAn_title(),a.getAn_text(),a.getAn_image(),a.getAn_status()};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		return false;
	}
	//查找所有公告信息
	public List<Announcement> checkAll(int pagenow,int pagesize){
		String sql="select * from announcement limit ?,?";
		Object[] obj={pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				an=new Announcement(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5));
				list.add(an);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查找显示公告信息
	public List<Announcement> checkInf(){
		String sql="select * from announcement where an_status='1' limit 0,4";
		Object[] obj={};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				an=new Announcement(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5));
				list.add(an);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查询公告信息数量
	public int checkCount(){
		String sql="select count(*) from announcement";
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
	//根据ID查找公告信息
	public Announcement checkById(String n){
		int id=Integer.parseInt(n);
		String sql="select * from announcement where an_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				an=new Announcement(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return an;
	}
	//修改公告信息
	public boolean update(Announcement an){
		String sql;
		JDBCUtil ju=new JDBCUtil();
		int i=0;
		if(an.getAn_image()==null){
			sql="update announcement set an_title=?,an_text=?,an_status=? where an_id=?";
			Object[] obj={an.getAn_title(),an.getAn_text(),an.getAn_status(),an.getAn_id()};
			i=ju.update(sql, obj);
		}else{
			sql="update announcement set an_title=?,an_text=?,an_image=?,an_status=? where an_id=?";
			Object[] obj={an.getAn_title(),an.getAn_text(),an.getAn_image(),an.getAn_status(),an.getAn_id()};
			i=ju.update(sql, obj);
		}
		if(i>0) return true;
		return false;
	}
	//删除公告信息
	public boolean drop(String aid){
		int id=Integer.parseInt(aid);
		String sql="delete from announcement where an_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		else return false;
	}
}
