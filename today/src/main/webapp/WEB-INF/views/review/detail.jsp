<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<div class="content-main">
		<div class="align-center">
			<div class="align-left">
			<h2>예약진료 내용 영역</h2>
			<h2>${review.rev_num}</h2>
			<h2>${review.r_date}</h2>
			</div>
			<div class="align-right">
			<h2>${review.r_content}</h2>
			<!-- <h2>${rez.rev_num}</h2> -->
			</div>
		</div>
		<h2>로그인 중인 회원번호 : ${user_num}</h2>
		<h2>리뷰번호 : ${review.r_num}</h2>
		<h2>현재 리뷰 작성 회원번호: ${rez.m_num}</h2>
		
		<c:if test="${user_num == rez.m_num}">
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
		
		<!-- 댓글 시작 -->
		<div id="comm_div">
			<span class="cm-title">댓글</span>
			<form id="cm_form">
			<input type="hidden" name="r_num" 
			       value="${review.r_num}" id="r_num">
				<textarea rows="3" cols="50" name="cm_content" 
				  id="cm_content" class="cm-content"
				  <c:if test="${empty user_num}">disabled="disabled"</c:if>
				  ><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>       
				<c:if test="${!empty user_num}">
				<div id="cm_first">
					<input type="submit" value="전송">
				</div>
				<div id="cm_second">
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