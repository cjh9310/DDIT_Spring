package com.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
										HttpServletResponse response,
										Authentication auth) throws ServletException, IOException {
		
		String login_id = (String) auth.getName(); // 로그인 시도한 ID를 가져온다
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", login_id);

		
		super.onAuthenticationSuccess(request, response, auth);
	}

	
}
