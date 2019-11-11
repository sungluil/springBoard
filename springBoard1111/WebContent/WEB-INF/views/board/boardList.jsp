<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script id="entry-template" type="text/x-handlebars-template">
					{{#boardList}}
					<tr>
						<td align="center">
							{{comcodeVo.codeName}}
						</td>
						<td align="center">
							{{boardNum}}
						</td>
						<td>
							<a href = "/board/{{boardType}}/{{boardNum}}/boardView.do?pageNo={{pageNo}}">{{boardTitle}}</a>
						</td>
						<td align="center">
							{{createTime}}
						</td>
						<td align="center">
							{{boardHits}}
						</td>
					</tr>
					{{/boardList}}	
 					</script>
<script type="text/javascript">
	
	//키워드 서치
	
	$j(document).ready(function(){
	  $j("#search").on("keyup", function() {
	    var value = $j(this).val().toLowerCase();
	    $j("#searchTable tr").filter(function() {
	      $j(this).toggle($j(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
	
	
	
	$j(document).ready(function(){
		
		//전체 체크시에 전부체크 or 전체해제
		$j("#defaultInline1").click(function(){
			if ($j(this).prop('checked')) {
				$j("input[name=board2]:checkbox").each(function(){
					$j(this).prop("checked",true)
					var checkValue = $j(this).val();
					//console.log(checkValue);
				});
			} else {
				$j("input[name=board2]:checkbox").each(function(){
					$j(this).prop("checked",false)
				});
			}
		});
		//이름이 board2인 체크박스가 선택된 갯수
		var cnt = $j("input:checkbox[name='board2']:checked").size();
		console.log("cnt"+cnt);
		
		
		//일반,Q&A,익명,자유 4개 체크시 전체 체크
		$j("input:checkbox[name='board2']").click(function(){
			
			if($j('input:checkbox[name="board2"]:checked').length == 4) {
				$j('input[type="checkbox"][name="board1"]').prop('checked', true);
			} else {
				$j('input[type="checkbox"][name="board1"]').prop('checked', false);
			}
		});
		

		
		check();
	});
	
	function check() {
	 	
		console.log("==========================================================")
		
		
		//모든 체크박스의 값
		$j('input[type="checkbox"]').each(function(){
			var checkboxValue = $j(this).val();
			console.log("checkboxValue = " +checkboxValue);
		});
		
		
		//조회버튼 클릭시 체크박스 값
		
		$j("#searchButton").click(function(){
			var arrVal = [];
			$j("input[name='board2']:checked").each(function(){
				arrVal.push($j(this).val());
			});
			console.log(arrVal);
			var boardType = arrVal.join(",");
			console.log(boardType);
			
			$j.ajax({
				type:"POST",
				url:"/board/boardSearch.do",
				data:{"boardType":boardType},
				dataType: "json",
				success:function(json) {
					var source = $j("#entry-template").html();
					var template = Handlebars.compile(source);
					$j("#searchTable").html(template(json))
				},
				error:function(req) {
					alert("에러 = "+req.status);
				}
			});
// 			$j.ajax({
// 				url:"/board/boardSearch.do",
// 				data:{"boardType":boardType},
// 				type:"POST",
// 				dataType: "html",
// 				success:function(data) {
// 					$j("#searchTable").html(data);

// 				},
// 				error:function(req) {
// 					alert("에러 = "+req.status);
// 				}
// 			});
			
		});
		/*
		$("#searchButton").on("click", function() {
			var arrVal = [];
			$j("input[name='board2']:checked").each(function(){
				arrVal.push($j(this).val());
			});
			
			console.log("배열 값 = "+arrVal);
		});
		*/
		console.log("==========================================================")
		
		//조회버튼 클릭시 체크박스 갯수
		$j("#searchButton").click(function(){
			var checkNo = $j('input[type="checkbox"]:checked').size();
			
			console.log("체크박스 갯수 = "+checkNo);
		});
		
	}
	


</script>
				
<body>

<table  align="center">
	<tr>
		<td>
			<a href="${pageContext.request.contextPath }/board/memberWrite.do">join</a>
			<c:if test="${sessionScope.userinfo == null }">
			<a href="${pageContext.request.contextPath }/board/memberLogin.do">login</a>
			</c:if>
			<c:if test="${sessionScope.userinfo != null }">
			<a href="${pageContext.request.contextPath }/board/memberLogout.do">logout</a>
			<c:out value="${userinfo.userName } 님 환영합니다."></c:out> 
			<a href="${pageContext.request.contextPath }/board/MemberModify.do">회원정보</a>
			</c:if>
			
		</td>
		<td align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td>
			<div id="searchTableDiv"></div>
			<table class="table" id="boardTable" border="1">
			<thead class="thead-dark" >
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="40" align="center">
						Ref
					</td>
					<td width="40" align="center">
						Step
					</td>
					<td width="40" align="center">
						Lev
					</td>
					<td width="300" align="center">
						Title
					</td>
					<td width="150" align="center">
						Date
					</td>
					<td width="40" align="center">
						Hits
					</td>
				</tr>
			</thead>
			<tbody id="searchTable">
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							${list.comcodeVo.codeName}
						</td>
						<td align="center">
							${list.boardNum}
						</td>
						<td align="center">
							${list.boardRef}
						</td>
						<td align="center">
							${list.boardStep}
						</td>
						<td align="center">
							${list.boardLev}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
						<td align="center">
							${list.createTime}
						</td>
						<td align="center">
							${list.boardHits}
						</td>
					</tr>	
				</c:forEach>
			</tbody>
			
			
<!--             <tbody id="searchTable"> -->
					
 			
			</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right" style="font-size: 15px;">
			<a href ="/board/boardWrite.do">글쓰기</a>
		</td>
	</tr>
	<tr>
	<td>

	<form class="checkboxControl" id="checkboxArea">	
		<div class="custom-control custom-checkbox" id="checkboxArea">
			<input value="all" type="checkbox" name="board1" id="defaultInline1">전체
			<c:forEach var="list" items="${codeList}">
				<c:if test="${list.codeType eq 'menu' }"> 
					<input type="checkbox" name="board2"value="${list.codeId }">${list.codeName}
				</c:if>
			</c:forEach>
			<%--  
			<div class="custom-control custom-checkbox custom-control-inline">
			  <input value="a01" type="checkbox" name="board2" class="custom-control-input" id="defaultInline2">
			  <label class="custom-control-label" for="defaultInline2">일반</label>
			</div>
			<div class="custom-control custom-checkbox custom-control-inline">
			  <input value="a02" type="checkbox" name="board2" class="custom-control-input" id="defaultInline3">
			  <label class="custom-control-label" for="defaultInline3">Q&A</label>
			</div>
			<div class="custom-control custom-checkbox custom-control-inline">
			  <input value="a03" type="checkbox" name="board2" class="custom-control-input" id="defaultInline4">
			  <label class="custom-control-label" for="defaultInline4">익명</label>
			</div>
			<div class="custom-control custom-checkbox custom-control-inline">
			  <input value="a04" type="checkbox" name="board2" class="custom-control-input" id="defaultInline5">
			  <label class="custom-control-label" for="defaultInline5">자유</label>
			</div>
			--%>
			<button id="searchButton" type="button" class="btn btn-primary btn-sm">조회</button>
		</div>
		</form>
		<div align="right">
<%-- 		<c:forEach begin="${pageVo.startPage }" end="${pageVo.endPage }" var="count"> --%>
<%-- 		<c:if test="${pageVo.pageNo ne count }"></c:if> --%>
<%-- 			<a href="boardList.do?pageNo=${count }">[${count }]</a> --%>
<%-- 		</c:forEach> --%>

		<ul class="pagination">
		<li class="page-item">
		<a href="boardList.do?pageNo=1">&lt;&lt;</a>
		<a href="boardList.do?pageNo=${pageVo.prevPage }">&lt;</a>
		<c:forEach var="i" begin="${pageVo.startPage}" end="${pageVo.endPage}">
            <c:choose>
                <c:when test="${i eq pageVo.pageNo}"><a>${i}</a></c:when>
                <c:otherwise><a class="page-link" href="boardList.do?pageNo=${i}">${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>
        <a href="boardList.do?pageNo=${pageVo.nextPage }">&gt;</a>
        <a href="boardList.do?pageNo=${pageVo.totalPage }">&gt;&gt;</a>
        </li>
		</ul>
		</div>		
		</td>
	</tr>
</table>


  <div class="form-group row" >
    <div class="col-sm-10" style="margin-left: 20%; padding-right: 60%">
      <input type="text" class="form-control form-control-lg" id="search" placeholder="search">
    </div>
  </div>
<%-- 
<div class="container" align="center">			
	<div class="btn-group-sm" data-toggle="buttons">
		<label class="btn btn-success active">
			<input type="radio" name="options" id="option2" autocomplete="off" >전체
			<span class="glyphicon glyphicon-ok"></span>
		</label>

		<label class="btn btn-primary">
			<input type="radio" name="options" id="option1" autocomplete="off">일반
			<span class="glyphicon glyphicon-ok"></span>
		</label>

		<label class="btn btn-info">
			<input type="radio" name="options" id="option2" autocomplete="off">질문
			<span class="glyphicon glyphicon-ok"></span>
		</label>
		<label class="btn btn-warning">
			<input type="radio" name="options" id="option2" autocomplete="off">익명
			<span class="glyphicon glyphicon-ok"></span>
		</label>

		<label class="btn btn-danger">
			<input type="radio" name="options" id="option2" autocomplete="off">자유
			<span class="glyphicon glyphicon-ok"></span>
		</label>
		&emsp;&emsp;<button type="button" class="btn btn-primary">조회</button>
		
	</div>
</div>
  --%>


</body>
</html>