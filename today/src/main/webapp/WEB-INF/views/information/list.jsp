<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>

<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/member.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>	
	
	<div class="content-main">
		<h2>공지사항</h2>
		<hr width="100%" size="1" noshade="noshade">
		
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 공지사항이 없습니다.
		</div>
		</c:if>
		
		<c:if test="${count>0}">
		<table>
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="infolist" items="${list}">
			<tr>
				<td>${infolist.info_num}</td>
				<td><a href="detail.do?info_num=${infolist.info_num}">${infolist.info_title}</a></td>
				<td>${infolist.id}</td>
				<td>${infolist.info_date}</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${page}</div>
		</c:if>
		<div class="list-space align-right">
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'"
				<c:if test="${empty user_num or user_auth==2}">disabled="disabled"</c:if> />
	
		</div>
	</div>
</div>
</body>
</html>