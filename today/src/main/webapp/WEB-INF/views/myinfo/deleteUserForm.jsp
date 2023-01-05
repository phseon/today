<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 폼</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myInfoDetailStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	
	<div class="content">
		<div class="content-head">
			<p>회원탈퇴</p>
		</div>

		<div class="content-main">
			<form id="delete_form" action="deleteUser.do" method="post">
				<ul>
					<li>
						<label for="id">ID</label>
						<input type="text" name="id" id="id" maxlength="10" autocomplete="off">                       
					</li>
					<li>
						<label for="pwd">PASSWORD</label>
						<input type="password" name="pwd" id="pwd" maxlength="15" autocomplete="off">
					</li>
				</ul>
				<div class="main-button">
					<input type="submit" value="회원 탈퇴">
					<input type="button" value="마이페이지" onclick="location.href='myPage.do'">
				</div>                                   
			</form>
		</div>
	</div>
</body>
</html>