package com.jsp.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.action.Action;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

public class BoardRegistAction implements Action {
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String url="/board/regist_success";
		
		BoardVO board = HttpRequestParameterAdapter.execute(request, BoardVO.class);
				
		board.setTitle(HTMLInputFilter.htmlSpecialChars(board.getTitle()));
				
		boardService.regist(board);
		
		return url;
	}
}
