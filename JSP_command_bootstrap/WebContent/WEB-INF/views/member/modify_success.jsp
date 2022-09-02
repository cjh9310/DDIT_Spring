<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
 <script>
	alert("${member.name}님의 정보가 수정되었습니다.");	
	location.href='detail.do?id=${member.id}';
	
	if(${parentReload}){
		if(confirm('로그인 사용자의 정보가 수정되었습니다.\n현재 화면을 닫고 새로고침 하시겠습니까?')){
			window.opener.location.reload(true);
			window.close();
		}	
	}
</script>       