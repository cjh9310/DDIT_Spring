package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.Menu2VO;
import com.jsp.dto.MenuVO;

public interface MenuDAO {
	
	// 메인메뉴
	List<Menu2VO> selectMainMenu(SqlSession session) throws SQLException;

	// 서브메뉴
	List<Menu2VO> selectSubMenu(SqlSession session, String mCode) throws SQLException;

	// 메뉴정보
	Menu2VO selectMenuByMcode(SqlSession session, String mCode) throws SQLException;
	Menu2VO selectMenuByMname(SqlSession session, String mName) throws SQLException;
	
}








