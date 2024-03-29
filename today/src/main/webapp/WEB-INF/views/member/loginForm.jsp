<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/member.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/footerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
 	$(function(){
 		$('#login_form').submit(function(){
 			if($('#id').val().trim()==''){
 				alert('아이디를 입력하세요');
 				$('#id').val('').focus();
 				return false;
 			}
 			if($('#pwd').val().trim()==''){
 				alert('비밀번호를 입력하세요');
 				$('#pwd').val('').focus();
 				return false;
 			}
 		});
 	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>	
	
	<div class="content-main">
		<form id="login_form" action="login.do" method="post">
			<h2>로그인</h2>
			<hr width="30%" size="1" noshade="noshade" >
			<ul>
				<li>
					<label for="id"></label>
					<input type="text" name="id" id="id" maxlength="15" autocomplete="off" placeholder="아이디">
				</li>
				<li>
					<label for="pwd"></label>
					<input type="password" name="pwd" id="pwd" maxlength="20" placeholder="비밀번호">
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="로그인">
			</div>
			<div class="align-center">
				<p><a class="register_link" href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a></p>
			</div>
		</form>	
	</div>
	
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
</body>
</html>