package com.jsp.service;

import com.jsp.dto.MemberVO;

public interface ExtraMemberService extends MemberService{

	
	// 회원등록
	public void regist(MemberVO member) throws Exception;
	
	// 회원수정
	public void modify(MemberVO member) throws Exception;
		
	// 회원탈퇴
	public void remove(String id) throws Exception;
}
