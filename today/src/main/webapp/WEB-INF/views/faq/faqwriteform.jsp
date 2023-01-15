<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 글쓰기</title>
<link rel = "stylesheet" href ="style.css" type="text/css"> 
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/faq.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/faqHeader.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/footerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
	$(function(){
		$('#register_form').submit(function(){
			if($('#faq_title').val().trim()==''){
				alert('제목을 입력하세요');
				$('#faq_title').val('').focus();
				return false;
			}
		if($('input[type=radio]:checked').length<1){
				alert('문의 종류를 선택하세요');
				return false;
			} 
			  
		if($('#faq_content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#faq_content').val('').focus();
			return false; 
		}
		
	}
	});
});
</script>

</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/faq/faqHeader.jsp"/> 
	<div class="content-main">
		
		<h2 class="align-center">FAQ 쓰기</h2>
		<form id="write_form" action="faqWrite.do" method="post"
		                       enctype="multipart/form-data">
		                     
		                     
			<ul class="content">
				<li>
					<label for="faq_title">제목</label>
					<input type="text" name="faq_title" id="faq_title"
					                          maxlength="50">
				</li>
				<li class="radiobt">
						<label for="faq_type">문의종류</label>
						
						<input type="radio" id="chk_type1" name="faq_type" value="시술문의">시술문의
				 
				        <input type="radio" id="chk_type2" name="faq_type" value="예약문의">예약문의
				 
				        <input type="radio" id="chk_type3" name="faq_type" value="기타">기타
												
					</li>
				<li>
					<label for="faq_content">내용</label>
					<textarea rows="5" cols="30" 
					name="faq_content" id="faq_content"></textarea>
				</li>
				
			</ul> 
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="취소" 
					onclick="location.href='qnaPage.do'">
			</div>  
			                 
		</form>
		
	</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>