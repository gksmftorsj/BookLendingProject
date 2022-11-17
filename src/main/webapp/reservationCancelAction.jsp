<%@page import="bookReservation.BookReservationDAO"%>
<%@page import="userManagement.UserManagementDAO"%>
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
		int brdResult = 0;
		int bmuResult = 0;
		int umuResult = 0;
		out.print(userNo);
		out.print(bookNo);
// 		brdResult = bookReservationDAO.deleteReservation(userNo, bookNo);
// 		if(brdResult > 0){
// 			bmuResult = bookManagementDAO.updateReservationAvailabilityTrue(bookNo);
// 			umuResult = userManagementDAO.updateCurrentReservationCntMinus(userNo);
// 			if(bmuResult > 0 && umuResult >0){
// 				script.println("<script>");
// 				script.println("alert('예약이 취소되었습니다.');");
// 				script.println("location.href = './index.jsp';");
// 				script.println("</script>");
// 				script.close();
// 			} else if(bmuResult == 0 || umuResult == 0){
// 				script.println("<script>");
// 				script.println("alert('예약 취소에 실패하였습니다 다시 시도해주세요.');");
// 				script.println("location.href = 'history.back()';");
// 				script.println("</script>");
// 				script.close();
// 			}
// 		} else {
// 			script.println("<script>");
// 			script.println("alert('현재 예약중이지 않습니다.');");
// 			script.println("history.back();");
// 			script.println("</script>");
// 			script.close();
// 		}
%>
