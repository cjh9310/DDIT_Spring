package com.jsp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


public class TestFilter3 implements Filter {


    public TestFilter3() {

    }


	public void destroy() {
		System.out.println("destory method3");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("doFilter method3");
		
		request.setAttribute("message", "지영누님... 염색어그로 굿");
		
		chain.doFilter(request, response);
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init method3");
	}

}
