<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
					<h3>${param.failMessage }</h3>
					<form method="post" action="loginProcess" enctype="multipart/form-data">
						<div class="mb-3">
						  <label for="id" class="form-label">ID</label>
						  <input type="text" value="${cookie.rememberId.value}" class="form-control" name="username">
						</div>
						<div class="mb-3">
						  <label for="password" class="form-label">password</label>
						  <input type="password" class="form-control" name="password">
						</div>
						<div class="mb-3 form-check">
							<input type="checkbox" class="form-check-input"
								id="exampleCheck1" value="1" name="rememberId"> <label class="form-check-label"
								for="exampleCheck1">ID 기억하기</label>
						</div>
						<div class="mb-3 form-check">
							<input type="checkbox" class="form-check-input"
								id="exampleCheck1" value="1" name="remember-me"> <label class="form-check-label"
								for="exampleCheck1">자동로그인</label>
						</div>

						<button type="submit" class="btn btn-primary">Login</button>
					</form>
					
					<div>
<!-- 						<a class="btn btn-warning" href="/member/kakaoLogin">카카오로그인</a> -->
						<a class="btn btn-warning" href="/oauth2/authorization/kakao">카카오로그인</a>

					</div>
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