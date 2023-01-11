<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시술 생성</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath }/css/procedureStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#write_form').submit(function(){
		if($('#title').val().trim()==''){
			alert('제목을 입력하세요!');
			$('#title').val('').focus();
			return false;
		}
		if($('#content').val().trim()==''){
			alert('내용을 입력하세요!');
			$('#content').val('').focus();
			return false;
		}
		if($('#imgsrc').val()==''){ 
			alert('사진을 입력하세요');
			$('#imgsrc').focus();
			return false;
		}
	});
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
<h1 id="_title">
시술 생성
</h1>
	<form action="write.do" method="post" id="write_form" enctype="multipart/form-data">
		<ul>
			<li>
				<input type="text" id="title" name="title" size="20" maxlength="20" placeholder="제목을 입력하세요">
			</li>
			<li>
				
				<textarea rows="20" cols="90" id="content" name="content"  placeholder="내용을 입력하세요"></textarea>
			</li>
			<li>
				<label for="imgsrc">이미지 파일 첨부</label>
				<input type="file" name="imgsrc" id="imgsrc" accept="image/gif, image/png, image/jpeg">
			</li>
		</ul>
		<div id="align_center">
		<input type="submit" value="글생성">
		<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form>
</body>
</html>