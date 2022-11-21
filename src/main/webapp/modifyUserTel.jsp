<%@page import="userInfo.UserInfoDTO" %>
	<%@page import="userInfo.UserInfoDAO" %>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
			<!DOCTYPE html>
			<html>

				<head>
					<meta charset="UTF-8">
					<title>Insert title here</title>
					<style>
						.myacc_th {
							background: #f5f5f5;
							padding: 7px 5px 7px 10px;
							text-align: center;
							font-weight: bold;
							font-size: 15px;
							color: #606060;
						}

						.myacc_td {
							background: #FFFFFF;
							padding: 7px 5px;
							font-weight: normal;
							color: #333333;
						}
					</style>
					<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
						integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
				</head>

				<body>
					<% String userID=null; if(session.getAttribute("userID") !=null){ userID=(String)
						session.getAttribute("userID"); } UserInfoDAO userInfoDAO=new UserInfoDAO(); UserInfoDTO
						userInfoDTO=userInfoDAO.selectUserInfoByUserID(userID); %>
						<form method="post" action="./modifyUserInfoAction.jsp">
							<table class="container mt-4	" width="100%" cellpadding="0" cellspacing="1" bgcolor="#d1d1d1" style="border-collapse: separate;
	    text-indent: initial;
	    border-spacing: 2px; padding: 0px; height: 200px;">
								<colgroup>
									<col width="30%">
									<col width="70%">
								</colgroup>
								<tbody>
									<tr>
										<th scope="row" class="myacc_th">기존 휴대전화번호</th>
										<td class="myacc_td">
											<%=userInfoDTO.getUserTel()%>
										</td>
									</tr>
									<tr>
										<th scope="row" class="myacc_th" style="width:40px">변경할 주소</th>
										<td class="myacc_td"><input type="tel" name="userTel"><button type="submit"
												class="btn btn-primary ms-3">변경하기</button></td>
									</tr>
								</tbody>
							</table>
						</form>
						<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
							integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
							crossorigin="anonymous"></script>
				</body>

			</html>