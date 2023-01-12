<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/mainStyle.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>

	<div class="main">
	<img src="${pageContext.request.contextPath}/images/mainBackground05.jpg" class="back-img">
		<div class="main-content">
			<h2>자신있게 미소지을 하얀 치아를 원한다면!</h2>
			<p>당신의 삶에 자신감을 심어드립니다.</p>
		</div>
	</div>
	
	
</body>
</html>