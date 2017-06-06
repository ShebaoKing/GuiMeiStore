package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Admin;
import com.entity.Announcement;
import com.entity.GoodsCar;
import com.entity.GoodsClass;
import com.entity.Order;
import com.entity.User;
import com.util.JDBCUtil;


public class GoodsCarDao {
	List<GoodsCar> list=new ArrayList<GoodsCar>();
	//添加购物车信息
	public boolean add(GoodsCar c){
		GoodsCar car=checkOne(c.getCar_goods_id(),c.getCar_user_id());
		if(car==null){
			String sql="insert into goodscar values(null,?,?,?)";
			Object[] obj={c.getCar_goods_id(),c.getCar_user_id(),c.getCar_no()};
			JDBCUtil ju=new JDBCUtil();
			int i=ju.update(sql, obj);
			if(i>0)return true;
		}else{
			String sql="update goodscar set car_no=? where car_id=?";
			int no=c.getCar_no()+checkNo(car);
			Object[] obj={no,car.getCar_id()};
			JDBCUtil ju=new JDBCUtil();
			int i=ju.update(sql, obj);
			if(i>0)return true;
		}
		return false;
	}
	//查询用户购物车信息
	public List<GoodsCar> check(String user_id){
		int id=Integer.parseInt(user_id);
		String sql="select * from goodscar where car_user_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				GoodsCar car=new GoodsCar(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4));
				list.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查询用户购物车信息
	public GoodsCar checkOne(String goods_id,String user_id){
		GoodsCar car=null;
		int id1=Integer.parseInt(goods_id);
		int id2=Integer.parseInt(user_id);
		String sql="select * from goodscar where car_goods_id=? and car_user_id=?";
		Object[] obj={id1,id2};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				car=new GoodsCar(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return car;
	}
	//修改购物车信息
	public boolean update(String car_id,String num){
		int id=Integer.parseInt(car_id);
		int n=Integer.parseInt(num);
		JDBCUtil ju=new JDBCUtil();
		String sql="update goodscar set car_no=? where car_id=?";
		Object[] obj={n,id};
		int i=ju.update(sql, obj);
		if(i>0) return true;
		return false;
	}
	//计算价格
	public double[] sum(String user_id){
		int id=Integer.parseInt(user_id);
		double[] sum={0.0,0.0};
		String sql="select * from goodscar where car_user_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				GoodsCar car=new GoodsCar(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4));
				sum[0]+=car.getCar_no()*car.getGoods().getGood_sale()*car.getGoods().getSale_dz();
				sum[1]+=car.getCar_no()*car.getGoods().getGood_sale()*(1-car.getGoods().getSale_dz());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
	//查询用户购物车商品数量
	public int checkNo(GoodsCar car){
		int id=0;
		int id1=Integer.parseInt(car.getCar_id());
		String sql="select car_no from goodscar where car_id=?";
		Object[] obj={id1};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
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
	//删除购物车信息
	public boolean drop(String car_id){
		int id=Integer.parseInt(car_id);
		String sql="delete from goodscar where car_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		else return false;
	}
}
