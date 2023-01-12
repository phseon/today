<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
</head>
<body>
<!-- header 시작 -->
<div class = "wrap">
	<div class = "header">
		<div class = "nav_bar">
			<div class = "title">
				<h1 id = "main-logo-title"><a href = "${pageContext.request.contextPath}/main/main.do">오늘의치과</a></h1>
			</div>
			<ul class = "menu">
				<li>
					<a href="${pageContext.request.contextPath}/information/list.do">공지사항</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/doctor/doctorList.do">의료진소개</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/procedure/list.do">시술</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/qna/qnaPage.do">QnA</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/review/reviewPage.do">리뷰</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/event/eventPage.do">이벤트</a>
				</li>
			</ul>
			<div class = "loginBar">
			<c:if test="${empty user_num}">
				<span><a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a></span>
				<span><a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a></span>
			</c:if>
			<c:if test="${!empty user_num}">
				<span><a href="${pageContext.request.contextPath}/myinfo/myPage.do">마이페이지</a></span>
				<span><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></span>
			</c:if>
		</div>
		</div>
	</div>
</div>
<!-- header 끝 -->
</body>
</html>