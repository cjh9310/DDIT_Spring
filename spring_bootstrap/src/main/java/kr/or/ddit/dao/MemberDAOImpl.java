package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.command.Criteria;
import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	private SqlSession session;

	public void setSqlSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<MemberVO> selectMemberList(Criteria cri) throws SQLException {

		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<MemberVO> memberList 
				= session.selectList("Member-Mapper.selectMemberList", cri, rowBounds);

		return memberList;

	}

	@Override
	public int selectMemberListCount(Criteria cri) throws SQLException {
		int count = session.selectOne("Member-Mapper.selectMemberListCount", cri);
		return count;

	}

	@Override
	public MemberVO selectMemberById(String id) throws SQLException {

		MemberVO member = session.selectOne("Member-Mapper.selectMemberById", id);
		return member;

	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {

		session.update("Member-Mapper.insertMember", member);

	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		session.update("Member-Mapper.updateMember", member);

	}

	@Override
	public void deleteMember(String id) throws SQLException {
		session.update("Member-Mapper.deleteMember", id);

	}

}
