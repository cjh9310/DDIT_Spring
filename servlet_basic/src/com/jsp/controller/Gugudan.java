package com.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gugudan")
public class Gugudan extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String danStr = request.getParameter("dan");
		String gopStr = request.getParameter("gop");
		
		int danNum = Integer.parseInt(danStr);
		int gopNum = Integer.parseInt(gopStr);		
				
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		for(int dan=2;dan<danNum+1;dan++) {
			//System.out.println(dan+"단");
			out.println(dan+"단<br/>");
			for(int gop=1;gop<gopNum+1;gop++) {
				out.println(dan + " * " + gop + " = " + (dan * gop)+"<br/>");
			}
			out.println("<br/>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
