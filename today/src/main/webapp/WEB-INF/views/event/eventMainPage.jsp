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
<script type="text/javascript" src = "${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<!-- <script type="text/javascript">
	$(function(){
		remainDays();
	});
	function remainDays(){
		/* let endDate = "<c:out value = '${event.e_end}'/>"; */
		let endDate = new Date($('#endday').attr('data-remainDay'));
		let today = new Date();
		let remain = endDate - today;
		
		let remainDay = Math.floor(remain / (1000*60*60*24));
		
		console.log(remainDay);
	}
</script> -->
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<jsp:include page="/WEB-INF/views/event/event_header.jsp"/>
<div class = "e-wrap"> 
	<div class = "board-box">
		<c:if test="${count == 0}">
			<div align = "center">
				표시할 게시물이 없습니다.
			</div>
		</c:if>
		<c:if test="${count > 0}">
			<ul class = "event-list">
				<c:forEach var = "eList" items = "${list}">
					<li>
						<a href = "eventDetail.do?e_num=${eList.e_num}">
							<span class = "thumb">
								<img src = "${pageContext.request.contextPath}/upload/${eList.e_thumb}">
								<em>이벤트보기</em>
							</span>
							<strong>${eList.e_title}</strong>
						</a>
						<p>기간 : ${eList.e_start}~${eList.e_end} D-DAY : ${eList.cal_date }</p>
					</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class = "paging-box">${page }</div>
	</div>
</div>

	<div class = "event-search-wrap">
		<form id = "event-searchform" action="eventPage.do" method = "get">
			<select name = "keyfield">
				<option value = "1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
				<option value = "2" <c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>			
			</select>
		<div class = "input-box">
			<input type = "search" size = "16" name = "keyword" id = "keyword" value = "${param.keyword}">
		</div>
		<input id = "search_btn" type = "submit" value = "검색">
		</form>
		<br>
		<div class = "e-buttons">
<%-- 			<c:if test="${user_auth ==1}">
 --%>				<span class = "show-event-list">
					<a  href = "eventWriteFormPage.do">글쓰기</a>
				</span>
<%-- 			</c:if>
 --%>			<span class = "show-event-list">
				<a  href = "eventPage.do">목록</a>
			</span>
		</div>
	</div>
</body>
</html>