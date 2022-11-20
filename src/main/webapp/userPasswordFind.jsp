<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
</head>
<body>
	<%@ include file="userNavbar.jsp"%>
	<div class="container mt-3" style="max-width: 560px;">
		<form method="post" action="./userPasswordFindAction.jsp">
			<div class="form-group">
				<label>비밀번호 찾기</label> <input type="te
				xt" name="userID"
					class="form-control" placeholder="비밀번호를 찾고자 하는 아이디를 입력해 주세요.">
			</div>
			<button type="submit" class="btn btn-secondary mt-3 me-2">비밀번호 찾기</button>
		</form>
	</div>
	<footer class="my-3 text-center text-small">
		<p class="mb-1">&copy; 2022 지니북스</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>