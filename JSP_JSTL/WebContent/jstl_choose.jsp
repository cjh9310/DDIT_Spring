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

<c:set var="k" value="90"/> 
	<c:choose>
		<c:when test = "${k ge 90 }"/>
			A
		<c:when test = "${k ge 80 }"/>
			B
		<c:when test = "${k ge 70 }"/>
			C
		<c:when test = "${k ge 60 }"/>
			D
		<c:otherwewise>
			F
		</c:otherwewise>
	</c:choose>
</body>
</html>