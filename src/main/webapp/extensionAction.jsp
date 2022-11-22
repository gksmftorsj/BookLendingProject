<%@page import="bookLend.BookLendDTO"%>
<%@page import="bookLend.BookLendDAO"%>
<%@page import="userInfo.UserInfoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.SHA256"%>
<%@ page import="java.io.PrintWriter"%>
<%
		request.setCharacterEncoding("UTF-8");
		PrintWriter script = response.getWriter();
		
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		String adminExtensionUserID = null;
		if (request.getParameter("userID") != null) {
			adminExtensionUserID = request.getParameter("userID");
		}
		
		String bookIsbn = null;
		if (request.getParameter("isbn") != null) {
			bookIsbn = request.getParameter("isbn");
		}
		
		BookLendDAO bookLendDAO = new BookLendDAO();
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		String userNo = null;
		
		if(adminExtensionUserID == null){
			userNo = userInfoDAO.selectUserNo(userID);
		}else{
			userNo = userInfoDAO.selectUserNo(adminExtensionUserID);
		}
		
		BookLendDTO bookLendDTO = bookLendDAO.selectUserLendingData(userNo, bookIsbn);
		int result = 0;
		result = bookLendDAO.updateExtension(bookLendDTO.getBookNo());
		out.print(result);
		out.print(userID);
		out.print(bookIsbn);
		if(result > 0){
			script.println("<script>");
			script.println("alert('연장 완료되었습니다.');");
			script.println("location.href = document.referrer;");
			script.println("</script>");
			script.close();
			return;
		} else{
			script.println("<script>");
			script.println("alert('연장에 실패하였습니다. 다시 시도해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
%>
