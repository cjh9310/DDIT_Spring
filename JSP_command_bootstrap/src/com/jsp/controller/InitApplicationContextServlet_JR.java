package com.jsp.controller;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.jsp.context.ApplicationContext;
import com.jsp.dao.MemberDAO;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.datasource.OracleMybatisSqlSessionFactory;
import com.jsp.service.MemberServiceImpl;

public class InitApplicationContextServlet_JR extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// System.out.println("init process....");

		Map<String, Object> map = ApplicationContext.getApplicationContext();
		MemberServiceImpl memberService = new MemberServiceImpl();
		MemberDAOImpl memberDAO = (MemberDAOImpl) new MemberDAOImpl();
		OracleMybatisSqlSessionFactory sqlSessionFactory = new OracleMybatisSqlSessionFactory();


		memberService.setMemberDAO(memberDAO);


		memberDAO.setSqlSessionFactory(sqlSessionFactory);

	}

}
