<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

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

	<div class="container">
		<h2>관리자 페이지</h2>
		<form name="lendingInfoForm" class="lendingInfoForm" method="post">
			<div class="col-auto">
				<label for="name">전체회원목록</label>
				<fieldset>
					<div class="order_box01">
						<div class="order_box02">
							<div class="account_select01">
								<label for="select04" style="color: #636363; font-weight: bold;">
									상태별조회</label><select id="select_searchOrderStatusSel"
									class="Searchselect_01">
									<option value="0" selected="">전체보기</option>
									<option value="1">대여중</option>
									<option value="2">연체중</option>
									<option value="3">반납완료</option>
								</select>
							</div>
						</div>
						<div class="order_box_line"></div>

						<div class="order_box03">

							<div class="account_select03" style="width: 100%">
								<div class="input-group">
									<button class="btn btn-outline-secondary dropdown-toggle"
										type="button" data-bs-toggle="dropdown" aria-expanded="false">대여내역조회</button>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">대여도서명</a></li>
										<li><a class="dropdown-item" href="#">대여번호</a></li>
									</ul>
									<input type="text" class="form-control"
										aria-label="Text input with 2 dropdown buttons">
									<button class="btn btn-outline-secondary" types="button">검색</button>
								</div>
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
								<th scope="col"><p>대여일자</p></th>
								<th scope="col"><p>대여번호</p></th>
								<th scope="col"><p>대여도서</p></th>
								<th scope="col"><p>반납일자</p></th>
								<th scope="col"><p>연장</p></th>
								<th scope="col"><p>비고(연체정보)</p></th>
							</tr>
						</thead>
						<tbody>
							<%
							for (int i = 0; i < 5; i++) {
							%>
							<tr>
								<td><p>YYYY-MM-DD</p></td>
								<td><p>
										<a href="#">YYMMDDLD0001</a>
									</p></td>
								<td><p>
										도서명<%=i + 1%></p></td>
								<td><p>YYYY-MM-DD</p></td>
								<td><p><%
										if (i % 6 == 0) {
										%><a href="#">연장가능</a>
										<%
										} else {
										%>기간만료<%}%>
										</a>
									</p></td>
								<td><p><%
										if ((i+1)%4 == 0) {
										%>연체
										<%
										} else {
										%><%}%>
										</p></td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
			</fieldset>

			<div class="col-auto">
				<label for="name">예약내역조회</label>
				<fieldset>
					<div class="order_box01">
						<div class="order_box02">
							<div class="account_select01">
								<select id="select_searchYearSel" class="Searchselect_01"
									title="연도 선택">
									<option value="0" selected="">전체보기</option>
									<option value="2022">2022</option>
								</select><span style="color: #636363; font-weight: bold;"> 년</span> <select
									id="select_searchMonthSel" class="Searchselect_01" title="월 선택">
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

							</div>
						</div>
						<div class="order_box_line"></div>

						<div class="order_box03">

							<div class="account_select03" style="width: 100%">
								<div class="input-group">
									<button class="btn btn-outline-secondary dropdown-toggle"
										type="button" data-bs-toggle="dropdown" aria-expanded="false">대여내역조회</button>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">예약도서명</a></li>
										<li><a class="dropdown-item" href="#">예약번호</a></li>
									</ul>
									<input type="text" class="form-control"
										aria-label="Text input with 2 dropdown buttons">
									<button class="btn btn-outline-secondary" types="button">검색</button>
								</div>
							</div>
						</div>
					</div>
			</fieldset>
			</div>
			
			<fieldset>
			<div class="col-auto">
				<label for="name">예약내역</label>
				<div class="myTable">
					<table class="reservationTable container">
						<thead>
							<tr>
								<th scope="col"><p>예약일자</p></th>
								<th scope="col"><p>예약번호</p></th>
								<th scope="col"><p>예약도서</p></th>
								<th scope="col"><p>대여가능일자</p></th>
								<th scope="col"><p>비고(순번)</p></th>
							</tr>
						</thead>
						<tbody>
							<%
							for (int i = 0; i < 5; i++) {
							%>
							<tr>
								<td><p>YYYY-MM-DD</p></td>
								<td><p>
										<a href="#">YYMMDDRS000<%=i + 1%></a>
									</p></td>
								<td><p>
										도서명<%=i + 1%></p></td>
								<td><p>YYYY-MM-DD</p></td>
								<td><p><%=i + 1%></p></td>
							</tr>
							<%
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