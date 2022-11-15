<%@page import="userManagement.UserManagementDAO"%>
<%@page import="bookLend.BookLendDTO"%>
<%@page import="bookLend.BookLendDAO"%>
<%@page import="userInfo.UserInfoDAO"%>
<%@page import="bookManagement.BookManagementDAO"%>
<%@page import="bookManagement.BookManagementDTO"%>
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
		
		String bookIsbn = null;
		if (request.getParameter("isbn") != null) {
			bookIsbn = request.getParameter("isbn");
		}
		
		BookManagementDAO bookManagementDAO = new BookManagementDAO();
		BookManagementDTO bookManagementDTO = bookManagementDAO.selectBookManagementDetail(bookIsbn);
		script.println("<script>");
		script.println("alert('bookIsbn: " + bookIsbn + "')");
		script.println("alert('bookNo: " + bookManagementDTO.getBookNo() + "')");
		script.println("alert('bookLendingAvailability: " + bookManagementDTO.getBookLendingAvailability() + "')");
		script.println("alert('bookReservationAvailability: " + bookManagementDTO.getBookReservationAvailability() + "')");
		script.println("alert('bookIsbn: " + bookManagementDTO.getBookIsbn() + "')");
		script.println("</script>");
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		BookLendDAO bookLendDAO = new BookLendDAO();
		UserManagementDAO userManagementDAO = new UserManagementDAO();
		
		String userNo = userInfoDAO.selectUserNo(userID);
		String bookNo = bookManagementDTO.getBookNo();
		script.println("<script>");
		script.println("alert('userNo: " + userNo + "')");
		script.println("alert('bookNo: " + bookNo + "')");
		script.println("</script>");
		String extensionStatus = "true";
		int extensionAvailabilityCnt = 1;
		BookLendDTO bookLendDTO = new BookLendDTO();
		bookLendDTO.setUserNo(userNo);
		bookLendDTO.setBookNo(bookNo);
		bookLendDTO.setExtensionStatus(extensionStatus);
		bookLendDTO.setExtensionAvailabilityCnt(extensionAvailabilityCnt);
		int blResult = 0;
		int bmResult = 0;
		int umResult = 0;
		script.println("<script>");
		script.println("alert('여기 들어옴." + bookIsbn + "')");
		script.println("</script>");
		
		blResult = bookLendDAO.insertBookLend(bookLendDTO);
		bmResult = bookManagementDAO.updateBookManagementDetail(bookIsbn);
		umResult = userManagementDAO.updateUserManagement(userNo);
		
		script.println("<script>");
		script.println("alert('blResult: " + blResult + "')");
		script.println("alert('bmResult: " + bmResult + "')");
		script.println("alert('umResult: " + umResult + "')");
		script.println("</script>");
		if (blResult > 0 && bmResult > 0 && umResult > 0) {
			script.println("<script>");
			script.println("alert('대여 완료되었습니다.');");
			script.println("location.href = './index.jsp';");
			script.println("</script>");
			script.close();
			return;
		} else if (blResult == 0 || bmResult == 0 || umResult == 0) {
			script.println("<script>");
			script.println("alert('대여에 실패하였습니다. 다시 시도해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
%>

