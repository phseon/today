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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerStyle.css">
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
			if(!$('input[type=radio]:checked').val()){
				alert('시술시간을 선택하세요!');
				return false;
			}
		});
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
	<br><br>
	<h2>예약 폼</h2>
	<div class="form-display">
		
		<form id="reserve_form" action="reserve.do" method="post">
			<ul>
				<!--<li>
					<label for="dr_num">의사명</label>
					<select id="dr_num" name="dr_num">
					<c:forEach var="doctor" items="${d_list}">
						<option value="${doctor.m_num}">${doctor.name}</option>
					</c:forEach>
					</select>
					<c:if test=""></c:if>
				</li>-->
				<!-- 
				<c:forEach var="procedure" items="${list}">
						<option value="${procedure.m_num}">${procedure.p_title}</option>
				</c:forEach>
				<c:if test="${dactor.m_num}!=(this.value)">
					<option value="${procedure.m_num}" disabled>${procedure.p_title}</option>
				
				</c:if>
				-->
				
				
				<li>
					 <label for="procedure">시술명</label>
					<select id="p_num" name="p_num">
					<c:forEach var="procedure" items="${list}">
						<option value="${procedure.p_num}">${procedure.p_title}</option>
						<h2>${procedure.p_num}</h2>
					</c:forEach>
					</select>
				</li>
			
				<li>
					<label for="name">예약일</label>
					<input type="date" name="date" id="date" max="">
					<script>
						var today = new Date().toISOString().substring(0, 10);
  						document.getElementById('date').setAttribute('min',today); 
					</script>
				</li>
				<li>예약시간</li>
				<li>
				<div class="timeSelect">
					<input type="radio" name="time" id="time1" value="10:00"><label for="time1">10:00</label>
					<input type="radio" name="time" id="time2" value="10:30"><label for="time2">10:30</label>
					<input type="radio" name="time" id="time3" value="11:00"><label for="time3">11:00</label>
					<input type="radio" name="time" id="time4" value="11:30"><label for="time4">11:30</label>
					<input type="radio" name="time" id="time5" value="12:00"><label for="time5">12:00</label>
					<input type="radio" name="time" id="time6" value="12:30"><label for="time6">12:30</label>
					<input type="radio" name="time" id="time7" value="13:00"><label for="time7">13:00</label>
					<input type="radio" name="time" id="time8" value="13:30"><label for="time8">13:30</label>
					<input type="radio" name="time" id="time9" value="14:00"><label for="time9">14:00</label>
					<input type="radio" name="time" id="time10" value="14:30"><label for="time10">14:30</label>
					<input type="radio" name="time" id="time11" value="15:00"><label for="time11">15:00</label>
					<input type="radio" name="time" id="time12" value="15:30"><label for="time12">15:30</label>
					<input type="radio" name="time" id="time13" value="16:00"><label for="time13">16:00</label>
					<input type="radio" name="time" id="time14" value="16:30"><label for="time14">16:30</label>
					<input type="radio" name="time" id="time15" value="17:00"><label for="time15">17:00</label>
					<input type="radio" name="time" id="time16" value="17:30"><label for="time16">17:30</label>
					<input type="radio" name="time" id="time17" value="18:00"><label for="time17">18:00</label>
					<input type="radio" name="time" id="time12" value="15:30"><label for="time12">18:30</label>
				</div>
				</li>
			</ul>
		<div class="align-center">
			<input type="submit" value="예약">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>