<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>Spring FileUpload</h1>
	<hr/>
	<h3>MultipartFile 사용</h3>
	<form action="multipartFile" method="post" enctype="multipart/form-data">
		제목 : <input type='text' name="title" /><br/>
		파일 : <input type="file" name='file' /><br/>
		<input type="submit" value="전송" />
	</form>
	
	<hr/>
	<h3>MultipartHttpServletRequest 사용</h3>
	<form action="multipartHttpServletRequest" method="post" enctype="multipart/form-data">
		제목 : <input type='text' name="title" /><br/>
		파일 : <input type="file" name='file' /><br/>
		<input type="submit" value="전송" />
	</form>
	
	<hr/>
	<h3>커맨드객체 사용</h3>
	<form action="commandObject" method="post" enctype="multipart/form-data">
		제목 : <input type='text' name="title" /><br/>
		파일 : <input type="file" name='file' /><br/>
		<input type="submit" value="전송" />
	</form>
</body>