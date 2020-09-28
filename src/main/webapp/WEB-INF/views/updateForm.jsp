<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	// 수정할 때도 확인 작업을 해줘야 한다.
	function chk() {
		if (frm.password.value != frm.password2.value) {
			alert("암호와 암호확인이 서로 일치하지 않습니다.");
			frm.password.value = "";
			frm.password.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container" align="center">
		<h2 class="text-primary">회원정보 수정</h2>
		<form action="update.html" method="post" enctype="multipart/form-data"
			name="frm" onsubmit="return chk()">
			<input type="hidden" name="id" value="${member.id }">
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td>${member.id }</td>
				</tr>
				<tr>
					<td>암호 <span class="glyphicon glyphicon-lock"></span></td>
					<td><input type="password" name="password"
						autofocus="autofocus" required="required"></td>
				</tr>
				<tr>
					<td>암호확인 <span class="glyphicon glyphicon-lock"></span></td>
					<td><input type="password" name="password2"
						required="required"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" required="required"
						value="${member.name}"></td>
				</tr>
				<tr>
					<td>이메일 <span class="glyphicon glyphicon-envelope"></span></td>
					<td><input type="email" name="email" required="required"
						value="${member.email }"></td>
				</tr>
				<tr>
					<td>사진 <span class="glyphicon glyphicon-picture"></span></td>
					<td><input type="file" name="file" required="required">
						<img alt="" src="${path }/upload/${member.fileName}" height="200">
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="확인"
						class="btn btn-success"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>