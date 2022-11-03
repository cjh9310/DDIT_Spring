/**
 * 
 */

function MemberPictureThumb(contextPath){
	for(var target of document.querySelectorAll('.manPicture')){
		var id = target.getAttribute('data-id');
		
		target.style.backgroundImage="url('" + contextPath + "/member/getPicture.do?id="+id+"')";
		target.style.backgroundPosition="center";
		target.style.backgroundRepeat="no-repeat";
		target.style.backgroundSize="cover";
	}
}


function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", " 
							+"height="+ WinHeight +", top="+ wintop +", left=" 
							+ winleft +", resizable=yes, status=yes"  );
	win.focus() ; 
}

function CloseWindow(){
	window.opener.location.reload(true);
	window.close();
}

function list_go(page,url){
	//alert(page);
	if(!url) url="list.do";
	
	var jobForm=$('#jobForm');
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
	jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
	jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
	jobForm.attr({
		action:url,
		method:'get'
	}).submit(); 
}

var contextPath = "";


function summernote_go(target, context) {
	
	contextPath = context;
	
	$(target).summernote({
		placeholder : '여기에 내용을 적으세요',
		lang : 'ko-KR',
		height:250,
		disableResizeEditor:true,
		callbacks: {
			onImageUpload : function(files, editor, welEditable) {
				for(var file of files) {
					if(file.name.substring(file.name.lastIndexOf(".")+1).toUpperCase() != "JPG"){
						alert("JPG 형식만 가능합닏");
						return;
					}
					if(file.size > 1024*1024*5){
						alert("이미지는 5MB 미만입니다");
						return;
					}
				}
				
				//유효성(이미지파일 전체가 위에 for문에 걸리지 않으면 이미지 보냄)
				for (var file of files) {
					sendFile(file, this);
				}
			},
			onMediaDelete : function(target) {
				deleteFile(target[0].src);
			}
		}
	});
}

function sendFile(file, el) {
	var form_data = new FormData();
	form_data.append("file", file);
	$.ajax({
		url : contextPath + "/uploadImg.do",
		data : form_data,
		type : "POST",
		contentType : false,
		processData : false,
		success : function(img_url) {
        	//항상 업로드된 파일의 url이 있어야 한다.
			$(el).summernote('editor.insertImage', img_url);
		}
	});
}

function deleteFile(src) {		
	var splitSrc= src.split("=");
	var fileName = splitSrc[splitSrc.length-1];
	
	var fileData = {fileName:fileName};
	
	$.ajax({
		url: contextPath + "/deleteImg.do",
		data : JSON.stringify(fileData),
		type:"post",
		contentType:"application/json",
		success:function(res){
			console.log(res);
		},
		error:function(){
			alert("이미지 삭제가 불가합니다.");
		}
	});
}

//restful error 처리
function AjaxErrorSecurityRedirectHandler(status) {
	if (status == "302") {
		alert("세션이 만료되었습니다.\n로그인 하세요.");
		location.reload();
	}else if(status == "403"){
		alert("권한이 유효하지 않습니다.");
		history.go(-1);		
	}else if(status == "404"){
		alert("해당 페이지를 찾을수 없습니다.");
	}else {
		alert("시스템장애로 실행이 불가합니다.");
		history.go(-1);
	}

}

























