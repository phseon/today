<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:if test="${check}">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 완료</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<div style="width: 100vw; height: 500px;">
		<div style="position: absolute; left: 50%; transform:translateX(-50%); margin-top: 200px;">
			<p>회원탈퇴가 성공적으로 이루어졌습니다.</p>
			<div style="position: absolute; left: 50%; transform:translateX(-50%); margin-top: 20px;">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/main/main.do'" value="메인페이지 이동"
				style="background:white; font-size: 15px; border: 1.5px solid grey; padding: 5px; cursor: pointer;">
			</div>
		</div>
	</div>

</body>
</html>
</c:if>
<c:if test="${!check}">
	<script>
		alert('입력한 정보가 정확하지 않습니다.');
		history.go(-1);
	</script>
</c:if>


