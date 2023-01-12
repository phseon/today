<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의치과 - MY REVIEW</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myInfoDetailStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	
	<div class="content">
		<div class="content-head">
			<p>MY REVIEW</p>
		</div>

		<div class="content-main">
			<div class="content-middle">
				<table>
					<tr class="table-title">
						<td class="first-td">작성 날짜</td>
						<td class="second-td">리뷰 작성 내용</td>
					</tr>
					<tr class="table-detail">
						<td class="first-td">${myReview.r_date}</td>
						<td class="second-td">${myReview.r_content}</td>
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