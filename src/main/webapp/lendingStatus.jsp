<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="bookLend.BookLendDAO" %>
<%@ page import="bookLend.BookLendDTO" %>
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
	display: inline;
}
.order_box03 {
	margin-top: 15px;	
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
</style>
</head>
<body>
	<%@ include file="adminNavbar.jsp"%>

	<%
	String date = ("2009-03-20"+" 00:00:00.0"); // 형식을 지켜야 함
	LocalDateTime now = LocalDateTime.now();
	Timestamp today = Timestamp.valueOf(now);
	out.print(today);
	%>


	<div class="container">
		<h2>관리자 페이지</h2>
		<form name="lendingInfoForm" class="lendingInfoForm" method="post">
			<div class="col-auto">
				<label for="name">전체대여현황</label>
				<fieldset>
					<div class="order_box01">
						<div class="order_box02">
							<div class="account_select01">
								<select id="select_searchYearSel" name="year" class="Searchselect_01"
									title="연도 선택">
									<option value="0" selected="">전체보기</option>
									<option value="2022">2022</option>
								</select><span style="color: #636363; font-weight: bold;"> 년</span> <select
									id="select_searchMonthSel" name="month" class="Searchselect_01" title="월 선택">
									<option value="0" selected="">전체보기</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
								</select><span style="color: #636363; font-weight: bold;"> 월</span>

<!-- 								<div class="account_select02">
									<label for="select04" style="color: #636363; font-weight: bold;"> 상태별조회</label> <select
										id="select_searchOrderStatusSel" name="lendingStatus" class="Searchselect_01">
										<option value="0" selected="">전체보기</option>
										<option value="1">대여중</option>
										<option value="2">연체중</option>
										<option value="3">반납완료</option>
									</select>
								</div>	 -->							
							</div>
						</div>
						<div class="order_box_line"></div>

						<div class="order_box03">

							<div class="account_select03" style="width: 100%">
								<form action="lendingStatus.jsp" method="post">
									<div class="input-group">
										<select name="lendingOption" required
											class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
											id="inputGroupSelect01">
											<ul class="dropdown-menu">
												<li><option class="dropdown-item" selected>대여내역조회</option></li>
												<li><option class="dropdown-item" value="lendNo">대여번호</option></li>
												<li><option class="dropdown-item" value="title">도서명</option></li>
												<li><option class="dropdown-divider" disabled>-------</option></li>
												<li><option class="dropdown-item" value="userNo">회원번호</option></li>
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
			</div>
			</fieldset>

			<fieldset>
			<div class="col-auto">
				<label for="name">대여내역</label>
				<div class="myTable">
					<table class="lendingTable container">
						<thead>
							<tr>
								<th scope="col"><p>대여일</p></th>
								<th scope="col"><p>대여번호</p></th>
								<th scope="col"><p>회원명</p></th>
								<th scope="col"><p>도서명</p></th>
								<th scope="col"><p>반납</p></th>
								<th scope="col"><p>연장</p></th>
								<th scope="col"><p>비고</p></th>
							</tr>
						</thead>
						<tbody>
	<%
	BookLendDAO bookLendDao = new BookLendDAO();
	List<BookLendDTO> bookLendList = null;

	if( request.getParameter("searching") != null ){
		String search = request.getParameter("searching");		
		String option = request.getParameter("lendingOption");

		if ( request.getParameter("year") != null && request.getParameter("month") != null ) {
			String lendDate = request.getParameter("year") + request.getParameter("month");	
			
			if ( option.equals("lendNo") ) {
				bookLendList = bookLendDao.selectAdminBookLendDetailByLendDateAndLendNo(lendDate, search);
				if(bookLendList != null && bookLendList.size()>0) {
					for(BookLendDTO bookLend : bookLendList){
	%>					<tr>
							<td><p><%=bookLend.getLendDate() %></p></td>
							<td><p><%=bookLend.getLendNo() %></p></td>
							<td><p>
								<a href="userManager.jsp?userNo=<%=bookLend.getUserNo() %>"><%=bookLend.getUserName() %></a>
							</p></td>
							<td><p><%=bookLend.getTitle() %></p></td>
							<td><p><%if (bookLend.getReturnStatus().equals("false")) {
								%><button type="button" class="btn btn-dark returnBtn returnTitle" style="width: 100px;">반납가능</button><%
								} else {%>반납완료<%}%>
							</p></td>
							<td><p><%if (bookLend.getExtensionStatus() == "false") {
								%>만료
								<%} else {%>
								<button type="button" class="btn btn-success extensionBtn extensionTitle" style="width: 100px;">연장가능</button>
								<%}%>
							</p></td>
							<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
								%>연체중<%
								} else {%>								
								<%}%>
							</p></td>
						</tr>
	<%
					}
				}
			} else if ( option.equals("title") ) {
				bookLendList = bookLendDao.selectAdminBookLendDetailByLendDateAndTitle(lendDate, search);
				if(bookLendList != null && bookLendList.size()>0) {
					for(BookLendDTO bookLend : bookLendList){
						%>					<tr>
						<td><p><%=bookLend.getLendDate() %></p></td>
						<td><p>
								<a href="#"><%=bookLend.getLendNo() %></a>
						</p></td>
						<td><p>
							<a href="#"><%=bookLend.getUserName() %></a>
						</p></td>
						<td><p><%=bookLend.getTitle() %></p></td>
						<td><p><%if (bookLend.getReturnStatus().equals("false")) {
							%><a href="#">반납가능</a><%
							} else {%>반납완료<%}%>
						</p></td>
						<td><p><%if (bookLend.getExtensionStatus() == "false") {
							%>만료
							<%} else {%>
							<a href="#">연장가능</a>
							<%}%>
						</p></td>
						<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
							%>연체중<%
							} else {%>								
							<%}%>
						</p></td>
					</tr>
<%
					}
				}
			} else if ( option.equals("userNo") ) {
				bookLendList = bookLendDao.selectAdminBookLendDetailByLendDateAndUserNo(lendDate, search);
				if(bookLendList != null && bookLendList.size()>0) {
					for(BookLendDTO bookLend : bookLendList){
						%>					<tr>
						<td><p><%=bookLend.getLendDate() %></p></td>
						<td><p>
								<a href="#"><%=bookLend.getLendNo() %></a>
						</p></td>
						<td><p>
							<a href="#"><%=bookLend.getUserName() %></a>
						</p></td>
						<td><p><%=bookLend.getTitle() %></p></td>
						<td><p><%if (bookLend.getReturnStatus().equals("false")) {
							%><a href="#">반납가능</a><%
							} else {%>반납완료<%}%>
						</p></td>
						<td><p><%if (bookLend.getExtensionStatus() == "false") {
							%>만료
							<%} else {%>
							<a href="#">연장가능</a>
							<%}%>
						</p></td>
						<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
							%>연체중<%
							} else {%>								
							<%}%>
						</p></td>
					</tr>
<%
					}
				}
			}
		} else {
				
				if ( option.equals("lendNo") ) {
					bookLendList = bookLendDao.selectAdminBookLendDetailByLendNo(search);
					if(bookLendList != null && bookLendList.size()>0) {
						for(BookLendDTO bookLend : bookLendList){
		%>					<tr>
								<td><p><%=bookLend.getLendDate() %></p></td>
								<td><p>
										<a href="#"><%=bookLend.getLendNo() %></a>
								</p></td>
								<td><p>
									<a href="#"><%=bookLend.getUserName() %></a>
								</p></td>
								<td><p><%=bookLend.getTitle() %></p></td>
								<td><p><%if (bookLend.getReturnStatus().equals("false")) {
									%><a href="#">반납가능</a><%
									} else {%>반납완료<%}%>
								</p></td>
								<td><p><%if (bookLend.getExtensionStatus() == "false") {
									%>만료
									<%} else {%>
									<a href="#">연장가능</a>
									<%}%>
								</p></td>
								<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
									%>연체중<%
									} else {%>								
									<%}%>
								</p></td>
							</tr>
		<%
						}
					}
				} else if ( option.equals("title") ) {
					bookLendList = bookLendDao.selectAdminBookLendDetailByTitle(search);
					if(bookLendList != null && bookLendList.size()>0) {
						for(BookLendDTO bookLend : bookLendList){
							%>					<tr>
							<td><p><%=bookLend.getLendDate() %></p></td>
							<td><p>
									<a href="#"><%=bookLend.getLendNo() %></a>
							</p></td>
							<td><p>
								<a href="#"><%=bookLend.getUserName() %></a>
							</p></td>
							<td><p><%=bookLend.getTitle() %></p></td>
							<td><p><%if (bookLend.getReturnStatus().equals("false")) {
								%><a href="#">반납가능</a><%
								} else {%>반납완료<%}%>
							</p></td>
							<td><p><%if (bookLend.getExtensionStatus() == "false") {
								%>만료
								<%} else {%>
								<a href="#">연장가능</a>
								<%}%>
							</p></td>
							<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
								%>연체중<%
								} else {%>								
								<%}%>
							</p></td>
						</tr>
	<%
						}
					}
				} else if ( option.equals("userNo") ) {
					bookLendList = bookLendDao.selectAdminBookLendDetailByUserNo(search);
					if(bookLendList != null && bookLendList.size()>0) {
						for(BookLendDTO bookLend : bookLendList){
							%>					<tr>
							<td><p><%=bookLend.getLendDate() %></p></td>
							<td><p>
									<a href="#"><%=bookLend.getLendNo() %></a>
							</p></td>
							<td><p>
								<a href="#"><%=bookLend.getUserName() %></a>
							</p></td>
							<td><p><%=bookLend.getTitle() %></p></td>
							<td><p><%if (bookLend.getReturnStatus().equals("false")) {
								%><a href="#">반납가능</a><%
								} else {%>반납완료<%}%>
							</p></td>
							<td><p><%if (bookLend.getExtensionStatus() == "false") {
								%>만료
								<%} else {%>
								<a href="#">연장가능</a>
								<%}%>
							</p></td>
							<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
								%>연체중<%
								} else {%>								
								<%}%>
							</p></td>
						</tr>
	<%
						}
					}
				}
			}

		} else {
			bookLendList = bookLendDao.selectAdminBookLendDetailThisMonth();
			if(bookLendList != null && bookLendList.size()>0) {
				for(BookLendDTO bookLend : bookLendList){
			%>
					<tr>
						<td><p><%=bookLend.getLendDate() %></p></td>
						<td><p>
								<a href="#"><%=bookLend.getLendNo() %></a>
						</p></td>
						<td><p>
								<a href="#"><%=bookLend.getUserName() %></a>
						</p></td>
						<td><p><%=bookLend.getTitle() %></p></td>
						<td><p><%if (bookLend.getReturnStatus().equals("false")) {
								%><a href="#">반납가능</a><%
								} else {%>반납완료<%}%>
						</p></td>
						<td><p><%if (bookLend.getExtensionStatus() == "false") {
								%>만료
								<%} else {%>
								<a href="#">연장가능</a>
								<%}%>
						</p></td>
						<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
								%>연체중<%
								} else {%>								
								<%}%>
						</p></td>
					</tr>
	<%
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