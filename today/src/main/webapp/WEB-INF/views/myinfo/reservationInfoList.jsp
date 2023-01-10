<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의치과 - MY RESERVATION</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myInfoDetailStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	
	<div class="content">
		<div class="content-head">
			<p>MY RESERVATION</p>
		</div>

		<div class="content-main">
			<div class="content-middle">
				<table>
					<tr class="table-title">
						<td>예약 날짜</td>
						<td>예약 시간</td>
						<td>시술내용</td>
					</tr>
					<tr class="table-detail">
						<td>${myReservation.rev_date}</td>
						<td>${myReservation.rev_time}</td>
						<td>${myProcedure.p_title}</td>
					</tr>
				</table>
				<div class="content-button">
					<input type="button" value="마이페이지" onclick="location.href='myPage.do'">
				</div> 
			</div>
		</div>
	</div>
</body>
</html>