<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/informationDetailStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/footerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>	
	<jsp:include page="/WEB-INF/views/information/information_header.jsp"/>
	
	<div class="content-main">
		
		<ul class="content">
			<li>제목 : ${info.info_title}<li>
			<li>작성자 : ${info.id}<li>
			<li>작성일 : ${info.info_date}<li>
			<table>
				<tr>
					<td>${info.info_content}</td>
				</tr>
			</table>
		
		</ul>
		
		<%--로그인한 회원번호와 작성자 회원번호가 일치해야 수정,삭제 가능 --%>
		<c:if test="${user_num == info.m_num}">
		<input type="button" value="수정" onclick="location.href='updateForm.do?info_num=${info.info_num}'">
		<input type="button" value="삭제" id="delete_btn"> 
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn')
			//이벤트 연결
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?info_num=${info.info_num}');
				}
			};
		</script>
		</c:if>
		<input type="button" value="목록" onclick="location.href='list.do'">
 		
		
		
		
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
</div>
</body>
</html>