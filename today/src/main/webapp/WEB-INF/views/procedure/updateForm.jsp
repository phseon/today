<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시술 수정</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath }/css/procedureStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventHeaderStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){ 
	$('#update_form').submit(function(){
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
	});
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
<jsp:include page="/WEB-INF/views/procedure/procedure_header.jsp"/>
<h1 id="_title">
시술 정보 수정
</h1>
<form action="update.do" method="post" id="update_form" enctype="multipart/form-data">
	<input type="hidden" name="p_num" value="${pro.p_num}">
	<ul>
		<li>
			<label for="title">제목</label>
			<input type="text" id="title" name="title" value="${pro.p_title }"size="20" maxlength="20">
		</li>
		<li>
			<label for="content">내용</label>
			<textarea rows="20" cols="80" id="content" name="content">${pro.p_content }</textarea>
		</li>
		<li>
			<label for="imgsrc">파일</label>
			<input type="file" name="imgsrc" id="imgsrc" accept="image/gif, image/png, image/jpeg">
			<c:if test="${!empty pro.p_imgsrc}">
			<div id="file_detail">
				[${pro.p_imgsrc}]파일이 등록되어 있습니다
				<input type="button" value="파일삭제" id="image_del">
			</div>
				<script type="text/javascript">
					$(function(){
						$('#image_del').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'imageDel.do',
									type:'post',
									data:{p_num:${pro.p_num}},
									dataType:'json',
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요');
										}else if(param.result == 'success'){
											$('#file_detail').hide();
										}else if(param.result == 'wrongAccess'){
											alert('잘못된 접속입니다');
										}else{
											alert('파일 삭제 오류');
										}
									},
									error:function(){
										alert('네트워크 오류 발생');
									}
								})
							}
						});
					});
				</script>
			</c:if>
		</li>
	</ul>
	<div id="align_center">
		<input type="submit" value="수정">
		<input type="button" value="글상세" onclick="location.href='detail.do?p_num=${pro.p_num}'">
	</div>
</form>
<br><br><br><br><br><br>
</body>
</html>