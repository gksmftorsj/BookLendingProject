<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.DatabaseUtil"%>
<%@ page import="bookInfo.BookInfoDTO" %>
<%@ page import="bookInfo.BookInfoDAO" %>
<%@ page import="bookManagement.BookManagementDTO" %>
<%@ page import="bookManagement.BookManagementDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	margin: 0px;
	padding: 0;
	font-family: Malgun Gothic, 돋움, Dotum, AppleGothic, sans-serif;
	font-size: 12px;
	color: #666;
	line-height: 18px;
}

.order_box01 {
	background: #f5f5f5;
	width: 100%;
	padding: 15px 30px;
	margin-bottom: 15px;
	border: 1px solid #d1d1d1;
	overflow: visible;
	justify-content: space-around;
	display: inline-block;
}
.order_box02 {
	margin-top: 15px;	
	display: inline;
}

.account_select01 {
width: 40%;
display: inline;
}
.account_select02 {
width: 40%;
display: inline;
}

.myTable{
	width: 100%;
	padding: 15px 30px;
	margin-bottom: 15px;
	border: 1px solid #d1d1d1;
	text-align: center;
}

.order_box_line{
border-bottom: 1px solid #d1d1d1;
}

p{
	margin : 0 10px;
}

</style>
</head>
<body>
	<%@ include file="adminNavbar.jsp"%>

	<div class="container">
		<h2>관리자 페이지</h2>
		<form name="adminForm" class="adminForm" method="post">
			<div class="col-auto">
				<label for="name">보유도서조회</label>
				<fieldset>
					<div class="order_box01">

						<div class="order_box02">

							<div class="account_select03" style="width: 100%">
								<form action="bookManagerDetail.jsp" method="post">
									<div class="input-group">
										<select name="bookOption" required
											class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
											id="inputGroupSelect01">
											<ul class="dropdown-menu">
												<li><option class="dropdown-item" selected>보유도서검색</option></li>
												<li><option class="dropdown-item" value="title">도서명</option></li>
												<li><option class="dropdown-item" value="author">저자명</option></li>
												<li><option class="dropdown-divider" disabled>-------</option></li>
												<li><option class="dropdown-item" value="isbn">ISBN</option></li>
										</select>
										</ul>

										<input type="text" class="form-control"
											aria-label="Text input with 2 dropdown buttons"
											name="searching" value="" required>
										<button class="btn btn-outline-secondary" type="submit">검색</button>
										</label>
									</div>
								</form>
							</div>
						</div>
					</div>
				</fieldset>
			</div>

			<fieldset>
			<div class="col-auto">
				<label for="name">도서상세</label>
				<div class="myTable">
					<table class="lendingTable container">
						<thead>
							<tr>
								<th scope="col"><p>도서번호</p></th>
								<th scope="col"><p>커버</p></th>
								<th scope="col"><p>도서명</p></th>
								<th scope="col"><p>저자명</p></th>
								<th scope="col"><p>출판사</p></th>
								<th scope="col"><p>대여상태</p></th>
								<th scope="col"><p>비고</p></th>
							</tr>
							
						</thead>
						
						<tbody>
								<%
		BookManagementDAO bookMdao = new BookManagementDAO();
		if( request.getParameter("searching") == null){
			if( request.getParameter("isbn") != null ){
				String isbn = request.getParameter("isbn");
				List<BookManagementDTO> bookMlist = bookMdao.selectAdminBookManagementDetailByIsbn(isbn);
			
	
						if(bookMlist != null && bookMlist.size()>0) {
			  				for(BookManagementDTO bmd : bookMlist){
						%>
						
						<tr>
								<td><p><%=bmd.getBookNo() %></p></td>
								<td><p>
										<img style="width: 3em; object-fit: contain;"
										onclick="window.open(this.src)" src=<%=bmd.getCover() %>>
								</p></td>
								<td><p><%=bmd.getTitle() %></p></td>
								<td><p><%=bmd.getAuthor() %></p></td>
								<td><p><%=bmd.getPublisher() %></p></td>
								<td><p>
								<%if( (bmd.getBookLendingAvailability()).equals("대여중") ) {
								%> <button type="button" class="btn btn-lg btn-danger" 
								data-bs-toggle="popover" data-bs-title="대여중" 
								data-bs-content="And here's some amazing content.">
								대여중</button> <% } else {%> 대여가능 <% } %></p></td>
								<td><p><%=bmd.getBookReservationAvailability() %></p></td>
							</tr>
						<%
			  				}
						}
		}}
	
		if( request.getParameter("searching") != null ){
			List<BookManagementDTO> bookMlist = null;
			String search = request.getParameter("searching");		
			String option = request.getParameter("bookOption");
	
			if( option.equals("title") ){
				out.print(search);
				bookMlist = bookMdao.selectAdminBookManagementDetailByTitle(search);
					if(bookMlist != null && bookMlist.size()>0) {
	  				for(BookManagementDTO bmd : bookMlist){
  	%>
							<tr>
								<td><p><%=bmd.getBookNo() %></p></td>
								<td><p>
									<img style="width: 3em; object-fit: contain;"
									onclick="window.open(this.src)" src=<%=bmd.getCover() %>>
								</p></td>
								<td><p><%=bmd.getTitle() %></p></td>
								<td><p><%=bmd.getAuthor() %></p></td>
								<td><p><%=bmd.getPublisher() %></p></td>
								<td><p>
								<%if( (bmd.getBookLendingAvailability()).equals("대여중") ) {
								%> <button type="button" class="btn btn-lg btn-danger" 
								data-bs-toggle="popover" data-bs-title="대여중" 
								data-bs-content="And here's some amazing content.">
								대여중</button> <% } else {%> 대여가능 <% } %></p></td>
								<td><p><%=bmd.getBookReservationAvailability() %></p></td>
							</tr>
	<%
					}
  				}
			} else if( option.equals("author") ) {
				bookMlist = bookMdao.selectAdminBookManagementDetailByAuthor(search);
					if(bookMlist != null && bookMlist.size()>0) {
	  				for(BookManagementDTO bmd : bookMlist){
  	%>
  							<tr>
								<td><p><%=bmd.getBookNo() %></p></td>
								<td><p>
									<img style="width: 3em; object-fit: contain;"
									onclick="window.open(this.src)" src=<%=bmd.getCover() %>>
								</p></td>
								<td><p><%=bmd.getTitle() %></p></td>
								<td><p><%=bmd.getAuthor() %></p></td>
								<td><p><%=bmd.getPublisher() %></p></td>
								<td><p>
								<%if( (bmd.getBookLendingAvailability()).equals("대여중") ) {
								%> <button type="button" class="btn btn-lg btn-danger" 
								data-bs-toggle="popover" data-bs-title="대여중" 
								data-bs-content="And here's some amazing content.">
								대여중</button> <% } else {%> 대여가능 <% } %></p></td>
								<td><p><%=bmd.getBookReservationAvailability() %></p></td>
							</tr>
  	<%
					}
	  			}
			} else if( option.equals("isbn") ) {
				bookMlist = bookMdao.selectAdminBookManagementDetailByIsbn(search);
					if(bookMlist != null && bookMlist.size()>0) {
	  				for(BookManagementDTO bmd : bookMlist){
  	%>
  							<tr>
								<td><p><%=bmd.getBookNo() %></p></td>
								<td><p>
									<img style="width: 3em; object-fit: contain;"
									onclick="window.open(this.src)" src=<%=bmd.getCover() %>>
								</p></td>
								<td><p><%=bmd.getTitle() %></p></td>
								<td><p><%=bmd.getAuthor() %></p></td>
								<td><p><%=bmd.getPublisher() %></p></td>
								<td><p>
								<%if( (bmd.getBookLendingAvailability()).equals("대여중") ) {
								%> <button type="button" class="btn btn-lg btn-danger" 
								data-bs-toggle="popover" data-bs-title="대여중" 
								data-bs-content="And here's some amazing content.">
								대여중</button> <% } else {%> 대여가능 <% } %></p></td>
								<td><p><%=bmd.getBookReservationAvailability() %></p></td>
							</tr>
  	<%
					}
	  			}
			}
		}
	%>
  	
						</tbody>
					</table>
				</div>
			</div>
			</fieldset>
		</form>
	</div>
</body>
</html>