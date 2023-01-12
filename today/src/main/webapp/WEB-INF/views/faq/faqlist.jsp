<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>faq 목록</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>	
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<div class="content-main">
		<h2>FAQ</h2>
		<h5>자주 묻는 질문</h5>
		
		<div class="list-space align-right">
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'"
			   <c:if test="${empty user_num or user_auth==2}">disabled="disabled"</c:if> />
		
		</div>
		<c:if test="${count == 0}">
		<div class="result-display">
			FAQ가 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
			<tr>
				<th>번호</th>
				<th>카테고리</th>
				<th>제목</th>
			</tr>
			<c:forEach var="faqlist" items="${list}">
			<tr>
				<td>${faqlist.faq_num}</td>
				<td><a href="detail.do?faq_num=${faqlist.faq_num}">${faqlist.faq_title}</a></td>
				<td>${faqlist.faq_title}</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${page}</div>
		</c:if>
	</div>
</div>
</body>
</html>


    