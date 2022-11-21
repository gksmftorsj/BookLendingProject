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
<style>
	@font-face {
	    font-family: '양진체';
	    src: url('https://cdn.jsdelivr.net/gh/supernovice-lab/font@0.9/yangjin.woff') format('woff');
	    font-weight: normal;
	    font-style: normal;
	}
	.navbar-brand {
	  font-family: '양진체';
	}
</style>

</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String valUserID = null;
	if(session.getAttribute("userID") != null){
		valUserID = (String) session.getAttribute("userID");
	}
%>
	   <nav class="navbar navbar-expand-lg ">
      <div class="container-fluid">
         <a class="navbar-brand" href="./index.jsp">지니북스 </a>
<!--          <img class="logo" -->
<!--             src="./img/logo.png"> -->
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
                     <li><a class="dropdown-item" href="#">나의대여현황</a></li>
                     <li><a class="dropdown-item" href="#">나의예약현황</a></li>
                     <li><hr class="dropdown-divider"></li>
                     <li><a class="dropdown-item" href="#">회원정보수정</a></li>
                  </ul></li>
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
         </div>
      </div>
   </nav>
   
   <div class="bar" style="border-bottom: 1px solid gray; width: 100%;"></div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>