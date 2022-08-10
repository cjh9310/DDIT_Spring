package com.jsp.action.member;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberDetailAction implements Action {
	
	// DB에서 가져오려고
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// url => 새로운 페이지를 만들기 위해
		String url = "/member/detail";
		
		
		String id = request.getParameter("id");
		
			try {
				MemberVO member = memberService.getMember(id);
				
				request.setAttribute("member", member);
				
			}catch(Exception e) {
				e.printStackTrace();
				
				url= "/member/detail_fail";
				// response.sendError(response.SC_INTERNAL_SERVER_ERROR);
				// 경고창을 띄워줌
			}
					

		return url;
	}

}
