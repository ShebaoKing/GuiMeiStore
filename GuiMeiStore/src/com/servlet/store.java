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
import com.dao.StoreDao;
import com.dao.UserDao;
import com.entity.Goods;
import com.entity.Page;
import com.entity.Store;
import com.entity.User;
import com.util.uploadUtil;

public class store extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action=req.getParameter("action");
		File file=new File("E:\\Workspaces\\GuiMeiStore\\WebRoot\\images\\store");
		Map<String, String> map;
		Store store;
		StoreDao sd=new StoreDao();
		UserDao ud=new UserDao();
		GoodDao gd=new GoodDao();
		Page page;
		List<Store> list=new ArrayList<Store>();
		List<Goods> goodslist=new ArrayList<Goods>();
		if(action.equals("add")){		//添加店铺信息
			
			map=uploadUtil.upload(req, file);
			store=new Store(map.get("store_user_id"), map.get("store_name"), map.get("store_photo"),
							map.get("store_card"),"2");
			sd.add(store);
			User user=ud.checkById(store.getStore_user_id());
			req.getSession().setAttribute("user",user);
			resp.sendRedirect("../webpage2/userCenter.jsp");
			
		}else if(action.equals("check")){
			
			String num=req.getParameter("page");
			int count=sd.checkCount();
			page=new Page(num,7,count);
			list=sd.checkInf(page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("storelist",list);
			req.getRequestDispatcher("../webpage2/storeManage.jsp").forward(req, resp);
			
			
		}else if(action.equals("checkAll")){
			
			String num=req.getParameter("page");
			int count=sd.checkCount2();
			page=new Page(num,3,count);
			list=sd.checkInf2(page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("storelist",list);
			req.getRequestDispatcher("../webpage/storeAll.jsp").forward(req, resp);
			
			
		}else if(action.equals("checkOne")){			//查询单个店铺
			
			String store_id=req.getParameter("store_id");
			store=sd.checkById(store_id);
			req.setAttribute("store", store);
			req.getRequestDispatcher("store.jsp").forward(req, resp);
			
		}else if(action.equals("storeGoods")){				//查询店铺所有商品
			
			PrintWriter out=resp.getWriter();
			String store_id=req.getParameter("store_id");
			goodslist=gd.checkStore(store_id,0,10000);
			JSONArray json=JSONArray.fromObject(goodslist);
			out.write(json.toString());
			out.close();
			
		}else if(action.equals("update")){				//查询修改店铺信息
			
			String store_id=req.getParameter("store_id");
			String store_page=req.getParameter("page");
			store=sd.checkById(store_id);
			req.setAttribute("store",store);
			req.setAttribute("page",store_page);
			req.getRequestDispatcher("../webpage2/storeUpdate.jsp").forward(req, resp);
			
			
		}else if(action.equals("update2")){				//提交店铺修改信息
			
			String store_id=req.getParameter("store_id");
			String store_type=req.getParameter("store_type");
			String store_page=req.getParameter("page");
			sd.update(store_id,store_type);
			store=sd.checkById(store_id);
			User user=ud.checkById(store.getStore_user_id());
			req.getSession().setAttribute("user",user);
			resp.sendRedirect("store?action=check&page="+store_page);
			
			
		}else if(action.equals("checkName")){			//店铺名查重
			
			String name=req.getParameter("store_name");
			store=sd.checkByName(name);
			if(store!=null){
				resp.getWriter().write("店铺名已注册！");
			}
			
		}else if(action.equals("checkCard")){			//店铺身份证查重
			
			String card=req.getParameter("store_card");
			if(sd.checkByCard(card)){
				resp.getWriter().write("身份证已注册！");
			}
			
		}else if(action.equals("download")){		//加载店铺图片
			
			String store_photo=req.getParameter("store_photo");
			File img=new File(file, store_photo);	
			if(store_photo!=null){
				//判断该文件是否存在
				if(img.exists()){
					BufferedInputStream bin=new BufferedInputStream(new FileInputStream(img));
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
			
		}else if(action.equals("delete")){
			
			String store_id=req.getParameter("store_id");
			String store_page=req.getParameter("page");
			sd.drop(store_id);
			store=sd.checkById(store_id);
			User user=ud.checkById(store.getStore_user_id());
			req.getSession().setAttribute("user",user);
			req.setAttribute("page",store_page);
			resp.sendRedirect("store?action=check&page="+store_page);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
