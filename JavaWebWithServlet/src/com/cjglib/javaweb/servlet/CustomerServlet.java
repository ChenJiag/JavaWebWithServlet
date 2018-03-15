package com.cjglib.javaweb.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

import com.cjglib.javaweb.daoimlp.CustomerDAOJdbcImlp;
import com.cjglib.javaweb.domain.CriteriaCustomer;
import com.cjglib.javaweb.domain.Customer;
import com.cjglib.javawebmvc.dao.CustomerDAO;

/**
 * Servlet implementation class CustomerServlet
 */

public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO = new CustomerDAOJdbcImlp();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String method = request.getParameter("method");
	    switch (method) {
	        case "add": add(request,response); break;
	        case "query": query(request,response); break;
	        case "delete": delete(request,response); break;
		}
	}*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String servletPath = request.getServletPath();
	    String methodName = servletPath.substring(1);
	    methodName = methodName.substring(0, methodName.length() - 3);
	    
	    try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request,response);
		} catch (Exception e) {
			response.sendRedirect("error.jsp");
		}
	    
		
	}
	

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		System.out.println("update1");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = 0;
		
		//try的作用是为了防止ID不能转为INT类型。直接转到query.do
		try {
			id = Integer.parseInt(idString);
			customerDAO.delete(id);
		} catch (Exception e) {
			
		}
		response.sendRedirect("query.do");
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		CriteriaCustomer criteriaCustomer = new CriteriaCustomer(name, address, phone);
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(criteriaCustomer);
		request.setAttribute("customer",customers);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		System.out.println("add");
	}

}
