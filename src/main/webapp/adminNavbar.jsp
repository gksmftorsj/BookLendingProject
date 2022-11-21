<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
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
<style>
/* .logo { */
/*    transform: rotate(30deg); */
/*    position: relative; */
/*    top: -6px; */
/*    left: -9px; */
/*    width: 30px; */
/*    height: 30px; */
/* } */
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
<body>
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <a class="navbar-brand" href="./index.jsp">지니북스
         <img class="logo" src="./img/logo3.jpg" style="width:30px; height:30px;">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="./lendingStatus.jsp">대여현황</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="./reservationStatus.jsp">예약현황</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./bookManager.jsp">도서관리</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./userManager.jsp">회원관리</a>
          </li>
        </ul>
        <button type="submit" class="btn btn-secondary me-3"><a style="text-decoration: none; color: white;" href="./userLogin.jsp">로그인</a></button>
        <form class="d-flex" role="search">
          <input class="form-control me-2" type="search" placeholder="회원명 혹은 도서명을 입력해주세요" aria-label="Search">
          <button class="btn btn-outline-success" type="submit" style="width:75px;">검색</button>
        </form>
      </div>
    </div>
  </nav>
  <div class="bar" style="border-bottom: 1px solid gray; width: 100%;"></div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
    crossorigin="anonymous"></script>
</body>
</html>