<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/member.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/footerStyle.css">
</head>
<body>
<div class="page-main"> 
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>	

	<div class="content-main">
		<h2>회원가입 완료</h2>
		<hr width="30%" size="1" noshade="noshade" >
		<div class="result-display">
			<div class="align-center">
				회원가입이 완료되었습니다.<p>
				<input type="button" value="메인으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</div>
	</div>
	
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
</body>
</html>