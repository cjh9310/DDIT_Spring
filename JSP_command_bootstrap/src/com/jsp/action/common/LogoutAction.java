package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;

public class LogoutAction implements Action {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "redirect:/";    // 아무것도 안주면 index.jsp로 간다.
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return url;
	}

}
