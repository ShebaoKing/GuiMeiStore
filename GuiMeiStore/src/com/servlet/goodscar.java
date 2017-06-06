package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.dao.GoodsCarDao;
import com.entity.GoodsCar;

public class goodscar extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action=req.getParameter("action");
		GoodsCarDao gcd=new GoodsCarDao();
		List<GoodsCar> list=new ArrayList<GoodsCar>();
		GoodsCar car;
		
		if(action.equals("check")){				//查询购物车信息
			
			PrintWriter out=resp.getWriter();
			String user_id=req.getParameter("user_id");
			list=gcd.check(user_id);
			JSONArray json=JSONArray.fromObject(list);
			out.write(json.toString());
			out.close();
			
		}else if(action.equals("delete")){			//删除购物车信息
			
			String car_id=req.getParameter("car_id");
			gcd.drop(car_id);
			resp.sendRedirect("../webpage2/buy.jsp");
			
		}else if(action.equals("add")){			//添加购物车信息
			
			PrintWriter out=resp.getWriter();
			String car_goods_id=req.getParameter("car_goods_id");
			String car_user_id=req.getParameter("car_user_id");
			String car_no=req.getParameter("car_no");
			car=new GoodsCar(car_goods_id, car_user_id, car_no);
			if(gcd.add(car)){
				out.println("已添加入购物车!");
				
			}else{
				out.println("添加购物车失败!");
			}
			out.close();
		}else if(action.equals("update")){
			PrintWriter out=resp.getWriter();
			String car_id=req.getParameter("car_id");
			String car_no=req.getParameter("car_no");
			String user_id=req.getParameter("user_id");
			if(gcd.update(car_id, car_no)){
				double[] sum=gcd.sum(user_id);
				JSONArray json=JSONArray.fromObject(sum);
				out.write(json.toString());
			}
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
