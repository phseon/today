<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안내</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reserveForm.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerStyle.css">
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>	
	<div class="content-main">
		<h2>안내</h2>
		<div class="result-display">
			<div class="align-center">
				<c:if test="${!empty accessMsg}">
					<h2>${accessMsg}</h2>
				</c:if>
				<c:if test="${empty accessMsg}">
					<h2>잘못된 접속입니다.</h2>
				</c:if>
				<p>
				<c:if test="${!empty accessUrl}">
					<input type="button" value="마이페이지"
					    onclick="location.href='${accessUrl}'">
				</c:if>
				<c:if test="${empty accessUrl}">
					<input type="button" value="홈으로"
					   onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				</c:if>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>






