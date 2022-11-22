<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="bookReservation.BookReservationDAO" %>
<%@ page import="bookReservation.BookReservationDTO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - ReservationStatus</title>
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
		<form name="reservationInfoForm" class="reservationInfoForm" method="post">
			<div class="col-auto">
				<label for="name">전체예약현황</label>
				<fieldset>
					<div class="order_box01">
						<div class="order_box02">
							<div class="account_select01">
								<select id="select_searchYearSel" name="year" class="Searchselect_01"
									title="연도 선택">
									<option value="" selected="">전체보기</option>
									<option value="2022">2022</option>
								</select><span style="color: #636363; font-weight: bold;"> 년</span> <select
									id="select_searchMonthSel" name="month" class="Searchselect_01" title="월 선택">
									<option value="" selected="">전체보기</option>
									<option value="1">01</option>
									<option value="2">02</option>
									<option value="3">03</option>
									<option value="4">04</option>
									<option value="5">05</option>
									<option value="6">06</option>
									<option value="7">07</option>
									<option value="8">08</option>
									<option value="9">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
								</select><span style="color: #636363; font-weight: bold;"> 월</span>

<!-- 								<div class="account_select02">
									<label for="select04" style="color: #636363; font-weight: bold;"> 상태별조회</label> <select
										id="select_searchOrderStatusSel" name="lendingStatus" class="Searchselect_01">
										<option value="0" selected="">전체보기</option>
										<option value="1">예약중</option>
										<option value="2">대여완료</option>
									</select>
								</div>	 -->							
							</div>
						</div>
						<div class="order_box_line"></div>

						<div class="order_box03">

							<div class="account_select03" style="width: 100%">
								<form action="reservationStatus.jsp" method="post">
									<div class="input-group">
										<select name="reservationOption"
											class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
											id="inputGroupSelect01">
											<ul class="dropdown-menu">
												<li><option class="dropdown-item" value="" selected>예약내역조회</option></li>
												<li><option class="dropdown-item" value="reservationNo">예약번호</option></li>
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
								</form>
							</div>
						</div>
					</div>
			</div>
			</fieldset>

			<fieldset>
			<div class="col-auto">
				<label for="name">대여내역</label>
				<div class="adminTable">
					<table class="reservationTable container">
						<thead>
							<tr>
								<th scope="col"><p>예약일</p></th>
								<th scope="col"><p>대여가능일</p></th>
								<th scope="col"><p>예약번호</p></th>
								<th scope="col"><p>회원명</p></th>
								<th scope="col"><p>도서명</p></th>
								<th scope="col"><p>비고</p></th>
							</tr>
						</thead>
						<tbody>
	<%
	BookReservationDAO bookReservationDao = new BookReservationDAO();
	List<BookReservationDTO> bookReservationList = null;
						

						if ( request.getParameter("year") != null && request.getParameter("month") != null && request.getParameter("year") != "" && request.getParameter("month") != "" ) {
								String rsDate = request.getParameter("year") + request.getParameter("month");
								if ( (request.getParameter("searching") == null) || (request.getParameter("searching") == "")) {
								//1. 날짜선택o & 검색어 입력x
							bookReservationList = bookReservationDao.selectAdminBookReservationDetailByRsDate(rsDate);
											if(bookReservationList != null && bookReservationList.size()>0) {
												for(BookReservationDTO bookReservation : bookReservationList){
								%>					<tr>
														<td><p><%=bookReservation.getRsDate() %></p></td>
														<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
														<td><p><%=bookReservation.getReservationNo() %></p></td>
														<td><p>
															<a href="userManager.jsp?userNo=<%=bookReservation.getUserNo() %>"><%=bookReservation.getUserName() %></a>
														</p></td>
														<td><p><a href="bookManager.jsp?searching=<%=bookReservation.getBookIsbn() %>"><%=bookReservation.getTitle() %></a></p></td>
														<td><p>
																<%
																if (bookReservation.getLendStatus().equals("false")) {
																	if (today.after(bookReservation.getExpectedReturnDate())) {
																%>
																<button type="button"
																	class="btn btn-warning lendingOverDueBtn lendingOverDueTitle"
																	style="width: 100px;">대여보류</button>
																<%
																} else {
																%><button type="button"
																	class="btn btn-info reservationBtn reservationTitle"
																	style="width: 100px;">예약중</button>
																<%}
																} else {%>대여완료<%}%>
														</p></td>
								</tr>
								<%
												}
											}
								} else {

										String search = request.getParameter("searching");
										String option = request.getParameter("reservationOption");
										
										//2. 날짜선택o & 검색어입력o
										if ( option.equals("reservationNo") ) {
											bookReservationList = bookReservationDao.selectAdminBookReservationDetailByRsDateAndRsNo(rsDate, search);
											if(bookReservationList != null && bookReservationList.size()>0) {
												for(BookReservationDTO bookReservation : bookReservationList){
								%>					<tr>
														<td><p><%=bookReservation.getRsDate() %></p></td>
														<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
														<td><p><%=bookReservation.getReservationNo() %></p></td>
														<td><p>
															<a href="userManager.jsp?userNo=<%=bookReservation.getUserNo() %>"><%=bookReservation.getUserName() %></a>
														</p></td>
														<td><p><a href="bookManager.jsp?searching=<%=bookReservation.getBookIsbn() %>"><%=bookReservation.getTitle() %></a></p></td>
														<td><p>
																<%
																if (bookReservation.getLendStatus().equals("false")) {
																	if (today.after(bookReservation.getExpectedReturnDate())) {
																%>
																<button type="button"
																	class="btn btn-warning lendingOverDueBtn lendingOverDueTitle"
																	style="width: 100px;">대여보류</button>
																<%
																} else {
																%><button type="button"
																	class="btn btn-info reservationBtn reservationTitle"
																	style="width: 100px;">예약중</button>
																<%}
																} else {%>대여완료<%}%>
														</p></td>
								</tr>
								<%
												}
											}
										} else if ( option.equals("title") ) {
											bookReservationList = bookReservationDao.selectAdminBookReservationDetailByRsDateAndTitle(rsDate, search);
											if(bookReservationList != null && bookReservationList.size()>0) {
												for(BookReservationDTO bookReservation : bookReservationList){
								%>					<tr>
														<td><p><%=bookReservation.getRsDate() %></p></td>
														<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
														<td><p><%=bookReservation.getReservationNo() %></p></td>
														<td><p>
															<a href="userManager.jsp?userNo=<%=bookReservation.getUserNo() %>"><%=bookReservation.getUserName() %></a>
														</p></td>
														<td><p><a href="bookManager.jsp?searching=<%=bookReservation.getBookIsbn() %>"><%=bookReservation.getTitle() %></a></p></td>
														<td><p></p></td>
													</tr>
								<%
												}
											}
										} else if ( option.equals("userNo") ) {
											bookReservationList = bookReservationDao.selectAdminBookReservationDetailByRsDateAndUserNo(rsDate, search);
											if(bookReservationList != null && bookReservationList.size()>0) {
												for(BookReservationDTO bookReservation : bookReservationList){
								%>					<tr>
														<td><p><%=bookReservation.getRsDate() %></p></td>
														<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
														<td><p><%=bookReservation.getReservationNo() %></p></td>
														<td><p>
															<a href="userManager.jsp?userNo=<%=bookReservation.getUserNo() %>"><%=bookReservation.getUserName() %></a>
														</p></td>
														<td><p><a href="bookManager.jsp?searching=<%=bookReservation.getBookIsbn() %>"><%=bookReservation.getTitle() %></a></p></td>
														<td><p>
																<%
																if (bookReservation.getLendStatus().equals("false")) {
																	if (today.after(bookReservation.getExpectedReturnDate())) {
																%>
																<button type="button"
																	class="btn btn-warning lendingOverDueBtn lendingOverDueTitle"
																	style="width: 100px;">대여보류</button>
																<%
																} else {
																%><button type="button"
																	class="btn btn-info reservationBtn reservationTitle"
																	style="width: 100px;">예약중</button>
																<%}
																} else {%>대여완료<%}%>
														</p></td>
								</tr>
								<%
												}
											}
										}
									}
						} else {
						//1. 날짜선택x	 & 검색어입력o
						if( request.getParameter("searching") != null && request.getParameter("searching") != ""){
							String search = request.getParameter("searching");
							String option = request.getParameter("reservationOption");
							

							if ( option.equals("reservationNo") ) {
								bookReservationList = bookReservationDao.selectAdminBookReservationDetailByRsNo(search);
								if(bookReservationList != null && bookReservationList.size()>0) {
									for(BookReservationDTO bookReservation : bookReservationList){
					%>					<tr>
											<td><p><%=bookReservation.getRsDate() %></p></td>
											<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
											<td><p><%=bookReservation.getReservationNo() %></p></td>
											<td><p>
												<a href="userManager.jsp?userNo=<%=bookReservation.getUserNo() %>"><%=bookReservation.getUserName() %></a>
											</p></td>
											<td><p><a href="bookManager.jsp?searching=<%=bookReservation.getBookIsbn() %>"><%=bookReservation.getTitle() %></a></p></td>
											<td><p>
													<%
													if (bookReservation.getLendStatus().equals("false")) {
														if (today.after(bookReservation.getExpectedReturnDate())) {
													%>
													<button type="button"
														class="btn btn-warning lendingOverDueBtn lendingOverDueTitle"
														style="width: 100px;">대여보류</button>
													<%
													} else {
													%><button type="button"
														class="btn btn-info reservationBtn reservationTitle"
														style="width: 100px;">예약중</button>
													<%}
													} else {%>대여완료<%}%>
											</p></td>
								</tr>
					<%
									}
								}
							} else if ( option.equals("title") ) {
								bookReservationList = bookReservationDao.selectAdminBookReservationDetailByTitle(search);
								if(bookReservationList != null && bookReservationList.size()>0) {
									for(BookReservationDTO bookReservation : bookReservationList){
					%>					<tr>
											<td><p><%=bookReservation.getRsDate() %></p></td>
											<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
											<td><p><%=bookReservation.getReservationNo() %></p></td>
											<td><p>
												<a href="userManager.jsp?userNo=<%=bookReservation.getUserNo() %>"><%=bookReservation.getUserName() %></a>
											</p></td>
											<td><p><a href="bookManager.jsp?searching=<%=bookReservation.getBookIsbn() %>"><%=bookReservation.getTitle() %></a></p></td>
											<td><p>
													<%
													if (bookReservation.getLendStatus().equals("false")) {
														if (today.after(bookReservation.getExpectedReturnDate())) {
													%>
													<button type="button"
														class="btn btn-warning lendingOverDueBtn lendingOverDueTitle"
														style="width: 100px;">대여보류</button>
													<%
													} else {
													%><button type="button"
														class="btn btn-info reservationBtn reservationTitle"
														style="width: 100px;">예약중</button>
													<%}
													} else {%>대여완료<%}%>
											</p></td>
								</tr>
					<%
									}
								}
							} else if ( option.equals("userNo") ) {
								bookReservationList = bookReservationDao.selectAdminBookReservationDetailByUserNo(search);
								if(bookReservationList != null && bookReservationList.size()>0) {
									for(BookReservationDTO bookReservation : bookReservationList){
					%>					<tr>
											<td><p><%=bookReservation.getRsDate() %></p></td>
											<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
											<td><p><%=bookReservation.getReservationNo() %></p></td>
											<td><p>
												<a href="userManager.jsp?userNo=<%=bookReservation.getUserNo() %>"><%=bookReservation.getUserName() %></a>
											</p></td>
											<td><p><a href="bookManager.jsp?searching=<%=bookReservation.getBookIsbn() %>"><%=bookReservation.getTitle() %></a></p></td>
											<td><p>
													<%
													if (bookReservation.getLendStatus().equals("false")) {
														if (today.after(bookReservation.getExpectedReturnDate())) {
													%>
													<button type="button"
														class="btn btn-warning lendingOverDueBtn lendingOverDueTitle"
														style="width: 100px;">대여보류</button>
													<%
													} else {
													%><button type="button"
														class="btn btn-info reservationBtn reservationTitle"
														style="width: 100px;">예약중</button>
													<%}
													} else {%>대여완료<%}%>
											</p></td>
								</tr>
					<%
									}
								}
							}
							} else {
								//2-1. 날짜선택x & 검색어 입력x & 타페이지에서 userNo받아옴
								if( request.getParameter("userNo") != null ){
									String userNo = request.getParameter("userNo");
									bookReservationList = bookReservationDao.selectAdminNotLendBookReservationDetailByUserNo(userNo);
									if(bookReservationList != null && bookReservationList.size()>0) {
										for(BookReservationDTO bookReservation : bookReservationList){
						%>					<tr>
												<td><p><%=bookReservation.getRsDate() %></p></td>
												<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
												<td><p><%=bookReservation.getReservationNo() %></p></td>
												<td><p>
													<a href="userManager.jsp?userNo=<%=bookReservation.getUserNo() %>"><%=bookReservation.getUserName() %></a>
												</p></td>
												<td><p><a href="bookManager.jsp?searching=<%=bookReservation.getBookIsbn() %>"><%=bookReservation.getTitle() %></a></p></td>
												<td><p>
														<%
														if (bookReservation.getLendStatus().equals("false")) {
															if (today.after(bookReservation.getExpectedReturnDate())) {
														%>
														<button type="button"
															class="btn btn-warning lendingOverDueBtn lendingOverDueTitle"
															style="width: 100px;">대여보류</button>
														<%
														} else {
														%><button type="button"
															class="btn btn-info reservationBtn reservationTitle"
															style="width: 100px;">예약중</button>
														<%}
														} else {%>대여완료<%}%>
												</p></td>
								</tr>
						<%
										}
									}
								} else {
							//2-2. 날짜선택x & 검색어 입력x
									bookReservationList = bookReservationDao.selectAdminBookReservationDetailThisMonth();
									if(bookReservationList != null && bookReservationList.size()>0) {
										for(BookReservationDTO bookReservation : bookReservationList){
						%>					<tr>
												<td><p><%=bookReservation.getRsDate() %></p></td>
												<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
												<td><p><%=bookReservation.getReservationNo() %></p></td>
												<td><p>
													<a href="userManager.jsp?userNo=<%=bookReservation.getUserNo() %>"><%=bookReservation.getUserName() %></a>
												</p></td>
												<td><p><a href="bookManager.jsp?bookIsbn=<%=bookReservation.getBookIsbn() %>"><%=bookReservation.getTitle() %></a></p></td>
												<td><p>
														<%
														if (bookReservation.getLendStatus().equals("false")) {
															if (today.after(bookReservation.getExpectedReturnDate())) {
														%>
														<button type="button"
															class="btn btn-warning lendingOverDueBtn lendingOverDueTitle"
															style="width: 100px;">대여보류</button>
														<%
														} else {
														%><button type="button"
															class="btn btn-info reservationBtn reservationTitle"
															style="width: 100px;">예약중</button>
														<%}
														} else {%>대여완료<%}%>
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
		</form>
	</div>
</body>
</html>