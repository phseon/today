<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>	
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<div class="content-main">
	<form id="write_form" action="write.do" method="post">
		<h2>공지사항</h2>
		<hr width="100%" size="1" noshade="noshade">
		<ul>
			<li>
				<label for="title">제목</label>
				<input type="text" name="title" id="title" maxlength="50">
			</li>
			<li>
				<label for="pwd">비밀번호</label>
				<input type="password" name="pwd" id="pwd" maxlength="20">
			</li>
			<li>
				<label for="content">내용</label>
				<textarea rows="10" cols="40" name="content" id="content"></textarea>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="글쓰기">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form>
	</div>
</div>
</body>
</html>