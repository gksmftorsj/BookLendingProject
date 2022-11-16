<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="util.DatabaseUtil"%>
<%@ page import="userInfo.UserInfoDTO" %>
<%@ page import="userInfo.UserInfoDAO" %>
<%@ page import="userManagement.UserManagementDTO" %>
<%@ page import="userManagement.UserManagementDAO" %>
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
				<label for="name">회원정보조회</label>
				<fieldset>
					<div class="order_box01">

						<div class="order_box02">

							<div class="account_select03" style="width: 100%">
								<form action="userManager.jsp" method="post">
									<div class="input-group">
										<select name="userOption" required
											class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
											id="inputGroupSelect01">
											<ul class="dropdown-menu">
												<li><option class="dropdown-item" selected>회원정보검색</option></li>
												<li><option class="dropdown-item" value="userName">회원명</option></li>
												<li><option class="dropdown-item" value="userId">아이디</option></li>
												<li><option class="dropdown-divider" disabled>-------</option></li>
												<li><option class="dropdown-item" value="userNo">회원번호</option></li>
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
					<label for="name">회원목록</label>
					<div class="myTable">
						<table class="userTable container">
							<thead>
								<tr>
									<th scope="col"><p>회원번호</p></th>
									<th scope="col"><p>회원명</p></th>
									<th scope="col"><p>ID</p></th>
									<th scope="col"><p>Email</p></th>
									<th scope="col"><p>연락처</p></th>
									<th scope="col"><p>주소</p></th>
									<th scope="col"><p>현재대여건수</p></th>
									<th scope="col"><p>현재예약건수</p></th>
									<th scope="col"><p>총대여건수</p></th>
									<th scope="col"><p>비고(연체정보)</p></th>
								</tr>

							</thead>

							<tbody>
	<%
	UserManagementDAO userManagementDao = new UserManagementDAO();
	List<UserManagementDTO> userAdminList = null;
							
	if( request.getParameter("searching") == null) {
		userAdminList = userManagementDao.selectAdminUserManagement();
		if(userAdminList != null && userAdminList.size()>0) {
				for(UserManagementDTO userInfo : userAdminList){
	%>
							<tr>
								<td><p><%=userInfo.getUserNo() %></p></td>
								<td><p><%=userInfo.getUserName() %></p></td>
								<td><p><%=userInfo.getUserID() %></p></td>
								<td><p><%=userInfo.getUserEmail() %></p></td>
								<td><p><%=userInfo.getUserTel() %>/5</p></td>
								<td><p><%=userInfo.getUserAddress() %></p></td>
								<td><p><%=userInfo.getCurrentLendingCnt() %></p></td>
								<td><p><%=userInfo.getCurrentReservationCnt() %></p></td>
								<td><p><%=userInfo.getTotalLendingCnt() %></p></td>
								<td><p><%=userInfo.getOverDueCnt() %></p></td>
							</tr>
	<%
				}
		}
	} else {
		String search = request.getParameter("searching");		
		String option = request.getParameter("userOption");
	
		if( option.equals("userName") ){
			userAdminList = userManagementDao.selectAdminUserManagementByUserName(search);
			if(userAdminList != null && userAdminList.size()>0) {
				for(UserManagementDTO userInfo : userAdminList){
	%>
					<tr>
						<td><p><%=userInfo.getUserNo() %></p></td>
						<td><p><%=userInfo.getUserName() %></p></td>
						<td><p><%=userInfo.getUserID() %></p></td>
						<td><p><%=userInfo.getUserEmail() %></p></td>
						<td><p><%=userInfo.getUserTel() %>/5</p></td>
						<td><p><%=userInfo.getUserAddress() %></p></td>
						<td><p><%=userInfo.getCurrentLendingCnt() %></p></td>
						<td><p><%=userInfo.getCurrentReservationCnt() %></p></td>
						<td><p><%=userInfo.getTotalLendingCnt() %></p></td>
						<td><p><%=userInfo.getOverDueCnt() %></p></td>
					</tr>
	<%
					}
  				}
		} else if( option.equals("userId") ) {
			userAdminList = userManagementDao.selectAdminUserManagementByUserId(search);
			if(userAdminList != null && userAdminList.size()>0) {
				for(UserManagementDTO userInfo : userAdminList){
	%>
					<tr>
						<td><p><%=userInfo.getUserNo() %></p></td>
						<td><p><%=userInfo.getUserName() %></p></td>
						<td><p><%=userInfo.getUserID() %></p></td>
						<td><p><%=userInfo.getUserEmail() %></p></td>
						<td><p><%=userInfo.getUserTel() %>/5</p></td>
						<td><p><%=userInfo.getUserAddress() %></p></td>
						<td><p><%=userInfo.getCurrentLendingCnt() %></p></td>
						<td><p><%=userInfo.getCurrentReservationCnt() %></p></td>
						<td><p><%=userInfo.getTotalLendingCnt() %></p></td>
						<td><p><%=userInfo.getOverDueCnt() %></p></td>
					</tr>
	<%
					}}
		} else if( option.equals("userNo") ) {
			userAdminList = userManagementDao.selectAdminUserManagementByUserNo(search);
			if(userAdminList != null && userAdminList.size()>0) {
				for(UserManagementDTO userInfo : userAdminList){
	%>
					<tr>
						<td><p><%=userInfo.getUserNo() %></p></td>
						<td><p><%=userInfo.getUserName() %></p></td>
						<td><p><%=userInfo.getUserID() %></p></td>
						<td><p><%=userInfo.getUserEmail() %></p></td>
						<td><p><%=userInfo.getUserTel() %>/5</p></td>
						<td><p><%=userInfo.getUserAddress() %></p></td>
						<td><p><%=userInfo.getCurrentLendingCnt() %></p></td>
						<td><p><%=userInfo.getCurrentReservationCnt() %></p></td>
						<td><p><%=userInfo.getTotalLendingCnt() %></p></td>
						<td><p><%=userInfo.getOverDueCnt() %></p></td>
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