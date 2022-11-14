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


	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	
	String bookIsbn = null;
	if(request.getParameter("isbn") != null){
		bookIsbn = request.getParameter("isbn");
	}
	
	BookManagementDAO bookManagementDAO = new BookManagementDAO();
	BookManagementDTO bookManagementDTO = bookManagementDAO.selectBookManagementDetail(bookIsbn);
	
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	BookLendDAO bookLendDAO = new BookLendDAO();
	
	String userNo = userInfoDAO.selectUserNo(userID);
	String bookNo = bookManagementDTO.getBookNo();
	String extensionStatus = "true";
	int extensionAvailabilityCnt = 1;
	BookLendDTO bookLendDTO = new BookLendDTO();
	bookLendDTO.setUserNo(userNo);
	bookLendDTO.setBookNo(bookNo);
	bookLendDTO.setExtensionStatus(extensionStatus);
	bookLendDTO.setExtensionAvailabilityCnt(extensionAvailabilityCnt);
	int result = bookLendDAO.insertBookLend(bookLendDTO);
	if(result > 0){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('대여 완료되었습니다.');");
		script.println("location.href = './index.jsp';");
		script.println("</script>");
		script.close();
		return;
	} else if(result == 0){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('대여에 실패하였습니다. 다시 시도해주세요.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	} 
%>

