package com.jsp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.MemberVO;

@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/WEB-INF/views/member/list.jsp";
		
		
		//처리 : service
		List<MemberVO> memberList = new ArrayList<MemberVO>();		
		for(int i=0;i<100;i++) {
			memberList.add(new MemberVO("mimi"+i,"mimi"+i));
		}
				
		//출력 : attribute
		request.setAttribute("memberList",memberList);
		
		//화면
		request.getRequestDispatcher(url).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
