<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String str ="1,2,3,4,5";
	StringTokenizer token = new StringTokenizer(str,",");
	while(token.hasMoreTokens()){
		out.println(token.nextToken()+"<br/>");
	}
	%>
	<hr/>
	
	<c:set var = "str" value="<%=str %>" />
	<c:forTokens items="${str }" delims="," var="token">
		${token } <br/>
	</c:forTokens>
	
	
</body>
</html>