<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<div class="content-main">
		<h2>리뷰 등록</h2>
		<form id="write_form" action="write.do" method="post"
		                       enctype="multipart/form-data">
			<ul>
				<li>
					예약 날짜 : ${rez.rev_time} !!<br>
					<h2>예약진료 내용 영역</h2>
					<h2>${myRez.rev_date}</h2>
					<h2>${myProc.p_title}</h2>
				</li>
				<li>
					<label for="r_content">내용</label>
					<textarea rows="10" cols="50" 
					name="r_content" id="r_content"></textarea>
				</li>
				<li>
					<label for="r_imgsrc">파일</label>
					<input type="file" name="r_imgsrc" 
					            id="r_imgsrc" 
					 accept="image/gif,image/png,image/jpeg">
				</li>
			</ul> 
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="취소"
				            onclick="location.href='reviewPage.do'">
			</div>                      
		</form>
	</div>
</div>
</body>
</html>