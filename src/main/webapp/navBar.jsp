<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
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
	<%
	String valUserID = null;
	if(session.getAttribute("userID") != null){
		valUserID = (String) session.getAttribute("userID");
	}
// 	if(valUserID != null){
// 		PrintWriter script = response.getWriter();
// 		script.println("<script>");
// 		script.println("alert('로그인이 된 상태입니다.');");
// 		script.println("location.href = 'index.jsp';");
// 		script.println("</script>");
// 		script.close();
// 		return;
// 	}
%>
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="./index.jsp">지니북스</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<%
	if(valUserID == null){
%>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				</ul>
				<%
	} else{		
%>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 마이페이지 </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">회원정보수정</a></li>
							<li><a class="dropdown-item" href="#">Another action</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">Something else
									here</a></li>
						</ul>
					</li>
				</ul>
				<%
	}
				%>
				<%
	if(valUserID == null){
%>
				<button type="submit" class="btn btn-secondary me-3">
					<a style="text-decoration: none; color: white;"
						href="./userLogin.jsp">로그인</a>
				</button>
				<button type="submit" class="btn btn-secondary me-3">
					<a style="text-decoration: none; color: white;"
						href="./userRegister.jsp">회원가입</a>
				</button>
				<%
	} else{		
%>
				<button type="submit" class="btn btn-secondary me-3">
					<a style="text-decoration: none; color: white;"
						href="./userLogout.jsp">로그아웃</a>
				</button>
				<%
	}		
%>
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search"
						placeholder="제목을 입력해주세요" aria-label="Search">
					<button class="btn btn-outline-success" type="submit"
						style="width: 75px;">검색</button>
				</form>
			</div>
		</div>
	</nav>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>