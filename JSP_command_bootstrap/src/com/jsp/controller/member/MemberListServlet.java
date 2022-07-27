package com.jsp.controller.member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.context.ApplicationContext;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;


@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	
	private MemberService service;
	{
		Map<String, Object> container = ApplicationContext.getApplicationContext();
		this.service = (MemberService)container.get("memberService");
		//MemberServiceImpl
		//MemberService가 나온 이유는  set메소드를 호출할 필요가 없어서   
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/views/member/list.jsp";
		
		List<MemberVO> memberList;
		try {
			memberList = service.getMemberList();
			request.setAttribute("memberList", memberList);
		} catch(SQLException e) {
			e.printStackTrace();
			url="/WEB-INF/views/error/500.jsp";
		}
		
		
		
		request.getRequestDispatcher(url).forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
