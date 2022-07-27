package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.MemberVO;

public interface MemberService {
	
	//회원목록조회
	List<MemberVO> getMemberList()throws SQLException;
	
	//회원상세조회
	MemberVO getMember(String id)throws SQLException;
}
