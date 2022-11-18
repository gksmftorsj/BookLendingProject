<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.sql.Timestamp"%>
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
								<form action="bookManager.jsp" method="post">
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
											placeholder="검색어를 입력하세요"
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
					<label for="name">도서목록</label>
					<div class="myTable">
						<table class="bookTable container">
							<thead>
								<tr>
									<th scope="col"><p>순위</p></th>
									<th scope="col"><p>커버</p></th>
									<th scope="col"><p>ISBN</p></th>
									<th scope="col"><p>도서명</p></th>
									<th scope="col"><p>저자명</p></th>
									<th scope="col"><p>출판사</p></th>
									<th scope="col"><p>대여현황</p></th>
									<th scope="col"><p>비고(총대여횟수)</p></th>
								</tr>

							</thead>

							<tbody>
	<%
	BookInfoDAO bookInfoDao = new BookInfoDAO();
	BookManagementDAO bookMdao = new BookManagementDAO();
	
	if( request.getParameter("searching") != null ){
		String search = request.getParameter("searching");		
		String option = request.getParameter("bookOption");
	
		if( option.equals("title") ){
			List<BookInfoDTO> bookInfoList = bookInfoDao.selectBookInfoByTitle(search);
				if(bookInfoList != null && bookInfoList.size()>0) {
  				for(BookInfoDTO bookInfo : bookInfoList){
	%>
								<tr>
									<td><p><%=bookInfo.getRank() %></p></td>
									<td><p>
											<img style="width: 3em; object-fit: contain;"
											onclick="window.open(this.src)" src=<%=bookInfo.getCover() %>>
									</p></td>
									<td><p>
											<a href="bookManagerDetail.jsp?isbn=<%=bookInfo.getIsbn() %>"><%=bookInfo.getIsbn() %></a>
										</p></td>
									<td><p><%=bookInfo.getTitle() %></p></td>
									<td><p><%=bookInfo.getAuthor() %></p></td>
									<td><p><%=bookInfo.getPublisher() %></p></td>
									<td><p><%=bookInfo.getBookLendingCnt() %>/5</p></td>
									<td><p><%=bookInfo.getBookTotalLendingCnt() %></p></td>
								</tr>
	<%
					}
  				}
		} else if( option.equals("author") ) {
			List<BookInfoDTO> bookInfoList = bookInfoDao.selectBookInfoByAuthor(search);
			if(bookInfoList != null && bookInfoList.size()>0) {
				for(BookInfoDTO bookInfo : bookInfoList){
	%>
					<tr>
						<td><p><%=bookInfo.getRank() %></p></td>
						<td><p>
								<img style="width: 3em; object-fit: contain;"
								onclick="window.open(this.src)" src=<%=bookInfo.getCover() %>>
						</p></td>
						<td><p>
								<a href="bookManagerDetail.jsp?isbn=<%=bookInfo.getIsbn() %>"><%=bookInfo.getIsbn() %></a>
							</p></td>
						<td><p><%=bookInfo.getTitle() %></p></td>
						<td><p><%=bookInfo.getAuthor() %></p></td>
						<td><p><%=bookInfo.getPublisher() %></p></td>
						<td><p><%=bookInfo.getBookLendingCnt() %>/5</p></td>
						<td><p><%=bookInfo.getBookTotalLendingCnt() %></p></td>
					</tr>
	<%
					}}
		} else if( option.equals("isbn") ) {
			List<BookInfoDTO> bookInfoList = bookInfoDao.selectBookInfoByIsbn(search);
			if(bookInfoList != null && bookInfoList.size()>0) {
				for(BookInfoDTO bookInfo : bookInfoList){
	%>
					<tr>
						<td><p><%=bookInfo.getRank() %></p></td>
						<td><p>
								<img style="width: 3em; object-fit: contain;"
								onclick="window.open(this.src)" src=<%=bookInfo.getCover() %>>
						</p></td>
						<td><p>
								<a href="bookManagerDetail.jsp?isbn=<%=bookInfo.getIsbn() %>"><%=bookInfo.getIsbn() %></a>
							</p></td>
						<td><p><%=bookInfo.getTitle() %></p></td>
						<td><p><%=bookInfo.getAuthor() %></p></td>
						<td><p><%=bookInfo.getPublisher() %></p></td>
						<td><p><%=bookInfo.getBookLendingCnt() %>/5</p></td>
						<td><p><%=bookInfo.getBookTotalLendingCnt() %></p></td>
					</tr>
	<%
					}}
		}} else {
			List<BookInfoDTO> bookInfoList = bookInfoDao.selectAdminBookInfo();
				if(bookInfoList != null && bookInfoList.size()>0) {
  				for(BookInfoDTO bookInfo : bookInfoList){
	%>
					<tr>
						<td><p><%=bookInfo.getRank() %></p></td>
						<td><p>
								<img style="width: 3em; object-fit: contain;"
								onclick="window.open(this.src)" src=<%=bookInfo.getCover() %>>
						</p></td>
						<td><p>
								<a href="bookManagerDetail.jsp?isbn=<%=bookInfo.getIsbn() %>"><%=bookInfo.getIsbn() %></a>
							</p></td>
						<td><p><%=bookInfo.getTitle() %></p></td>
						<td><p><%=bookInfo.getAuthor() %></p></td>
						<td><p><%=bookInfo.getPublisher() %></p></td>
						<td><p><%=bookInfo.getBookLendingCnt() %>/5</p></td>
						<td><p><%=bookInfo.getBookTotalLendingCnt() %></p></td>
					</tr>
	<%
							}};
	}%>


							</tbody>
						</table>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>