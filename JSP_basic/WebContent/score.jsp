<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%!

	int kor;
	int eng;
	int math;
	int sci;	

	private int total(){
		return kor+eng+math+sci;
	}
%>


<%  // jpsService(request,response)
	this.kor = Integer.parseInt(request.getParameter("kor"));
	this.eng = Integer.parseInt(request.getParameter("eng"));
	this.math = Integer.parseInt(request.getParameter("math"));
	this.sci = Integer.parseInt(request.getParameter("sci"));
	

%>

<ul>
	<li>국어 : <% out.println(kor); %></li>
	<li>영어 : <% out.println(eng); %></li>
	<li>수학 : <% out.println(math); %></li>
	<li>과학 : <% out.println(sci); %></li>
	<li>총점 : <% out.println(total()); %></li>
	<li>평균 : <% out.println(total()/(float)4); %></li>
</ul>




</body>
</html>









