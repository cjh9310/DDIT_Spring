<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><decorator:title default="kim's system" /></title>

<link  rel="stylesheet" href="<%=request.getContextPath()%>/resources/main.css" />
<decorator:head />

</head>
<body>

	<div id="header">
		<h1>header.jsp</h1>
			<h1>message:${message }</h1>	
	</div>
	<hr/>
