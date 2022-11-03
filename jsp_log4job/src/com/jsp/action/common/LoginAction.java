package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class LoginAction implements Action{

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="redirect:/member/list.do";
		
		//입력
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberVO member = memberService.getMember(id);
		
		if(member!=null) { // 아이디 존재
			if(member.getPwd().equals(pwd)) { //로그인 성공
				
				//세션처리
				HttpSession session = request.getSession();
				session.setAttribute("loginUser",member);
				session.setMaxInactiveInterval(5);
				
				return url;
			}else { //패스워드 불일치
				url="/common/login_fail";
				request.setAttribute("msg", "패스워드가 일치하지 않습니다.");
			}
		}else { // 아이디 불일치
			url="/common/login_fail";
			request.setAttribute("msg", "아이디가 일치하지 않습니다.");
		}
		
		return url;
	}

}










