<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>시술 삭제</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath }/css/procedureStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventHeaderStyle.css">

</head>
<body>
<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
<jsp:include page="/WEB-INF/views/procedure/procedure_header.jsp"/>
<h1 id="_title">
시술 정보 삭제
</h1>
<br>
<form action="delete.do" method="post" id="delete_form">
	<input type="hidden" name="p_num" value="${pro.p_num}">
	<div id="align_center">
	<label for="title">비밀번호</label>
	<input type="password" id="passwd" name="passwd" size="15" maxlength="15">
	</div>
	<div id="align_center">
	<input type="submit" value="삭제">
	<input type="button" value="글상세" onclick="location.href='detail.do?p_num=${pro.p_num}'">
	</div>
</form>
<br><br><br><br><br><br>
</body>
</html>