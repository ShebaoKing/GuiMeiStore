package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Admin;
import com.entity.GoodsClass;
import com.entity.Order;
import com.entity.User;
import com.util.JDBCUtil;


public class OrderDao {
	Order order;
	List<Order> list=new ArrayList<Order>();
	//添加订单
	public boolean add(Order o){
		String sql="insert into orders values(null,?,?,?,?,?,?,1,?)";
		int goods_id=Integer.parseInt(o.getOrder_goods_id());
		int user_id=Integer.parseInt(o.getOrder_user_id());
		Object[] obj={goods_id,user_id,o.getOrder_no(),o.getOrder_name(),
				o.getOrder_phone(),o.getOrder_address(),o.getOrder_date()};
		JDBCUtil ju=new JDBCUtil(); 
		int i=ju.update(sql, obj);
		if(i>0)return true;
		return false;
	}
	//管理员根据用户或订单编号查找订单信息
	public List<Order> check(Order order,int pagenow,int pagesize){
		int id1=-1,id2=-1;
		String sql;
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=null;
		String order_user_id=order.getOrder_user_id();
		String order_id=order.getOrder_id();
		if(order_user_id!=null){
			id1=Integer.parseInt(order_user_id);			
		}
		if(order_id!=null){
			id2=Integer.parseInt(order_id);
		}
		if(id1>-1&&id2>-1){
			sql="select * from orders where order_user_id=? and order_id=?";
			Object[] obj={id1,id2};
			rs=ju.select(sql,obj);
		}else if(id1>-1){
			sql="select * from orders where order_user_id=? order by order_date desc";
			Object[] obj={id1};
			rs=ju.select(sql, obj);
		}else if(id2>-1){
			sql="select * from orders where order_id=?";
			Object[] obj={id2};
			rs=ju.select(sql, obj);
		}else if(id1==-1 && id2==-1){
			sql="select * from orders order by order_date desc limit ?,?";
			Object[] obj={pagenow,pagesize};
			rs=ju.select(sql, obj);
		}
		try {
			while(rs.next()){
				order=new Order(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9));
				list.add(order);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//店铺根据用户或订单编号查找订单信息
	public List<Order> check2(String store_id,Order order,int pagenow,int pagesize){
		int id1=-1,id2=-1;
		String sql;
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=null;
		String order_user_id=order.getOrder_user_id();
		String order_id=order.getOrder_id();
		int s_id=Integer.parseInt(store_id);
		if(order_user_id!=null){
			id1=Integer.parseInt(order_user_id);			
		}
		if(order_id!=null){
			id2=Integer.parseInt(order_id);
		}
		if(id1>-1&&id2>-1){
			sql="select * from (select orders.* from orders left join gooddetail on order_goods_id=good_id where store_id=?) t where t.order_user_id=? and t.order_id=?";
			Object[] obj={s_id,id1,id2};
			rs=ju.select(sql,obj);
		}else if(id1>-1){
			sql="select * from (select orders.* from orders left join gooddetail on order_goods_id=good_id where store_id=?) t where t.order_user_id=? order by order_date desc";
			Object[] obj={s_id,id1};
			rs=ju.select(sql, obj);
		}else if(id2>-1){
			sql="select * from (select orders.* from orders left join gooddetail on order_goods_id=good_id where store_id=?) t where t.order_id=?";
			Object[] obj={s_id,id2};
			rs=ju.select(sql, obj);
		}else if(id1==-1 && id2==-1){
			sql="select orders.* from orders left join gooddetail on order_goods_id=good_id where store_id=? order by order_date desc limit ?,?";
			Object[] obj={s_id,pagenow,pagesize};
			rs=ju.select(sql, obj);
		}
		try {
			while(rs.next()){
				order=new Order(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9));
				list.add(order);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查询所有订单信息
	public List<Order> checkInf(int pagenow,int pagesize){
		String sql="select * from orders order by order_date desc limit ?,?";
		Object[] obj={pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				order=new Order(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//根据商品ID查询订单
	public List<Order> checkById(String good_id) {
		int id=Integer.parseInt(good_id);
		String sql="select * from orders where order_goods_id=? order by order_date desc";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				order=new Order(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
		
	}
	//查询店铺订单
	public List<Order> checkStore(String store_id, int pagestart, int pagesize) {
		int id=Integer.parseInt(store_id);
		String sql="select orders.* from orders left join gooddetail on order_goods_id=good_id where store_id=? order by order_date desc limit ?,?";
		Object[] obj={id,pagestart,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				order=new Order(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查询类目信息数量
	public int checkCount(int i,String oid){
		JDBCUtil ju=new JDBCUtil();
		String sql;
		ResultSet rs=null;
		if(i==1){
			sql="select count(*) from orders";
			Object[] obj={};
			rs=ju.select(sql, obj);
		}else if(i==2){
			sql="select count(*) from orders where order_user_id=?";
			int id=Integer.parseInt(oid);
			Object[] obj={id};
			rs=ju.select(sql, obj);
		}
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
	//查询店铺订单数量
	public int checkCount2(String store_id) {
		int id=Integer.parseInt(store_id);
		String sql="select count(*) from orders left join gooddetail on order_goods_id=good_id where store_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return 0;
	}
	//修改订单状态
	public boolean update(String order_id,String order_status){
		int id=Integer.parseInt(order_id);
		String sql="update orders set order_status=? where order_id=?";
		Object[] obj={order_status,id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		else return false;
	}
	//删除订单信息
	public boolean drop(String order_id){
		int id=Integer.parseInt(order_id);
		String sql="delete from orders where order_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		else return false;
	}
}
