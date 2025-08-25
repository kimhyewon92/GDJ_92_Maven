<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<c:import url="/WEB-INF/views/include/head_css.jsp"></c:import>
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/include/sidebar.jsp"></c:import>
		
		<!-- Start -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/include/topbar.jsp"></c:import>
				<div class="container-fluid">
					<!-- page contents 내용 -->
					<sec:authentication property="principal" var="memberVO"/>
					<form:form method="post" modelAttribute="memberVO" enctype="multipart/form-data">
						<div class="mb-3">
						  <label for="name" class="form-label">name</label>
						  <form:input path="name" cssClass="form-control" value="${memberVO.name }"/>
						  <form:errors path="name"></form:errors>
						</div>
						<div class="mb-3">
						  <label for="email" class="form-label">email</label>
						  <form:input path="email" cssClass="form-control" value="${memberVO.email }"/>
						  <form:errors path="email"></form:errors>
						</div>
						<div class="mb-3">
						  <label for="phone" class="form-label">phone</label>
						  <form:input path="phone" cssClass="form-control" value="${memberVO.phone }"/>
						</div>
						<div class="mb-3">
						  <label for="birth" class="form-label">birth</label>
						  <input type="date" class="form-control" name="birth" value="${memberVO.birth }">
						  <form:errors path="birth"></form:errors>
						</div>
						
						<div>
							<label for="file" class="form-label">file</label>
							<input type="file" class="" name="profile">
						</div>

						<button type="submit" class="btn btn-primary">Submit</button>
					</form:form>
				</div>
			</div>
			<!-- End Content -->
			
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		<!-- End of Content Wrapper -->
		
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	
</body>
</html>