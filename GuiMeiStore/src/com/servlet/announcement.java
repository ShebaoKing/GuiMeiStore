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

import com.dao.AnnouncementDao;
import com.dao.GoodDao;
import com.entity.Announcement;
import com.entity.Goods;
import com.entity.Page;
import com.entity.User;
import com.util.uploadUtil;

public class announcement extends HttpServlet {
	
	File file=new File("E:\\Workspaces\\GuiMeiStore\\WebRoot\\images\\announcement");

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String action=req.getParameter("action");
		AnnouncementDao ad=new AnnouncementDao();
		Map<String, String> map;
		Announcement an;
		Page page;
		List<Announcement> list=new ArrayList<Announcement>();
		if(action.equals("check")){				//查询所有商品信息
			
			String num=req.getParameter("page");
			int count=ad.checkCount();
			page=new Page(num,7, count);
			list=ad.checkAll(page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("anlist",list);
			req.getRequestDispatcher("../webpage2/announcementManage.jsp").forward(req, resp);
			
		}else if(action.equals("show")){		//展示公告
			
			PrintWriter out=resp.getWriter();
			list=ad.checkInf();
			JSONArray json=JSONArray.fromObject(list);
			out.write(json.toString());
			
		}else if(action.equals("update")){			//修改公告信息
			
			String an_id=req.getParameter("an_id");
			String an_page=req.getParameter("page");
			an=ad.checkById(an_id);
			req.setAttribute("an",an);
			req.setAttribute("page",an_page);
			req.getRequestDispatcher("../webpage2/announcementUpdate.jsp").forward(req, resp);
		
		}else if(action.equals("add")){		//增加公告信息
			
			
			map=uploadUtil.upload(req,file);
			an=new Announcement(map.get("an_title"),map.get("an_text"),map.get("an_image"),map.get("an_status"));
			if(ad.add(an)){
				resp.sendRedirect("announcement?action=check");
			}else{
				resp.sendRedirect("../webpage/announcementAdd.jsp");
			}
			
		}else if(action.equals("download")){
			
			String an_image=req.getParameter("an_image");
			File img=new File(file, an_image);	
			if(!an_image.equals("")){
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
			
		}else if(action.equals("update2")){			//修改公告信息
			
			map=uploadUtil.upload(req,file);
			an=new Announcement(map.get("an_id"),map.get("an_title"),map.get("an_text"),map.get("an_image"),map.get("an_status"));
			ad.update(an);
			String num=map.get("an_page");
			resp.sendRedirect("announcement?action=check&page="+num);
			
		}else if(action.equals("delete")){			//删除公告信息
			
			String an_id=req.getParameter("an_id");
			String an_page=req.getParameter("page");
			ad.drop(an_id);
			resp.sendRedirect("announcement?action=check&page="+an_page);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
