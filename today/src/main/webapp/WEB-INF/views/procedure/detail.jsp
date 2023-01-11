<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시술 상세 정보</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath }/css/procedureStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>



	<h1 id="_title">

	${pro.p_title }
	
	</h1> 
	<hr size="3" noshade="noshade" width="100%">
	
	<span id="detail_left">
	<img src="${pageContext.request.contextPath}/upload/${pro.p_imgsrc}" width="400" height="400">
	</span>
	<p>
	<span id="detail_right">
	 ${pro.p_content }
	</span>
	</p>
	<hr size="3" noshade="noshade" width="100%">
	<div id="detail_clear"></div>
	<br>
	 
	<br>
<div id="align_center">
<c:if test="${user_auth == 1}">
<input type="button" value="수정" onclick="location.href='updateForm.do?p_num=${pro.p_num}'">
<input type="button" value="삭제" onclick="location.href='deleteForm.do?p_num=${pro.p_num}'">
</c:if>
<input type="button" value="목록" onclick="location.href='list.do'">
<c:if test="${user_auth == 2}">
<input type="button" value="예약"  onclick="location.href='r.do?p_num=${pro.p_num}'">
</c:if>
</div>
</body>
</html>