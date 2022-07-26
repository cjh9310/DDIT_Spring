package com.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/background/color")
public class BackgroundServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String url = "/WEB-INF/views/colorForm.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
		
		
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		
//		out.println("<!DOCTYPE>");
//		out.println("<html>");
//			out.println("<head>");
//				out.println("<title>");
//					out.print("배경색 바꾸기");
//				out.println("</title>");				
//			out.println("</head>");
//			out.println("<body>");
//				out.println("<form method='post' >");
//					out.println("<input type='text' name='color' />");
//					out.println("<input type='submit' value='변경' />");
//				out.println("</form>");
//			out.println("</body>");		
//		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/WEB-INF/views/color.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
		
//		String color = request.getParameter("color");
		
		
		
		
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		
//		out.println("<!DOCTYPE>");
//		out.println("<html>");
//			out.println("<head>");
//				out.println("<title>");
//					out.print("배경색 바꾸기");
//				out.println("</title>");
//				out.println("<style>");
//					out.println("body{background:"+color+";}");
//				out.println("</style>");
//			out.println("</head>");
//			out.println("<body>");
//			out.println("</body>");		
//		out.println("</html>");
		
	}

}
