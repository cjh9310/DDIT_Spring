package com.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/calculatorResult2.jsp";
		request.setAttribute("result", "");
		request.getRequestDispatcher(url).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/calculatorResult2.jsp";
		
		String firstVal = request.getParameter("firstNum");
		String operator = request.getParameter("operator");
		String lastVal = request.getParameter("lastNum");
		
		System.out.println(operator);
		
		int firstNum = Integer.parseInt(firstVal);
		int lastNum = Integer.parseInt(lastVal);
		
		int result = calculate(firstNum, lastNum, operator);
		
		String result2 = "{\"result\":\"" + result + "\"}";
		
		
		PrintWriter out = response.getWriter();
		out.print(result2);
		
		
		
	}
	
	private int calculate(int firstNum, int lastNum, String operator) {
		
		int resultNum = 0;
		
		switch (operator) {
		case "103":
			resultNum = firstNum / lastNum;
			break;
		case "102":
			resultNum = firstNum * lastNum;
			break;
		case "101":
			resultNum = firstNum - lastNum;
			break;
		case "100":
			resultNum = firstNum + lastNum;
			break;
		}
		return resultNum;
	}
		
}
