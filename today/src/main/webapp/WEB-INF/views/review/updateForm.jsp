<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#update_form').submit(function(){
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
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<div class="content-main">
		<h2>리뷰 수정</h2>
		<form action="update.do" method="post" id="update_form"
		              enctype="multipart/form-data">
			<input type="hidden" name="r_num" 
			                       value="${review.r_num}">
			<ul>
				<li>
					${review.r_num}
					${review.m_num}
					<label for="content">내용</label>
					<textarea rows="5" cols="30" name="content" 
					   id="content">${review.r_content}</textarea>
				</li>
				<li>
					<label for="filename">파일</label>
					<input type="file" name="r_imgsrc" id="r_imgsrc"
					     accept="image/gif,image/png,image/jpeg">
					<c:if test="${!empty review.r_imgsrc}">
					<div id="file_detail">
						(${review.r_imgsrc})파일이 등록되어 있습니다.
						<input type="button" value="파일삭제" id="file_del">
					</div>	
					<script type="text/javascript">
						$(function(){
							$('#file_del').click(function(){
								let choice = confirm('삭제하시겠습니까?');
								if(choice){
									$.ajax({
										url:'deleteFile.do',
										type:'post',
										data:{r_num:${review.r_num}},
										dataType:'json',
										success:function(param){
											if(param.result == 'logout'){
												alert('로그인 후 사용하세요!');
											}else if(param.result == 'success'){
												$('#file_detail').hide();
											}else if(param.result == 'wrongAccess'){
												alert('잘못된 접속입니다.');
											}else{
												alert('파일 삭제 오류 발생');
											}
										},
										error:function(){
											alert('네트워크 오류 발생');
										}
									});
								}
							});
						});
					</script>
					</c:if>     
				</li>
			</ul>  
			<div class="align-center">
				<input type="submit" value="수정">
				<input type="button" value="취소"
				  onclick="location.href='detail.do?r_num=${review.r_num}'">
			</div>                     
		</form>
	</div>
</div>
</body>
</html>