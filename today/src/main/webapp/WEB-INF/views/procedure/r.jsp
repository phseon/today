<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/procedureStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	document.getElementById("rev_date").value = new Date().toISOString().slice(0, 10);
	$('#reservation_form').submit(function(){
		if(!($('input[type=radio][name=time]:checked').val())){
			alert('시간을 선택하세요');
			return false;
		}
	});
});

</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
<h1 id="_title">
진료예약
</h1>
	<form action="re.do" method="post" id="reservation_form">
		<ul>
			<!-- <li>
				<label for="d_name">의사</label>
				<select name="d_name">
					<c:forEach var="dm" items="${m_list}">
					<option value="${dm.m_num}">${dm_name}</option>
					</c:forEach>
				</select>
			</li> -->
			<li>
				<label for="r_name">진료명</label>
				<select name="r_name" id="r_name">
					<c:forEach var="pro" items="${list}">
					<option value="${pro.p_num}" <c:if test="${p_num ==pro.p_num}">selected</c:if>>${pro.p_title }</option>
					</c:forEach>
				    
				</select>
			</li>
			<li>
				<label for="rev_date">진료일</label>
				<input type="date" id="rev_date" name="rev_date">
				
			</li>
			<li>
				<label for="time">진료시간</label><br>
				<input type="radio" name="time" id="rev_time1" value="9"><label for="rev_time1">9시</label>
				<input type="radio" name="time" id="rev_time2" value="10"><label for="rev_time2">10시</label>
				<input type="radio" name="time" id="rev_time3" value="11"><label for="rev_time3">11시</label>
				<input type="radio" name="time" id="rev_time4" value="12"><label for="rev_time4">12시</label>
				<br>
				<input type="radio" name="time" id="rev_time5" value="14"><label for="rev_time5">14시</label>
				<input type="radio" name="time" id="rev_time6" value="15"><label for="rev_time6">15시</label>
				<input type="radio" name="time" id="rev_time7" value="16"><label for="rev_time7">16시</label>
				<input type="radio" name="time" id="rev_time8" value="17"><label for="rev_time8">17시</label>
			</li>
			<li>
				리뷰 작성 여부 <input type="radio" name="r_ox" id="r_ox" value="T"><label for="r_ox">체크</label>
			</li>
		</ul>
		<div id="align_center">
		<input type="submit" value="예약완료">
		<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form>
</body>
</html>