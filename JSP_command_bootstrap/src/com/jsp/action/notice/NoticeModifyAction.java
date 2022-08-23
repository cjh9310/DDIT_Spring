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
	// 보통은 _success 후 상세보기로 가지만 이번에는 바로 보내보기. (redirect: 활용)
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "redirect:/notice/detail.do?nno="+request.getParameter("nno");
		
		// HttpRequestParameterAdapter때문에 실행이 안됨.. nno가 db에서 integer라 안된다고 한다.
		// HttpRequestParameterAdapter는 아마 스트링만 실행되는듯.
		// 해결법. NoticeModifyCommand에서 nno를 string으로 줌
//		NoticeVO notice = HttpRequestParameterAdapter.execute(request, NoticeVO.class);
		NoticeModifyCommand noticeCMD
			= HttpRequestParameterAdapter.execute(request, NoticeModifyCommand.class);
		
		NoticeVO notice = noticeCMD.toNoticeVO();
		
		noticeService.modify(notice);
		return url;
	}

}
