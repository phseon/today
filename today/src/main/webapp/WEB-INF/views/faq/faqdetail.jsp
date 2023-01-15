<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>faq 내용</title>
<link rel = "stylesheet" href ="style.css" type="text/css"> 
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/faq.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/faqHeader.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/footerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>	
	<jsp:include page="/WEB-INF/views/faq/faqHeader.jsp"/> 
	
	<div class="content-main">
	
		<ul class="content">
			<li><h2>Q</h2>  ${faq.faq_title}</li><br><br>
			<li><h2>A</h2>  ${faq.faq_content}</li>
		</ul>
		
		<div class="align-center">
		<input type="button" value="목록" onclick="location.href='qnaPage.do'">
		
		<%--관리자일 경우 수정 삭제 가능 --%>
		<c:if test="${user_auth == 1}">
		<input type="button" value="수정" onclick="location.href='faqUpdateForm.do?faq_num=${faq.faq_num}'">
		<input type="button" value="삭제" id="delete_btn"> 
		<script type="text/javascript">
		
			let delete_btn = document.getElementById('delete_btn')
			
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('faqDelete.do?faq_num=${faq.faq_num}');
				}
			
			};
				
			</script>
			</c:if>
	 		</div>
			

	
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>