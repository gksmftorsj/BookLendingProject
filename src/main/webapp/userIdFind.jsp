<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="userNavbar.jsp"%>
	<div class="container mt-3" style="max-width: 560px;">
		<form method="post" action="./userIdFindAction.jsp">
			<div>아이디 찾기</div>
			<div class="form-group">
				<label>이름</label>
				<input type="text" name="userName" class="form-control" placeholder="이름을 입력해주세요." required>
			</div>
			<div class="form-group">
				<label>전화번호</label>
				<input type="tel" name="userTel" class="form-control" placeholder="휴대전화번호를 입력해주세요" required>
			</div>
			<button type="submit" class="btn btn-secondary mt-3 me-2">아이디찾기</button>
		</form>
	</div>
	<footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2022 지니북스</p>
    </footer>

</body>
</html>