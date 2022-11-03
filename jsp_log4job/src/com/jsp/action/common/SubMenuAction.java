package com.jsp.action.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.JSONViewResolver;
import com.jsp.dto.Menu2VO;
import com.jsp.dto.MenuVO;
import com.jsp.service.MenuService;

public class SubMenuAction implements Action {
	
	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;		
		
		String mCode = request.getParameter("mCode");
		
		List<Menu2VO> subMenu =  menuService.getSubMenuList(mCode);
		
		JSONViewResolver.view(response, subMenu);
		
		return url;
	}
	
	
}
