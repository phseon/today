<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안내</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath }/css/procedureStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
		<h2 id="align_center">안내</h2>
		<div class="result-display">
			<div id="align_center">
				<c:if test="${!empty accessMsg}">
					${accessMsg}
				</c:if>
				<c:if test="${empty accessMsg}">
					잘못된 접속입니다.
				</c:if>
			</div>
		</div>
</body>
</html>






