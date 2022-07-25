package com.java.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.java.datasource.JDBCDataSource;
import com.java.dto.MemberVO;


public class TestJDBCDataSourece {
	
	private Connection conn;
	
	@Before
	public void init()throws Exception {
		JDBCDataSource dataSource = JDBCDataSource.getInstance();	
		conn = dataSource.getConnection();
	}
	
	@Test
	public void selectMember()throws Exception {
		String testID = "mimi";
		
		String sql = "select * " + " from member" + " where id=?";

		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, testID);
		ResultSet rs = ptmt.executeQuery();

		MemberVO member = new MemberVO();
		if (rs.next()) {
			member.setId(rs.getString("id"));			
		}
		
		Assert.assertEquals(testID, member.getId());
	}
	
	
	@After
	public void complete()throws Exception {
		if(conn!=null) conn.close();
	}
}










