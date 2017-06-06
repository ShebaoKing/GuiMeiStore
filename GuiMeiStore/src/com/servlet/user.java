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

import com.dao.UserDao;
import com.entity.Goods;
import com.entity.Page;
import com.entity.User;
import com.util.uploadUtil;

public class user extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action=req.getParameter("action");
		UserDao ud=new UserDao();
		Map<String, String> map;
		User user;
		Page page;
		List<User> list=new ArrayList<User>();
		File file=new File("E:\\Workspaces\\GuiMeiStore\\WebRoot\\images\\head");
		if(action.equals("register")){		//用户注册
			
			map=uploadUtil.upload(req,file);
			user=new User(map.get("user_name"),
						  map.get("user_login"),map.get("user_pwd"), map.get("user_phone"),
						  map.get("user_email"),map.get("user_sex"), 
						  map.get("user_photo"),map.get("user_hobby"),
						  map.get("user_birthday1")+"-"+map.get("user_birthday2")+"-"+map.get("user_birthday3"),"0");
			if(ud.add(user)){
				resp.getWriter().write("注册成功！");
				resp.sendRedirect("RegisterSuccess.jsp");
			}else{
				resp.getWriter().write("注册失败！");
				resp.sendRedirect("Register.jsp");
			}
			
		}else if(action.equals("login")){		//用户登录
			
			String name=req.getParameter("name");
			String password=req.getParameter("password");
			user=ud.check(name, password);
			if(user==null){
				resp.sendRedirect("Login.jsp");
			}else{
				req.getSession().setAttribute("user",user);
				req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
			}
			
		}else if(action.equals("logout")){			//注销
			
			req.getSession().invalidate();
			resp.sendRedirect("HomePage.jsp");
			
		}else if(action.equals("checkLogin")){		//用户名查重
			
			String login=req.getParameter("user_login");
			if(ud.checkByLogin(login)){
				resp.getWriter().write("用户名已存在！");
			}
			
		}else if(action.equals("checkEmail")){			//邮箱查重
			
			PrintWriter out=resp.getWriter();
			String email=req.getParameter("user_email");
			String user_id=req.getParameter("user_id");
			if(user_id==null){
				user_id="0";
			}
			if(ud.checkByEmail(email,user_id)){
				out.write("邮箱已被注册！");
			}
			out.close();
			
		}else if(action.equals("checkPhone")){			//电话查重
			
			PrintWriter out=resp.getWriter();
			String phone=req.getParameter("user_phone");
			String user_id=req.getParameter("user_id");
			if(user_id==null){
				user_id="0";
			}
			if(ud.checkByPhone(phone,user_id)){
				out.write("手机号码已被注册！");
			}
			out.close();
			
		}else if(action.equals("check")){
			
			String num=req.getParameter("page");		//查询所有用户信息
			int count=ud.checkCount();
			page=new Page(num,5, count);
			list=ud.checkAll(page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("userList",list);
			req.getRequestDispatcher("../webpage2/userManage.jsp").forward(req, resp);
		
		}else if(action.equals("update")){			//修改用户信息
			
			String user_id=req.getParameter("user_id");
			String user_page=req.getParameter("page");
			user=ud.checkById(user_id);
			req.setAttribute("user",user);
			req.setAttribute("page",user_page);
			req.getRequestDispatcher("../webpage2/userUpdate.jsp").forward(req, resp);
		
		}else if(action.equals("update2")){			//修改用户信息
			
			map=uploadUtil.upload(req,file);
			user=new User(map.get("user_id"),map.get("user_name"),map.get("user_login"), 
						map.get("user_pwd"),map.get("user_phone"),map.get("user_email"),map.get("user_sex"),
						map.get("user_photo"),map.get("user_hobby"),map.get("user_birthday1")+"-"+map.get("user_birthday2")+"-"+map.get("user_birthday3"),map.get("user_type"));
			ud.update(user);
			String num=map.get("user_page");
			resp.sendRedirect("user?action=check&page="+num);
			
		}else if(action.equals("update3")){			//修改用户信息
			
			map=uploadUtil.upload(req,file);
			user=new User(map.get("user_id"),map.get("user_name"),map.get("user_login"), 
						map.get("user_pwd"),map.get("user_phone"),map.get("user_email"),map.get("user_sex"),
						map.get("user_photo"),map.get("user_hobby"),map.get("user_birthday1")+"-"+map.get("user_birthday2")+"-"+map.get("user_birthday3"),map.get("user_type"));
			ud.update(user);
			user=ud.checkById(map.get("user_id"));
			req.getSession().setAttribute("user",user);
			resp.sendRedirect("../webpage2/centerUserInf.jsp");
			
		}else if(action.equals("update4")){
			
			String user_login=req.getParameter("user_login");
			String user_pwd=req.getParameter("user_pwd");
			boolean b=ud.updatePwd(user_login, user_pwd);
			if(b){
				resp.sendRedirect("user?action=logout");
			}
			
		}else if(action.equals("delete")){			//删除用户信息
			
			String user_id=req.getParameter("user_id");
			String user_page=req.getParameter("page");
			ud.drop(user_id);
			resp.sendRedirect("user?action=check&page="+user_page);
			
		}else if(action.equals("download")){		//加载头像
			
			String user_photo=req.getParameter("user_photo");
			if(!user_photo.equals("")){
				File file2=new File(file, user_photo);
				//判断该文件是否存在
				if(file2.exists()){
					BufferedInputStream bin=new BufferedInputStream(new FileInputStream(file2));
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
			
		}
		
	}

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
