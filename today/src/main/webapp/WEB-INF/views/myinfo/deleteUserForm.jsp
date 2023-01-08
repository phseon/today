<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의치과 - 회원 탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myInfoDetailStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
// 폼 전송버튼 눌렀을 때 안의 정보가 빈칸인지 확인하기
	$('#delete_form').submit(function(){
		if($('#form_id').val().trim()==''){
			alert('아이디를 입력하세요.');
			$('#form_id').val('').focus();
			return false;
		}
		if($('#form_pwd').val().trim()==''){
			alert('비밀번호를 입력하세요.');
			$('#form_pwd').val('').focus();
			return false;
		}
	})
})
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
						<label for="form_id">아이디</label>
						<input type="text" name="form_id" id="form_id" maxlength="10" autocomplete="off">                       
					</li>
					<li>
						<label for="form_pwd">비밀번호</label>
						<input type="password" name="form_pwd" id="form_pwd" maxlength="15" autocomplete="off">
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