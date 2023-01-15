<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerStyle.css">
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
	<div id = "review_header">
		<div id = "review_header_notice">오늘의 치과<br>리뷰</div>
	</div>	
</div>
	<div class="content-main">
		<div class="align-return">
			<input type="button" class="return_btn"
						  onclick="location.href='reviewPage.do'">
		</div>
		
		
		<form action="update.do" method="post" id="update_form"
		              enctype="multipart/form-data">
			<input type="hidden" name="r_num" 
			                       value="${review.r_num}">
			<div class="align-center">
			<div class="align-top">
			<table>
					<tr class="table-title">
						<td>예약 날짜 | ${review.rev_date}</td>
					</tr>
					<tr>
						<td>예약 시간 | ${review.rev_time}</td>
					</tr>
					<tr>
					<td>시술명 | ${review.p_title}</td>
					</tr>
					<tr class="check">
					<td>
					<label for="star-select">별점</label>
						<input type="radio" class="sel-star" name="star" value=0><label for="star0">☆☆☆☆☆</label>
						<input type="radio" class="sel-star" name="star" value=1><label for="star1">★☆☆☆☆</label>
						<input type="radio" class="sel-star" name="star" value=2><label for="star2">★★☆☆☆</label>
					</td>
					</tr>
					<tr class="check">
					<td>
					<label for="star-select" style="color:#fff;">별점</label>
						<input type="radio" class="sel-star" name="star" value=3><label for="star3">★★★☆☆</label>
						<input type="radio" class="sel-star" name="star" value=4><label for="star4">★★★★☆</label>
						<input type="radio" class="sel-star" name="star" value=5 checked="checked"><label for="star5">★★★★★</label>
					
					</td>
					</tr>
			</table>
			</div>
			<hr class="write_hr"/>
			<div class="align-bottom">
			<ul>
				<li>
					<label for="content"></label>
					<textarea rows="5" cols="30" name="content" 
					   id="content">${review.r_content}</textarea>
				</li>
				<li>
					<!--  
					<label for="filename"></label>
					<input type="file" name="r_imgsrc" id="r_imgsrc"
					     accept="image/gif,image/png,image/jpeg">-->
					<c:if test="${!empty review.r_imgsrc}">
					
					<div id="file_del_detail">
						 <h2>파일 [ ${review.r_imgsrc} ]이 등록되어 있습니다.</h2>
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
												$('#file_del_detail').hide();
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
				<div>
				<input type="submit" class="update_btn" value="수정">
				</div>
			</div>
			</div>                    
		</form>

	</div>
</body>
</html>