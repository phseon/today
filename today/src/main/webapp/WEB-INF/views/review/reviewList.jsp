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
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>내용</option>
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>의사</option>
						<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>시술</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" name="keyword"
					       id="keyword" value="${param.keyword}">
					<input type="submit" value="검색">
				</li>
			</ul>
		</form>
		<!-- 검색 폼 끝 -->
		<div class="list-space align-right">
		<h2>리뷰 작성번호:${rez.rev_num}</h2>
		<h2>리뷰 작성 회원번호:${rez.m_num}</h2>
		<h2>리뷰 작성여부:${rez.r_ox}</h2>

			<c:if test="${rez.r_ox=='T'}">
			<input type="button" value="리뷰 내역"
			   onclick="location.href='${pageContext.request.contextPath}/myinfo/reviewInfoList.do'"/>
			</c:if>
			
			<c:if test="${rez.r_ox=='F'}">
			<input type="button" value="리뷰 작성"
				   onclick="location.href='writeForm.do'"
				   <c:if test="${empty user_num}">disabled="disabled"</c:if>/>
			</c:if>
			
		<!-- list로 목록 넣으면 선택 가능 -> 리뷰 작성으로 수정 -> 작성 폼에서 선택 -->
		<!-- 리뷰 내역 유지하려면 내역 페이지에서도 수정, 삭제 넣어야 함 -->
		
		<!-- 위 라인 관련. 클래 -->

		</div>:
		
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