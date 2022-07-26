<%@page import="com.jsp.dto.MemberVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO member = new MemberVO();
		pageContext.setAttribute("member", 1);
	Map<String,String> map  = new HashMap<String,String>();
		pageContext.setAttribute("key", "ket");
		
	
	
%>
    
    
<%		// 아래 4 가지를 하나씩 주석처리하면서 실행해볼 것  sessionScope.를 뺴면
	pageContext.setAttribute("message", "pageContext message");    
	request.setAttribute("message","request message");
	session.setAttribute("message","session message");
	application.setAttribute("message","application message");
	
	pageContext.setAttribute("num1", 1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>attribute message : ${sessionScope.message }</h1> <%--  getAttribute영역 --%> 
	<h1>parameter message : ${param.message }</h1>    <%-- 파라미터 영역 -->  <!--  ?message=parameter를 웹에 입력해 볼 것 --%>
	
	<ul>// 연산자
		<li> 산술연산 : ${num+1}</li>
		<li> 문자열 더하기 : ${num}${message}</li>
		<li> 비교연산 : ${num ge 3} </li>    <%--  ge => great equals -->  <!--  num >= 3  왼쪽 기준으로 --%>
		<li> 유무연산 : ${empty num}, ${not empty requestScope.member}</li>
		<%--           없냐?  num 있음 => false ,   requestScope.member에 empty가 있냐? member 없음 => false --%>
	</ul>
	
	<ul> // 메소드 호출
		<li>${str }</li> <%-- out.println(pageContext.getAttribute("str")); --%>
		<li>일반메소드 호출 : ${str.split(",")[2] }
		<li>아이디 : ${member.getIdnPwd()}</li>
		<li>아이디 : ${member.idnPwd }</li>
			
		<li>Map : ${map.get("key") }</li>
		<li>Map : ${map.key }</li>
		
		<li>List : ${list.get(0) }</li>
		<li>List : ${list[0] }</li>
	</ul>
	
</body>
</html>
