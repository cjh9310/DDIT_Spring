package com.jsp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


public class TestFilter implements Filter {


    public TestFilter() {

    }


	public void destroy() {
		System.out.println("destory method");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("doFilter method");
		
		chain.doFilter(request, response);
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init method");
	}

}
