package com.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.dao.GoodDao;
import com.dao.OrderDao;
import com.entity.Announcement;
import com.entity.Goods;
import com.entity.Order;
import com.entity.Page;
import com.entity.User;
import com.util.uploadUtil;

public class good extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action=req.getParameter("action");
		GoodDao gd=new GoodDao();
		OrderDao od=new OrderDao();
		Map<String, String> map;
		Goods good;
		Page page;
		List<Goods> list=new ArrayList<Goods>();
		File file=new File("E:\\Workspaces\\GuiMeiStore\\WebRoot\\images\\goods");
		if(action.equals("check")){				//查询所有商品信息
			
			String num=req.getParameter("page");		
			int count=gd.checkCount();
			page=new Page(num,3, count);
			list=gd.checkAll(page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("goodslist",list);
			req.getRequestDispatcher("../webpage2/goodDetailManage.jsp").forward(req, resp);
			
		}else if(action.equals("storeCheck")){		//店铺查询
			
			String num=req.getParameter("page");
			String store_id=req.getParameter("store_id");
			if(num==null){
				num=(String) req.getAttribute("page");
			}
			if(store_id==null){
				store_id=(String) req.getAttribute("store_id");
			}
			int count=gd.checkCount5(store_id);
			page=new Page(num,3, count);
			list=gd.checkStore(store_id,page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("goodslist",list);
			req.getRequestDispatcher("../webpage2/storeGoodsManager.jsp").forward(req, resp);
			
			
		}else if(action.equals("checkHot")){				//查询热销商品信息
			
			PrintWriter out=resp.getWriter();
			list=gd.checkHot();
			JSONArray json=JSONArray.fromObject(list);
			out.write(json.toString());
			
		}else if(action.equals("checkNew")){				//查询新品信息
			
			PrintWriter out=resp.getWriter();
			list=gd.checkNew();
			JSONArray json=JSONArray.fromObject(list);
			out.write(json.toString());
			
		}else if(action.equals("checkCate")){				//查询小分类信息
			
			
			String num=req.getParameter("page");	
			String cate_id=req.getParameter("cate_id");
			int count=gd.checkCount2(cate_id);
			page=new Page(num,3, count);
			list=gd.checkCate(cate_id,page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("goodslist", list);
			req.getRequestDispatcher("Category.jsp").forward(req, resp);
			
		}else if(action.equals("searchInf")){			//查询搜索信息
			
			String num=req.getParameter("page");
			String key=req.getParameter("search");
			int count=gd.checkCount3(key);
			page=new Page(num,3, count);
			list=gd.checkSearch(key,page.getPagestart(),page.getPagesize());
			req.setAttribute("search",key);
			req.setAttribute("page",page);
			req.setAttribute("goodslist",list);
			req.getRequestDispatcher("SearchInf.jsp").forward(req, resp);
			
		}else if(action.equals("checkClass")){			//查询大分类
			
			String num=req.getParameter("page");
			String class_name=req.getParameter("class_name");
			int count=gd.checkCount4(class_name);
			page=new Page(num,3, count);
			list=gd.checkClass(class_name,page.getPagestart(),page.getPagesize());
			req.setAttribute("class_name",class_name);
			req.setAttribute("page",page);
			req.setAttribute("goodslist",list);
			req.getRequestDispatcher("GoodsclassInf.jsp").forward(req, resp);
			
		}else if(action.equals("checkOne")){				//查询单个商品信息
			
			
			String good_id=req.getParameter("good_id");
			good=gd.checkById(good_id);
			req.setAttribute("g",good);
			req.getRequestDispatcher("Goods.jsp").forward(req, resp);
			
		}else if(action.equals("add")){		//添加商品
			
			map=uploadUtil.upload(req,file);
			good=new Goods(map.get("good_name"),map.get("cate_id"),map.get("pic1"), 
							map.get("pic2"),map.get("pic3"),map.get("pic4"),
							map.get("good_count"),"0",map.get("good_sale"),
							map.get("sale_dz"),map.get("describe"),map.get("good_type"),map.get("store_id"));
			String store_id=map.get("store_id");
			if(gd.add(good)){
				resp.sendRedirect("good?action=storeCheck&store_id="+store_id);
			}else{
				resp.sendRedirect("../webpage2/goodsAdd.jsp");
			}
			
		}else if(action.equals("download")){		//加载图片
			 
			String good_photo=req.getParameter("good_pic");
			if(!good_photo.equals("")){
				//判断该文件是否存在
				if(file.exists()){
					BufferedInputStream bin=new BufferedInputStream(new FileInputStream(new File(file, good_photo)));
					BufferedOutputStream bout=new BufferedOutputStream(resp.getOutputStream());
					byte b[]=new byte[1024];
					int k=-1;
					while((k=bin.read(b))>-1){
						bout.write(b,0,k);
					}
					bout.close();
					bin.close();
				}
			}
			
		}else if(action.equals("delete")){			//删除商品信息
			
			String good_id=req.getParameter("good_id");
			String good_page=req.getParameter("page");
			List<Order> l=new ArrayList<Order>();
			l=od.checkById(good_id);
			if(l.size()==0){
				gd.drop(file,good_id);
			}
			resp.sendRedirect("good?action=check&page="+good_page);
			
		}else if(action.equals("update")){
			
			String good_id=req.getParameter("good_id");
			String good_page=req.getParameter("page");
			good=gd.checkById(good_id);
			req.setAttribute("good",good);
			req.setAttribute("page",good_page);
			req.getRequestDispatcher("../webpage2/goodsUpdate.jsp").forward(req, resp);
			
		}else if(action.equals("update2")){			//管理员修改商品信息
			
			map=uploadUtil.upload(req,file);
			good=new Goods(map.get("good_id"),map.get("good_name"),map.get("cate_id"), 
						map.get("good_pic1"),map.get("good_pic2"),map.get("good_pic3"),map.get("good_pic4"),
						map.get("good_count"),"0",map.get("good_sale"),
						map.get("sale_dz"),map.get("describe"),map.get("good_type"),map.get("store_id"));
			gd.update(good);
			String num=map.get("good_page");
			resp.sendRedirect("good?action=check&page="+num);
			
		}else if(action.equals("storeUpdate")){
			
			String good_id=req.getParameter("good_id");
			String good_page=req.getParameter("page");
			good=gd.checkById(good_id);
			req.setAttribute("good",good);
			req.setAttribute("page",good_page);
			req.getRequestDispatcher("../webpage2/goodsUpdate2.jsp").forward(req, resp);
			
		}else if(action.equals("storeUpdate2")){			//店铺修改商品信息
			
			map=uploadUtil.upload(req,file);
			good=new Goods(map.get("good_id"),map.get("good_name"),map.get("cate_id"), 
						map.get("good_pic1"),map.get("good_pic2"),map.get("good_pic3"),map.get("good_pic4"),
						map.get("good_count"),"0",map.get("good_sale"),
						map.get("sale_dz"),map.get("describe"),map.get("good_type"),map.get("store_id"));
			gd.update(good);
			String num=map.get("good_page");
			String store_id=map.get("store_id");
			req.setAttribute("page", num);
			req.setAttribute("store_id",store_id);
			req.getRequestDispatcher("good?action=storeCheck").forward(req, resp);			
		}else if(action.equals("storeDelete")){			//店铺删除商品信息
			
			String good_id=req.getParameter("good_id");
			String good_page=req.getParameter("page");
			String store_id=req.getParameter("store_id");
			List<Order> l=new ArrayList<Order>();
			l=od.checkById(good_id);
			if(l.size()==0){
				gd.drop(file,good_id);
			}
			req.setAttribute("page",good_page);
			req.setAttribute("store_id",store_id);
			req.getRequestDispatcher("good?action=storeCheck").forward(req, resp);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
