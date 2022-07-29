package com.jsp.dao;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

public interface ExtraMemberDAO extends MemberDAO{

	// 회원 추가
	public void insertMember(MemberVO member) throws SQLException;
	
	// 회원 수정
	public void updateMember(MemberVO member) throws SQLException;
	
	// 회원정보 삭제
	public void deleteMember(String id) throws SQLException;
}
