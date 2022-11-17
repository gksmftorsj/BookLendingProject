<%@page import="bookReservation.BookReservationDAO"%>
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
		BookReservationDAO bookReservationDAO = new BookReservationDAO();
		UserManagementDAO userManagementDAO = new UserManagementDAO();
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		
		String userNo = userInfoDAO.selectUserNo(userID);
		String bookNo = bookManagementDAO.selectBookReservationTrue(bookIsbn);
		int briResult = 0;
		int bmuResult = 0;
		int umuResult = 0;
		int umsResult = 0;

		umsResult = userManagementDAO.selectCurrentReservationCnt(userNo);
		if (umsResult < 5) {
			briResult = bookReservationDAO.insertBookReservation(userNo, bookNo);
			
			bmuResult = bookManagementDAO.updateReservationAvailabilityFlase(bookNo);
			umuResult = userManagementDAO.updateCurrentReservationCnt(userNo);
			
			if (briResult > 0 && bmuResult > 0 && umuResult > 0) {
				script.println("<script>");
				script.println("alert('"+bmuResult+"');");
				script.println("alert('예약 완료되었습니다.');");
				script.println("location.href = './index.jsp';");
				script.println("</script>");
				script.close();
				return;
			} else if (briResult == 0 || bmuResult == 0 || umuResult == 0) {
				script.println("<script>");
				script.println("alert('예약에 실패하였습니다. 다시 시도해주세요.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
			}
		} else{
			script.println("<script>");
			script.println("alert('현재 총 예약권수가 5권입니다. 예약취소 후 이용하시길 바랍니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
%>
