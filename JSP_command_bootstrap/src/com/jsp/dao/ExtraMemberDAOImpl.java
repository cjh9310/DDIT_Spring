package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.MemberVO;

public class ExtraMemberDAOImpl extends MemberDAOImpl  
								implements ExtraMemberDAO{

	@Override
	public List<MemberVO> selectMemberList() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMember(String id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
