<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<%@ include file="navBar.jsp"%>
	
	<div class="container mt-3" style="max-width: 560px;">
		<form method="post" action="./userLoginAction.jsp">
			<div class="form-group">
				<label>아이디</label> 
				<input type="text" name="userID" class="form-control">
			</div>
			<div class="form-group">
				<label>비밀번호</label>
				<input type="password" name="userPassword" class="form-control">
			</div>
			<div class="d-flex align-items-center">
				<button type="submit" class="btn btn-secondary mt-3 me-2">로그인</button>
				<button type="submit" class="btn btn-secondary mt-3 me-2"><a style="text-decoration: none; color: white;" href="./userRegister.jsp">회원가입</a></button>
				<div class="find mt-3">
					<a href="./userIdFind.jsp">아이디찾기/</a>
					<a href="./userPasswordFind.jsp">비밀번호찾기</a>						
				</div>
			</div>
		</form>
	</div>

</body>
</html>