<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<%
		int k =5;
		if(k>3) {
			out.print("true");
		}
	%>
	
	<hr/>
	
	<c:set var="k" value="<%=k %>"/>  <%-- 스크립트 영역에 있는 k=5를  가져왔음--%>   <%--var은 무조건 pageContext --%>
	<c:if test="${k>3 }" >
		true
	</c:if>
</body>
</html>