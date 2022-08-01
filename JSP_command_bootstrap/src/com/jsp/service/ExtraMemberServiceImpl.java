package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dao.ExtraMemberDAO;
import com.jsp.dto.MemberVO;

public class ExtraMemberServiceImpl implements ExtraMemberService {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	private ExtraMemberDAO extraMemberDAO;
	public void setExtraMemberDAO(ExtraMemberDAO extraMemberDAO) {
		this.extraMemberDAO = extraMemberDAO;
	}

	
	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		return memberService.getMemberList();
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		return memberService.getMember(id);
	}

	@Override
	public void regist(MemberVO member) throws Exception {
		extraMemberDAO.insertMember(member);

	}

	@Override
	public void modify(MemberVO member) throws Exception {
		extraMemberDAO.updateMember(member);


	}

	@Override
	public void remove(String id) throws Exception {
		extraMemberDAO.deleteMember(id);


	}

}
