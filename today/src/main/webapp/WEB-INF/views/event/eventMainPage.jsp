<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 메인 페이지</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventHeaderStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventBodyStyle.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<jsp:include page="/WEB-INF/views/event/event_header.jsp"/>
<div class = "e-wrap"> 
	<div class = "board-box">
		<ul class = "event-list">
			<li>
				<a href = "eventDetail.do">
					<span class = "thumb">
						<img src = "${pageContext.request.contextPath}/images/tooth.png">
						<em>이벤트보기</em>
					</span>
					<strong>이벤트제목</strong>
				</a>
				<p>기간 : 2020-10-10~2020-12-12</p>
			</li>
			<li>
				<a href = "#">
					<span class = "thumb">
						<img src = "${pageContext.request.contextPath}/images/tooth.png">
						<em>이벤트보기</em>
					</span>
					<strong>이벤트제목</strong>
				</a>
				<p>기간 : 2020-10-10~2020-12-12</p>
			</li>
			<li class = "margin-no">
				<a href = "#">
					<span class = "thumb">
						<img src = "${pageContext.request.contextPath}/images/tooth.png">
						<em>이벤트보기</em>
					</span>
					<strong>이벤트제목</strong>
				</a>
				<p>기간 : 2020-10-10~2020-12-12</p>
			</li>
			<li>
				<a href = "#">
					<span class = "thumb">
						<img src = "${pageContext.request.contextPath}/images/tooth.png">
						<em>이벤트보기</em>
					</span>
					<strong>이벤트제목</strong>
				</a>
				<p>기간 : 2020-10-10~2020-12-12</p>
			</li>
		</ul>
	</div>
</div>

	<div class = "event-search-wrap">
		<form id = "event-searchform" action="eventPage.do" method = "get">
			<select name = "keyfield">
				<option value = "1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
				<option value = "2" <c:if test="${param.keyfield == 2}">selected</c:if>>작성자</option>
				<option value = "3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>			
			</select>
		<div class = "input-box">
			<input type = "search" size = "16" name = "keyword" id = "keyword" value = "${param.keyword}">
		</div>
		<input id = "search_btn" type = "submit" value = "검색">
		</form>
		<br>
		<div class = "e-buttons">
			<%-- <c:if test="${auth ==1}"> --%>
				<span class = "show-event-list">
					<a  href = "eventWriteFormPage.do">글쓰기</a>
				</span>			
			<%-- </c:if> --%>
			<span class = "show-event-list">
				<a  href = "eventPage.do">목록</a>
			</span>
		</div>
	</div>
</body>
</html>