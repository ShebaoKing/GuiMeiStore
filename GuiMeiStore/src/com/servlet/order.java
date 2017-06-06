package com.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GoodDao;
import com.dao.GoodsCarDao;
import com.dao.OrderDao;
import com.entity.GoodsCar;
import com.entity.Order;
import com.entity.Page;

public class order extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action=req.getParameter("action");
		List<Order> list=new ArrayList<Order>();
		OrderDao od=new OrderDao();
		GoodDao gd=new GoodDao();
		Order order=new Order();
		Page page;
		if(action.equals("check")){
			
			String num=req.getParameter("page");	
			int count=od.checkCount(1,null);
			page=new Page(num,7,count);
			list=od.checkInf(page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("orderlist",list);
			req.getRequestDispatcher("../webpage2/orderManage.jsp").forward(req, resp);
			
		}else if(action.equals("checkOne")){
			
			String order_id=req.getParameter("order_id");
			order.setOrder_id(order_id);
			list=od.check(order,0,0);
			order=list.get(0);
			req.setAttribute("order",order);
			req.getRequestDispatcher("../webpage2/orderCheck.jsp").forward(req, resp);
			
		}else if(action.equals("storeCheck")){		//店铺查询
			
			String num=req.getParameter("page");
			String store_id=req.getParameter("store_id");
			if(num==null){
				num=(String) req.getAttribute("page");
			}
			if(store_id==null){
				store_id=(String) req.getAttribute("store_id");
			}
			int count=od.checkCount2(store_id);
			page=new Page(num,5, count);
			list=od.checkStore(store_id,page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("orderlist",list);
			req.getRequestDispatcher("../webpage2/storeOrderManage.jsp").forward(req, resp);
			
		}else if(action.equals("add")){
			
			String order_address=req.getParameter("order_address");
			String order_name=req.getParameter("order_name");
			String order_phone=req.getParameter("order_phone");
			String user_id=req.getParameter("user_id");
			
			GoodsCarDao gcd=new GoodsCarDao();
			List<GoodsCar> car=gcd.check(user_id);
			List<String> mess=new ArrayList<String>();
			for (GoodsCar c : car) {
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
				String time=sdf.format(date);
				Order order2=new Order(c.getCar_goods_id(),c.getCar_user_id(),c.getCar_no()+"", order_name,order_phone,order_address,"",time);
				
				int num=gd.checkCount(c.getCar_goods_id());
				if(num<c.getCar_no()){
					String str=c.getGoods().getGood_name()+"库存为："+num+",请减少您的购物车商品数量！";
					mess.add(str);
				}else{
					gd.addSale(c.getCar_no(),c.getCar_goods_id());
					od.add(order2);
					gcd.drop(c.getCar_id());
				}
			}
			req.setAttribute("mess",mess);
			req.getRequestDispatcher("../webpage2/buy.jsp").forward(req, resp);			
		}else if(action.equals("update")){			//管理员修改订单状态
			
			String order_id=req.getParameter("order_id");
			String order_page=req.getParameter("page");
			order.setOrder_id(order_id);
			list=od.check(order,0,0);
			order=list.get(0);
			req.setAttribute("order",order);
			req.setAttribute("page",order_page);
			req.getRequestDispatcher("../webpage2/OrdersUpdate.jsp").forward(req, resp);
			
		}else if(action.equals("storeUpdate")){			//店铺修改订单状态
			
			String order_id=req.getParameter("order_id");
			String order_page=req.getParameter("page");
			order.setOrder_id(order_id);
			list=od.check(order,0,0);
			order=list.get(0);
			req.setAttribute("order",order);
			req.setAttribute("page",order_page);
			req.getRequestDispatcher("../webpage2/OrdersUpdate2.jsp").forward(req, resp);
			
		}else if(action.equals("update2")){				//管理员修改订单状态
			
			String order_id=req.getParameter("order_id");
			String order_status=req.getParameter("order_status");
			String order_page=req.getParameter("page");
			od.update(order_id,order_status);
			resp.sendRedirect("order?action=check&page="+order_page);
			
		}else if(action.equals("storeUpdate2")){				//店铺修改订单状态
			
			String order_id=req.getParameter("order_id");
			String store_id=req.getParameter("store_id");
			String order_status=req.getParameter("order_status");
			String order_page=req.getParameter("page");
			od.update(order_id,order_status);			
			req.setAttribute("page",order_page);
			req.setAttribute("store_id",store_id);
			req.getRequestDispatcher("order?action=storeCheck").forward(req, resp);
			
		}else if(action.equals("userUpdate2")){				//确认收货
			
			String order_id=req.getParameter("order_id");
			String user_name=req.getParameter("user_name");
			String order_page=req.getParameter("page");
			od.update(order_id,"4");			
			req.setAttribute("page",order_page);
			req.setAttribute("user_name",user_name);
			req.getRequestDispatcher("order?action=check3").forward(req, resp);
			
		}else if(action.equals("check2")){					//后台搜索框查询
			
			String num=req.getParameter("page");
			String order_id=req.getParameter("order_id");
			String user_name=req.getParameter("user_name");
			int count=1;
			if(order_id!=""){
				order.setOrder_id(order_id);
			}
			if(user_name!=""){
				order.setOrder_user_name(user_name);
			}
			if(order_id=="" && user_name!=""){
				count=od.checkCount(2,order.getOrder_user_id());
			}
			page=new Page(num,7,count);
			list=od.check(order,page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("orderlist",list);
			req.getRequestDispatcher("../webpage2/orderManage.jsp").forward(req, resp);
			
		}else if(action.equals("storeCheck2")){					//店铺搜索框查询
			
			String num=req.getParameter("page");
			String order_id=req.getParameter("order_id");
			String user_name=req.getParameter("user_name");
			String store_id=req.getParameter("store_id");
			int count=1;
			if(order_id!=""){
				order.setOrder_id(order_id);
			}
			if(user_name!=""){
				order.setOrder_user_name(user_name);
			}
			if(order_id=="" && user_name!=""){
				count=od.checkCount(2,order.getOrder_user_id());
			}
			page=new Page(num,7,count);
			list=od.check2(store_id,order,page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("orderlist",list);
			req.getRequestDispatcher("../webpage2/storeOrderManage.jsp").forward(req, resp);
			
			
		}else if(action.equals("check3")){				//用户查询个人订单
			
			int count=0;
			String num=req.getParameter("page");
			String user_name=req.getParameter("user_name");
			order.setOrder_user_name(user_name);
			count=od.checkCount(2,order.getOrder_user_id());
			page=new Page(num,5,count);
			list=od.check(order,page.getPagestart(),page.getPagesize());
			req.setAttribute("page",page);
			req.setAttribute("orderlist",list);
			req.getRequestDispatcher("../webpage2/centerOrderInf.jsp").forward(req, resp);
			
		}else if(action.equals("delete")){			//管理员删除订单
			
			String order_id=req.getParameter("order_id");
			String order_page=req.getParameter("page");
			od.drop(order_id);
			resp.sendRedirect("order?action=check&page="+order_page);
			
		}else if(action.equals("delete2")){			//买家删除订单
			
			boolean b=true;
			String order_id=req.getParameter("order_id");
			String user_name=req.getParameter("user_name");
			String order_page=req.getParameter("page");
			b=b&&od.drop(order_id);
			if(b){
				req.setAttribute("user_name",user_name);
				req.setAttribute("page",order_page);
				req.getRequestDispatcher("order?action=check3").forward(req, resp);
			}
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
