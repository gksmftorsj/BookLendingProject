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
<title>Admin - LendingStatus</title>
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

.adminTable{
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
	%>


	<div class="container">
		<h2>관리자 페이지</h2>
			<div class="col-auto">
				<label for="name">전체대여현황</label>
				<fieldset>
					<div class="order_box01">
					<form action="lendingStatus.jsp" method="post">
						<div class="order_box02">
							<div class="account_select01">
								<select id="select_searchYearSel" name="year" class="Searchselect_01"
									title="연도 선택">
									<option value="" selected>전체보기</option>
									<option value="2022">2022</option>
								</select><span style="color: #636363; font-weight: bold;"> 년</span> <select
									id="select_searchMonthSel" name="month" class="Searchselect_01" title="월 선택">
									<option value="" selected>전체보기</option>
									<option value="01">1</option>
									<option value="02">2</option>
									<option value="03">3</option>
									<option value="04">4</option>
									<option value="05">5</option>
									<option value="06">6</option>
									<option value="07">7</option>
									<option value="08">8</option>
									<option value="09">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
								</select><span style="color: #636363; font-weight: bold;"> 월</span>

<!-- 								<div class="account_select02">
									<label for="select04" style="color: #636363; font-weight: bold;"> 상태별조회</label> <select
										id="select_searchOrderStatusSel" name="lendingStatus" class="Searchselect_01">
										<option value="0" selected="">전체보기</option>
										<option value="1">대여중</option>
										<option value="2"><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button></option>
										<option value="3">반납완료</option>
									</select>
								</div>	 -->							
							</div>
						</div>
						<div class="order_box_line"></div>

						<div class="order_box03">
							<div class="account_select03" style="width: 100%">								
									<div class="input-group">
										<select name="lendingOption"
											class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
											id="inputGroupSelect01">
											<ul class="dropdown-menu">
												<li><option class="dropdown-item" value="" selected>대여내역조회</option></li>
												<li><option class="dropdown-item" value="lendNo">대여번호</option></li>
												<li><option class="dropdown-item" value="title">도서명</option></li>
												<li><option class="dropdown-divider" disabled>-------</option></li>
												<li><option class="dropdown-item" value="userNo">회원번호</option></li>
										</select>
										</ul>
										<input type="text" class="form-control"
											aria-label="Text input with 2 dropdown buttons"
											placeholder="검색어를 입력하세요"
											name="searching" value="">
										<button class="btn btn-outline-secondary" type="submit">검색</button>
										</label>
									</div>
								
							</div>
						</div>
						</form>
					</div>
			</div>
			</fieldset>

			<fieldset>
			<div class="col-auto">
				<label for="name">대여내역</label>
				<div class="adminTable">
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
						

	if ( request.getParameter("year") != null && request.getParameter("month") != null && request.getParameter("year") != "" && request.getParameter("month") != "" ) {
			String lendDate = request.getParameter("year") + request.getParameter("month");
			if ( (request.getParameter("searching") == null) || (request.getParameter("searching") == "")) {
			//날짜선택o & 검색어 입력x
				bookLendList = bookLendDao.selectAdminBookLendDetailByLendDate(lendDate);
				if(bookLendList != null && bookLendList.size()>0) {
					for(BookLendDTO bookLend : bookLendList){	%>
					<tr>
						<td><p><%=bookLend.getLendDate() %></p></td>
						<td><p>
								<%=bookLend.getLendNo() %>
						</p></td>
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
								<button type="button" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button>
								<%}%>
						</p></td>
						<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
								%><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;"><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
								} else {%>								
								<%}%>
						</p></td>
					</tr>			
			
<%					}
				}
			} else {
				//날짜 선택o & 검색어 입력o
					String search = request.getParameter("searching");
					String option = request.getParameter("lendingOption");
					
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
											<button type="button" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button>
											<%}%>
										</p></td>
										<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
											%><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
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
											<%=bookLend.getLendNo() %>
									</p></td>
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
										<button type="button" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button>
										<%}%>
									</p></td>
									<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
										%><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
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
											<%=bookLend.getLendNo() %>
									</p></td>
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
										<button type="button" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button>
										<%}%>
									</p></td>
									<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
										%><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
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
	//날짜 선택x & 검색어 입력o	
	if( request.getParameter("searching") != null && request.getParameter("searching") != ""){
		String search = request.getParameter("searching");
		String option = request.getParameter("lendingOption");
		
		if ( option.equals("lendNo") ) {
			bookLendList = bookLendDao.selectAdminBookLendDetailByLendNo(search);
				if(bookLendList != null && bookLendList.size()>0) {
					for(BookLendDTO bookLend : bookLendList){
		%>					<tr>
								<td><p><%=bookLend.getLendDate() %></p></td>
								<td><p>
										<%=bookLend.getLendNo() %>
								</p></td>
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
									<button type="button" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button>
									<%}%>
								</p></td>
								<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
									%><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
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
									<%=bookLend.getLendNo() %>
							</p></td>
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
								<button type="button" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button>
								<%}%>
							</p></td>
							<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
								%><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
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
									<%=bookLend.getLendNo() %>
							</p></td>
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
								<button type="button" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button>
								<%}%>
							</p></td>
							<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
								%><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
								} else {%>								
								<%}%>
							</p></td>
						</tr>
	<%
						}
					}
				}
			} else {
		//검색어 입력x & 날짜선택x & 타페이지에서 userNo받아옴
			if( request.getParameter("userNo") != null ){
				String userNo = request.getParameter("userNo");
				bookLendList = bookLendDao.selectAdminNotReturnBookLendDetailByUserNo(userNo);
				if(bookLendList != null && bookLendList.size()>0) {
					for(BookLendDTO bookLend : bookLendList){	%>
						<tr>
							<td><p><%=bookLend.getLendDate() %></p></td>
							<td><p>
									<%=bookLend.getLendNo() %>
							</p></td>
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
									<button type="button" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button>
									<%}%>
							</p></td>
							<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
									%><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;"><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
									} else {%>								
									<%}%>
							</p></td>
						</tr>			
				
	<%					}
					}
				} else {
		//검색어 입력x & 날짜선택x
					bookLendList = bookLendDao.selectAdminBookLendDetailThisMonth();
					if(bookLendList != null && bookLendList.size()>0) {
						for(BookLendDTO bookLend : bookLendList){
					%>
							<tr>
								<td><p><%=bookLend.getLendDate() %></p></td>
								<td><p>
										<%=bookLend.getLendNo() %>
								</p></td>
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
										<button type="button" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button>
										<%}%>
								</p></td>
								<td><p><%if (today.after(bookLend.getExpectedReturnDate())) {
										%><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;"><button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
										} else {%>								
										<%}%>
								</p></td>
							</tr>
		<%
							}
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
	</div>
</body>
</html>