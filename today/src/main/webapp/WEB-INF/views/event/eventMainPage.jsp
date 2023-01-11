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
<script type="text/javascript">
	$(function(){
		let set_date = $('#set_date');
		let eList_ul = $('.event-list').eq(1);
		let eList_li = eList_ul.children('li');
		var set_dateArray = [];
		for(var i = 0; i < set_date.children().length; i++){
			set_dateArray[i] = set_date.children('span:eq(' + i + ')').clone();
			var innerList = eList_li.eq(i).children('p');
			innerList.append(set_dateArray[i]);
		}
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<jsp:include page="/WEB-INF/views/event/event_header.jsp"/>
<div id = "set_date" style = "display : none;"></div>

<div class = "e-wrap"> 
	<div class = "board-box">
		<c:if test="${count == 0}">
			<div align = "center">
				표시할 게시물이 없습니다.
			</div>
		</c:if>
		<c:if test="${count > 0}">
			<ul class = "event-list">
				<c:forEach var = "cList" items = "${topList}">
					<li>
						<a href = "eventDetail.do?e_num=${cList.e_num}&pageNum=${pageNum}">
							<span class = "thumb">
								<img src = "${pageContext.request.contextPath}/upload/${cList.e_thumb}">
								<em>이벤트보기</em>
							</span>
							<strong>${cList.e_title}</strong>
						</a>
						<p>기간 : ${cList.e_start}~${cList.e_end} 
						<span class = "badge close">
							진행중 : D-${cList.cal_date}
						</span>
						</p>
					</li>
				</c:forEach>
			</ul>
			<ul class = "event-list">
			
				<c:forEach var = "eList" items = "${list}">
					<li>
						<a href = "eventDetail.do?e_num=${eList.e_num}&pageNum=${pageNum}">
							<span class = "thumb">
								<img src = "${pageContext.request.contextPath}/upload/${eList.e_thumb}">
								<em>이벤트보기</em>
							</span>
							<strong>${eList.e_title}</strong>
						</a>
						<p>기간 : ${eList.e_start}~${eList.e_end} 
						<script type="text/javascript">
							var today = new Date();
							var year = today.getFullYear();
							var month = today.getMonth() + 1;
							var date = today.getDate();
						
							var now = new Date(year, month, date);
							var stand = "<c:out value = '${eList.e_start}'/>";
							var end = "<c:out value = '${eList.e_end}'/>"
							
							var splitStand = stand.split('-');
							var splitEnd = end.split('-');
							
							var stand_by = new Date(splitStand[0], splitStand[1], splitStand[2]);
							var end_by = new Date(splitEnd[0], splitEnd[1], splitEnd[2]);
							var end_byDate = (now.getTime() - end_by.getTime()) / (1000*60*60*24);
							var stand_byDate = (now.getTime() - stand_by.getTime()) / (1000*60*60*24);

							if(stand_byDate < 0 && end_byDate < 0){
								//이벤트 시작 되지 않음
								var set_date = document.getElementById('set_date');
								var gen_badge = document.createElement('span');
								
								gen_badge.setAttribute('class', 'badge');
								gen_badge.className += ' beforeEvent';
								gen_badge.innerHTML = '진행대기';
								
								set_date.appendChild(gen_badge);
								
							}else if(stand_byDate > 0 && end_byDate > 0){
								var set_date = document.getElementById('set_date');
								var gen_badge = document.createElement('span');
								
								gen_badge.setAttribute('class', 'badge');
								gen_badge.className += ' end';
								gen_badge.innerHTML = '마감';
								
								set_date.appendChild(gen_badge);
							}else{
								var set_date = document.getElementById('set_date');
								var gen_badge = document.createElement('span');
								
								gen_badge.setAttribute('class', 'badge');
								gen_badge.className += ' continue';
								gen_badge.innerHTML = '진행중';
								
								set_date.appendChild(gen_badge);
							}
						</script>
						</p>
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
 			<c:if test="${user_auth ==1}">
				<span class = "show-event-list">
					<a  href = "eventWriteFormPage.do">글쓰기</a>
				</span>
 			</c:if>
			<span class = "show-event-list">
				<a  href = "eventPage.do">목록</a>
			</span>
		</div>
	</div>
</body>
</html>