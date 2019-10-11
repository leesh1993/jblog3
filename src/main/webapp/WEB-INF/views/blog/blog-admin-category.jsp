<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
$(function(){
	
	$("#category-add-btn").click(function(){
		
		var categoryVo = {
				"name" : $("#add-name").val(),
				"explanation" : $("#add-explanation").val(),
				"bid" : $("#add-bid").val()
		};
		
		if(categoryVo == ''){
			alert("카테고리 항목을 모두 입력해주세요.");
			return;
		}
		
		
		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/blog/category/add",
			type: "post",
			dataType: "json",	
			contentType: 'application/json',
			data: JSON.stringify(categoryVo),
			success : function(data) { 
				var vo = JSON.parse(data);				
				$("#add-categotyList").append(
						"<tr><td>"+vo.ccount+"</td>"+
						"<td>"+vo.name+"</td>"+
						"<td>"+0+"</td>"+
						"<td>"+vo.explanation+"</td>"+
						"<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>"+
						"</tr>");
			},
         	error : function(xhr, error) {
	        	console.error("error : " + error);
	        }

		});
				
	});
	
	
	
	$(document).on("click", ".delete-category", function(event) {
		  event.preventDefault();      
	  
	      var no = $(this).attr('id');
		  
	      $.ajax({
	         url : "${pageContext.servletContext.contextPath }/api/blog/delete?no=" + no,
	         type : "post",
	         dataType : "json",
	         success : function(data) {
	        	 alert(data);
	            $("list" + data).remove();
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
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<tbody id="add-categotyList" >
		      		<c:forEach items='${categoryList}' var='vo' varStatus='status'>
					<tr class="list${status.count}">
						<td>${status.count }</td>
						<td>${vo.name}</td>
						<td>${vo.pcount }</td>
						<td>${vo.explanation}</td>			
						<td><img class="delete-category" id="${vo.no }" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
						<input type="hidden" id="lastcount" value="${getCount }">
					</tr>  
				  	</c:forEach>
				  	</tbody>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      	<input type="hidden" id="add-bid" value="${blog.uid }">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="add-name" type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="add-explanation" type="text" name="explanation"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="category-add-btn" type="button" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>