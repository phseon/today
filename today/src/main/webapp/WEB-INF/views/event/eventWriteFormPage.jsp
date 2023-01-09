<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 글쓰기 페이지</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventHeaderStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventBodyStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventDetailStyle.css">
<script type="text/javascript" src = "${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function submit_eventwrite_form() {
		if ($('#e_title').val().trim() == '') {
			alert('제목을 입력해주세요.');
			$('#e_title').val('').focus();
			return false;
		}
		if ($('#e_startdate').val() == '') {
			alert('이벤트 시작일을 선택해주세요.');
			$('#e_startdate').focus();
			return false;
		}
		if ($('#e_enddate').val() == '') {
			alert('이벤트 마감일을 선택해주세요.');
			$('#e_enddate').focus();
			return false;
		}
		if ($('#e_imgsrc').val() == '' && $('#e_content').val() == '') {
			alert('이벤트 내용 입력 또는 이미지를 선택해주세요.');
			$('#e_imgsrc').focus();
			$('#e_content').focus();
			return false;
		}
		if ($('#e_thumb').val() == '') {
			alert('이벤트 썸네일을 선택해주세요.');
			$('#e_thumb').focus();
			return false;
		}
		let start = $('#e_startdate').val();
		let end = $('#e_enddate').val();
		
		let splitStart = start.split('-');
		let splitEnd = end.split('-');
		
		let startDate = new Date(splitStart[0], splitStart[1], splitStart[2]);
		let endDate = new Date(splitEnd[0], splitEnd[1], splitEnd[2]);
		
		if(startDate.getTime() > endDate.getTime()){
			alert('이벤트 시작일은 종료일보다 빨라야 합니다.');
			$('#e_startdate').focus();
			return false;
		}
		
		$('#eventwrite_form').submit();
	}
	$(function(){
		$('#e_rcheck').click(function(){
			let is_checked = $('#e_rcheck').is(':checked');
			
			if(is_checked){
				$('#res_btn_hide').show();
				$('#e_rcheck').val('true');
			}else{
				$('#res_btn_hide').hide();
				$('#e_rcheck').val('false');
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<jsp:include page="/WEB-INF/views/event/event_header.jsp"/>
	<div>                      
		<form id = "eventwrite_form" action="eventWritePage.do" method = "post" enctype = "multipart/form-data">
			<div class = "event-detail-title">
				<label for = "e_title">이벤트제목</label> : 
				<input type = "text" id = "e_title" name = "e_title" placeholder="제목을 입력하세요" maxlength = "50">
			</div>
			<div class = "event-detail">
				<span class = "event-detail-left">
				<label for = "e_startdate">시작일</label> : 
				</span>
				<input type = "date" id = "e_startdate" name = "e_startdate">
				<span class = "event-detail-left">
				<label for = "e_enddate">마감일</label> : 
				</span>
				<input type = "date" id = "e_enddate" name = "e_enddate">
				<div class = "event-detail-right">
					<span>
					<label for = "e_rcheck">예약버튼</label> 표시 : 
					</span>
					<input type = "checkbox" id = "e_rcheck" name = "e_rcheck">
				</div>
			</div>				
			<div style = "width : 1400px; margin : 0 auto;">
				<span>
				<label for = "e_thumb">썸네일파일</label> 첨부 : 
				</span>
				<input type = "file" name = "e_thumb" id = "e_thumb" accept = "image/gif, image/png, image/jpeg">
				<span>
				<label for = "e_imgsrc">이미지파일</label> 첨부 : 
				</span>
				<input type = "file" name = "e_imgsrc" id = "e_imgsrc" accept = "image/gif, image/png, image/jpeg">
			</div>
			<div class = "event-detail-content">
				<textarea id = "e_content" name = "e_content" placeholder = "내용을 입력하세요"></textarea>
				<br>
				
				<div id = "res_btn_hide" style = "display : none;">
					<input class = "reserve_btn" type = "button" value = "예약하기">
				</div>
				
			</div>
		</form>
	</div>
	<div class = "e-buttons">
		<%-- <c:if test = "${auth == 1}"> --%>
			<span class = "show-event-list">
				<a href = "eventPage.do">취소</a>
			</span>
			<span class = "show-event-list">
				<a href = "#" onclick = "return submit_eventwrite_form()">등록</a>
			</span>
		<%-- </c:if> --%>
		<span class = "show-event-list">
			<a href = "eventPage.do">목록</a>
		</span>
	</div>

</body>
</html>