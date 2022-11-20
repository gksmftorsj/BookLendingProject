<%@page import="userInfo.UserInfoDAO"%>
<%@page import="userInfo.UserInfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<style>
	tbody {
      display: table-row-group;
      vertical-align: middle;
      border-color: inherit;
    }
    
    table,
    tr,
    td{
      color: #3E3E3E;
      font-family: Malgun Gothic, '돋움', '굴림', Gulim, dotum, 'Segoe WPC', 'Segoe UI', 'Apple SD Gothic Neo', Helvetica, AppleGothic, Sans-serif !important;
      font-size: 20px;
      line-height: 18px;
    }

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
</head>
<body>
	<%
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	UserInfoDTO userInfoDTO = userInfoDAO.selectUserInfoByUserID(userID);
	%>
	<%@ include file="userNavbar.jsp"%>
	<table class="container mt-5" width="100%" cellpadding="0" cellspacing="1" bgcolor="#d1d1d1" style="border-collapse: separate;
    text-indent: initial;
    border-spacing: 2px; padding: 0px; height: 500px;">
    <colgroup>
      <col width="10%">
      <col width="90%">
    </colgroup>
    <tbody>
      <tr>
        <th scope="row" class="myacc_th">아이디</th>
        <td class="myacc_td"><%=userID%></td>
      </tr>
      <tr>
        <th scope="row" class="myacc_th">이름</th>
        <td class="myacc_td"><%=userInfoDTO.getUserName()%>&nbsp;<button class="btn btn-primary" id="modifyNameBtn">변경하기</button></td>
      </tr>
      <tr>
        <th scope="row" class="myacc_th">이메일</th>
        <td class="myacc_td"><%=userInfoDTO.getUserEmail()%>&nbsp;<button class="btn btn-primary" id="modifyEmailBtn">변경하기</button></td>
      </tr>
      <tr>
        <th scope="row" class="myacc_th">주소</th>
        <td class="myacc_td"><%=userInfoDTO.getUserAddress()%>&nbsp;<button class="btn btn-primary" id="modifyAddressBtn">변경하기</button></td>
      </tr>
      <tr>
        <th scope="row" class="myacc_th">휴대전화</th>
        <td class="myacc_td"><%=userInfoDTO.getUserTel()%>&nbsp;<button class="btn btn-primary" id="modifyTelBtn">변경하기</button></td>
      </tr>
      <tr>
        <th scope="row" class="myacc_th">비밀번호</th>
        <td class="myacc_td"><button class="btn btn-primary" id="modifyPasswordBtn">비밀번호 변경하기</button></td>
      </tr>
    </tbody>
  </table>
  <footer class="my-3 text-center text-small">
		<p class="mb-1">&copy; 2022 지니북스</p>
  </footer>
  
  <script>
    function modifyNamePopup() {
      const width = 600;
      const height = 300;

      let left = (document.body.offsetWidth / 2) - (width / 2);
      left += window.screenLeft;

      window.open(
    	"./modifyUserName.jsp", "이름변경 팝업", "width=" + width + ", height=" + height + ", left=" + left + ", top=100"
    	);
    }
    function modifyEmailPopup() {
    	const width = 600;
        const height = 300;

        let left = (document.body.offsetWidth / 2) - (width / 2);
        left += window.screenLeft;

        window.open(
      	"./modifyUserEmail.jsp", "이메일변경 팝업", "width=" + width + ", height=" + height + ", left=" + left + ", top=100"
      	);
    }
    function modifyAddressPopup() {
    	const width = 600;
        const height = 300;

        let left = (document.body.offsetWidth / 2) - (width / 2);
        left += window.screenLeft;

        window.open(
      	"./modifyUserAddress.jsp", "주소변경 팝업", "width=" + width + ", height=" + height + ", left=" + left + ", top=100"
      	);
    }
    function modifyTelPopup() {
    	const width = 600;
        const height = 300;

        let left = (document.body.offsetWidth / 2) - (width / 2);
        left += window.screenLeft;

        window.open(
      	"./modifyUserTel.jsp", "휴대전화번호변경 팝업", "width=" + width + ", height=" + height + ", left=" + left + ", top=100"
      	);
    }
    function modifyPasswordPopup() {
    	const width = 600;
        const height = 300;

        let left = (document.body.offsetWidth / 2) - (width / 2);
        left += window.screenLeft;

        window.open(
      	"./modifyUserPassword.jsp", "비밀번호변경 팝업", "width=" + width + ", height=" + height + ", left=" + left + ", top=100"
      	);
    }
    const modifyNameBtn = document.getElementById("modifyNameBtn");
    const modifyEmailBtn = document.getElementById("modifyEmailBtn");
    const modifyAddressBtn = document.getElementById("modifyAddressBtn");
    const modifyTelBtn = document.getElementById("modifyTelBtn");
    const modifyPasswordBtn = document.getElementById("modifyPasswordBtn");
    modifyNameBtn.addEventListener("click", () => { modifyNamePopup(); })
    modifyEmailBtn.addEventListener("click", () => { modifyEmailPopup(); })
    modifyAddressBtn.addEventListener("click", () => { modifyAddressPopup(); })
    modifyTelBtn.addEventListener("click", () => { modifyTelPopup(); })
    modifyPasswordBtn.addEventListener("click", () => { modifyPasswordPopup(); })
    
  </script>
  
</body>
</html>