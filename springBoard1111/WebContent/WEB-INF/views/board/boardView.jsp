<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
</head>
<body>
<form class="boardDelete" >
<table align="center">
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td width="120" align="center">
					Type
					</td>
					<td width="400">
					<input name="boardType" type="hidden" value="${board.boardType}">${board.comcodeVo.codeName}
					</td>
				</tr>
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					${board.boardTitle}
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					${board.boardComment}
					</td>
				</tr>
				<tr>
					<td align="center">
					Writer
					</td>
					<td>
					<input name="boardNum" type="hidden" value="${board.boardNum}">
					<input name="boardStep" type="hidden" value="${board.boardStep}">${board.boardStep}
					<input name="boardLev" type="hidden" value="${board.boardLev}">${board.boardLev}
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr align="right" style="font-size: 15px;">
		<td>
			<a href="/board/boardList.do">List</a>
			<a href = "/board/${board.boardType}/${board.boardNum}/boardUpdate.do?pageNo=${pageNo }">Update</a>
			<a href="" id="delete">Delete</a>
			<a href="/board/${board.boardType}/${board.boardNum}/boardRefWrite.do?pageNo=${pageNo }">답글</a>
			
		</td>
		<td>
		</td>
	</tr>
</table>
</form>	
</body>
<script type="text/javascript">
$j(document).ready(function(){
	
	$j("#delete").on("click",function(){
		var $frm = $j('.boardDelete :input');
		var param = $frm.serialize();
		//alert(param);
		
		$j.ajax({
		    url : "/booard/boardDeleteAction.do",
		    dataType: "TEXT",
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
		    	alert("실패"+jqXHR);
		    }
		});
	});	
});

</script>
</html>