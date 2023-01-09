<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>의사소개 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/doctorDetail.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<div class="content-main">
		<form id="doc_form" action="modifyDoctor.do" method="post" 
										enctype="multipart/form-data">
			<ul>
				<li>
					<label for="d_name">의사이름</label>
					<input type="text" name="d_name" id="d_name" maxlength="10">
				</li>
				<li>
					<label for="d_content">의사소개</label>
					<textarea rows="3" cols="50"></textarea>
				</li>
				<li>
					<label for="d_img">의사 이미지</label>
					<input type="file" id="d_img" name="d_img" accept="image/gif,image/png,image/jpeg">
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="취소" onclick="location.href='doctorDetail.do'">
			</div>
		</form>
	</div>
</div>
</body>
</html>