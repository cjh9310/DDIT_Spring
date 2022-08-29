<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.min.css">
</head>

<title>수정하기</title>

<body>
 <!-- Main content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>공지사항</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>공지사항
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	수정
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
	</section>
	 
  <!-- Main content -->
    <section class="content container-fluid">
		<div class="row justify-content-center">
			<div class="col-md-9" style="max-width:960px;">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h3 class="card-title p-1">수정하기</h3>
						<div class ="card-tools">
							<button type="button" class="btn btn-primary" id="modifyBtn" onclick="modifyPOST_go();">수 정</button>
							&nbsp;&nbsp;&nbsp;&nbsp;						
							<button type="button" class="btn btn-warning" id="cancelBtn" onclick="history.go(-1);" >취 소</button>
						</div>
					</div><!--end card-header  -->
					<div class="card-body pad">
						<form action="modify.do" method="post" role="modifyForm">
							<input type="hidden" name="nno" value="${notice.nno }" />
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title" value="${notice.title }"
									name='title' class="form-control" placeholder="제목을 쓰세요">
							</div>							
							<div class="form-group">
								<label for="writer">작성자</label> 
								<input type="text" id="writer" readonly
									name="writer" class="form-control" value="${notice.writer }">
							</div>
							<div class="form-group">
								<label for="content">내 용</label>
								<textarea class="textarea" name="content" id="content" rows="20"
									cols="90" placeholder="1000자 내외로 작성하세요." >${fn:escapeXml(notice.content)}</textarea>
							</div>
						</form>
					</div><!--end card-body  -->
					<div class="card-footer" style="display:none">
					
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
    <!-- /.content -->
    
    <script>
		window.onload=function(){
			summernote_go($('textarea[name="content"]'),'<%=request.getContextPath()%>');	
		}	
	</script>
    <script>
	    function modifyPOST_go(){
			 $("form[role='modifyForm']").submit();
		 }    
    </script>    
    
</body>








