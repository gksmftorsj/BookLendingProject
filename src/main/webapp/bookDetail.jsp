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
</head>
<body>

	 <%
  	   String title = (String) request.getParameter("title");
   	   BookInfoDAO bookInfoDAO = new BookInfoDAO();
	   BookInfoDTO bookInfoDTO = bookInfoDAO.selectBookDetail(title);
   	 %>
   	 
   	<%@ include file="userNavbar.jsp"%>
	
	<div style="margin: 50px;">
		<div class="container text-center"><h2>" <%=bookInfoDTO.getTitle()%> "</h2></div>
		<div class="container text-center"><h5> <%=bookInfoDTO.getAuthor()%> / <%=bookInfoDTO.getPublisher() %></h5></div>
	
		<div class="mt-5" style="border-bottom:1px solid gray; width:100%;"></div>
	  <div class="row" style="margin: 80px;">
	    <div class="col">
			<img src= <%=bookInfoDTO.getCover()%> style="height: 400px; width: 300px; margin-left:30px"></img>
	    </div>
	    <div class="col">	  
	      <p><%=bookInfoDTO.getDescription() %></p>
	      <p>대출 가능한 권 수
	      <button type="button" class="btn btn-outline-secondary" >대출하기</button>      
	      <button type="button" class="btn btn-outline-secondary" >예약하기</button>
	      <button type="button" class="btn btn-outline-secondary">
	      <p>찜하기 <img src= "https://cdn-icons-png.flaticon.com/512/138/138533.png" style="width: 1rem; height: 1rem;"></img></p></button>
	    </div>
	  </div>
	  <div class="mt-5" style="border-bottom:1px solid gray; width:100%;"></div>
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

</body>
</html>
