package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.AdminDao;
import com.entity.Admin;
public class admin extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		AdminDao ad=new AdminDao();
		String action=req.getParameter("action");
		Admin admin=null;
		if(action.equals("login")){
			String user=req.getParameter("name");
			String pwd=req.getParameter("pwd");
			admin=ad.check(user, pwd);
			if(admin!=null){
				req.getSession().setAttribute("admin",admin);
				resp.sendRedirect("../webpage2/Background.jsp");
			}else{
				resp.sendRedirect("ManagerLogin.jsp");
			}
		}
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
