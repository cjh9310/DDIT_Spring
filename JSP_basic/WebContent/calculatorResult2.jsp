<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
table {
	border-collapse: collapse;
	border: lightgray;
}

input[type=text] {
	width: 245px;
	height: 20px;
}
#AC {
	border: 1px solid black;
	width: 188px;
	height: 40px;
}
input[type=button] {
	border: 1px solid black;
	width: 60px;
	height: 40px;
}
input[type=submit] {
	border: 1px solid black;
	width: 60px;
	height: 40px;
}
#num0 {
	width: 188px;
	height: 40px;
}

.num {
	background-color: gray;
}
.op {
	background-color: pink;
}


</style>

<script>
$(document).ready(function() {
	let recordVal = "";
	let displayVal = "";
	
	let firstNumVal = "";
	let lastNumVal = "";
	
	let operatorVal = "";
	let operatorId = "";
	
	
	
	$('#submit').on("click",function() {
		displayVal = document.getElementById('display').value;
		firstNumVal = document.getElementById('firstNum').value;
		operatorVal = document.getElementById('operator').value;
		lastNumVal = document.getElementById('lastNum').value;
		
		if(displayVal == ""){
			alert("숫자 먼저 입력해주세요");
			return;
		}
		if((operatorVal != "") && (lastNumVal=="")) { //ex) 10+ = 을 했을 경우 10+10의 결과
			
			lastNumVal = firstNumVal;
			document.getElementById('lastNum').value = lastNumVal;
		}
		if((operatorVal=="") && (lastNumVal=="")) { 
			alert("연산자를 입력해주세요");
			return;
			lastNumVal = firstNumVal;
			
			console.log(firstNumVal);
			console.log(lastNumVal);
			document.getElementById('lastNum').value = lastNumVal;
		}
		
		let param = "";
	    param += "&firstNum="+$("#firstNum").val();
	    param += "&operator="+$("#operatorId").val();
	    param += "&lastNum="+$("#lastNum").val();
	   
	    $.ajax({
	       url : "calculator",
	       data : param,
	       dataType : "json",
	       type : "post",
// 	       async: false,
	       success : function(res) {
	          let result = res.result;
	          document.getElementById('record').value = firstNumVal + operatorVal + lastNumVal;
	          firstNumVal = result;
	          operatorVal="";
	          lastNumVal="";
	          $('#display').val(result);
	       	  $('#firstNum').val(result);
	          $('#operator').val("");
	          $('#operatorId').val("");
	          $('#lastNum').val("");

	           
	        },
	        error : function() {
	        	alert("error! 다시 입력해주세요");
	        	location.href = "calculator";
	        }
	     });
	});
	
	$('.num').on("click",function() {
		displayVal = document.getElementById('display').value;
		recordVal = document.getElementById('record').value;
		firstNumVal = document.getElementById('firstNum').value;
		lastNumVal = document.getElementById('lastNum').value;
		
		if(recordVal != "") {
			recordVal = "";
			displayVal = "";
			firstNumVal = "";
			document.getElementById('display').value = displayVal;
			document.getElementById('firstNum').value = firstNumVal;
			document.getElementById('record').value = recordVal;
			
		}
		
		if(($(this).val() == "0") && (displayVal == "")){
			alert("0을 입력할 수 없습니다.");
			return;
		}
		
		if(operatorVal != "") {
			lastNumVal += $(this).val();
			document.getElementById('lastNum').value = lastNumVal;
			document.getElementById('display').value = firstNumVal + operatorVal + lastNumVal;
			
		}else{
			firstNumVal += $(this).val();
			document.getElementById('firstNum').value = firstNumVal;
			document.getElementById('display').value = firstNumVal + operatorVal + lastNumVal;
			
		}
		
	});
	
	$('.op').on("click",function() {
		displayVal = document.getElementById('display').value;
		
		if(displayVal == ""){
			alert("숫자 먼저 입력해주세요");
			return;
		}
			operatorVal = $(this).val(); //operator의 id로 연산자 가져오기
			operatorId = $(this).attr("id");
			console.log(operatorVal);
			document.querySelector('#operator').value = operatorVal;
			document.querySelector('#operatorId').value = operatorId;
			document.getElementById('display').value = firstNumVal + operatorVal + lastNumVal;
	
			
	});
	
	$('.ac').on("click",function() {
		location.href = "calculator";
			
	});
});




</script>

</head>
<body>
	<%!
String[][] data = {{"AC","/"},
				   { "7","8","9","*"},
              	   {"4","5","6","-"},
              	   {"1","2","3","+"},
              	   {"0","="}};	
              	   %>



<table border="1">
   <tr>
   	  <td colspan="4">
      <input type="text"  name="record" id="record" style="text-align:right" value=""/>
      </td>
   </tr>
   <tr>
      <td colspan="4">
      <input type="text"  name="display" id="display" style="text-align:right" value="${result }"/>
      </td>
   <%int opId = 103; %>
   <%for(int i=0;i<data.length;i++){%>
   <tr>
   <%for(int j=0; j<data[i].length; j++){%>
      <%if(data[i][j] == data[0][0]) {%>
         <td colspan="3">
         <input type="button" class="<%="ac" %>" id="<%=data[i][j] %>" value="<%=data[i][j]%>"/>
         </td>
      <%}else if(data[i][j] == data[4][0]) {%>
         <td colspan="3">
         <input type="button" class="<%="num" %>" id="<%="num"+data[i][j] %>" value="<%=data[i][j]%>"/>
         </td>
      <%}else if(data[i][j] == data[0][1]||data[i][j] == data[1][3]||data[i][j] == data[2][3]||data[i][j] == data[3][3]){%>
        <td><input type="button" class="<%="op" %>" id="<%= opId %>"  value="<%=data[i][j] %>"/></td>
        <% opId-- ; %>
        <%}else if(data[i][j] == data[4][1]){%>
        <td><input type="button" id="submit"  value="<%=data[i][j] %>"/></td>
        <%}else {%>
        <td><input type="button"  class="<%="num" %>" value="<%=data[i][j] %>"/></td>
        <%}%> 
   <%}%>
   </tr>
   <%}%>
   
</table>
firstNum
<input type="text" id="firstNum" /> <br/>
operator
<input type="text" id="operator" /> <br/>
operatorId
<input type="text" id="operatorId" /> <br/>
lastNum
<input type="text" id="lastNum" /> <br/>
</body>
</html>