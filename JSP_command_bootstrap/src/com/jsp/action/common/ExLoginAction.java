package com.jsp.action.common;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIdException;
import com.jsp.service.MemberService;

public class ExLoginAction implements Action {
	
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
		String retUrl = request.getParameter("retUrl");
		
		if(retUrl!=null) url = "redirect:" + URLDecoder.decode(retUrl, "utf-8") ;
		
		try {
			memberService.login(id, pwd);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", memberService.getMember(id));
			session.setMaxInactiveInterval(100); //일정 대기시간이 되면 로그아웃됨(지금은 로그아웃 기능이 없어서 접근금지만)
		
		} catch (NotFoundIdException | InvalidPasswordException e) { //아이디가 틀렸는지 비번이 틀렸는지 판단은 memberServiceimpl
			//e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			url = "/common/login_fail";
			
		} catch (Exception e) {
			e.printStackTrace();
			//Exceptin 처리
			throw e;
		}
		
		
		return url;
	}

}
