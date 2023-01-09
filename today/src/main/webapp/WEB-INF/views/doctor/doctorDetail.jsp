<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>의사 상세정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/doctorDetail.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<div class="content-main">
		<div class="doc-detail">
			<ul class="doc-detail-photo">
				<li><img src="${pageContext.request.contextPath}/images/face.png" width="480px" height="360px"></li>
			</ul>
			<ul class="doc-content">
				<li id="doc_name">${doctor.name}</li>
				<li>구강내과 전문의</li>
				<li><input type="button" value="예약하기" onclick="reserveForm.do"></li>
			</ul>
		</div>
		<div class="align-right doc-end">
		<c:if test="${!empty user_num && user_auth == 1}">
			<input type="button" value="수정" onclick="location.href='modifyDoctorForm.do'">
		</c:if>
		</div>
	</div>
</div>
</body>
</html>