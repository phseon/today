<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/doctorDetail.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		//비밀번호 입력 여부 확인
		$('#check_form').submit(function(){
			if($('#passwd').val().trim()==''){
				alert('비밀번호를 입력하세요!');
				$('#passwd').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<body>
<div class="page-main">
<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<h2>비밀번호 확인</h2>
	<div class="check-display">
	<form id="check_form" action="checkedPasswd.do" method="post">
		<input type="hidden" name="d_num" value="${d_num}">
		<ul>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" size="12" maxlength="12">
			</li>
		</ul>
		<div class="align-center button">
			<input type="submit" value="수정하기">
			<input type="button" value="목록보기" onclick="location.href='doctorList.do'">
		</div>
	</form>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>