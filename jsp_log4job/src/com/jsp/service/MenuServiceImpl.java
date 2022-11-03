package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.MenuDAO;
import com.jsp.dto.Menu2VO;
import com.jsp.dto.MenuVO;

public class MenuServiceImpl implements MenuService{
	
	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	private MenuDAO menuDAO;
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	
	
	@Override
	public List<Menu2VO> getMainMenuList() throws SQLException {

		List<Menu2VO> menuList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			menuList = menuDAO.selectMainMenu(session);
		} finally {
			session.close();
		}

		return menuList;
	}

	@Override
	public List<Menu2VO> getSubMenuList(String mCode) throws SQLException {

		List<Menu2VO> menuList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			menuList = menuDAO.selectSubMenu(session, mCode);
		} finally {
			session.close();
		}

		return menuList;
	}

	@Override
	public Menu2VO getMenuByMcode(String mCode) throws SQLException {
		Menu2VO menu = null;

		SqlSession session = sqlSessionFactory.openSession();
		try {
			menu = menuDAO.selectMenuByMcode(session, mCode);
		} finally {
			session.close();
		}

		return menu;
	}

	@Override
	public Menu2VO getMenuByMname(String mName) throws SQLException {
		Menu2VO menu = null;

		SqlSession session = sqlSessionFactory.openSession();
		try {
			menu = menuDAO.selectMenuByMname(session, mName);
		} finally {
			session.close();
		}

		return menu;
	}
	
}
