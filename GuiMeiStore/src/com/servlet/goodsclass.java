package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.dao.CategoryDao;
import com.dao.GoodDao;
import com.dao.GoodsClassDao;
import com.entity.Category;
import com.entity.GoodsClass;
import com.entity.Page;

public class goodsclass extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action=req.getParameter("action");
		GoodsClassDao gd=new GoodsClassDao();
		CategoryDao cd=new CategoryDao();
		List<GoodsClass> list=new ArrayList<GoodsClass>();
		Page page;
		GoodsClass goods;
		PrintWriter out=resp.getWriter();
		File file=new File("E:\\Workspaces\\GuiMeiStore\\WebRoot\\images\\goods");
		if(action.equals("checkAll")){
			
			list=gd.checkAll();
			req.setAttribute("goodsclass",list);
			req.getRequestDispatcher("category?action=checkAll").forward(req,resp);
			
		}else if(action.equals("check")){   		//查询分类信息
			
			String num=req.getParameter("page");		
			int count=gd.checkCount();
			page=new Page(num,5,count);
			list=gd.checkInf(page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("classlist",list);
			req.getRequestDispatcher("../webpage2/classManage.jsp").forward(req, resp);
			
		}else if(action.equals("update")){
			
			String class_id=req.getParameter("class_id");
			String class_page=req.getParameter("page");
			goods=gd.checkById(class_id);
			req.setAttribute("goodsclass",goods);
			req.setAttribute("page",class_page);
			req.getRequestDispatcher("../webpage2/goodsclassUpdate.jsp").forward(req, resp);
		}else if(action.equals("update2")){
			String class_id=req.getParameter("class_id1");
			String class_name=req.getParameter("class_name1");
			String class_page=req.getParameter("page");
			goods=new GoodsClass(class_id, class_name);
			gd.update(goods);
			resp.sendRedirect("goodsclass?action=check&page="+class_page);
		}else if(action.equals("checkInf")){
			
			list=gd.checkAll();
			JSONArray json=JSONArray.fromObject(list);
			out.write(json.toString());
			
		}else if(action.equals("add")){			//添加分类
			
			goods=new GoodsClass(req.getParameter("class_name"));
			if(gd.add(goods)){
				resp.sendRedirect("goodsclass?action=check");
			}else{
				resp.sendRedirect("../webpage2/classAdd.jsp");
			}
			
		}else if(action.equals("delete")){
			
			String class_id=req.getParameter("class_id");
			String class_page=req.getParameter("page");
			List<Category> cate=new ArrayList<Category>();
			cate=cd.check(class_id);
			if(cate.size()==0){
				gd.drop(class_id);
			}
			resp.sendRedirect("goodsclass?action=check&page="+class_page);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
