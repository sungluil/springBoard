<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardWrite</title>
</head>
<script type="text/javascript">
	var page=1;
	
	$j(document).ready(function(){
		
		$j("#submit").on("click",function(){
			var $frm = $j('.boardRefWrite :input');
			var param = $frm.serialize();
			//alert(param);
			
			$j.ajax({
			    url : "/board/boardWriteRefAction.do",
			    dataType: "text",
			    type: "POST",
			    data : param,
			    success: function(result)
			    {
					//alert(result);
					
					//alert("메세지:"+data.success);
					if(result=="success") {
						location.href = "/board/boardList.do?pageNo="+page;						
					}
			    },
			    error: function (req)
			    {
			    	alert("실패"+req.status);
			    }
			});
		});	
	});
	

</script>
<body>
<form class="boardRefWrite">
	<table align="center">
		<tr>
			<td align="right">
			<input id="submit" type="button" value="작성">
			</td>
		</tr>
		<tr>
			<td>
				<table border ="1"> 
					<tr>
						<td width="120" align="center">
						Type
						</td>
						<td width="400" >
						<input name="boardType" type="hidden" value="${board.boardType}">${board.comcodeVo.codeName}
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
						Title
						</td>
						<td width="400" height="30">
						<input name="boardTitle" type="text" size="53">
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<textarea name="boardComment"  rows="20" cols="55"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center">
						Writer
						</td>
						<td>
						<input name="boardRef" type="hidden" value="${board.boardRef}">
						<input name="boardStep" type="hidden" value="${board.boardStep}">${board.boardStep}
						<input name="boardLev" type="hidden" value="${board.boardLev}">${board.boardLev}
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right" style="font-size: 15px;">
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>