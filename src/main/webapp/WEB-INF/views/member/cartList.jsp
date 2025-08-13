<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/include/head_css.jsp" %>
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/include/sidebar.jsp"></c:import>
		
		<!-- Start  -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/include/topbar.jsp"></c:import>
				<div class="container-fluid">
					<!-- page contents 내용 -->
					<div class="col-md-8 offset-md-2">
						<h2>장바구니</h2>
												
						<table class="table table-striped">
							<thead>
								<tr>
									<th>
										<div class="form-check">
										  <input class="form-check-input" type="checkbox" value="" id="checkAll">
										  <label class="form-check-label" for="checkDefault">
										    전체선택
										  </label>
										</div>
									</th>
									<th>상품명</th>
									<th>이자율</th>
									<th>상품종류</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="vo">
								<tr>
									<td>
										<div class="form-check">
										  <input class="form-check-input ch" type="checkbox" value="${vo.productNum}">
										</div>
									</td>
									<td>
										<a href="../products/detail?productNum=${vo.productNum}">${vo.productName}</a>
									</td>
									<td>${vo.productRate}</td>
									<td>${vo.kindNum}</td>
								</tr>
							</c:forEach>	
							</tbody>
						</table>
						
					</div>
				</div>
			</div>
			<!--  End Content  -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
		
		
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script type="text/javascript" src="/js/board/board_list.js"></script>
</body>
</html>