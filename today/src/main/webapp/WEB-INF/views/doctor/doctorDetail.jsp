<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>의사 상세정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/doctorDetail.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/doctorHeader.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
<div id = "doctor_header">
	<div id = "doctor_header_notice">오늘의 치과<br>의료진</div>
</div>
<div id = "doctor_title">
	<h1>의료진 소개</h1>
	<img src = "${pageContext.request.contextPath}/images/dividing_line.png">
</div>
<hr size="1" noshade="noshade" width="100%">
	<div class="content-main">
		<div class="doc-detail">
			<ul class="doc-detail-photo">
			<c:if test="${empty doctor.imgsrc}">
				<li><img src="${pageContext.request.contextPath}/images/face.png" width="480px" height="360px"></li>
			</c:if>
			<c:if test="${!empty doctor.imgsrc}">
				<li><img src="${pageContext.request.contextPath}/upload/${doctor.imgsrc}" width="480px" height="360px"></li>
			</c:if>
			</ul>
			<ul class="doc-content">
				<li id="doc_name">${doctor.name}</li>
				<c:if test="${!empty doctor.content}">
				<li>${doctor.content}</li>
				</c:if>
				<c:if test="${empty doctor.content}">
				<li>의사 소개를 입력하세요</li>
				</c:if>
				<li>
				<input class="reserve_doc" value="예약하기" onclick="location.href='${pageContext.request.contextPath}/reservation/reserveForm.do'">
				<c:if test="${!empty user_num && user_auth == 1}">
				<input class="modify_doc" value="수정" onclick="location.href='checkedPasswdForm.do?d_num=${doctor.m_num}'">
				</c:if>
				</li>
			</ul>
		</div>
		<div class="doc-end"></div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>