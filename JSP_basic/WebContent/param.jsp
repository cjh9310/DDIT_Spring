<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	스크립트릿 : <%out.print(request.getAttribute("message"));%> <br/>
	표현식       : <%= request.getAttribute("message") %> <br>
	EL문         : ${message} <br/>
</body>
</html>