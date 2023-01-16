<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>faq 목록</title>
<link rel = "stylesheet" href ="style.css" type="text/css"> 
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/faq.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel =	"stylesheet" href="${pageContext.request.contextPath}/css/faqHeader.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/footerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/faq/faqHeader.jsp"/>
	<div class="content-main">
		<div class="list-space align-right">
			<c:if test="${user_auth == 1}">
			
			<input class="write_btn" 
			type="button" value="글쓰기" onclick="location.href='faqWriteForm.do'"/>
			</c:if> 
		</div>
		<c:if test="${count == 0}">
		<div class="result-display">
			FAQ가 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table >
			<tr align="center" bgcolor="#d0d0d0" height="120%">
				<td>번호</td>
				<td>카테고리</td>
				<td>제목</td>
			</tr>
			<c:forEach var="faqlist" items="${list}">
			<tr>
				<td>${faqlist.faq_num}</td>
				<td>${faqlist.faq_type}</td>
				<td><a href="faqDetail.do?faq_num=${faqlist.faq_num}">${faqlist.faq_title}</a>
</td>
				
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${page}</div>
		</c:if>
	
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>


    