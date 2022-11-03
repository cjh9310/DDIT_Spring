package com.jsp.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.action.Action;
import com.jsp.command.BoardModifyCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

public class BoardModifyAction implements Action {
	

	private BoardService boardService;

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws Exception{		
		String url = "redirect:/board/detail.do?bno="+request.getParameter("bno");
		
		try {
		BoardModifyCommand modifyReq 
		= HttpRequestParameterAdapter.execute(request, BoardModifyCommand.class);
		
		BoardVO board = modifyReq.toBoardVO();
		
		board.setTitle(HTMLInputFilter.htmlSpecialChars(board.getTitle()));
		
		board.setContent(request.getParameter("content"));
		
		boardService.modify(board);
		}catch(Exception e) {
			e.printStackTrace();
			url = null;
			throw e;
		}			
		
		return url;
	}
}
