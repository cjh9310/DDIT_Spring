<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script>
	if('${loginUser.id}'){ // loginAction에서 loginUser에 member을 넣어서 id를 만듦    
		// loginUser.id 로그인 했어?
		// index.jsp에서 로그인을 했나 못했나 판단해서 다음 페이지를 출력시킴 
		location.href="<%=request.getContextPath()%>/member/list.do";
	}else{
		location.href="<%=request.getContextPath()%>/common/loginForm.do";
	}

</script>