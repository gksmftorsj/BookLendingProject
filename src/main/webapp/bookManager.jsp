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
    font-family: Malgun Gothic,돋움,Dotum,AppleGothic,sans-serif;
    font-size: 12px;
    color: #666;
    line-height: 18px;
}
div {
    display: inline-block;
}

.order_box03 {
    background: #f5f5f5;
    width: 754px;
    padding: 15px 0px;
    margin-bottom: 15px;
    border: 1px solid #d1d1d1;
    overflow: hidden;
}
</style>
</head>
<body>
	<%@ include file="adminNavbar.jsp"%>


	<div>
		<h2>관리자 페이지</h2>
		<form name="lendingInfoForm" class="row g-3" method="post">
			<div class="col-auto">
				<label for="name">전체보유도서</label>
				<fieldset>
					<div class="order_box03">
						<div class="floatL">
							<div class="order_box04">
								<div class="account_select01">
									<select id="select_searchYearSel" class="Searchselect_01"
										title="연도 선택">
										<option value="0" selected="">전체보기</option>
										<option value="2022">2022</option>
										<option value="2021">2021</option>
										<option value="2020">2020</option>
										<option value="2019">2019</option>
										<option value="2018">2018</option>
										<option value="2017">2017</option>
										<option value="2016">2016</option>
										<option value="2015">2015</option>
										<option value="2014">2014</option>
										<option value="2013">2013</option>
										<option value="2012">2012</option>
										<option value="2011">2011</option>
										<option value="2010">2010</option>
										<option value="2009">2009</option>
										<option value="2008">2008</option>
										<option value="2007">2007</option>
										<option value="2006">2006</option>
										<option value="2005">2005</option>
										<option value="2004">2004</option>
										<option value="2003">2003</option>
										<option value="2002">2002</option>
										<option value="2001">2001</option>
										<option value="2000">2000</option>
										<option value="1999">1999</option>
									</select><span style="color: #636363; font-weight: bold;"> 년</span> <select
										id="select_searchMonthSel" class="Searchselect_01"
										title="월 선택">
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

								<div class="account_select02" style="width: 370px">
									<div class="order_search">
										<label for="search02"
											style="color: #636363; font-weight: bold;">대여내역조회</label> <select
											id="select_searchTypeSel" class="Searchselect_01">
											<option value="1">대여도서</option>
											<option value="2">대여번호</option>
											<option value="3">회원명</option>
										</select>
									
									<input class="keyword_search" type="text" title="검색어"
										onkeydown="javascript:if(event.keyCode==13){submitOrdersearch();}"
										name="searchOrderWord" id="searchOrderWord" value=""></div>
								</div>
							</div>
							<div class="order_box_line"></div>

							<div class="order_box05">
								<div class="account_select03">
									<label for="select03"
										style="color: #636363; font-weight: bold;">상품별 조회</label> <select
										id="select_searchShopTypeSel" class="Searchselect_01">
										<option value="0" selected="">전체보기</option>
										<option value="1">베스트Top100</option>
										<option value="2">전자책</option>
										<option value="3">캐시충전</option>
									</select>
								</div>
								<div class="account_select04">
									<label for="select04"
										style="color: #636363; font-weight: bold;">상태별 조회</label> <select
										id="select_searchOrderStatusSel" class="Searchselect_01">
										<option value="0" selected="">전체보기</option>
										<option value="1">예약대기</option>
										<option value="2">대여중</option>
										<option value="4">연체중</option>
										<option value="3">반납완료</option>
									</select>
								</div>
							</div>

							<!--
                <div class="order_box_line"></div>
                    <div class="order_box05">
                        <div class="account_select05"> <input type="checkbox" name="searchOrderDeduct" id="searchOrderDeduct"  value="2" > <label for="searchOrderDeduct"><span class="s_tit">소득공제 미반영(별도제출 필요) 주문만 보기</span></label>
                    </div>
                </div>
                -->
						</div>

						<!-- 버튼 영역 -->
						<div class="floatL pt7">
							<div class="keyword_search_btn">
								<div class="button_blue keyword_leftRound">
									<a href="javascript:submitOrdersearch();"
										class="button_middle_white"
										style="padding: 11px 20px 11px 20px;">찾기</a>
								</div>
							</div>
						</div>

					</div>
			</div>
			</fieldset>
		</form>
	</div>

	<table class="table">
		<thead>
			<tr>
				<th scope="col"><p>랭킹</p></th>
				<th scope="col"><p>도서명</p></th>
				<th scope="col"><p>저자</p></th>
				<th scope="col"><p>출판사</p></th>
				<th scope="col"><p>ISBN</p></th>
				<th scope="col"><p>등록일자</p></th>
				<th scope="col"><p><a>대기/보유권수</a></p></th>
				
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < 5; i++) {
			%>
			<tr>
				<td><p><%=i+1%></p></td>
				<td><p>책이름<%=i+1%></p></td>
				<td><p>저자<%=i+1%></p></td>
				<td><p>출판사<%=i+1%></p></td>
				<td><p>isbn<%=i+1%></p></td>
				<td><p>22-11-11</p></td>
				<td><p><a href="#"><%=5-(i+1)%>/5</a></p></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>



</body>
</html>