<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>
	<div class="container" align="center">
		<h2 class="text-primary">${member.name}님 환영합니다!</h2>
		<table class="table table-hover">
			<tr>
				<td><a href="view.html" class="btn btn-info">조회</a></td>
			</tr>
			<tr>
				<td><a href="view2.html" class="btn btn-info">조회(사진복수)</a></td>
			</tr>
			<tr>
				<td><a href="updateForm.html" class="btn btn-warning">수정</a></td>
			</tr>
			<tr>
				<td><a href="delete.html" class="btn btn-danger">탈퇴</a></td>
			</tr>
			<tr>
				<td><a href="logout.html" class="btn btn-success">로그아웃</a></td>
			</tr>

		</table>
	</div>
</body>
</html>