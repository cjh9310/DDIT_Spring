package com.jsp.command;

import com.jsp.dto.NoticeVO;

public class NoticeModifyCommand {
	
	private String nno;          // 게시판번호
	private String title="";     // 제목
	private String writer;	  // 작성자 (회원)
	private String content="";   // 내용 (html)
	
	
	public String getNno() {
		return nno;
	}
	public void setNno(String nno) {
		this.nno = nno;
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
	// 여기서 다시 integer로 바꿔주는 중요한 역할을 해주는 곳
	public NoticeVO toNoticeVO() {
		NoticeVO notice = new NoticeVO();
		
		notice.setNno(Integer.parseInt(this.nno));
		notice.setTitle((this.title));
		notice.setContent((this.content));
		notice.setWriter((this.writer));
		
		return notice;
	}
	
}



