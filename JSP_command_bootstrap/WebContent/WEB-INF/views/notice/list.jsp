<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="noticeList" value="${dataMap.noticeList }" />
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${pageMaker.cri }" />
    
<title></title>

<head>
	<style>
		.wrap{
			max-width:1200px;
			margin:0 auto;
			padding:0;			
			font-size:16px;
		}
	</style>
</head>

<body>
	<div class="wrap" >
		<div class="content-header" >
			<h1 style="text-align:center;">공지사항</h1>
			<div class="tool-bar">			
				<select name="perPageNum" onchange="list_go(1);">
					<option ${cri.perPageNum eq 10 ? 'selected':'' } value="10">정렬개수</option>
					<option ${cri.perPageNum eq 20 ? 'selected':'' } value="20">20개씩</option>
					<option ${cri.perPageNum eq 50 ? 'selected':'' } value="50">50개씩</option>
					<option ${cri.perPageNum eq 100 ? 'selected':'' } value="100">100개씩</option>					
				</select>
				<select name="searchType" >
					<option ${empty cri.searchType ? 'selected':''} value="">검색구분</option>
					<option ${cri.searchType eq 't' ? 'selected':''} value="t">제목</option>
					<option ${cri.searchType eq 'w' ? 'selected':''} value="w">작성자</option>
					<option ${cri.searchType eq 'c' ? 'selected':''} value="c">내용</option>
					<option ${cri.searchType eq 'tc' ? 'selected':''} value="tc">제목+내용</option>
					<option ${cri.searchType eq 'cw' ? 'selected':''} value="cw">내용+작성자</option>
					<option ${cri.searchType eq 'tcw' ? 'selected':''} value="tcw">제목+내용+작성자</option>
				</select>
				<input type='text' name="keyword" value="${cri.keyword }"/>
				<input type="button" value="검색" onclick="list_go(1);" />
			</div>
			<hr/>
		</div>
		<div class="content-body" >
			<table>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>					
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				
				<c:forEach items="${noticeList }" var="notice" >
				<tr>
					<td>${notice.nno }</td>
					<td>${notice.title }</td>
					<td>${notice.writer }</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${notice.regDate }"/></td>
					<td>${notice.viewcnt }</td>					
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="content-footer"></div>
	</div>
	
	
  
	<form id="jobForm">	
		<input type='hidden' name="page" value="" />
		<input type='hidden' name="perPageNum" value=""/>
		<input type='hidden' name="searchType" value="" />
		<input type='hidden' name="keyword" value="" />
	</form>
   
	
	<script>
	function list_go(page,url){
		if(!url) url="list.do";
		
		var jobForm=$('#jobForm');
		jobForm.find("[name='page']").val(page);
		jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
		jobForm.find("[name='searchType']")
			.val($('select[name="searchType"]').val());
		jobForm.find("[name='keyword']")
			.val($('input[name="keyword"]').val());
		
		jobForm.attr({
			action:url,
			method:'get'
		}).submit();
	}
	
	</script>
</body>









