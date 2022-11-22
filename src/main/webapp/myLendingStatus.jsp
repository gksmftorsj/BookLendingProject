<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="userInfo.UserInfoDTO" %>
<%@ page import="userInfo.UserInfoDAO" %>
<%@ page import="bookLend.BookLendDAO" %>
<%@ page import="bookLend.BookLendDTO" %>
<%@ page import="bookInfo.BookInfoDAO" %>
<%@ page import="bookInfo.BookInfoDTO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page - LendingStatus</title>
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

	//로그인o
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	
		UserInfoDAO userInfoDao = new UserInfoDAO();
		userNo = userInfoDao.selectUserNo(userID);

	
	%>


	<div class="container">
		<h2>마이 페이지</h2>
			<div class="col-auto">
				<label for="name">나의대여현황</label>
				<fieldset>
					<div class="order_box01">
					<form action="myLendingStatus.jsp" method="post">
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
				<div class="myTable">
					<table class="lendingTable container">
						<thead>
							<tr>
								<th scope="col"><p>대여일</p></th>
								<th scope="col"><p>반납일</p></th>
								<th scope="col"><p>대여번호</p></th>
								<th scope="col"><p>회원명</p></th>
								<th scope="col"><p>도서명</p></th>
								<th scope="col"><p>연장</p></th>
								<th scope="col"><p>비고</p></th>
							</tr>
						</thead>
						<tbody>
	<%
		BookLendDAO bookLendDao = new BookLendDAO();
		List<BookLendDTO> bookLendList = null;
		BookInfoDAO bookInfoDao = new BookInfoDAO();
		String bookTitle = null;
	
							
		if (( request.getParameter("year") == "") || ( request.getParameter("month") == "" ) || ( request.getParameter("year") == null) || ( request.getParameter("month") == null)) {
			//1. 날짜 선택x & 검색어 입력o
			if (( request.getParameter("searching") != null ) && (request.getParameter("lendingOption") != null) && ( request.getParameter("searching") != "" ) && (request.getParameter("lendingOption") != "")) {
				String search = request.getParameter("searching");
				String option = request.getParameter("lendingOption");
				if ( option.equals("lendNo") ) {
					bookLendList = bookLendDao.selectAdminBookLendDetailByLendNoAndUserNo(search, userNo);
					if(bookLendList != null && bookLendList.size()>0) {
						for(BookLendDTO bookLend : bookLendList){
							bookTitle = bookInfoDao.selectBookTitleByIsbn(bookLend.getBookIsbn());
		%>
               <tr>
					<td><p><%=bookLend.getLdDate() %></p></td>
					<td><p><%=bookLend.getRtDate() %></p></td>
					<td><p><%=bookLend.getLendNo() %></p></td>
					<td><p><%=bookLend.getUserName() %></p></td>
					<td><p><a href="bookDetail.jsp?title=<%=bookTitle%>"><%=bookLend.getTitle() %></a></p></td>
	                <td><%if (bookLend.getExtensionStatus().equals("true")) {
	                  		if(bookLend.getReturnStatus().equals("true")){%><p>만료</p>
	                        <%} else {%>
	                        <form method="post" action="./extensionAction.jsp?isbn=<%=bookLend.getBookIsbn()%>&userID=<%=userID%>">
	                            <p><button type="submit" onclick="{
	                                if(!confirm('연장하시겠습니까?')){
	                                   alert('연장이 취소되었습니다.');
	                                } }" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button></p>
	                        </form><%}
	                        } else {%><p>연장완료</p><%}%>
	                </td>
	                <td><p><%if(bookLend.getReturnStatus().equals("false")){
	                			if (today.after(bookLend.getExpectedReturnDate())) {%>
	                       			<button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
	                        	} else {%><button type="button" class="btn btn-success lendingBtn lendingTitle" style="width: 100px;">대여중</button><%}
	                		} else {%>반납완료<%}%>
	                </p></td>
	           </tr>
		<%
									}
								}
				} else if ( option.equals("title") ) {
					bookLendList = bookLendDao.selectAdminBookLendDetailByTitleAndUserNo(search, userNo);
					if(bookLendList != null && bookLendList.size()>0) {
						for(BookLendDTO bookLend : bookLendList){
							bookTitle = bookInfoDao.selectBookTitleByIsbn(bookLend.getBookIsbn());
							%>
				               <tr>
									<td><p><%=bookLend.getLdDate() %></p></td>
									<td><p><%=bookLend.getRtDate() %></p></td>
									<td><p><%=bookLend.getLendNo() %></p></td>
									<td><p><%=bookLend.getUserName() %></p></td>
									<td><p><a href="bookDetail.jsp?title=<%=bookTitle%>"><%=bookLend.getTitle() %></a></p></td>
					                <td><%if (bookLend.getExtensionStatus().equals("true")) {
					                  		if(bookLend.getReturnStatus().equals("true")){%><p>만료</p>
					                        <%} else {%>
					                        <form method="post" action="./extensionAction.jsp?isbn=<%=bookLend.getBookIsbn()%>&userID=<%=userID%>">
					                            <p><button type="submit" onclick="{
					                                if(!confirm('연장하시겠습니까?')){
					                                   alert('연장이 취소되었습니다.');
					                                } }" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button></p>
					                        </form><%}
					                        } else {%><p>연장완료</p><%}%>
					                </td>
					                <td><p><%if(bookLend.getReturnStatus().equals("false")){
					                			if (today.after(bookLend.getExpectedReturnDate())) {%>
					                       			<button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
					                        	} else {%><button type="button" class="btn btn-success lendingBtn lendingTitle" style="width: 100px;">대여중</button><%}
					                		} else {%>반납완료<%}%>
					                </p></td>
					           </tr>
						<%					}
					}
				} 	
			} else { //2. 날짜 선택x & 검색어 입력x
				bookLendList = bookLendDao.selectAdminBookLendDetailThisMonthByUserNo(userNo);
				if(bookLendList != null && bookLendList.size()>0) {
					for(BookLendDTO bookLend : bookLendList){
						bookTitle = bookInfoDao.selectBookTitleByIsbn(bookLend.getBookIsbn());
						%>
			               <tr>
								<td><p><%=bookLend.getLdDate() %></p></td>
								<td><p><%=bookLend.getRtDate() %></p></td>
								<td><p><%=bookLend.getLendNo() %></p></td>
								<td><p><%=bookLend.getUserName() %></p></td>
								<td><p><a href="bookDetail.jsp?title=<%=bookTitle%>"><%=bookLend.getTitle() %></a></p></td>
				                <td><%if (bookLend.getExtensionStatus().equals("true")) {
				                  		if(bookLend.getReturnStatus().equals("true")){%><p>만료</p>
				                        <%} else {%>
				                        <form method="post" action="./extensionAction.jsp?isbn=<%=bookLend.getBookIsbn()%>&userID=<%=userID%>">
				                            <p><button type="submit" onclick="{
				                                if(!confirm('연장하시겠습니까?')){
				                                   alert('연장이 취소되었습니다.');
				                                } }" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button></p>
				                        </form><%}
				                        } else {%><p>연장완료</p><%}%>
				                </td>
				                <td><p><%if(bookLend.getReturnStatus().equals("false")){
				                			if (today.after(bookLend.getExpectedReturnDate())) {%>
				                       			<button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
				                        	} else {%><button type="button" class="btn btn-success lendingBtn lendingTitle" style="width: 100px;">대여중</button><%}
				                		} else {%>반납완료<%}%>
				                </p></td>
				           </tr>
					<%
						}
					}
			}		
		} else {
			String lendDate = request.getParameter("year") + request.getParameter("month");
			if ( (request.getParameter("searching") == null) || (request.getParameter("searching") == "") || (request.getParameter("lendingOption") == null) || (request.getParameter("lendingOption") == "")) {
			//1. 날짜선택o & 검색어 입력x
				bookLendList = bookLendDao.selectAdminBookLendDetailByLendDateAndUserNo(lendDate, userNo);
				if (bookLendList != null && bookLendList.size() > 0) {
					for (BookLendDTO bookLend : bookLendList) {
						bookTitle = bookInfoDao.selectBookTitleByIsbn(bookLend.getBookIsbn());
						%>
			               <tr>
								<td><p><%=bookLend.getLdDate() %></p></td>
								<td><p><%=bookLend.getRtDate() %></p></td>
								<td><p><%=bookLend.getLendNo() %></p></td>
								<td><p><%=bookLend.getUserName() %></p></td>
								<td><p><a href="bookDetail.jsp?title=<%=bookTitle%>"><%=bookLend.getTitle() %></a></p></td>
				                <td><%if (bookLend.getExtensionStatus().equals("true")) {
				                  		if(bookLend.getReturnStatus().equals("true")){%><p>만료</p>
				                        <%} else {%>
				                        <form method="post" action="./extensionAction.jsp?isbn=<%=bookLend.getBookIsbn()%>&userID=<%=userID%>">
				                            <p><button type="submit" onclick="{
				                                if(!confirm('연장하시겠습니까?')){
				                                   alert('연장이 취소되었습니다.');
				                                } }" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button></p>
				                        </form><%}
				                        } else {%><p>연장완료</p><%}%>
				                </td>
				                <td><p><%if(bookLend.getReturnStatus().equals("false")){
				                			if (today.after(bookLend.getExpectedReturnDate())) {%>
				                       			<button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
				                        	} else {%><button type="button" class="btn btn-success lendingBtn lendingTitle" style="width: 100px;">대여중</button><%}
				                		} else {%>반납완료<%}%>
				                </p></td>
				           </tr>
					<%
					}
				}
			} else { //2. 날짜 선택o & 검색어 입력o
				String search = request.getParameter("searching");
				String option = request.getParameter("lendingOption");
				if ( option.equals("lendNo") ) {
					bookLendList = bookLendDao.selectAdminBookLendDetailByLendDateAndLendNoAndUserNo(lendDate, search, userNo);
					if(bookLendList != null && bookLendList.size()>0) {
						for(BookLendDTO bookLend : bookLendList){
							bookTitle = bookInfoDao.selectBookTitleByIsbn(bookLend.getBookIsbn());
							%>
				               <tr>
									<td><p><%=bookLend.getLdDate() %></p></td>
									<td><p><%=bookLend.getRtDate() %></p></td>
									<td><p><%=bookLend.getLendNo() %></p></td>
									<td><p><%=bookLend.getUserName() %></p></td>
									<td><p><a href="bookDetail.jsp?title=<%=bookTitle%>"><%=bookLend.getTitle() %></a></p></td>
					                <td><%if (bookLend.getExtensionStatus().equals("true")) {
					                  		if(bookLend.getReturnStatus().equals("true")){%><p>만료</p>
					                        <%} else {%>
					                        <form method="post" action="./extensionAction.jsp?isbn=<%=bookLend.getBookIsbn()%>&userID=<%=userID%>">
					                            <p><button type="submit" onclick="{
					                                if(!confirm('연장하시겠습니까?')){
					                                   alert('연장이 취소되었습니다.');
					                                } }" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button></p>
					                        </form><%}
					                        } else {%><p>연장완료</p><%}%>
					                </td>
					                <td><p><%if(bookLend.getReturnStatus().equals("false")){
					                			if (today.after(bookLend.getExpectedReturnDate())) {%>
					                       			<button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
					                        	} else {%><button type="button" class="btn btn-success lendingBtn lendingTitle" style="width: 100px;">대여중</button><%}
					                		} else {%>반납완료<%}%>
					                </p></td>
					           </tr>
						<%
									}
								}
				} else if ( option.equals("title") ) {
					bookLendList = bookLendDao.selectAdminBookLendDetailByLendDateAndTitleAndUserNo(lendDate, search, userNo);
					if(bookLendList != null && bookLendList.size()>0) {
						for(BookLendDTO bookLend : bookLendList){
							bookTitle = bookInfoDao.selectBookTitleByIsbn(bookLend.getBookIsbn());
							%>
				               <tr>
									<td><p><%=bookLend.getLdDate() %></p></td>
									<td><p><%=bookLend.getRtDate() %></p></td>
									<td><p><%=bookLend.getLendNo() %></p></td>
									<td><p><%=bookLend.getUserName() %></p></td>
									<td><p><a href="bookDetail.jsp?title=<%=bookTitle%>"><%=bookLend.getTitle() %></a></p></td>
					                <td><%if (bookLend.getExtensionStatus().equals("true")) {
					                  		if(bookLend.getReturnStatus().equals("true")){%><p>만료</p>
					                        <%} else {%>
					                        <form method="post" action="./extensionAction.jsp?isbn=<%=bookLend.getBookIsbn()%>&userID=<%=userID%>">
					                            <p><button type="submit" onclick="{
					                                if(!confirm('연장하시겠습니까?')){
					                                   alert('연장이 취소되었습니다.');
					                                } }" class="btn btn-light extensionBtn extensionTitle" style="width: 100px;">연장가능</button></p>
					                        </form><%}
					                        } else {%><p>연장완료</p><%}%>
					                </td>
					                <td><p><%if(bookLend.getReturnStatus().equals("false")){
					                			if (today.after(bookLend.getExpectedReturnDate())) {%>
					                       			<button type="button" class="btn btn-danger overDueBtn overDueTitle" style="width: 100px;">연체중</button><%
					                        	} else {%><button type="button" class="btn btn-success lendingBtn lendingTitle" style="width: 100px;">대여중</button><%}
					                		} else {%>반납완료<%}%>
					                </p></td>
					           </tr>
						<%
						}
					}
				} 
			}
		}
	} else { //로그인x
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