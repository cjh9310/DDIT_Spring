package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.command.Criteria;
import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;

public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws SQLException {
		List<MemberVO> memberList = memberDAO.selectMemberList(cri);
		return memberList;
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		MemberVO member = memberDAO.selectMemberById(id);
		return member;
	}
	
	@Override
	public void regist(MemberVO member) throws Exception {
		memberDAO.insertMember(member);
		
	}

	@Override
	public void modify(MemberVO member) throws Exception {
		memberDAO.updateMember(member);		
	}

	@Override
	public void remove(String id) throws Exception {
		memberDAO.deleteMember(id);		
	}

}
