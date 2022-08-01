package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.MemberVO;

public class ExtraMemberDAOImpl implements ExtraMemberDAO {

	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<MemberVO> selectMemberList() throws SQLException {
		return memberDAO.selectMemberList();
	}

	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		return memberDAO.selectMemberById(id);
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.update("Member-Mapper.insertMember", member);
		} catch (Exception e) {
			// 에러처리
			if (e instanceof SQLException)
				throw e;
		} finally {
			if (session != null)
				session.close();
		}

	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.update("Member-Mapper.updateMember",member);
		} catch (Exception e) {
			// 에러처리
			if (e instanceof SQLException)
				throw e;
		} finally {
			if (session != null)
				session.close();
		}

	}

	@Override
	public void deleteMember(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.update("Member-Mapper.deleteMember",id);		
		} catch (Exception e) {
			// 에러처리
			if (e instanceof SQLException)
				throw e;
		} finally {
			if (session != null)
				session.close();
		}
	}

}
