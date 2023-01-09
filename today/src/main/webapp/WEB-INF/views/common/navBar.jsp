<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="main-bg sticky">
	<!-- sticky -->
	<div class="main_nav">
		<div id="menu_nav">
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/information/list.do">공지사항</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath}/doctor/doctorList.do">의료진소개</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath}/surgery/sergPage.do">시술</a>
				</li>
				<li><a href="${pageContext.request.contextPath}/qna/qnaPage.do">QnA</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath}/review/reviewPage.do">리뷰</a>
				</li>
				<li><a
					href="${pageContext.request.contextPath}/event/eventPage.do">이벤트</a>
				</li>
			</ul>
		</div>
		<div id="login_nav">
			<ul>
				<c:if test="${empty user_num}">
					<li><a
						href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a></li>
					<li><a
						href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a></li>
				</c:if>
				<c:if test="${!empty user_num}">
					<li><a
						href="${pageContext.request.contextPath}/member/myPage.do">마이페이지</a></li>
					<li><a
						href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>