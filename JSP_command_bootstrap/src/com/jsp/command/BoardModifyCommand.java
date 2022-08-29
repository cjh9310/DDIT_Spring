package com.jsp.command;

import java.util.Date;

import com.jsp.dto.BoardVO;

public class BoardModifyCommand {
	
	private String bno;          // 게시판번호
	private String title;
	private String writer;
	private String content;
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public BoardVO toBoardVO() {
		BoardVO board = new BoardVO();
		
		board.setBno(Integer.parseInt(bno));
		board.setContent(content);
		board.setTitle(title);
		board.setWriter(writer);
		board.setRegDate(new Date());
		board.setViewcnt(0);
		board.setUpdatedate(new Date());
		
		return board;
		
	}
	
}











