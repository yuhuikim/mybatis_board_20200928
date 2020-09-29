<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>
	<div class="container" align="center">
		<h2>회원 정보 상세</h2>
		<table class="table  table-bordered"></table>
		<tr>
			<td>아이디</td>
			<td>${member.id }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${member.name }</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${member.email }</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${member.regdate }</td>
		</tr>
		<tr>
			<td colspan="2">사진</td>
		</tr>
		<tr>
			<td colspan="2"><c:forEach var="ph" items="${list }">
					<img src="${path }/upload/${ph.fileName}" width="200" alt="" />
				</c:forEach></td>
		</tr>
	</div>
</body>
</html>