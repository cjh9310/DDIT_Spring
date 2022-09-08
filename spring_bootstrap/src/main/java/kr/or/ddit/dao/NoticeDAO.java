package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.dto.NoticeVO;

public interface NoticeDAO {
	

	List<NoticeVO> selectSearchNoticeList(Criteria cri) throws SQLException;

	int selectSearchNoticeListCount(Criteria cri) throws SQLException;
	
	NoticeVO selectNoticeByNno(int nno) throws SQLException;
	
	// viewcnt 증가
	void increaseViewCount(int nno) throws SQLException;

	// Notice_seq.nextval 가져오기
	int selectNoticeSequenceNextValue() throws SQLException;
	
	void insertNotice(NoticeVO Notice) throws SQLException;
	
	void updateNotice(NoticeVO Notice) throws SQLException;

	void deleteNotice(int nno) throws SQLException;
}
