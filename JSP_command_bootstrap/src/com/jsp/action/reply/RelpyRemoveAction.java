package com.jsp.action.reply;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.command.ReplyRemoveCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.service.ReplyService;

public class RelpyRemoveAction implements Action {

	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		ReplyRemoveCommand removeCMD = HttpRequestParameterAdapter.execute(request, ReplyRemoveCommand.class);
		
		replyService.removeReply(removeCMD.getRno());
		
		//page number
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(new Criteria());
		pageMaker.setTotalCount(replyService.getReplyListCount(removeCMD.getBno()));
		
		int realEndPage = pageMaker.getRealEndPage();
		
		int page = removeCMD.getPage();
		if (page > realEndPage) {
			page = realEndPage;
		}
		PrintWriter out = response.getWriter();
		out.print(page);
		out.close();
		
		return url;
	}

}
