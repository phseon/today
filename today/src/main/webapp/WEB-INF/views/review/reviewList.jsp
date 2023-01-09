<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 리스트</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<div class="content-main">
		<h2>회원 리뷰</h2>
		<!-- 검색 폼 시작 -->
		<form id="search_form" action="reviewPage.do" method="get">
			<ul class="search">
				<li>
					<input type="search" size="16" name="keyword"
					       id="keyword" value="${param.keyword}">
					<input type="submit" value="검색">
				</li>
			</ul>
		</form>
		<!-- 검색 폼 끝 -->
		<div class="list-space align-right">
			<input type="button" value="리뷰 작성"
				   onclick="location.href='writeForm.do'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>/>
		<!-- 위 라인 관련. 클래 -->

		</div>
		
		<c:if test="${count == 0}">
		<div class="result-display">
			작성된 리뷰가 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table><!-- 데이터 구분용. 이후 삭제 -->
			<tr>
				<th>리뷰내용</th>
				<th>작성일자</th>
			</tr>
			
			<c:forEach var="review" items="${list}">
			<tr>
				<td><a href="detail.do?r_num=${review.r_num}">${review.r_content}</a></td>
				<!-- 
				<td><a href="detail.do?board_num=${board.board_num}">${board.title}</a></td>
				-->
				<td>${review.r_date}</td>	
			</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</div>
</body>
</html>