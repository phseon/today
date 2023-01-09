<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>의사 소개</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/doctor.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<div class="content-main">
	<c:if test="${count == 0}">
		<div class="result-display">
			등록된 의사가 없습니다.
		</div>
	</c:if>
	<c:if test="${count > 0}">
		<div class="doc-list">
		<c:forEach var="doctor" items="${list}">
		<div class="doc" onclick="location.href='doctorDetail.do'">
		<input type="hidden" name="m_num" 
				       value="${doctor.m_num}" id="m_num">
			<span class="doc_photo">
			<c:if test="${!empty doctor.imgsrc}">
				<img src="${pageContext.request.contextPath}/upload/${doctor.imgsrc}" width="400" height="300">
			</c:if>
			<c:if test="${empty doctor.imgsrc}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="400" height="300">
			</c:if>
			</span>
			<span class="doc-name">
				<b>${doctor.name}</b> 원장
			</span>
		</div>
		<div class="doc-end">
			<hr size="1" noshade="noshade" width="100%">
		</div>
		</c:forEach>
		<div class="align-center">
		${page}
		</div>
		</div>
		</c:if>
	</div>
</div>
</body>
</html>