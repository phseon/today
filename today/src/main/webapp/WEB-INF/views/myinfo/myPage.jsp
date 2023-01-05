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
				<div class="photo-button">
						<input type="file" id="photo" accept="image/gif,image/png,image/jpeg"><br>
						<input type="button" value="전송" id="photo_submit">
						<input type="button" value="취소" id="photo_reset">
					</div>
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
			<p>개인정보</p>
			<ul>
				<li>이름 : ${member.name}</li>
				<li>전화번호 : ${member.phone}</li>
				<li>이메일 : ${member.email}</li>
				<li>우편번호 : ${member.zipcode}</li>
				<li>주소 : ${member.address1} ${member.address2}</li>
				<li>
					<input type="button" value="개인정보 수정하기" onclick="location.href='modifyUserForm.do'">
				</li>
			</ul>    
		</div>
	</div>
</body>
</html>