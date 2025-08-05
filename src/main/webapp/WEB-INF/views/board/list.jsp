<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
						<h2>${board}</h2>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Num</th>
									<th>Title</th>
									<th>Writer</th>
									<th>Date</th>
									<th>Hit</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="l" items="${list}">
									<tr>
										<td>${l.boardNum}</td>
										<td>
											<c:catch>
												<c:forEach begin="1" end="${l.boardDepth }">&nbsp;&nbsp;↳&nbsp;&nbsp;</c:forEach>
											</c:catch>
											<a href="./detail?boardNum=${l.boardNum }">${l.boardTitle}</a>
										</td>
										<td>${l.boardWriter}</td>
										<td>${l.boardDate}</td>
										<td>${l.boardHit}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<div>
							<a href="./add" class="btn btn-outline-primary">글쓰기</a>
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