<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>의사소개 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/doctorDetail.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		//파일 선택 여부 확인
		$('#doc_form').submit(function(){
			if($('#d_imgsrc').val()==''){
				alert('파일을 선택해주세요.');
				$('#d_imgsrc').focus();
				return false;
			}
		});
		
		//취소 시 뒤로가기
		$('input[type=button]').on('click',function(){
			history.back();
		})
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<div class="result-display">
	<br><br>
		<h3>의사 상세정보 변경</h3>
		<div class="form-display">
		<form id="doc-form" action="modifyDoctor.do" method="post" 
										enctype="multipart/form-data">
			<input type="hidden" name="d_num" value="${doctor.m_num}">
			<ul>
				<li>
					<label for="d_name">의사이름</label>
					<input type="text" name="d_name" id="d_name" maxlength="10" value="${doctor.name}" disabled>
				</li>
				<li>
					<label for="d_content">의사소개</label>
					<textarea rows="3" cols="50" name="d_content" id="d_content">${doctor.content}</textarea>
				</li>
				<li>
					<label for="d_imgsrc">의사 이미지</label>
					<input type="file" id="d_imgsrc" name="d_imgsrc" accept="image/gif,image/png,image/jpeg">
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="취소" onclick="location.href='doctorList.do'">
			</div>
		</form>
		</div>
	</div>
</div>
</body>
</html>