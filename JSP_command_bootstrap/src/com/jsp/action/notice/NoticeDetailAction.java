package com.jsp.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeDetailAction implements Action {
	
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/notice/detail";
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		// 리스트에서 상세보기 페이지 오픈
		// 상세보기 페이지는 리스트에서 왔음을 알리는 주소줄이 남아있음
		// 새로고침을해도 리스트에서 오픈되었다고 뜸
		// 문제점? 일단 조회수가 계속 올라감 
		// 해결법 : url을 재요청시켜서,  리스트에서 가져오는 url과 바꿔줌
		// 눈으로 보이는 차이점은?? from=list라는 url이 사라지고 조회수가 오르지 않음.
		// ex) */notice/detail.do?from=list&nno=49  => */notice/detail.do?nno=49

		// getNotice()는 조회수를 오르게 하고   , getNoticeFormModify()는 조회수가 오르지 않음
		
		String from = request.getParameter("from");
		
		NoticeVO notice = null;
		
		
		if (from != null && from.equals("list")) {
			notice = noticeService.getNotice(nno);
			url="redirect:/notice/detail.do?nno="+nno;
		}else {
			notice = noticeService.getNoticeForModify(nno);
		}
		
		
		request.setAttribute("notice", notice);
		
		return url;
	}

}
