<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/informationDetailStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/footerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>	
	<jsp:include page="/WEB-INF/views/information/information_header.jsp"/>
		<div class="content-main2">
			
			<form action="update.do" method="post" id="update_form">
				<input type="hidden" name="info_num" value="${info.info_num }">
				<ul class="content">
					<li>
						<label for="title">제목 : </label>
						<input type="text" name="title" id="title" value="${info.info_title}" maxlength="50">
					</li>
					<li>
						<label >작성자 : ${info.id }</label>
					</li>
					<li>
						<label>작성일 : ${info.info_date }</label>
					</li>
					<li>
						<textarea rows="10" cols="40" name="content" id="content">${info.info_content}</textarea>
					</li>
				</ul>
				<div class="align-center">
					<input type="submit" value="수정">
					<input type="button" value="목록" onclick="location.href='list.do'">
				</div>
			</form>
		</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
</div>
</body>
</html>