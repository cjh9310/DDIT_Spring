package com.jsp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/score2")
public class ScoreServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/scoreForm.jsp";
		request.getRequestDispatcher(url).forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url="/scoreResult.jsp";
		
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		int sci = Integer.parseInt(request.getParameter("sci"));
		
		int total = total(kor,eng,math,sci);
		
		request.setAttribute("total", total);
		
		request.getRequestDispatcher(url).forward(request, response);
		
		
		
		
	}
	
	private int total(int kor, int math, int eng, int sci) {
		return kor+math+eng+sci;
	}
	

}








