package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.Menu2VO;

public class MenuDAOImpl implements MenuDAO {
	
	@Override
	public List<Menu2VO> selectMainMenu(SqlSession session) throws SQLException {
		List<Menu2VO> menuList = session.selectList("Menu-Mapper.selectMainMenu");
		return menuList;
	}

	@Override
	public List<Menu2VO> selectSubMenu(SqlSession session, String mCode) throws SQLException {
		List<Menu2VO> menuList = session.selectList("Menu-Mapper.selectSubMenu",mCode);
		return menuList;
	}

	@Override
	public Menu2VO selectMenuByMcode(SqlSession session, String mCode) throws SQLException {
		Menu2VO menu = session.selectOne("Menu-Mapper.selectMenuByMcode",mCode);
		return menu;
	}
	
	@Override
	public Menu2VO selectMenuByMname(SqlSession session, String mName) throws SQLException {
		Menu2VO menu = session.selectOne("Menu-Mapper.selectMenuByMname",mName);
		return menu;
	}
}
