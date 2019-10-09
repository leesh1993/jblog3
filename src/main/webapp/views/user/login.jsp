<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/mainmenu.jsp" />
		<form:form 
			modelAttribute="userVo" 
			class="join-form" 
			id="join-form"
			method="post"
			 action="${pageContext.servletContext.contextPath }/user/auth">
      		<label>아이디</label> 
			<form:input path = "id"/>
			<label class="block-label" >패스워드</label>
			<form:password path = "password"/>
			<c:choose>
				<c:when test="${result eq 'fail'}">
					<p>
						로그인이 실패 했습니다. 
					</p>
				</c:when>
			</c:choose>
			<input type="submit" value="로그인">
		</form:form>
	</div>
</body>
</html>
