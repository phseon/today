<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시술 목록</title>
<link rel = "stylesheet" href = "${pageContext.request.contextPath }/css/procedureStyle.css">
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/headerStyle.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/headersample.jsp"/>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
<h1 id="_title">
시술 목록
</h1>
<c:if test="${user_auth == 1}">
	<div id="align_right">
	<input type="button" value="글쓰기" onclick="location.href='writeForm.do'">
	</div>
</c:if>
	<c:if test="${count ==0 }">
		등록된 게시물이 없습니다.
	</c:if>
	<c:if test="${count >0 }">
		<table id="ltable">
			<c:forEach var="pro" items="${list}" step="3">
			<tr>
				<td id="ltable_td">
					<a href="detail.do?p_num=${pro.p_num}">
					<img src="${pageContext.request.contextPath }/upload/${pro.p_imgsrc}" width="250" height="250">
					<br><span id="list_p_title">${pro.p_title }</span>
					</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<table id="mtable">
			<c:forEach var="pro" items="${list}" begin="1" step="3">
			<tr>
				<td>
					<a href="detail.do?p_num=${pro.p_num}">
					<img src="${pageContext.request.contextPath }/upload/${pro.p_imgsrc}" width="250" height="250">
					<br><span id="list_p_title">${pro.p_title }</span>
					</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<table id="rtable">
			<c:forEach var="pro" items="${list}" begin="2" step="3">
			<tr>
				<td>
					<a href="detail.do?p_num=${pro.p_num}">
					<img src="${pageContext.request.contextPath }/upload/${pro.p_imgsrc}" width="250" height="250">
					<br><span id="list_p_title">${pro.p_title }</span>
					</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>