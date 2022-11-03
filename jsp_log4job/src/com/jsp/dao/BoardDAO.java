package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.dto.BoardVO;

public interface BoardDAO {
	

	List<BoardVO> selectBoardCriteria(SqlSession session,Criteria cri) throws SQLException;

	int selectBoardCriteriaTotalCount(SqlSession session,Criteria cri) throws SQLException;

	BoardVO selectBoardByBno(SqlSession session,int bno) throws SQLException;

	void insertBoard(SqlSession session,BoardVO board) throws SQLException;

	void updateBoard(SqlSession session,BoardVO board) throws SQLException;

	void deleteBoard(SqlSession session,int bno) throws SQLException;

	// viewcnt 증가
	void increaseViewCnt(SqlSession session,int bno) throws SQLException;

	// board_seq.nextval 가져오기
	int selectBoardSeqNext(SqlSession session) throws SQLException;
}
