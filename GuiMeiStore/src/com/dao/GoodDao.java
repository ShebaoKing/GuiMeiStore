package com.dao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Announcement;
import com.entity.Goods;
import com.entity.User;
import com.util.JDBCUtil;


public class GoodDao {
	List<Goods> list=new ArrayList<Goods>();
	Goods good=null;
	//添加商品信息
	public boolean add(Goods good){
		
		String sql="insert into gooddetail values(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj={good.getGood_name(),good.getCate_id(),good.getPic1(),
				good.getPic2(),good.getPic3(),good.getPic4(),good.getGood_count(),
				good.getSale_count(),good.getGood_sale(),good.getSale_dz(),
				good.getDescribe(),good.getGood_type(),good.getStore_id()};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		return false;
	}
	//增加销量
	public boolean addSale(int count,String id){
		int gid=Integer.parseInt(id);
		String sql="update gooddetail set sale_count=sale_count+?,good_count=good_count-? where good_id=?";
		Object[] obj={count,count,gid};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return true;
		return false;
	}
	//查询库存
	public int checkCount(String id){
		int gid=Integer.parseInt(id);
		String sql="select good_count from gooddetail where good_id=?";
		Object[] obj={gid};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		int count=0;
		try {
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}
	//查找所有商品信息
	public List<Goods> checkAll(int pagenow,int pagesize){
		String sql="select * from (select g.* from gooddetail g left join store s on g.store_id=s.store_id where store_type='1' ) as t order by store_id limit ?,?";
		Object[] obj={pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				good=new Goods(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
				list.add(good);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查找所有热销商品信息
	public List<Goods> checkHot(){
		String sql="select * from (select g.* from gooddetail g left join store s on g.store_id=s.store_id where store_type='1' ) as t where t.des='1' limit 0,15";
		Object[] obj={};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				good=new Goods(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
				list.add(good);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查找所有新品信息
	public List<Goods> checkNew(){
		String sql="select * from (select g.* from gooddetail g left join store s on g.store_id=s.store_id where store_type='1' ) as t where t.good_type='1' limit 0,4";
		Object[] obj={};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				good=new Goods(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
				list.add(good);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//根据ID查找商品信息
	public List<Goods> checkCate(String cate_id,int pagenow,int pagesize){
		int id=Integer.parseInt(cate_id);
		String sql="select * from (select g.* from gooddetail g left join store s on g.store_id=s.store_id where store_type='1' ) as t where t.cate_id=? limit ?,?";
		Object[] obj={id,pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				good=new Goods(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
				list.add(good);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查找小分类下商品
	public List<Goods> checkCate2(String cate_id){
		int id=Integer.parseInt(cate_id);
		String sql="select * from (select g.* from gooddetail g left join store s on g.store_id=s.store_id where store_type='1' ) as t where t.cate_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				good=new Goods(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
				list.add(good);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查找店铺商品
	public List<Goods> checkStore(String store_id,int pagenow,int pagesize){
		int id=Integer.parseInt(store_id);
		String sql="select * from gooddetail where store_id=? limit ?,?";
		Object[] obj={id,pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				good=new Goods(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
				list.add(good);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//查询商品信息数量
	public int checkCount(){
		String sql="select count(*) from (select g.* from gooddetail g left join store s on g.store_id=s.store_id where store_type='1' ) as t";
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
	//查询商品信息数量
	public int checkCount2(String cate_id){
		int id=Integer.parseInt(cate_id);
		String sql="select count(*) from (select g.* from gooddetail g left join store s on g.store_id=s.store_id where store_type='1' ) as t where t.cate_id=?";
		Object[] obj={id};
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
	//查询搜索商品信息数量
	public int checkCount3(String str){
		String key="%"+str+"%";
		String sql="select count(*) from (select t.* from gooddetail t left join store s on t.store_id=s.store_id where store_type='1' ) as g left join (select cg.*,gc.class_name from category as cg left join goodsclass as gc on cg.class_id=gc.class_id) as c on g.cate_id="+
					"c.cate_id where class_name like ? or cate_name like ? or good_name like ?";
		Object[] obj={key,key,key};
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
	//查询大分类商品信息数量
	public int checkCount4(String str){
		String sql="select count(*) from (select t.* from gooddetail t left join store s on t.store_id=s.store_id where store_type='1' ) as g left join (select cg.*,gc.class_name from category as cg left join goodsclass as gc on cg.class_id=gc.class_id) as c on g.cate_id="+
				"c.cate_id where class_name=?";
		Object[] obj={str};
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
	//查询店铺商品数量
	public int checkCount5(String store_id){
		int id=Integer.parseInt(store_id);
		String sql="select count(*) from gooddetail where store_id=?";
		Object[] obj={id};
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
	//根据ID查找商品信息
	public Goods checkById(String n){
		int id=Integer.parseInt(n);
		String sql="select * from (select g.* from gooddetail g left join store s on g.store_id=s.store_id where store_type='1' ) as t where t.good_id=?";
		Object[] obj={id};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			if(rs.next()){
				good=new Goods(n,rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return good;
	}
	//修改商品信息
	public boolean update(Goods good){
		
		String sql;
		JDBCUtil ju=new JDBCUtil();
		int i=0;
		sql="update gooddetail set good_name=?,cate_id=?,";
		if(good.getPic1()!=null){
			sql+="pic1='"+good.getPic1()+"',";
		}
		if(good.getPic2()!=null){
			sql+="pic2='"+good.getPic2()+"',";
		}
		if(good.getPic3()!=null){
			sql+="pic3='"+good.getPic3()+"',";
		}
		if(good.getPic4()!=null){
			sql+="pic4='"+good.getPic4()+"',";
		}
		int id1=Integer.parseInt(good.getCate_id());
		int id2=Integer.parseInt(good.getGood_id());
		int count=good.getGood_count();
		double sale=good.getGood_sale();
		double dz=good.getSale_dz();
		sql+="good_count=?,good_sale=?,sale_dz=?,des=?,good_type=? where good_id=?";
		Object[] obj={good.getGood_name(),id1,count,sale,dz,good.getDescribe(),good.getGood_type(),id2};
		i=ju.update(sql,obj);
		if(i>0) return true;
		return false;
	}
	//模糊查询
	public List<Goods> checkSearch(String st,int pagenow,int pagesize){
		String key="%"+st+"%";
		String sql="select g.*,c.cate_name,c.class_id,c.class_name from (select t.* from gooddetail t left join store s on t.store_id=s.store_id where store_type='1' )as g left join (select cg.*,gc.class_name from category as cg left join goodsclass as gc on cg.class_id=gc.class_id) as c on g.cate_id="+
					"c.cate_id where class_name like ? or cate_name like ? or good_name like ? limit ?,?";
		Object[] obj={key,key,key,pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				good=new Goods(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
				list.add(good);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//大分类查询
	public List<Goods> checkClass(String st,int pagenow,int pagesize){
		String sql="select g.*,c.cate_name,c.class_id,c.class_name from (select t.* from gooddetail t left join store s on t.store_id=s.store_id where store_type='1' ) as g left join (select cg.*,gc.class_name from category as cg left join goodsclass as gc on cg.class_id=gc.class_id) as c on g.cate_id="+
				"c.cate_id where class_name=? limit ?,?";
		Object[] obj={st,pagenow,pagesize};
		JDBCUtil ju=new JDBCUtil();
		ResultSet rs=ju.select(sql, obj);
		try {
			while(rs.next()){
				good=new Goods(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14));
				list.add(good);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ju.close();
		}
		return list;
	}
	//删除商品图片
	public boolean dropImg(File file, String id){
		boolean b=true;
		good=checkById(id);
		String img;
		img=good.getPic1();
		if(img!=null){
			File f=new File(file, img);
			if(f.exists()){
				b=b&&f.delete();
			}
		}
		img=good.getPic2();
		if(img!=null){
			File f=new File(file, img);
			if(f.exists()){
				b=b&&f.delete();
			}
		}
		img=good.getPic3();
		if(img!=null){
			File f=new File(file, img);
			if(f.exists()){
				b=b&&f.delete();
			}
		}
		img=good.getPic4();
		if(img!=null){
			File f=new File(file, img);
			if(f.exists()){
				b=b&&f.delete();
			}
		}
		return b;
	}
	//删除商品信息
	public boolean drop(File file, String id){
		boolean b=dropImg(file, id);
		int good_id=Integer.parseInt(id);
		String sql="delete from gooddetail where good_id=?";
		Object[] obj={good_id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>0)return b&&true;
		else return false;
	}
	//删除商品信息
	public boolean drop2(File file,String id){
		boolean b=true;
		list=checkCate2(id);
		for (Goods g : list) {
			b=b&&dropImg(file, g.getGood_id());
		}
		int cate_id=Integer.parseInt(id);
		String sql="delete from gooddetail where cate_id=?";
		Object[] obj={cate_id};
		JDBCUtil ju=new JDBCUtil();
		int i=ju.update(sql, obj);
		if(i>-1)return b&&true;
		else return false;
	}
}
