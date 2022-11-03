<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<script>
	if('${loginUser.id}'){
		location.href="<%=request.getContextPath()%>/index.do";
	}else{
		location.href="<%=request.getContextPath()%>/common/loginForm.do";
	}
		
</script>    