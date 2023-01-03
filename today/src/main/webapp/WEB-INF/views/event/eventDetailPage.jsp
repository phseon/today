<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 디테일 페이지</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventHeaderStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventBodyStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventDetailStyle.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<jsp:include page="/WEB-INF/views/event/event_header.jsp"/>
	<div>
		<div class = "event-detail-title">
			이벤트 제목
		</div>
		<div class = "event-detail">
			<span class = "event-detail-left">진행기간 : 2022-10-10 ~ 2022-12-12</span>
			<div class = "event-detail-right">
				<span>작성자 : 관리자</span>
				<span>작성일 : 2022-10-10</span>
			</div>
		</div>
		<div class = "event-detail-content">
			<c:if test="${!empty board.filename }">
				<div align = "center">
					<img src = "${pageContext.request.contextPath}/upload/${board.filename}" style = "max-width : 800px">
				</div>		
			</c:if>
			<div align = "center">
				<img src = "${pageContext.request.contextPath}/images/esample.jpg" style = "max-width : 800px">
			</div>
		</div>
	</div>
	<div class = "e-buttons">
		<c:if test = "${auth == 1}">
			<span class = "show-event-list">
				<a  href = "eventPage.do">수정</a>
			</span>
			<span class = "show-event-list">
				<a  href = "eventPage.do">삭제</a>
			</span>
		</c:if>
		<span class = "show-event-list">
			<a  href = "eventPage.do">목록</a>
		</span>
	</div>

</body>
</html>