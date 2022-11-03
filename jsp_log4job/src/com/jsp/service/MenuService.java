package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.Menu2VO;
import com.jsp.dto.MenuVO;

public interface MenuService {
	

	List<Menu2VO> getMainMenuList()throws SQLException;
	
	List<Menu2VO> getSubMenuList(String mCode)throws SQLException;
	
	Menu2VO getMenuByMcode(String mCode)throws SQLException;
	
	Menu2VO getMenuByMname(String mName)throws SQLException;
}







