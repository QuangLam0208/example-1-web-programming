<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách Category</title>
<!-- Thêm Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
	<h2>Category Management</h2>
	
	<!-- Nút Thêm Mới -->
	<a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-primary mb-3">Add New Category</a>
	
	<table class="table table-bordered table-striped">
		<thead class="table-dark">
			<tr>
				<th>No.</th>
				<th>Image</th>
				<th>Category name</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${cateList}" var="cate" varStatus="STT" >
				<tr>
					<td>${STT.index + 1}</td>
					
					<c:url value="/image?fname=${cate.icons.replace('\\\\','/')}" var="imgUrl"></c:url>
					
					<td><img height="100" src="${imgUrl}" /></td>
					<td>${cate.cate_name}</td>
					<td>
						<a href="<c:url value='/admin/category/edit?id=${cate.cate_id}'/>" class="btn btn-warning btn-sm">Sửa</a>
						<a href="<c:url value='/admin/category/delete?id=${cate.cate_id}'/>" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

</body>
</html>