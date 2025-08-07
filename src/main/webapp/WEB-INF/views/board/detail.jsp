<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					<div class="row col-md-8 offset-md-2">
						<h2>${board }</h2>
						<table class="table">
							<tr>
								<th>No</th>
								<td>${boardVO.boardNum}</td>
								<th>Hit</th>
								<td>${boardVO.boardHit}</td>
							</tr>
							<tr>	
								<th>Writer</th>
								<td>${boardVO.boardWriter}</td>
								<th>Date</th>
								<td>${fn:replace(boardVO.boardDate, 'T', '&nbsp;&nbsp;')}</td>
							</tr>
							<tr>	
								<th>Title</th>
								<td colspan="3">${boardVO.boardTitle}</a></td>
							</tr>
							<tr>	
								<th>File</th>
								<c:forEach items="${boardVO.boardFileVOs }" var="f">
								<td colspan="3">
									<a href="/files/${board}/${f.saveName}">${f.oriName}</a><br>
								</c:forEach>
								</td>
							</tr>
							<tr>	
								<th>Content</th>
								<td colspan="3">${boardVO.boardContents}</a></td>
							</tr>
						</table>
												
						<div>
							<form id="frm">
								<input type="hidden" name="boardNum" value="${boardVO.boardNum}">
							</form>
							
							<button class="btn btn-outline-success action" data-kind="u">Update</button>
							<button class="btn btn-outline-danger action" data-kind="d">Delete</button>
							<c:if test="${board ne 'notice'}">
								<button class="btn btn-outline-primary action" data-kind="r">Reply</button>
							</c:if>
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