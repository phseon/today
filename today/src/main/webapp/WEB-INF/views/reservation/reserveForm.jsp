<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 폼</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reserveForm.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#reserve_form').submit(function(){
			if($('#p_num').val()==''){
				alert('시술명을 선택해주세요.');
				$('#p_num').focus();
				return false;
			}
			if($('#date').val()==''){
				alert('시술날짜를 선택해주세요.');
				$('#date').focus();
				return false;
			}
			if($('#time').val()==''){
				alert('시술시간을 선택해주세요.');
				$('#time').focus();
				return false;
			}
			if($!('input:[type:radio][name="r_ox"]:checked');){
				alert('리뷰 작성여부를 선택해주세요.');
				$('#r_ox').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
	<br><br>
	<h2>예약 폼</h2>
	<div class="result-display">
		
		<form id="reserve_form" action="reserve.do" method="post">
			<ul>
				<li>
					<label for="procedure">시술명</label>
					<select id="p_num" name="p_num">
					<c:forEach var="procedure" items="${list}">
						<option value="${procedure.p_num}">${procedure.p_title}</option>
					</c:forEach>
					</select>
					<c:if test=""></c:if>
				</li>
				<li>
					<label for="name">예약일</label>
					<input type="date" name="date" id="date" max="">
					<script>
						var today = new Date().toISOString().substring(0, 10);
  						document.getElementById('date').setAttribute('min',today); 
					</script>
				</li>
				<li>
					<label for="time">예약시간</label>
					<input type="time" name="time" id="time">
				</li>
			</ul>
		<div class="align-center">
			<input type="submit" value="예약">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
		</form>
	</div>
</div>
</body>
</html>