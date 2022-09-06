package com.spring.dataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.dto.MemberVO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/dataSource-context.xml")
@Transactional
public class TestSqlSession {
	
	
	@Autowired
	private SqlSession session;
	
	@Test
	public void testGetMember()throws Exception{
		String testID = "mimi";
		
		MemberVO member = session.selectOne("Member-Mapper.selectMemberById",testID);
		
		Assert.assertNotNull(member);
		Assert.assertEquals("mimi", member.getId());
	}
	
	@Test
	@Rollback
	public void testInsertMember()throws Exception{
		MemberVO member = new MemberVO();
		member.setId("tototo");
		member.setPwd("tototo");
		member.setEmail("tototo");
		member.setName("tototo");
		member.setPhone("000-0000-1111");
		member.setPicture("noimage.jpg");
		member.setAuthority("ROLE_USER");
		
		
		session.update("Member-Mapper.insertMember",member);
		
		MemberVO result
			= session.selectOne("Member-Mapper.selectMemberById",member.getId());
		
		
		Assert.assertNotNull(result);
	}
	
}






