<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="userInfo.UserInfoDTO" %>
<%@ page import="userInfo.UserInfoDAO" %>
<%@ page import="bookReservation.BookReservationDAO" %>
<%@ page import="bookReservation.BookReservationDTO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page - ReservationStatus</title>
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
	<%@ include file="userNavbar.jsp"%>

	<%
 	String date = ("2009-03-20"+" 00:00:00.0"); // 형식을 지켜야 함
	LocalDateTime now = LocalDateTime.now();
	Timestamp today = Timestamp.valueOf(now);

	String userID = null;
	String userNo = null;

	// 로그인 o
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	
		UserInfoDAO userInfoDao = new UserInfoDAO();
		userNo = userInfoDao.selectUserNo(userID);

	%>


	<div class="container">
		<h2>마이 페이지</h2>
			<div class="col-auto">
				<label for="name">나의예약현황</label>
				<fieldset>
					<div class="order_box01">
					<form action="myReservationStatus.jsp" method="post">
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
										<select name="reservationOption"
											class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
											id="inputGroupSelect01">
											<ul class="dropdown-menu">
												<li><option class="dropdown-item" value="" selected>대여내역조회</option></li>
												<li><option class="dropdown-item" value="reservationNo">예약번호</option></li>
												<li><option class="dropdown-item" value="title">도서명</option></li>
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
				<label for="name">예약내역</label>
				<div class="myTable">
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
													
	
							
		if (( request.getParameter("year") == "") || ( request.getParameter("month") == "" ) || ( request.getParameter("year") == null) || ( request.getParameter("month") == null)) {
			//1. 날짜 선택x & 검색어 입력o
			if (( request.getParameter("searching") != null ) && (request.getParameter("reservationOption") != null) && ( request.getParameter("searching") != "" ) && (request.getParameter("reservationOption") != "")) {
				String search = request.getParameter("searching");
				String option = request.getParameter("reservationOption");
				if ( option.equals("reservationNo") ) {
					bookReservationList = bookReservationDao.selectAdminBookReservationDetailByRsNoAndUserNo(search, userNo);
					if(bookReservationList != null && bookReservationList.size()>0) {
						for(BookReservationDTO bookReservation : bookReservationList){
			%>					<tr>
									<td><p><%=bookReservation.getRsDate() %></p></td>
									<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
									<td><p><%=bookReservation.getReservationNo() %></p></td>
									<td><p><%=bookReservation.getUserName() %></p></td>
									<td><p><%=bookReservation.getTitle() %></p></td>
									<td><p><%if(bookReservation.getLendStatus().equals("false")){
												if (today.after(bookReservation.getExpectedReturnDate())) {%>
													<button type="button" class="btn btn-warning lendingOverDueBtn lendingOverDueTitle" style="width: 100px;">대여보류</button><%
												} else{%><button type="button" class="btn btn-info reservationBtn reservationTitle" style="width: 100px;">예약중</button><%}
											} else {%>대여완료<%}%>
									</p></td>
								</tr>
			<%
						}
					}
				} else if ( option.equals("title") ) {
					bookReservationList = bookReservationDao.selectAdminBookReservationDetailByTitleAndUserNo(search, userNo);
					if(bookReservationList != null && bookReservationList.size()>0) {
						for(BookReservationDTO bookReservation : bookReservationList){
			%>					<tr>
									<td><p><%=bookReservation.getRsDate() %></p></td>
									<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
									<td><p><%=bookReservation.getReservationNo() %></p></td>
									<td><p><%=bookReservation.getUserName() %></p></td>
									<td><p><%=bookReservation.getTitle() %></p></td>
									<td><p><%if(bookReservation.getLendStatus().equals("false")){
												if (today.after(bookReservation.getExpectedReturnDate())) {%>
													<button type="button" class="btn btn-warning lendingOverDueBtn lendingOverDueTitle" style="width: 100px;">대여보류</button><%
												} else{%><button type="button" class="btn btn-info reservationBtn reservationTitle" style="width: 100px;">예약중</button><%}
											} else {%>대여완료<%}%>
									</p></td>
								</tr>
			<%
						}
					}
				}
			} else { //2. 날짜 선택x & 검색어 입력x
				bookReservationList = bookReservationDao.selectAdminBookReservationDetailThisMonthByUserNo(userNo);
				if(bookReservationList != null && bookReservationList.size()>0) {
					for(BookReservationDTO bookReservation : bookReservationList){
		%>					<tr>
								<td><p><%=bookReservation.getRsDate() %></p></td>
								<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
								<td><p><%=bookReservation.getReservationNo() %></p></td>
								<td><p><%=bookReservation.getUserName() %></p></td>
								<td><p><%=bookReservation.getTitle() %></p></td>
								<td><p><%if(bookReservation.getLendStatus().equals("false")){
											if (today.after(bookReservation.getExpectedReturnDate())) {%>
												<button type="button" class="btn btn-warning lendingOverDueBtn lendingOverDueTitle" style="width: 100px;">대여보류</button><%
											} else{%><button type="button" class="btn btn-info reservationBtn reservationTitle" style="width: 100px;">예약중</button><%}
										} else {%>대여완료<%}%>
								</p></td>
							</tr>
		<%
					}
				}
			}		
		} else {
			String rsDate = request.getParameter("year") + request.getParameter("month");
			if ( (request.getParameter("searching") == null) || (request.getParameter("searching") == "") || (request.getParameter("reservationOption") == null) || (request.getParameter("reservationOption") == "")) {
			//1. 날짜선택o & 검색어 입력x
				bookReservationList = bookReservationDao.selectAdminBookReservationDetailByRsDateAndUserNo(rsDate, userNo);
				if(bookReservationList != null && bookReservationList.size()>0) {
					for(BookReservationDTO bookReservation : bookReservationList){
		%>					<tr>
								<td><p><%=bookReservation.getRsDate() %></p></td>
								<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
								<td><p><%=bookReservation.getReservationNo() %></p></td>
								<td><p><%=bookReservation.getUserName() %></p></td>
								<td><p><%=bookReservation.getTitle() %></p></td>
								<td><p><%if(bookReservation.getLendStatus().equals("false")){
											if (today.after(bookReservation.getExpectedReturnDate())) {%>
												<button type="button" class="btn btn-warning lendingOverDueBtn lendingOverDueTitle" style="width: 100px;">대여보류</button><%
											} else{%><button type="button" class="btn btn-info reservationBtn reservationTitle" style="width: 100px;">예약중</button><%}
										} else {%>대여완료<%}%>
								</p></td>
							</tr>
		<%
					}
				}
			} else { //2. 날짜 선택o & 검색어 입력o
				String search = request.getParameter("searching");
				String option = request.getParameter("reservationOption");
				if ( option.equals("reservationNo") ) {
					bookReservationList = bookReservationDao.selectAdminBookReservationDetailByRsDateAndRsNoAndUserNo(rsDate, search, userNo);
					if(bookReservationList != null && bookReservationList.size()>0) {
						for(BookReservationDTO bookReservation : bookReservationList){
			%>					<tr>
									<td><p><%=bookReservation.getRsDate() %></p></td>
									<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
									<td><p><%=bookReservation.getReservationNo() %></p></td>
									<td><p><%=bookReservation.getUserName() %></p></td>
									<td><p><%=bookReservation.getTitle() %></p></td>
									<td><p><%if(bookReservation.getLendStatus().equals("false")){
												if (today.after(bookReservation.getExpectedReturnDate())) {%>
													<button type="button" class="btn btn-warning lendingOverDueBtn lendingOverDueTitle" style="width: 100px;">대여보류</button><%
												} else{%><button type="button" class="btn btn-info reservationBtn reservationTitle" style="width: 100px;">예약중</button><%}
											} else {%>대여완료<%}%>
									</p></td>
								</tr>
			<%
						}
					}
				} else if ( option.equals("title") ) {
					bookReservationList = bookReservationDao.selectAdminBookReservationDetailByRsDateAndTitleAndUserNo(rsDate, search, userNo);
					if(bookReservationList != null && bookReservationList.size()>0) {
						for(BookReservationDTO bookReservation : bookReservationList){
			%>					<tr>
									<td><p><%=bookReservation.getRsDate() %></p></td>
									<td><p><%=bookReservation.getExpectedLendingDate() %></p></td>
									<td><p><%=bookReservation.getReservationNo() %></p></td>
									<td><p><%=bookReservation.getUserName() %></p></td>
									<td><p><%=bookReservation.getTitle() %></p></td>
									<td><p><%if(bookReservation.getLendStatus().equals("false")){
												if (today.after(bookReservation.getExpectedReturnDate())) {%>
													<button type="button" class="btn btn-warning lendingOverDueBtn lendingOverDueTitle" style="width: 100px;">대여보류</button><%
												} else{%><button type="button" class="btn btn-info reservationBtn reservationTitle" style="width: 100px;">예약중</button><%}
											} else {%>대여완료<%}%>
									</p></td>
								</tr>
			<%
						}
					}
				} 
			}
		}
	} else { // 로그인x
	%>
	<script>
		alert('로그인이 필요합니다.');
		location.href = "index.jsp";
	</script>
	<%	
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