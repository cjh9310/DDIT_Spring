<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    

<script>
	alert('${member.name}님을 삭제했습니다.');
	if(window.opener)window.opener.location.reload(true);
	window.close();
</script>    