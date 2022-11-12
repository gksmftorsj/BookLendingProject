<%@page import="bookInfo.BookInfoDTO"%>
<%@page import="bookInfo.BookInfoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
request.setCharacterEncoding("UTF-8");
%>
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
			String title = (String) request.getParameter("title");
			BookInfoDAO bookInfoDAO = new BookInfoDAO();
			bookInfoDAO.selectBookDetail(title);
			
			BookInfoDTO bookInfoDTO = new BookInfoDTO();
			
		%>
      	
      	<h1><%=bookInfoDTO.getTitle()%></h1>
      	
       
          
     <div>
      <nav class="navbar navbar-expand-lg bg-light">
          <div class="container-fluid">
            <a class="navbar-brand" href="./index.jsp">지니북스</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="./index.jsp">메인</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="./myInfo.jsp">내정보</a>
                </li>
              </ul>
              <button type="submit" class="btn btn-secondary me-3"><a style="text-decoration: none; color: white;" href="./userLogin.jsp">로그인</a></button>
              <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="제목을 입력해주세요" aria-label="Search">
                <button class="btn btn-outline-success" type="submit" style="width:75px;">검색</button>
              </form>
            </div>
          </div>
      </nav>
   <div style="margin: 50px;">
   <div class="container text-center"><h2>책 제목, 지은이, 날짜 등</h2></div>  
     <div class="row" style="margin: 50px;">
       <div class="col">
         <img src= "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788954680004.jpg" 
               style="width: 25rem; height: 30rem;"></img>
       </div>
       <div class="col">     
         <p>description</p>
         <p>대출 가능한 권 수
         <button type="button" class="btn btn-outline-secondary" >대출하기</button>      
         <button type="button" class="btn btn-outline-secondary" >예약하기</button>
         <button type="button" class="btn btn-outline-secondary">
         <p>찜하기 <img src= "https://cdn-icons-png.flaticon.com/512/138/138533.png" style="width: 1rem; height: 1rem;"></img></p></button>
       </div>
     </div>
      <div class="form-floating text-center" style=" width: 100%; height: 100%;">
         <h2 style="margin: 50px;">리뷰 및 별점</h2>
            <div class="container text-center">
               <div class="row">
                  <div class="col-sm-10">
                     <textarea class="form-control" placeholder="Leave a comment here"
                     id="floatingTextarea2" style="display: inline-block; height: 100px">comments</textarea>                  
                  </div>
                  <div class="col-sm-2"><button type="button" class="btn btn-primary">Primary</button></div>
               </div>
            </div>
         </div>
      </div>
   </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
    crossorigin="anonymous">
  </script>
</body>
</html>