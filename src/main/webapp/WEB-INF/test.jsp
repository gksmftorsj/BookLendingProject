<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bookInfo.BookInfoDAO" %>
<%@ page import="bookInfo.BookInfoDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		BookInfoDAO bookInfoDAO = new BookInfoDAO();
		BookInfoDTO bookInfoDTO = new BookInfoDTO();
		bookInfoDAO.insertBookInfo(bookInfoDTO);
	
	
	%>
</body>
</html>