package com.jsp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class TestListener implements ServletContextListener { // 우리가 필요한 Context는 ? Event에서 뽑으면 됨.


	// 어플리케이션이 중지될 때 호출된다.
    public void contextDestroyed(ServletContextEvent arg0)  { // 이벤트가 발생하면 디스패처가 이벤트가 발생한 대상까지 합쳐서 보내줌
         // TODO Auto-generated method stub
    }
	// 어플리케이션이 시작될 때 호출된다.
    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext ctx = event.getServletContext();
        String message=ctx.getInitParameter("message");
        System.out.println(message);
    }
	
}
