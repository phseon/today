<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 글 수정</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventHeaderStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventBodyStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/eventDetailStyle.css">
<script type="text/javascript" src = "${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function submit_eventupdate_form() {
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
		$('#eventupdate_form').submit();
	}

	function deleteFileAjax(id){
		let choice = confirm('삭제하시겠습니까?');
		if(choice){
			$.ajax({
				url : 'eventDeleteFile.do',
				type : 'post',
				data : {e_num : ${event.e_num},
						e_imgType : id},
				dataType : 'json',
				success : function(param){
					if(param.result == 'logout'){
						alert('로그인 후 사용하세요.');
					}else if(param.result == 'success'){
						//$(this).parent().hide();
						if(id == 'e_thumb'){
							$('#file_detail1').hide();
						}else{
							$('#file_detail2').hide();
						}
					}else if(param.result == 'wrongAccess'){
						alert('권한이 없습니다.');
					}else{
						alert('파일 삭제 오류 발생');
					}
				},
				error : function(){
					alert('네트워크 오류 발생');
				}
			});
		}
	}
	
	$(function(){
		$('#e_rcheck').click(function(){
			let is_checked = $('#e_rcheck').is(':checked');
			
			if(is_checked){
				$('#res_btn_hide').show();
				$('#e_rcheck').val('true');
				console.log($(this).attr('id'));
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
		<form id = "eventupdate_form" action="eventUpdatePage.do" method = "post" enctype = "multipart/form-data">
			<input type = "hidden"	name = "e_num" value = "${event.e_num}">
			<div class = "event-detail-title">
				<label for = "e_title">이벤트제목</label> : 
				<input type = "text" id = "e_title" name = "e_title" value = "${event.e_title}" placeholder="제목을 입력하세요" maxlength = "50">
			</div>
			<div class = "event-detail">
				<span class = "event-detail-left">
				<label for = "e_startdate">시작일</label> : 
				</span>
				<input type = "date" id = "e_startdate" name = "e_startdate" value = "${event.e_start}">
				<span class = "event-detail-left">
				<label for = "e_enddate">마감일</label> : 
				</span>
				<input type = "date" id = "e_enddate" name = "e_enddate" value = "${event.e_end}">
				<div class = "event-detail-right">
					<span>
					<label for = "e_rcheck">예약버튼</label> 표시 : 
					</span>
					<input type = "checkbox" id = "e_rcheck" name = "e_rcheck" <c:if test="${event.e_resbtn == 'true'}">checked = "checked"</c:if>>
				</div>
			</div>				
			<div style = "width : 1400px; margin : 0 auto;">
				<span>
				<label for = "e_thumb">썸네일파일</label> 첨부 : 
				</span>
				<input type = "file" name = "e_thumb" id = "e_thumb" accept = "image/gif, image/png, image/jpeg">
				<c:if test="${!empty event.e_thumb}">
				<span id = "file_detail1">
					(${event.e_thumb})파일이 등록되어 있습니다.
					<input type = "button" value = "파일삭제" id = "aaa" onclick = "deleteFileAjax('e_thumb')">
				</span>
				</c:if>
				<span>
				<label for = "e_imgsrc">이미지파일</label> 첨부 : 
				</span>
				<input type = "file" name = "e_imgsrc" id = "e_imgsrc" accept = "image/gif, image/png, image/jpeg">
				<c:if test="${!empty event.e_imgsrc}">
				<span id = "file_detail2">
					(${event.e_imgsrc})파일이 등록되어 있습니다.
					<input type = "button" value = "파일삭제" onclick = "deleteFileAjax('e_imgsrc')">
				</span>
				</c:if>
			</div>
			<div class = "event-detail-content">
				<textarea id = "e_content" name = "e_content" placeholder = "내용을 입력하세요">${event.e_content}</textarea>
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
				<a href = "eventDetail.do?e_num=${event.e_num}">취소</a>
			</span>
			<span class = "show-event-list">
				<a href = "#" onclick = "return submit_eventupdate_form()">수정</a>
			</span>
		<%-- </c:if> --%>
		<span class = "show-event-list">
			<a href = "eventPage.do">목록</a>
		</span>
	</div>

</body>
</html>