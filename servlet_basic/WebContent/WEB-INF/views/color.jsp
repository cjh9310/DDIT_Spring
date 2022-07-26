<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	body{
		background:<% out.print(request.getParameter("color")); %>;
	}
</style>
</head>
<body>
	<h1>color.jsp</h1>
	
<script>
 <%out.print(request.getParameter("order"));%>("안녕하세요");
 alert("<% out.print(request.getParameter("color")); %>");
</script>
	
</body>
</html>






