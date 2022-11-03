package com.jsp.action.reply;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.Criteria;
import com.jsp.controller.JSONViewResolver;
import com.jsp.service.ReplyService;

public class ReplyListAction implements Action {
	
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		int bno = Integer.parseInt(request.getParameter("bno"));		
		int page = Integer.parseInt(request.getParameter("page"));
		
		Criteria cri = new Criteria();
		cri.setPage(page);
		
		Map<String, Object> dataMap = replyService.getReplyList(bno, cri);
		
		JSONViewResolver.view(response, dataMap);
		
		return url;
	}
}









