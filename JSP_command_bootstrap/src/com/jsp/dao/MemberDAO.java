package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;

public interface MemberDAO {
	
	List<MemberVO> selectMemberList(Criteria cri)throws SQLException;
	int selectMemberListCount(Criteria cri)throws SQLException;
	
	MemberVO selectMemberById(String id)throws SQLException;
	
	
	// 회원 추가
	public void insertMember(MemberVO member) throws SQLException;
	
	// 회원 수정
	public void updateMember(MemberVO member) throws SQLException;
	
	// 회원정보 삭제
	void deleteMember(String id) throws SQLException;

}	
