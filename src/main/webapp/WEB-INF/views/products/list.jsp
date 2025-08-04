<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice List</title>
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
					<div class="row col-md-8 offset-md-2">
						<h1>Product List</h1>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>No</th>
									<th>Product Name</th>
									<th>Rate(%)</th>
									<th>Sales Period</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="l" items="${list}">
									<tr>
										<td>${l.productNum }</td>
										<td><a href="./detail?productNum=${l.productNum }">${l.productName}</a></td>
										<td>${l.productRate }</td>
										<td>${fn:substringBefore(l.productDate, 'T')}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<div>
							<a href="./add" class="btn btn-outline-primary">+ Add Product</a>
						</div>
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