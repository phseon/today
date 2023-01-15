<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/review.comm.js"></script>

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
			<c:if test="${user_num == review.m_num}">
				<input type="button" value="수정" class="update_btn"
				onclick="location.href='updateForm.do?r_num=${review.r_num}'">
				<input type="button" value="삭제" class="delete_btn" id="delete_btn">
				<script type="text/javascript">
					let delete_btn = document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick=function(){
						let choice = confirm('리뷰를 삭제하시겠습니까?');
						if(choice){
							location.replace('delete.do?r_num=${review.r_num}');
							}
						};
					</script>
			</c:if>
		</div>
		
		<div class="align-center">
			<div class="align-top">
			<h2>${review.dr_name}
				<a style="font-size:17px;font-weight:normal;"> 의사</a></h2>
			<h2>${review.p_title}</h2>
			<div style="display:inline-block;width:1px;height:23px;
			background-color:#bababa;vertical-align:middle;margin:0 6px;"></div>
			<h2 class="star">
			<c:if test="${review.star == 1}">
				★☆☆☆☆</c:if>
				<c:if test="${review.star == 2}">
				★★☆☆☆</c:if>
				<c:if test="${review.star == 3}">
				★★★☆☆</c:if>
				<c:if test="${review.star == 4}">
				★★★★☆</c:if>
				<c:if test="${review.star == 5}">
				★★★★★</c:if>
			</h2>
			<h2>${review.r_date} 작성</h2>
			</div>
			<hr class="review_hr"/>
		<div class="align-bottom">
			<h2>${review.r_content}</h2>
			<!-- <h2>${rez.rev_num}</h2> -->
			</div>
		
		</div>
		
		<form action="detail.do" method="post" id="detail"
		              enctype="multipart/form-data">
			<input type="hidden" name="r_num" 
			                       value="${review.r_num}">
		</form>	
		<c:if test="${!empty review.r_imgsrc}">
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/upload/${review.r_imgsrc}" class="detail-img">
		</div>
		</c:if>
			
			<input type="hidden" name="r_num" 
			                       value="${review.r_num}">
			<input type="hidden" name="m_num" 
			                       value="${comm.m_num}">
		<!-- 댓글 시작 -->
		<div class="align-comm">
			<span class="c-title">댓글 </span>
			<div class="zz">
			<div id="c_count"></div>
			</div>
			<form id="c_form" enctype="multipart/form-data">
			
			<!-- DetailRevAction에 넘어가는 r_num -->
			<input type="hidden" name="r_num" 
			       value="${review.r_num}" id="r_num">
			
			        <textarea rows="3" cols="50" name="c_content" 
				  id="c_content" class="comm-content"
				  <c:if test="${empty user_num}">disabled="disabled"</c:if>
				  ><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>       
				<c:if test="${!empty user_num}">
				<div class="c_submit">
				<div id="c_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="c_second">
					<input type="submit" value="등록">
				</div>
				</div>
				</c:if>
			</form>
		<!-- 댓글 목록 출력 시작 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" class="more_comm" value="댓글 더보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글 끝 -->
		</div>
</div>
</body>
</html>