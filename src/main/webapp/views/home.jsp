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
<title>Trang chủ</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body class="p-5"> 
	<div class="container">
		<h2>Have a good day bro, ${sessionScope.account.username}!</h2>
			
		<a href="${pageContext.request.contextPath}/logout" class="btn btn-danger mt-3">Đăng xuất</a>
	</div>

</body>
</html>