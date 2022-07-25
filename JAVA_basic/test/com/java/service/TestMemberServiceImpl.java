package com.java.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.java.dao.MemberDAO;
import com.java.dto.MemberVO;



public class TestMemberServiceImpl {

	private MemberServiceImpl memberService;
	private MemberDAO memberDAO;
	
	@Before
	public void init()throws Exception{
		memberService = new MemberServiceImpl();
		memberDAO = new MockMemberDAO();
		memberService.setMemberDAO(memberDAO);
	}
	
	@Test
	public void testGetMemberList()throws SQLException {
		List<MemberVO> memberList = memberService.getMemberList();
		
		Assert.assertEquals(10, memberList.size());
	}
	
	@Test
	public void testGetMember()throws SQLException{
		String testID="mama";
		MemberVO member = memberDAO.selectMemberById(testID);
		
		Assert.assertEquals(testID, member.getId());
	}
}








