<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/review.comm.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<div class="content-main">
		<div class="align-center">
			<div class="align-left">
			<h2>예약진료 내용 영역</h2>
			<h2>${myRez.rev_date}</h2>
			<h2>${myProc.p_title}</h2>
			<h2>예약진료 내용 영역</h2>
			<h2>${review.rev_num}</h2>
			<h2>${review.r_date}</h2>
			<h2>${review.dr_name}</h2>
			<h2>${review.p_title}</h2>
			</div>
			<div class="align-right">
			<h2>${review.r_content}</h2>
			<!-- <h2>${rez.rev_num}</h2> -->
			</div>
		</div>
		<h2>로그인 중인 회원번호 : ${user_num}</h2>
		<h2>리뷰번호 : ${review.r_num}</h2>
	<!-- <h2>rez현재 리뷰 작성 회원번호: ${rez.m_num}</h2> -->	
		<h2>review현재 리뷰 작성 회원번호: ${review.m_num}</h2>
		<h2>별점: ${review.star}</h2>
		<!-- 조건문 해보기 -->
		<c:if test="'${review.star}'==5">
			<h2>★★★★★</h2>
		</c:if>
		
		<!-- <h2>로그인한 회원의 예약번호: ${rezInfo.rev_num}</h2>
		리뷰 작성 폼에서만 보이기. 여러개면 리스트로 가져오기? -->
		
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
			
		<c:if test="${user_num == review.m_num}">
				<input type="button" value="수정" 
				onclick="location.href='updateForm.do?r_num=${review.r_num}'">
				<input type="button" value="삭제" id="delete_btn">
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
		
		<!-- 
		<form action="update.do" method="post" id="update_form"
		              enctype="multipart/form-data">
		-->
			<input type="hidden" name="r_num" 
			                       value="${review.r_num}">
			<input type="hidden" name="m_num" 
			                       value="${comm.m_num}">
		<!-- 댓글 시작 -->
		<div id="comm_div">
			<span class="c-title">댓글</span>
			<form id="c_form" enctype="multipart/form-data">
			<!-- <form action="listComm.do" method="post" id="c_form" enctype="multipart/form-data">
			-->
			<!-- DetailRevAction에 넘어가는 r_num -->
			<input type="hidden" name="r_num" 
			       value="${review.r_num}" id="r_num">
			<!-- 
			<input type="hidden" name="review_num" 
			       value="${review.r_num}" id="r_num">
			<input type="hidden" name="review_num" 
			       value="${comm.r_num}" id="r_num">
			       -->
			       <h2>aa${review.r_num}</h2>
				<textarea rows="3" cols="50" name="c_content" 
				  id="c_content" class="comm-content"
				  <c:if test="${empty user_num}">disabled="disabled"</c:if>
				  ><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>       
				<c:if test="${!empty user_num}">
				<div id="c_second">
					<input type="submit" value="전송">
				</div>
				<div id="c_first">
					<span class="letter-count">300/300</span>
				</div>
				</c:if>
			</form>
		</div>
		<!-- 댓글 목록 출력 시작 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
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