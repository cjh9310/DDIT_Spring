package com.jsp.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.NoticeModifyCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeModifyAction implements Action {
	

	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "redirect:/notice/detail.do?nno="+request.getParameter("nno");
		
		NoticeModifyCommand noticeCMD 
			= HttpRequestParameterAdapter.execute(request, NoticeModifyCommand.class);
		
		NoticeVO notice = noticeCMD.toNoticeVO();
		
		noticeService.modify(notice);
		
		return url;
	}

}









