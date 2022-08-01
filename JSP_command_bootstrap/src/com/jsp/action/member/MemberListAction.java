package com.jsp.action.member;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.context.ApplicationContext;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberListAction implements Action {
	
	private MemberService service;
	public void setMemberService(MemberService service) {
		this.service = service;
	}
	

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/member/list";

		List<MemberVO> memberList;
		try {
			memberList = service.getMemberList();
			request.setAttribute("memberList", memberList);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "/error/500";
		}

		return url;
	}

}
