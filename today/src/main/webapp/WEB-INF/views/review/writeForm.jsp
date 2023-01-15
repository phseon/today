<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#write_form').submit(function(){
			if($('#r_content').val().trim()==''){
				alert('내용을 입력하세요!');
				$('#r_content').val('').focus();
				return false;
			}
		});
		
		
		let my_photo;

		// 이미지 변경 시
		$('#r_imgsrc').change(function(){
				my_photo = this.files[0];
				
				if(my_photo.size > 1024*1024){
					alert('1024kbytes까지만 업로드가 가능합니다.');
					return;
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
		
		<form id="write_form" action="write.do" method="post"
		                       enctype="multipart/form-data">
			
			<div class="align-center">
			<div class="align-top">
			<table>
					<tr class="table-title">
						<td>예약 날짜 | ${rez.rev_date}</td>
					</tr>
					<tr>
						<td>예약 시간 | ${rez.rev_time}</td>
					</tr>
					<tr>
					<td>시술명 | ${myProc.p_title}</td>
					</tr>
					<tr class="check">
					<td>
					<label for="star-select">별점</label>
						<input type="radio" class="sel-star" name="star" value=0><label for="star0">☆☆☆☆☆</label>
						<input type="radio" class="sel-star" name="star" value=1><label for="star1">★☆☆☆☆</label>
						<input type="radio" class="sel-star" name="star" value=2><label for="star2">★★☆☆☆</label>
					</td>
					<tr class="check">
					<td>
					<label for="star-select" style="color:#fff;">별점</label>
						<input type="radio" class="sel-star" name="star" value=3><label for="star3">★★★☆☆</label>
						<input type="radio" class="sel-star" name="star" value=4><label for="star4">★★★★☆</label>
						<input type="radio" class="sel-star" name="star" value=5 checked="checked"><label for="star5">★★★★★</label>
					
					</td>
					</tr>
					</tr>
			</table>
			</div>
			
			<hr class="write_hr"/>
			<div class="align-bottom">
			
				<li>
					<label for="r_content"></label>
					<textarea rows="10" cols="50" 
					name="r_content" id="r_content"></textarea>
				</li>
				<li class="write_li">
					<label for="r_imgsrc" class="img_upload"></label>
					<input type="file" name="r_imgsrc"
					            id="r_imgsrc" 
					 accept="image/gif,image/png,image/jpeg">
				</li>
				<div class="aling-return">
				<input type="submit" value="등록" class="review_btn"></div>
				</div>
		</div>                     
	</form>	
</body>
</html>