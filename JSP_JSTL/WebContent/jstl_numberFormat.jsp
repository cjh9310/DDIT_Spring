<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int price = 1000000;
		String won = "￦"+ new DecimalFormat("#,###").format(price);
		out.print(won);
	
	%>
	<hr/>
	
	<c:set var = "price" value="<%=price %>" />
	￦<fmt:formatNumber pattern = "#,###" value="${price }" />


</body>
</html>