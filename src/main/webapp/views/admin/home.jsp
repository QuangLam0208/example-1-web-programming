<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.account}">
    <%
        response.sendRedirect(request.getContextPath() + "/login");
    %>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-5">

	<div class="container">
		<div class="card">
			<div class="card-header">
				<h2>Admin Dashboard</h2>
			</div>
			<div class="card-body">
				<h5 class="card-title">Welcome Admin ${sessionScope.account.fullname}!</h5>
				<p class="card-text">This is the admin page, you can perform the functions below.</p>
				
				<a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-primary">
					Category Management
				</a>
				
				<!-- (nút "Quản lý Sản phẩm" ở đây) -->
				
				<!-- Nút Đăng xuất -->
				<a href="${pageContext.request.contextPath}/logout" class="btn btn-danger" style="float: right;">Log Out</a>
			</div>
		</div>
	</div>

</body>
</html>