<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>회원가입</title>
<style type="text/css">

input::placeholder {
	color: gray;
	font-style: italic;
	opacity: 0.5;	
	text-align: center;
}
select {
	width:53px;
	height: 26px;
}
.t1 {
	width:150px;
	text-align: center;	
}
.t2 {
	width: 175px;
}
.error {
	color: red;
}

</style>
<%@include file="/WEB-INF/views/common/common.jsp"%>
</head>
<body>
<table  align="center">
	<tr>
		<td align="left">
			<a href="${pageContext.request.contextPath }/board/boardList.do">List</a>
		</td>
		</td>
	</tr>
	<tr>
		<td>
		<form name="f" method="post" class="login" >
			<table id="boardTable" border = "1">
			<thead>
				<tr>
					<td height="40" width="80" align="center" colspan="3">
						로그인
					</td>
				</tr>
			</thead>
			<tbody>	
				<tr>
					<td class="t1">
						Id
					</td>
					<td class="t2">
						<input name="userId" id="id" type="text" placeholder="아이디">
						<span id="msg" class="error"></span>
					</td>
				</tr>
				<tr>
					<td class="t1">
						pw
					</td>
					<td class="t2">
						<input name="userPw" id="pw" type="password" placeholder="비밀번호">
					</td>
				</tr>

			</tbody>
			</table>
			<div align="right">
			<button type="button" class="submit">login</button>
			</div>
		</form>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
$j(document).ready(function(){
	
	$j(".submit").on("click",function(){
		var userId = $j("#id").val();
		var userPw = $j("#pw").val();
		
		//console.log(userId+userPw);
		
		if($j("#id").val() == "") {
	 		alert("아이디를 입력하세요");
	 		f.userId.focus();
		} else if($j("#pw").val() == "") {
	 		alert("비밀번호를 입력하세요");
	 		f.userId.focus();
		} else {
			$j.ajax({
				url:"/board/idchecked.do",
				type:"POST",
				data:{"userId":userId,"userPw":userPw},
				success:function(result) {
					if(result == 0) {
						$j("#msg").text("아이디가 존재하지않거나 비밀번호가 틀렸습니다.");
						return false;
					} else {
						alert("로그인성공");
						location.href = "${pageContext.request.contextPath }/board/boardList.do";	
						return true;
					}
					
					
				},
				error:function(req) {
					alert("에러 = "+req);
				}
				
			});
		}
	});	
});
// function create() {
	
// 	if(f.userId.value == "") {
// 		alert("아이디를 입력하세요");
// 		f.userId.focus();
// 		return;
// 	}
// 	if(f.userPw.value == "") {
// 		alert("비밀번호를 입력하세요");
// 		f.userPw.focus();
// 		return;
// 	}
		
	
// 	f.action = "${pageContext.request.contextPath }/board/boardList.do";
// 	f.submit();
// }


</script>
</html>