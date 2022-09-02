package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.dto.PdsVO;

public interface PdsDAO {
	

	List<PdsVO> selectPdsCriteria(SqlSession session,Criteria cri)	throws SQLException;
	int selectPdsCriteriaTotalCount(SqlSession session,Criteria cri) throws SQLException;
	
	PdsVO selectPdsByPno(SqlSession session,int pno)throws SQLException;
	
	void insertPds(SqlSession session,PdsVO pds)throws SQLException;
	void updatePds(SqlSession session,PdsVO pds)throws SQLException;
	void deletePds(SqlSession session,int pno)throws SQLException;
	
	//viewcnt  증가
	void increaseViewCnt(SqlSession session,int pno)throws SQLException;
	
	//pds_seq.nextval 가져오기
	int selectPdsSeqNext(SqlSession session) throws SQLException;

}
