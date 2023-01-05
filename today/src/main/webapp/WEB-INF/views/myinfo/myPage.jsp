<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYPAGE</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myInfoStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	
	<div class="content">
		<div class="content-head">
			<p>MY PAGE</p>
		</div>
		
		<div class="content-middle">
			<div class="middle-left"> <!-- 프로필사진. 아직은 기본이미지 출력 -->
				<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo">
			</div>
			<div class="middle-right"> <!-- 바로가기 리스트 -->
				<ul>
					<li><a href="${pageContext.request.contextPath}/myinfo/modifyPasswordForm.do">비밀번호 수정</a></li>
					<li><a href="#">병원예약 조회</a></li>
					<li><a href="#">QNA & REVIEW</a></li>
					<li><a href="${pageContext.request.contextPath}/myinfo/deleteUserForm.do">회원 탈퇴</a></li>
				</ul>
			</div>
		</div>
		
		<div class="content-bottom"> <!-- 개인정보 -->
			개인정보 출력
		</div>
	</div>
</body>
</html>