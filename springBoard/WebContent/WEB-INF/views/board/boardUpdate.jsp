<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardUpdate</title>
</head>
<script type="text/javascript">


$j(document).ready(function(){
	
	$j("#submit").on("click",function(){
		var $frm = $j('.boardUpdate :input');
		var param = $frm.serialize();
		alert(param);
		
		$j.ajax({
		    url : "/board/boardUpdateAction.do",
		    dataType: "json",
		    type: "POST",
		    data : param,
		    success: function(data, textStatus, jqXHR)
		    {
				//alert("작성완료");
				
				//alert("메세지:"+data.success);
				
				location.href = "/board/boardList.do";
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		    	alert("실패");
		    }
		});
	});	
});



</script>
<body>
<form class="boardUpdate">
	
	<table align="center">
		<tr>
			<td align="right">
			<input id="submit" type="button" value="수정">
			</td>
		</tr>
		<tr>
			<td>
				<table border ="1">
					<tr>
						<td width="120" align="center">
						Type
						</td>
						<td width="400">
						<input name="boardType" type="hidden" id="boardType" value="${board.boardType }">${board.comcodeVo.codeName }
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
						Title
						</td>
						<td width="400">
						<input name="boardTitle" type="text" size="50" id="boardTitle" value="${board.boardTitle}"> 
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<textarea name="boardComment"  rows="20" cols="55" id="boardComment">${board.boardComment}</textarea>
						</td>
					</tr>
					<tr>
						<td align="center">
						Writer
						</td>
						<td>
						<input name="boardNum" type="hidden" id="boardNum" value="${board.boardNum }">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<a href = "${pageContext.request.contextPath }/board/boardList.do">취소</a>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>