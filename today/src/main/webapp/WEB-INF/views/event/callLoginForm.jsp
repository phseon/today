<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function getContextPath(){
		var hostIndex = location.href.indexOf(location.host) + location.host.length;
		var contextPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
		
		return contextPath;
	}
	alert('로그인 후 이용 가능합니다.');
	location.href = getContextPath() + '/member/loginForm.do'; 
</script>