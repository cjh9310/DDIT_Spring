<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" >
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>등록일</th>
		</tr>
		<c:forEach items="${memberList }" var="member" >
		<tr>
			<td>${member.id }</td>
			<td>${member.name }</td>
			<td>${member.phone }</td>
			<td>${member.email }</td>
			<td><fmt:formatDate value="${member.regDate }" pattern="yyyy-MM-dd" /></td>		
		</tr>		
		</c:forEach>
		
		
	</table>
</body>
</html>







