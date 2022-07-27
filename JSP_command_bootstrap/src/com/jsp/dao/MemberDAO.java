package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.MemberVO;

public interface MemberDAO {
	
	List<MemberVO> selectMemberList()throws SQLException;
	MemberVO selectMemberById(String id)throws SQLException;
	
}
