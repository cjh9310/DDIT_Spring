package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.command.Criteria;
import com.jsp.dto.ReplyVO;

public interface ReplyDAO {
	
	void insertReply( ReplyVO reply) throws SQLException;
	void updateReply( ReplyVO reply) throws SQLException;
	void deleteReply( int rno) throws SQLException;
	
	int selectReplySeqNextValue() throws SQLException;
	List<ReplyVO> selectReplyListPage( int bno, Criteria cri) throws SQLException;
	
	int countReply( int bno) throws SQLException;
}
