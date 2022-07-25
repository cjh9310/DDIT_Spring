package com.java.datasource;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestOracleMybatisSqlSessionFactory {

	private OracleMybatisSqlSessionFactory factory = new OracleMybatisSqlSessionFactory();
	
	private SqlSession session;
	
	@Before
	public void initSqlSession() {
		session = factory.openSession();
	}
	@After
	public void closeSqlSession() {
		if (session!=null)session.close();
	}
	
	@Test
	public void testSqlSession()throws SQLException{
		Collection<String> mapNames=
		(Collection<String>)session.getConfiguration().getMappedStatementNames();
		
		Assert.assertTrue(mapNames.contains("Member-Mapper.selectMemberList"));
	}
	
}







