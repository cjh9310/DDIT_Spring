package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.dto.NoticeVO;
import com.jsp.dao.NoticeDAO;

public class NoticeDAOImpl implements kr.or.ddit.dao.NoticeDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	private NoticeDAO noticeDAO;
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	@Override
	public List<NoticeVO> selectSearchNoticeList(Criteria cri) throws SQLException {		
		return noticeDAO.selectSearchNoticeList(session, cri);
	}

	@Override
	public int selectSearchNoticeListCount(Criteria cri) throws SQLException {		
		return noticeDAO.selectSearchNoticeListCount(session, cri);
	}

	@Override
	public NoticeVO selectNoticeByNno(int nno) throws SQLException {
		return noticeDAO.selectNoticeByNno(session, nno);
	}

	@Override
	public void increaseViewCount(int nno) throws SQLException {
		noticeDAO.increaseViewCount(session, nno);
		
	}

	@Override
	public int selectNoticeSequenceNextValue() throws SQLException {		
		return noticeDAO.selectNoticeSequenceNextValue(session);
	}

	@Override
	public void insertNotice(NoticeVO notice) throws SQLException {
		noticeDAO.insertNotice(session, notice);
		
	}

	@Override
	public void updateNotice(NoticeVO notice) throws SQLException {
		noticeDAO.updateNotice(session, notice);
		
		
	}

	@Override
	public void deleteNotice(int nno) throws SQLException {
		noticeDAO.deleteNotice(session, nno);
		
	}

}
