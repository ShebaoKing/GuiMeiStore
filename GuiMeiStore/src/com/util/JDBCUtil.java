package com.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JDBCUtil {
	public String aa="https://github.com/ShebaoKing/GuiMeiStore.git";
	private String url="jdbc:mysql://localhost:3306/guimei";
	private String user="root";
	private String pwd="redhat";
	private Connection con=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;	
	//加载驱动
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//获取连接
	public void getCon(){
		try {
			con=DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//增删改
	public int update(String sql,Object[] obj){
		getCon();
		int n=0;
		try {
			ps=con.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setObject(i+1, obj[i]);
			}
			n=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return n;
	}
	//查
	public ResultSet select(String sql,Object[] obj){
		getCon();
		try {
			ps=con.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i+1, obj[i]);
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	//获取列数
	public int columnCount(){
		int colum=0;
		try {
			colum=rs.getMetaData().getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colum;
	}
	//释放资源
	public void close(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
