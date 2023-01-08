<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의치과 - MY PAGE</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myInfoStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	

	let photo_path = $('.my-photo').attr('src');
	let my_photo;

	// 이미지 변경 시
	$('#photo').change(function(){
		my_photo = this.files[0];
		if(!my_photo){
			$('.my-photo').attr('src',photo_path);
			return;
		}
		if(my_photo.size > 1024*1024){
			alert('1024kbytes까지만 업로드 가능합니다.');
			$('.my-photo').attr('src',photo_path); //기본이미지로 다시 변경
			$(this).val('');
			return;
		}
		let reader = new FileReader(); 
		reader.readerAsDataURL(my_photo);
		reader.onload=function(){
			$('.my-photo').attr('src',reader.result); // 사진이름 출력
		};
	});
	
	// 이미지전송버튼 눌렀을 때(빈칸이면)
	$('#photo_submit').click(function(){
		if($('#photo').val()==''){
			alert('파일을 선택하세요.');
			$('#photo').focus();
			return;
		}
		
	// 이미지 전송 버튼 눌렀을 때(빈칸이 아니면) - 서버에 파일 보내기
		//서버에 파일 전송
		let form_data = new FormData();  
		form_data.append('photo',my_photo);   // 여기라인 코드좀 알아보기
		$.ajax({
			url:'updateMyPhoto.do',
			type:'post',
			data:form_data,
			dataType:'json',
			contentType:false, //데이터 객체를 문자열로 바꿀지에 대한 설정,true면 일반 문자
			processData:false, //해당 타입을 true로 하면 일반 text로 구분
			enctype:'multipart/form-data',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용부탁드립니다.');
				}else if(param.result == 'success'){
				    alert('프로필 사진이 업로드 되었습니다.');
				    photo_path = $('.my-photo').attr('src');
				    $('#photo').val('');
				    history.go(0); //새로고침
				}else{
					alert('파일 전송 중 오류가 발생하였습니다.');
				}
			},
			error:function(){
				alert('네트워크 오류가 발생하였습니다.');
			}
		});
	})
	
	// 기본프로필로 변경 버튼을 누르면
	$('#photo_reset').click(function(){
		$.ajax({
			url:'deleteMyPhoto.do',
			type:'post',
			dataType:'json',
			contentType:false, //데이터 객체를 문자열로 바꿀지에 대한 설정,true면 일반 문자
			processData:false, //해당 타입을 true로 하면 일반 text로 구분
			enctype:'multipart/form-data',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용부탁드립니다.');
				}else if(param.result == 'success'){
				    alert('기본이미지로 변경이 되었습니다.');
				    photo_path = $('.my-photo').attr('src');
				    $('#photo').val('');
				    history.go(0); //새로고침
				}else{
					alert('파일 전송 중 오류가 발생하였습니다.');
				}
			},
			error:function(){
				alert('네트워크 오류가 발생하였습니다.');
			}
		});
	
	})
	
	// 이미지 취소버튼 눌렀을 때
	$('#photo_cancle').click(function(){
		$('.my-photo').attr('src',photo_path); //다시 그전이미지로
		$('#photo').val('');
	})
	
	// 버튼누를 시 모달창 보이기
	$('.photo-button').css('display','none');
	$('#photo_edit').click(function(){
		$('.photo-button').css('display','block');
		$('#photo_edit').css('display','none');
	})
})
</script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	
	<div class="content">
		<div class="content-head">
			<p>MY PAGE</p>
		</div>
		
		<div class="content-middle">
			<div class="middle-left">
				<c:if test="${empty member.imgsrc}">  <!--  프로필사진. db비어있으면 기본경로 출력 -->
					<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo">		
				</c:if>
				<c:if test="${!empty member.imgsrc}">
					<img src="${pageContext.request.contextPath}/upload/${member.imgsrc}" class="my-photo">		
				</c:if>
				
				
				<input type="button" value="프로필 수정 및 삭제" id="photo_edit">
				<div class="photo-button">
					<c:if test="${!empty member.imgsrc}"><!-- 프로필사진이 비어있지않다면 기본 프로필 사진으로 변경할 버튼이 보이도록 -->
						<input type="button" value="기본 프로필사진으로 변경" id="photo_reset"><br>
					</c:if>
					<input type="file" id="photo" accept="image/gif,image/png,image/jpeg"><br>
					<input type="button" value="전송" id="photo_submit">
					<input type="button" value="취소" id="photo_cancle">
				</div>
					
			</div>
			<div class="middle-right"> <!-- 바로가기 리스트 -->
				<ul>
					<li><a href="${pageContext.request.contextPath}/myinfo/modifyPasswordForm.do">비밀번호 수정</a></li>
					<li><a href="${pageContext.request.contextPath}/myinfo/reservationInfoList.do">병원예약 조회</a></li>
				</ul>
				<ul>
					<li><a href="${pageContext.request.contextPath}/myinfo/questionInfoList.do">QNA</a></li>
					<li><a href="${pageContext.request.contextPath}/myinfo/reviewInfoList.do">REVIEW</a></li>
				</ul>
				<ul>
					<li><a href="${pageContext.request.contextPath}/myinfo/deleteUserForm.do">회원 탈퇴</a></li>
				</ul>
			</div>
		</div>
		
		<div class="content-bottom"> <!-- 개인정보 -->
			<p>INFORMATION</p>
			<ul>
				<li>이름 : ${member.name}</li>
				<li>전화번호 : ${member.phone}</li>
				<li>이메일 : ${member.email}</li>
				<li>우편번호 : ${member.zipcode}</li>
				<li>주소 : ${member.address1} ${member.address2}</li>
				<li>
					<input type="button" value="개인정보 수정" onclick="location.href='modifyUserForm.do'">
				</li>
			</ul>    
		</div>
	</div>
</body>
</html>