package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.command.Criteria;
import com.jsp.dto.BoardVO;

public interface BoardDAO {
	
	List<BoardVO> selectBoardCriteria( Criteria cri) throws SQLException;
	
	int selectBoardCriteriaTotalCount( Criteria cri) throws SQLException;
	
	BoardVO selectBoardByBno ( int bno) throws SQLException;
	
	void insertBoard( BoardVO board) throws SQLException;
	
	void updateBoard( BoardVO board) throws SQLException;
	
	void deleteBoard( int bno) throws SQLException;

	
	void increaseViewCnt( int bno) throws SQLException;
	
	int selectBoardSeqNext() throws SQLException;
}
