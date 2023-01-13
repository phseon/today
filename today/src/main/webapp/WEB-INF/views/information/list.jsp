<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>

<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/informationStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/footerStyle.css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>	
	<jsp:include page="/WEB-INF/views/information/information_header.jsp"/>
	
	<div class="content-main">
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 공지사항이 없습니다.
		</div>
		</c:if>
		
		<c:if test="${count>0}">
		<table>
			<tr class="tr_title">
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
		<div class="align-right">
			
		<c:if test="${!empty user_num and user_auth==1}">
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'">
		
		</c:if>
	
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
</div>
</body>
</html>