<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 리스트</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<div id = "review_header">
		<div id = "review_header_notice">오늘의 치과<br>리뷰</div>
	</div>
	<div id = "review_title">
		<h1>회원 리뷰</h1>
		<img src = "${pageContext.request.contextPath}/images/dividing_line.png">
	</div>
	<div class="content-main">
		<!-- 검색 폼 시작 -->
		<form id="search_form" action="reviewPage.do" method="get">
			<ul class="search_review">
			<ul class="search">
				<li>
					<select name="keyfield" class="select">
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>내용</option>
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>의사</option>
						<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>시술</option>
					</select>
				</li>
				<li>
					<div class="search_form">
					<input type="search" size="16" name="keyword" class="keyword"
					       id="keyword" value="${param.keyword}">
					<input type="submit" class="search_button" value="">
					</div>
				</li>
				</ul>
			
			<h2 style="display:none;">리뷰 작성여부:${rez.r_ox}</h2>
		
			<c:if test="${rez.r_ox=='T'}">
			<input type="button" value="리뷰 내역" class="review_btn"
			   onclick="location.href='${pageContext.request.contextPath}/myinfo/reviewInfoList.do'"/>
			</c:if>
			
			<c:if test="${rez.r_ox=='F'}">
			<input type="button" value="리뷰 작성" class="review_btn"
				   onclick="location.href='writeForm.do'"
				   <c:if test="${empty user_num}">disabled="disabled"</c:if>/>
			</c:if>
			</ul>
		</form>
		<!-- 검색 폼 끝 -->
		
		<!--
		<h2>리뷰 작성번호:${rez.rev_num}</h2>
		<h2>리뷰 작성 회원번호:${rez.m_num}</h2>
		-->
		
			
		<!-- list로 목록 넣으면 선택 가능 -> 리뷰 작성으로 수정 -> 작성 폼에서 선택 -->
		<!-- 리뷰 내역 유지하려면 내역 페이지에서도 수정, 삭제 넣어야 함 -->
		
		<!-- 위 라인 관련. 클래 -->

		</div>
		
		<c:if test="${count == 0}">
		<div class="result-display">
			작성된 리뷰가 없습니다.
		</div>
		</c:if>
		
		<c:if test="${count > 0}">
		

		
		
		<c:forEach var="review" items="${list}">
   		 <hr class="table_hr"/>
		<table class="loc_detail" onclick="location.href='detail.do?r_num=${review.r_num}'" style="cursor:pointer;">
			<tr>
				<td width="200px" height="30px" class="td_pad_top">${review.dr_name}<a style="font-size:17px;font-weight:normal;"> 의사</a></td>
				<td rowspan="4" class="table_content">
					<c:set var="TextValue" value="${review.r_content}"/>
	    			${fn:substring(TextValue,0,120)}
	    				<c:if test="${fn:length(TextValue)>120}"><a class="more">...더보기</a></c:if>
	    		</td>
			</tr>
			<tr>
				<td width="200px" class="td_pad">${review.p_title}</td>
			</tr>
			<tr>
				<td class="td_star">
				<c:if test="${review.star == 1}">
				★☆☆☆☆</c:if>
				<c:if test="${review.star == 2}">
				★★☆☆☆</c:if>
				<c:if test="${review.star == 3}">
				★★★☆☆</c:if>
				<c:if test="${review.star == 4}">
				★★★★☆</c:if>
				<c:if test="${review.star == 5}">
				★★★★★</c:if>
				</td>
			</tr>
			<tr>
				<td class="td_pad_bottom">${review.r_date} 작성</td>	
			</tr>
		</table>
		</c:forEach>
		</c:if>
		<div class="margin"></div>
		<!-- 
		<c:if test="${count > 0}">
		
		<c:forEach var="review" items="${list}">
		<table class="loc_detail" onclick="location.href='detail.do?r_num=${review.r_num}'" style="cursor:pointer;">
			<tr>
				<td width="200px" height="30px">의사명 | ${review.dr_name}</td>
				<td width="500px"></td>
				<c:if test="${review.star == 1}">
				<td>리뷰별점 | ★☆☆☆☆</td></c:if>
				<c:if test="${review.star == 2}">
				<td>리뷰별점 | ★★☆☆☆</td></c:if>
				<c:if test="${review.star == 3}">
				<td>리뷰별점 | ★★★☆☆</td></c:if>
				<c:if test="${review.star == 4}">
				<td>리뷰별점 | ★★★★☆</td></c:if>
				<c:if test="${review.star == 5}">
				<td>리뷰별점 | ★★★★★</td></c:if>
			</tr>
			<tr>	
				<td width="200px">시술명 | ${review.p_title}</td>
				<td width="500px"></td>	
				<td>작성날짜 | ${review.r_date}</td>	
			</tr>
			<tr>	
				<td colspan="3" class="table_content">
				${review.r_content}</td>	
			</tr>
		</table>
		</c:forEach>
		</c:if>
		-->
		
	</div>
</div>
</body>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
</html>