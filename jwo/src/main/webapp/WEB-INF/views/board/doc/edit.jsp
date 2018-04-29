<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="/WEB-INF/views/inc/head.jsp" />
<script>
$(document).ready(function(){
	
	$("#btnDocSave").click(function(){
		var title = $("#title").val();
		var content = $("#boardContents").val();
		if( title == "" || content == ""){
			alert("제목 또는 내용을 적으세요");
		} else {
		if(("#frmEdit").valid()){
		$("#frmEdit").submit();
	}
// 		$.post(url, $("#frmWrite").serialize(), function(s) {
// 			document.location.href = "${_ctx}/board/doc/"+s;
// 		});
		}
	});
	
	
});

</script>
</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/inc/header.jsp" />
		<c:import url="/WEB-INF/views/inc/left.jsp" />

		<div id="rightWrap">

			<div class="rightBlock">
				<div class="page_top">
					<h1></h1>
				</div>

				<div class="boardWrap">


					<form id="frmEdit" name="frmEidt" action="${_ctx}/board/doc/edit.god" method="post">

						<input type="hidden" name="mapId" id="mapId" value="${search.mapId}"></input> <input type="hidden" name="docId" id="docId" value="${docId}"></input>
						<table class="base_tbl tbl_write">
							<tbody>
								<tr>
									<th width="20%" class="t_color">제목입력</th>
									<td><input type="text" id="title" name="title" style="font-family: '궁서'" required="required" value="${docDTO.title}" /></td>
								</tr>
								<tr>
									<th class="t_color">내용입력</th>
									<td><textarea name="boardContents" id="boardContents" style="font-family: '궁서'" required="required">${docDTO.boardContents}</textarea></td>
								</tr>
							</tbody>
						</table>

						<div class="btnSet alignCenter">
							<a href="javascript:;" id="btnDocSave" class="disPB btnBase">저장</a> <a href="javascript:history.back();" class="disPB btnBase">취소</a>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
