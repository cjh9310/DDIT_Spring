package com.jsp.action.common;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.Criteria;
import com.jsp.command.CriteriaCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.dto.Menu2VO;
import com.jsp.dto.MenuVO;
import com.jsp.service.BoardService;
import com.jsp.service.MemberService;
import com.jsp.service.MenuService;
import com.jsp.service.NoticeService;
import com.jsp.service.PdsService;

public class IndexPageAction implements Action {

	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	private MemberService service;
	public void setMemberService(MemberService service) {
		this.service = service;
	}
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/common/indexPage";
		
		String mCode = request.getParameter("mCode");
		
		if(mCode ==null) mCode="M000000";
		
		try {
			//GNB
			List<Menu2VO> menuList = menuService.getMainMenuList();			
			request.setAttribute("menuList", menuList);
			List<Menu2VO> subMenuList = menuService.getSubMenuList(mCode);
			request.setAttribute("subMenuList", subMenuList);
			
			// iframe 상태유지
			Menu2VO menu = menuService.getMenuByMcode(mCode);
			request.setAttribute("menu", menu);
			
			CriteriaCommand criCMD
			=HttpRequestParameterAdapter.execute(request, CriteriaCommand.class);

			Criteria cri = criCMD.toCriteria();		

			Map<String, Object> boardMap = boardService.getBoardList(cri);
			request.setAttribute("boardMap", boardMap);
			Map<String,Object> memberMap = service.getMemberList(cri);
			request.setAttribute("memberMap", memberMap);	
			Map<String,Object> noticeMap = noticeService.getNoticeList(cri);
			request.setAttribute("noticeMap",noticeMap);
			Map<String, Object> pdsMap = pdsService.getList(cri);
			request.setAttribute("pdsMap", pdsMap);
			
		} catch (SQLException e) {
			e.printStackTrace();
			// Exception 처리 : log 기록...
			throw e;
		}
		
		return url;
	}
}










