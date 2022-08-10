package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;

public interface MemberService {
	
	//회원목록조회
	Map<String,Object> getMemberList(Criteria cri)throws SQLException;
	
	//회원상세조회
	public MemberVO getMember(String id)throws SQLException;

	//회원등록
	public void regist(MemberVO member) throws Exception;
	
	//회원수정
	public void modify(MemberVO member)throws Exception;
	
	//회원탈퇴
	public void remove(String id)throws Exception; 
}
