package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.dto.BoardVO;
import com.jsp.dao.BoardDAO;

public class BoardDAOImpl implements kr.or.ddit.dao.BoardDAO{

	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	@Override
	public List<BoardVO> selectBoardCriteria(Criteria cri) throws SQLException {
		return boardDAO.selectBoardCriteria(session,cri);
	}
	
	@Override
	public int selectBoardCriteriaTotalCount(Criteria cri) throws SQLException {
		return boardDAO.selectBoardCriteriaTotalCount(session, cri);
	}
	@Override
	public BoardVO selectBoardByBno(int bno) throws SQLException {
		return boardDAO.selectBoardByBno(session, bno);
	}

	@Override
	public void insertBoard(BoardVO board) throws SQLException {
		boardDAO.insertBoard(session, board);
	}

	@Override
	public void updateBoard(BoardVO board) throws SQLException {
		boardDAO.updateBoard(session, board);
	}

	@Override
	public void deleteBoard(int bno) throws SQLException {
		boardDAO.deleteBoard(session, bno);
	}

	@Override
	public void increaseViewCnt(int bno) throws SQLException {
		boardDAO.increaseViewCnt(session, bno);
	}

	@Override
	public int selectBoardSeqNext() throws SQLException {
		return boardDAO.selectBoardSeqNext(session);
	}





	
}
