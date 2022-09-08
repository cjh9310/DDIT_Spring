package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dao.MenuDAO;
import com.jsp.dto.MenuVO;

public class MenuDAOImpl implements kr.or.ddit.dao.MenuDAO {
	
	private SqlSession session;
	private MenuDAO menuDAO;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	
	
	@Override
	public List<MenuVO> selectMainMenu() throws SQLException {
		return menuDAO.selectMainMenu(session);
	}

	@Override
	public List<MenuVO> selectSubMenu(String mCode) throws SQLException {
		return menuDAO.selectSubMenu(session, mCode);
	}

	@Override
	public MenuVO selectMenuByMcode(String mCode) throws SQLException {
		return menuDAO.selectMenuByMcode(session, mCode);
	}

	@Override
	public MenuVO selectMenuByMname(String mName) throws SQLException {
		return menuDAO.selectMenuByMname(session, mName);
	}
}
