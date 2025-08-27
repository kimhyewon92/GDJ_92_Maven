<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
					<h1>Index</h1>
					<h3>
						<spring:message code="welcome.message2" text="hi"></spring:message>
					</h3>
					<%-- <c:if test="${not empty member }">
						<h3>${member.name} 님, 반갑습니다!</h3>
						<h3>
							<spring:message code="user.info" arguments="${member.username }, ${member.email }" argumentSeparator="," />
						</h3>
					</c:if> --%>
<%-- 					<sec:authorize access="isAuthenticated()"> --%>
<%-- 						<h3><sec:authentication property="name" /> 님, 반갑습니다!</h3> --%>

<!-- 						<h3> -->
<%-- 							<sec:authentication property="principal" var="vo"/> --%>
<%-- 							${vo.username} ${vo.email} --%>
							
<!-- 						</h3>							 -->
<%-- 					</sec:authorize> --%>
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
