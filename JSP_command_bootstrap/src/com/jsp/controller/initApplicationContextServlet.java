package com.jsp.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.jsp.context.ApplicationContext;
import com.jsp.dao.MemberDAO;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.datasource.OracleMybatisSqlSessionFactory;
import com.jsp.service.MemberServiceImpl;
// 요청해서 처리하는 것이 아니라 doget dopost가 필요없다.
// 인스턴스가 먼저 만들고(Map에 넣어둠 그 다음 꺼내서)조립하는 것    
// 리플렉션형태로 바뀐다.
public class initApplicationContextServlet extends HttpServlet {
// init을 사용하는 이유가 우리가 배운것이 서블릿 밖에 없어서..... 서블릿을 사용할려면 기본적으로 req(요청)이 필요한데 요청없이 
	// 
	@Override
	public void init() throws ServletException {
		//System.out.println("init process....");
		Map<String,Object> map = ApplicationContext.getApplicationContext(); //object는 최상위 클래스라 안에 속해있는 클래스를 모두 담았음
		//ApplicationContext.getApplicationContext();의 내부에는  new HashMap<String, Object>();가 들어있음
		map.put("memberService", new MemberServiceImpl());  // 서블릿이 가장 먼저 만나는 서비스를 호출
		map.put("memberDAO", new MemberDAOImpl());          // 서비스는 DAO를 만나서 DAO 호출
		map.put("sqlSessionFactory", new OracleMybatisSqlSessionFactory());   // 
		
		
		MemberServiceImpl memberService = (MemberServiceImpl)map.get("memberService"); // 서비스랑 다오를 연결해준다(MemberServiceImpl) / memberService메소드를 통해서 연결됨
		MemberDAO memberDAO = (MemberDAO)map.get("memberDAO"); // 캐스팅 한 이유 : 오브젝트로 들어가서 오브젝트로 인식함 그래서 각각의 클래스로 인식하기 위해서
		memberService.setMemberDAO(memberDAO);
		
		MemberDAOImpl memberDAOImpl = (MemberDAOImpl)map.get("memberDAO");
		OracleMybatisSqlSessionFactory sqlSessionFactory 
					= (OracleMybatisSqlSessionFactory)map.get("sqlSessionFactory");
		memberDAOImpl.setSqlSessionFactory(sqlSessionFactory);
		
		
		// doget dopost가 있는 서블릿에 안쓰는 이유는 여러 서블렛이 있으면  이 것들을 다 넣어줘야해서 필요한 서블릿만 가져가서 쓸 수 있게 
		
	}
}
