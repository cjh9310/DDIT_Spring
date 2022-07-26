<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>                      
<body>
	<ul>
		<li>국어 : <%= request.getParameter("kor") %></li>
		<li>영어 : <%= request.getParameter("eng") %></li>
		<li>수학 : <%= request.getParameter("math") %></li>
		<li>과학 : <%= request.getParameter("sci") %></li>
		<li>총점 : <%= request.getAttribute("total") %>		
		<li>평균 : <%= (int)request.getAttribute("total")/4f %>
	</ul>
</body>
</html>