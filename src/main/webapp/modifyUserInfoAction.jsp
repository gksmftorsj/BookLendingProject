<%@page import="userInfo.UserInfoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%
		request.setCharacterEncoding("UTF-8");
		PrintWriter script = response.getWriter();
		
		String userID = null;
		String userName = null;
		String userEmail = null;
		String userAddress = null;
		String userTel = null;
		String userCurrentPassword = null;
		String userModifyPassword = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		if(request.getParameter("userName") != null){
			userName = (String) request.getParameter("userName");
		}
		if(request.getParameter("userEmail") != null){
			userEmail = (String) request.getParameter("userEmail");
		}
		if(request.getParameter("userAddress") != null){
			userAddress = (String) request.getParameter("userAddress");
		}
		if(request.getParameter("userTel") != null){
			userTel = (String) request.getParameter("userTel");
		}
		if(request.getParameter("userCurrentPassword") != null){
			userCurrentPassword = (String) request.getParameter("userCurrentPassword");
		}
		if(request.getParameter("userModifyPassword") != null){
			userModifyPassword = (String) request.getParameter("userModifyPassword");
		}
		
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		
		if(userName != null){
			int updateNameResult = 0;
			updateNameResult = userInfoDAO.updateUserName(userName, userID);
			if(updateNameResult > 0){
				script.println("<script>");
				script.println("alert('이름이 변경되었습니다.');");
				script.println("location.href = './modifyUserInfo.jsp';");
				script.println("</script>");
				script.close();
				return;
			}else{
				script.println("<script>");
				script.println("alert('이름이 변경을 실패했습니다.');");
				script.println("location.href = './modifyUserInfo.jsp';");
				script.println("</script>");
				script.close();
				return;
			}
		}
		if(userEmail != null){
			int updateEmailResult = 0;
			updateEmailResult = userInfoDAO.updateUserEmail(userEmail, userID);
			if(updateEmailResult > 0){
				script.println("<script>");
				script.println("alert('이메일이 변경되었습니다.');");
				script.println("location.href = './modifyUserInfo.jsp';");
				script.println("</script>");
				script.close();
				return;
			}else{
				script.println("<script>");
				script.println("alert('이메일 변경을 실패했습니다.');");
				script.println("location.href = './modifyUserInfo.jsp';");
				script.println("</script>");
				script.close();
				return;
			}
		}
		if(userAddress != null){
			int updateAddressResult = 0;
			updateAddressResult = userInfoDAO.updateUserAddress(userAddress, userID);
			if(updateAddressResult > 0){
				script.println("<script>");
				script.println("alert('주소가 변경되었습니다.');");
				script.println("location.href = './modifyUserInfo.jsp';");
				script.println("</script>");
				script.close();
				return;
			}else{
				script.println("<script>");
				script.println("alert('주소 변경을 실패했습니다.');");
				script.println("location.href = './modifyUserInfo.jsp';");
				script.println("</script>");
				script.close();
				return;
			}
		}
		if(userTel != null){
			int updateTelResult = 0;
			updateTelResult = userInfoDAO.updateUserTel(userTel, userID);
			if(updateTelResult > 0){
				script.println("<script>");
				script.println("alert('휴대전화번호가 변경되었습니다.');");
				script.println("location.href = './modifyUserInfo.jsp';");
				script.println("</script>");
				script.close();
				return;
			}else{
				script.println("<script>");
				script.println("alert('휴대전화번호 변경을 실패했습니다.');");
				script.println("location.href = './modifyUserInfo.jsp';");
				script.println("</script>");
				script.close();
				return;
			}
		}
		if(userCurrentPassword != null && userModifyPassword != null){
			if(userCurrentPassword.equals(userModifyPassword)){
				script.println("<script>");
				script.println("alert('현재 비밀번호와 다른 비밀번호로 변경해주세요.');");
				script.println("history.back()");
				script.println("</script>");
				script.close();
				return;
			}
			int updatePasswordResult = 0;
			updatePasswordResult = userInfoDAO.updateUserPassword(userCurrentPassword, userModifyPassword, userID);
			if(updatePasswordResult > 0){
				script.println("<script>");
				script.println("alert('비밀번호가 변경되었습니다.');");
				script.println("location.href = './modifyUserInfo.jsp';");
				script.println("</script>");
				script.close();
				return;
			}else{
				script.println("<script>");
				script.println("alert('비밀번호가 일치하지 않습니다.');");
				script.println("history.back()");
				script.println("</script>");
				script.close();
				return;
			}
		}
%>
