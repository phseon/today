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
			${event.e_title}
		</div>
		<div class = "event-detail">
			<span class = "event-detail-left">진행기간 : ${event.e_start} ~ ${event.e_end }</span>
			<div class = "event-detail-right">
				<span>작성자 : 관리자</span>
				<span>작성일 : ${event.e_date}</span>
			</div>
		</div>
		<div class = "event-detail-content">
			<c:if test="${!empty event.e_imgsrc}">
				<div align = "center">
					<img src = "${pageContext.request.contextPath}/upload/${event.e_imgsrc}" style = "max-width : 800px">
				</div>		
			</c:if>
			<c:if test="${!empty event.e_content }">
				<div align = "center">
					${event.e_content}
				</div>
			</c:if>
		</div>
	</div>
	<div class = "e-buttons">
		<c:if test = "${auth == 1}">
			<span class = "show-event-list">
				<a  href = "eventUpdateFormPage.do?e_num=${event.e_num}">수정</a>
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