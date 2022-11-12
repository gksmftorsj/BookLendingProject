<%@page import="bookInfo.BookInfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bookInfo.BookInfoDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<body>
	<%
		BookInfoDAO bookInfoDAO = new BookInfoDAO();
		bookInfoDAO.selectBookInfo();
		
		List<BookInfoDTO> bookInfoList = bookInfoDAO.selectBookInfo();
	%>
	<%@ include file="navBar.jsp"%>
	
	
	
	<div id="carouselExampleInterval" class="container carousel slide mt-5"
		data-bs-ride="carousel">
		<div class="carousel-inner d-flex justify-content-center">
			<div class="carousel-item active" data-bs-interval="3000">
				<img src="https://image.aladin.co.kr/product/30473/65/cover/k172830285_1.jpg" alt="..."> 
				<div>책 제목</div> 
			</div>	
			
			<%
				for (BookInfoDTO bi : bookInfoList) {
			%>
				<div class="carousel-item" data-bs-interval="3000">
					<img src="<%=bi.cover%>" alt="...">
					<div><%=bi.title%></div> 
				</div>	
			<%
				}
			%>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<div class="container sortBook d-flex justify-content-end mt-5">
		<span class="me-3">최신순</span> <span class="me-3">이름순</span> <select
			class="form-select" style="width: 100px;">
			<option selected>장르</option>
			<option value="철학">철학</option>
			<option value="종교">종교</option>
			<option value="역사">역사</option>
		</select>
	</div>

	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">순위</th>
					<th scope="col">제목</th>
					<th scope="col">저자</th>
					<th scope="col">Handle</th>
				</tr>
			</thead>
			<tbody>
			
			<%
				for (BookInfoDTO bi : bookInfoList) {
			%>
				<tr>
					<th scope="row"><%=bi.rank%></th>
					<td><a style="text-decoration:none; color:black;" href="./bookDetail.jsp?title=<%=bi.title%>"><%=bi.title%></a></td>
					<td><%=bi.author%></td>
					<td><button style="width: 80px;">예약가능</button></td>
				</tr>	
			
			<%
				}
			%>
			</tbody>
			
			
		</table>

	</div>
	<footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2022 지니북스</p>
    </footer>




</body>
</html>