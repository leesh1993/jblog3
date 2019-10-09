<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
	$(function() {
		$("#id").change(function() {
			$("#btn-check-id").show();
			$("#img-check-id").hide();
		});

		$("#btn-check-id").click(function() {
							var id = $("#id").val();
							if (id == "") {
								return;
							}

							// ajax 통신
							$.ajax({
										url : "${pageContext.servletContext.contextPath }/api/user/checkid?id=" + id,
										type : "get",
										dataType : "json",
										data : "",
										success : function(response) {
											if (response.result == "fail") {
												console.error(response.message);
												return;
											}

											if (response.data == true) {
												alert("이미 존재하는 아이디입니다.");
												$("#id").val("");
												$("#id").focus();
												return;
											}

											$("#btn-check-id").hide();
											$("#img-check-id").show();
										},
										error : function(xhr, error) {
											console.error("error : " + error);
										}
									});
						});
	});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/mainmenu.jsp" />
		<form:form 
			modelAttribute="userVo" 
			class="join-form" 
			id="join-form"
			method="post"
			action="${pageContext.request.contextPath}/user/join">
			
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<p
				style="font-weight: bold; color: red; text-align: left; padding: 2px 0 0 0">
				<form:errors path="name" />
			</p>

			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id" />

			<input id="btn-check-id" type="button" value="중복체크">
			<p
				style="font-weight: bold; color: red; text-align: left; padding: 2px 0 0 0">
				<form:errors path="id" />
			</p>
			<img id="img-check-id" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<form:input type="password" path="password" />
			<p
				style="font-weight: bold; color: red; text-align: left; padding: 2px 0 0 0">
				<form:errors path="password" />
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">
		</form:form>
	</div>
</body>
</html>
