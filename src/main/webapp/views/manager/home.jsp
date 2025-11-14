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
</head>
<body>

	<h2>Chào mừng Manager ${sessionScope.account.username}!</h2>
	<p>Đây là trang quản lý.</p>
		
	<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>

</body>
</html>