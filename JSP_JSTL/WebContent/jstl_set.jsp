<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%--prefix 태그 이름마다 namespace가 있는데 prefix는 namespace 역할을 한다.  c는 코어 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <% pageContext.setAttribute("k",1); %> --%>

<%-- jstl은 선언할때 Attribute영역에서 만들어야 한다.--%>
<%-- 자바처럼 쓰지 않을려고 사용하는 방식 --%>
	<c:set var="k" value="1" scope="page" />
	<c:set var="k" value="abc" scope="request" />
	
	<%-- <c:remove var = "k"  var k에 저장된 값 전부 삭제 --%>
	<c:remove var = "k" scope="page"/> <%-- var k에 지정된 선택삭제 --%> 
	
	//remove after
	<h1>k = ${k }</h1>
	<h1>request scope : k = ${requestScope.k }</h1>   <%-- Scope 소문자는 안됨 --%>

</body>
</html>