<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
	<%-- <jsp:include page="/include/header.jsp" />	
	<jsp:include page="/include/aside.jsp" /> --%>
	
	<%@ include file="/include/header.jsp" %>	
	<%@ include file="/include/aside.jsp" %>

	
   	<%
   		pageContext.setAttribute("message","안녕하세요");
   	%> 
    
	<h1>main2.jsp</h1>

	
	<%-- <jsp:include page="/include/footer.jsp" /> --%>
	<%@ include file="/include/footer.jsp" %>