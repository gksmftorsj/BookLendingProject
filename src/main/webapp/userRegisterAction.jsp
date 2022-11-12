<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="userInfo.UserInfoDTO"%>
<%@page import="userInfo.UserInfoDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="util.SHA256"%>
<%
	request.setCharacterEncoding("UTF-8");
	String userName = null;
	String userID = null;
	String userPassword = null;
	String userEmail = null;
	String userTel = null;
	String userAddress = null;
	if(request.getParameter("userName") != null){
		userName = (String) request.getParameter("userName");
	}
	if(request.getParameter("userID") != null){
		userID = (String) request.getParameter("userID");
	}
	if(request.getParameter("userPassword") != null){
		userPassword = (String) request.getParameter("userPassword");
	}
	if(request.getParameter("userEmail") != null){
		userEmail = (String) request.getParameter("userEmail");
	}
	if(request.getParameter("userTel") != null){
		userTel = (String) request.getParameter("userTel");
	}
	if(request.getParameter("userAddress") != null){
		if(request.getParameter("userAddressDetail") != null){
			userAddress = (String) request.getParameter("userAddress") + (String) request.getParameter("userAddressDetail");
		} else{
			userAddress = (String) request.getParameter("userAddress");
		}
	}
	
	if(userName == "" || userID == "" || userPassword == "" || userEmail == "" || userTel == "" || userAddress == ""){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	int result = userInfoDAO.register(new UserInfoDTO(userName, userID, userPassword, userEmail, SHA256.getSHA256(userEmail), "false", userAddress, userTel));
	if(result == -1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 존재하는 아이디입니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	} else {
		session.setAttribute("userID", userID);
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = 'emailSendAction.jsp'");
		script.println("</script>");
		script.close();
	}

%>