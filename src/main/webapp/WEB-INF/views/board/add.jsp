<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
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
					<form method="post" enctype="multipart/form-data">
						<input type="hidden" name="boardNum" value="${boardVO.boardNum }">
						<div class="mb-3">
						  <label for="exampleInputEmail1" class="form-label">Writer</label>
						  <input type="text" class="form-control" id="exampleInputEmail1" name="boardWriter" aria-describedby="emailHelp" value="${boardVO.boardWriter }">
						</div>
						<div class="mb-3">
						  <label for="exampleInputEmail1" class="form-label">Title</label>
						  <input type="text" class="form-control" id="exampleInputEmail1" name="boardTitle" aria-describedby="emailHelp" value="${boardVO.boardTitle }">
						</div>
						<div class="mb-3">
						  <label for="exampleInputEmail1" class="form-label">Content</label>
						  <input type="text" class="form-control" id="exampleInputEmail1" name="boardContents" aria-describedby="emailHelp" value="${boardVO.boardContents }">
						</div>
						
						<div>
							<button class="btn btn-primary" type="button" id="add">ADD</button>
						</div>
						
						<div>
							<c:forEach items="${boardVO.boardFileVOs}" var="f">
								<button class="deleteFile" data-file-num="${f.fileNum}" type="button">${f.oriName}</button>							
							</c:forEach>
						</div>

						<div id="result" data-file-count="${boardVO.boardFileVOs.size()}">
							<!-- ${fn:length(boardVO.boardFileVOs)}> -->
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
	<script type="text/javascript" src="/js/board/board_add.js"></script>
</body>
</html>