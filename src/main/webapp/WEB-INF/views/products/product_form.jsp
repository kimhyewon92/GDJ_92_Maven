<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Page</title>
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
					<!-- 상품종류, 상품이름, 상품내용, 판매기간, 상품이율 -->
					<form method="post">
						<input type="hidden" name="productNum" value="${productVO.productNum }">
						<div class="mb-3">
							<select class="form-select" name="kindNum" aria-label="Default select example">
								<option selected>상품 종류</option>
								<option value="1" ${productVO.kindNum eq 1 ? 'selected' : ''}>예금</option>
								<option value="2" ${productVO.kindNum eq 2 ? 'selected' : ''}>적금</option>
								<option value="3" ${productVO.kindNum eq 3 ? 'selected' : ''}>대출</option>
							</select>
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Name</label>
							<input type="text" class="form-control" id="exampleInputEmail1" name="productName" aria-describedby="emailHelp" value="${productVO.productName }">
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Rate</label>
							<input type="number" step="0.1" class="form-control" id="exampleInputEmail1" name="productRate" aria-describedby="emailHelp" value="${productVO.productRate }">
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Sales Period</label>
							<input type="date" class="form-control" id="exampleInputEmail1" name="productDate" aria-describedby="emailHelp" value="${productVO.productDate }">
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Contents</label>
							<input type="text" class="form-control" id="exampleInputEmail1" name="productContents" aria-describedby="emailHelp" value="${productVO.productContents }">
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
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