package com.jsp.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.controller.JSPViewResolver;
import com.jsp.dto.MemberVO;



public class LoginUserCheckFilter implements Filter {
	
	private List<String> exURLs = new ArrayList<String>(); // ArrayList는 web.xml에 있는 login,logout를 담음

	public void init(FilterConfig fConfig) throws ServletException {
		String excludeURLNames = fConfig.getInitParameter("exclude");  // web.xml에 사용함
		                             //  exclude로 로그인을 안해도 들어갈 수 있는 페이지를 설정함
		StringTokenizer st = new StringTokenizer(excludeURLNames, ","); // StringTokenizer substring와 비슷함.
		// excludeURLNames에 login,logout가 들어가 있는데 ,로 나눠줌
		while (st.hasMoreTokens()) {
			exURLs.add(st.nextToken().trim());  // 띄워쓰기(공백) 방지
		}
	}
 
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		// 제외할 url 확인
		// 글자를 왜 자르는가 uri 경로는 /부터 가져와서 contextPath부분은 필요없어 그 부분은 자른다.
		String reqUrl = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
		                                                  //httpReq.getContextPath() = http://localhost/JSP_command_bootstrap
//		System.out.println(reqUrl);
		// /member/list.do
		// /common/loginForm.do 가 출력된다.
		
		if(excludeCheck(reqUrl)) {  // 체크를 할 때 메소드 실행   
			// excludeCheck(reqUrl)가 false가 나오면 login check를 함
			System.out.println("reqUrl이 되면 실행됨");
			chain.doFilter(request, response); 
			
			return; // return하면 excludeCheck메소드가 실행이 안됨
		}
		
		// login check 세션에서 로그인 체크
		HttpSession session = httpReq.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		// login 확인
		if(loginUser==null) { //비로그인 상태 
			String contextPath = httpReq.getContextPath();
			String retUrl = httpReq.getRequestURI().replace(contextPath,"");
			
			String queryString = httpReq.getQueryString();
			if(queryString != null) {
				retUrl += "?" + URLEncoder.encode(queryString,"utf-8");
			}
			
			httpReq.setAttribute("viewName", "redirect:/common/loginForm.do?eeror=-1&retUrl="+retUrl);
			// error= -1 이 LoginFormAction 에서 LoginUserCheckFilter의 setAttribute를 실행시켰음을 인식시켜줌
			JSPViewResolver.view(httpReq, httpResp);
		}else { // 로그인 아이디가 있으면 
			chain.doFilter(request, response);
		}
		
		
	}
	// 
	private boolean excludeCheck(String url) { // url = reqUrl

		boolean result = false;  
		
		result = result || url.length() <= 1; // 1보다 작거나 같으면 true가 나옴(구하는 기준은 http://localhost/JSP_command_bootstrap뒤에 , url.length() <= 1는 로그인 없이 열 수 있어야함
		// url.length() <= 1;는 로그인 되어있으면 꼭 list.do까지 치지 않아도 자동으로 list.do가 출력되게 해줌 단 공백이거나 / 만 가능 애매하게 친 것은 안됨
		// or면  하나가 true면 true가 됨 true가 나오면 제외함 제외를 한다는 것은 로그인 체크를 안해도 됨
		
		for(String exURL : exURLs) { // 위에 List에서 하나씩 꺼내서 검사하는데 여기서 걸리면 
			result = result || url.contains(exURL);//contains는 web.xml에 있는 login,logout
		}
		
		System.out.println(result);
		return result;
	}
	
	
	public void destroy() {

	}
}
