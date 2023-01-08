<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의치과 - 비밀번호 변경</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myInfoDetailStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
//비밀번호 변경 폼 전송시 작성안된 란 확인하기
	$('#password_form').submit(function(){
		if($('#id').val().trim()==''){ // 아이디가 빈칸일 때
			alert('아이디를 작성하시길 바랍니다.');
			$('#id').val('').focus();
			return false;
		}
		if($('#origin_pwd').val().trim()==''){ // 비밀번호가 빈칸일 때
			alert('비밀번호를 입력하시기 바랍니다.');
			$('#origin_pwd').val('').focus();
			return false;
		}
		if($('#pwd').val().trim()==''){ // 새 비밀번호가 빈칸일 때
			alert('새 비밀번호를 입력하시기 바랍니다.');
			$('#pwd').val('').focus();
			return false;
		}
		if($('#check_pwd').val().trim()==''){ // 새 비밀번호 확인이 빈칸일 때
			alert('새 비밀번호 확인을 입력하시기 바랍니다.');
			$('#check_pwd').val('').focus();
			return false;
		}
		if($('#pwd').val()!=$('#check_pwd').val()){
			alert('비밀번호와 새 비밀번호가 일치하지 않습니다.');
			$('#check_pwd').val('');
			$('#pwd').val('').focus();
			return false;
		}
	})
});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	
	<div class="content">
		<div class="content-head">
			<p>비밀번호 수정</p>
		</div>

		<div class="content-main">
			<form id="password_form" action="modifyPassword.do" method="post">
				<ul>
					<li>
						<label for="id">아이디</label>
						<input type="text" name="id" id="id" maxlength="10" autocomplete="off">                       
					</li>
					<li>
						<label for="origin_pwd">비밀번호</label>
						<input type="password" name="origin_pwd" id="origin_pwd" maxlength="15" autocomplete="off">
					</li>
					<li>
						<label for="pwd">새 비밀번호</label>
						<input type="password" name="pwd" id="pwd" maxlength="15" autocomplete="off">
					</li>
					<li>
						<label for="check_pwd">비밀번호 확인</label>
						<input type="password" name="check_pwd" id="check_pwd" maxlength="15" autocomplete="off">
					</li>
				</ul>
				<div class="main-button">
					<input type="submit" value="비밀번호 수정">
					<input type="button" value="마이페이지" onclick="location.href='myPage.do'">
				</div>                                   
			</form>
		</div>
	</div>
</body>
</html>