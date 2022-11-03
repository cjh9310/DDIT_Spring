package kr.or.ddit.dao;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

public interface MemberDAO extends com.jsp.dao.MemberDAO{
	
	MemberVO selectMemberByPicture(String picture) throws SQLException;
}
