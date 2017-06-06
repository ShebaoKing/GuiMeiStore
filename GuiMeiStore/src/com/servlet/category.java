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
import com.entity.Goods;
import com.entity.GoodsClass;
import com.entity.Page;

public class category extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		String action=req.getParameter("action");
		GoodDao gdd=new GoodDao();
		CategoryDao cd=new CategoryDao();
		GoodsClassDao gd=new GoodsClassDao();
		List<GoodsClass> goodsclass=new ArrayList<GoodsClass>();
		List<GoodsClass> goodsclass2=new ArrayList<GoodsClass>();
		List<Category> list=new ArrayList<Category>();
		Page page;
		Category cate;
		File file=new File("E:\\Workspaces\\GuiMeiStore\\WebRoot\\images\\goods");
		if(action.equals("checkAll")){
			
			goodsclass=(List<GoodsClass>) req.getAttribute("goodsclass");
			for (GoodsClass good : goodsclass) {
				good.setCate(cd.check(good.getClass_id()));
				goodsclass2.add(good);
			}
			JSONArray json=JSONArray.fromObject(goodsclass2);
			out.write(json.toString());
			
		}else if(action.equals("check")){   		//查询所有分类信息
			
			String num=req.getParameter("page");		
			int count=cd.checkCount();
			page=new Page(num,7,count);
			list=cd.checkInf(page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("catelist",list);
			req.getRequestDispatcher("../webpage2/categoryManage.jsp").forward(req, resp);
			
		}else if(action.equals("update")){
			
			String cate_id=req.getParameter("cate_id");
			String cate_page=req.getParameter("page");
			cate=cd.checkById(cate_id);
			goodsclass=gd.checkAll();
			req.setAttribute("category",cate);
			req.setAttribute("page",cate_page);
			req.setAttribute("goodslist",goodsclass);
			req.getRequestDispatcher("../webpage2/categoryUpdate.jsp").forward(req, resp);
			
		}else if(action.equals("update2")){
			
			String cate_id=req.getParameter("cate_id1");
			String cate_name=req.getParameter("cate_name1");
			String class_id=req.getParameter("class_id1");
			String cate_page=req.getParameter("page");
			cate=new Category(cate_id, cate_name, class_id);
			cd.update(cate);
			resp.sendRedirect("category?action=check&page="+cate_page);
			
		}else if(action.equals("checkInf")){			//查找大分类下小分类信息
			
			String class_id=req.getParameter("class_id");
			list=cd.check(class_id);
			JSONArray json=JSONArray.fromObject(list);
			out.write(json.toString());
			
		}else if(action.equals("add")){					//添加小分类
			
			String cate_name=req.getParameter("cate_name");
			String class_id=req.getParameter("class_id");
			cate=new Category(cate_name, class_id);
			if(cd.add(cate)){
				resp.sendRedirect("category?action=check");
			}else{
				resp.sendRedirect("../webpage2/categoryAdd.jsp");
			}
			
		}else if(action.equals("delete")){				//删除小分类
			
			String cate_id=req.getParameter("cate_id");
			String cate_page=req.getParameter("page");
			List<Goods> l=new ArrayList<Goods>();
			l=gdd.checkCate2(cate_id);
			if(l.size()==0){
				cd.drop(cate_id);
			}
			resp.sendRedirect("category?action=check&page="+cate_page);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
