<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
					<h1>Product Detail</h1>
					<div class="row col-md-8 offset-md-2">
						<table class="table">
							<tr>
								<th>Product Type</th>
								<td>${detailVO.productKindVO.kindName}</td>
								<th>Sales Period</th>
								<td>${detailVO.productDate}</td>
							</tr>
							<tr>
								<th>Product Name</th>
								<td>${detailVO.productName}</td>
								<th>Rate(%)</th>
								<td>${detailVO.productRate}</td>
							</tr>
							<tr>	
								<th colspan="3">Product Contents</th>
								<td>${detailVO.productContents}</a></td>
							</tr>
						</table>
						
						<div>
							<form id="frm">
								<input type="hidden" name="productNum" value="${detailVO.productNum}">
							</form>
							
							<button class="btn btn-outline-success action" data-kind="u">Update</button>
							<button class="btn btn-outline-danger action" data-kind="d">Delete</button>
							
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
	<script type="text/javascript" src="/js/board/board_detail.js"></script>
</body>
</html>