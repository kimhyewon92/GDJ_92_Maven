<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board} List</title>
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
					<div class="col-md-8 offset-md-2">
						<h2>${board}</h2>
						
						<div class="row">
							<form id="searchForm">
								<div class="input-group mb-3">
									<input type="hidden" id="pageNum" name="pageNum">
									<select class="form-control" name="kind" aria-label="Default select example">
									  <option value="k1" ${pager.kind eq 'k1' ? 'selected':'' }>Title</option>
									  <option value="k2" ${pager.kind eq 'k2' ? 'selected':'' }>Contents</option>
									  <option value="k3" ${pager.kind eq 'k3' ? 'selected':'' }>Writer</option>
									</select>
								  <input type="text" class="form-control" value="${pager.keyword}" name="keyword" placeholder="keyword" aria-label="Recipient’s username" aria-describedby="button-addon2">
								  <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Button</button>
								</div>
							</form>
						</div>
						
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
										<td>${fn:replace(l.boardDate, 'T', '&nbsp;&nbsp;')}</td>
										<td>${l.boardHit}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<div>
							<nav aria-label="Page navigation example">
							  <ul class="pagination">
								  <li class="page-item">
								    <button class="page-link pn" data-pn="${pager.startNum-1 }" aria-label="Previous">
								      <span aria-hidden="true">&laquo;</span>
								    </button>
								  </li>
								  
								  <c:forEach begin="${pager.startNum}" end="${pager.endNum}" var="i">
								  	<li class="page-item"><button class="page-link pn" data-pn="${i}">${i }</button></li>
								  </c:forEach>
								  
								  <li class="page-item">
								    <button class="page-link pn" data-pn="${pager.endNum+1 }" aria-label="Next">
								      <span aria-hidden="true">&raquo;</span>
								    </button>
								  </li>
								</ul>
							</nav>
						</div>
						
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
	<script type="text/javascript" src="/js/board/board_list.js"></script>
</body>
</html>