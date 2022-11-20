<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="userInfo.UserInfoDAO"%>
<%@ page import="bookReview.*"%>
<%
   PrintWriter script = response.getWriter();

   String userID = null;
   if (session.getAttribute("userID") != null) {
      userID = (String) session.getAttribute("userID");
   }

   String reviewNo = null;
   if(request.getParameter("reviewNo")!=null){
      reviewNo = request.getParameter("reviewNo");
   }
   
   UserInfoDAO userInfoDAO = new UserInfoDAO();
   String userNo = null;
   userNo = userInfoDAO.selectUserNo(userID);
   
   BookReviewDAO bookReviewDAO = new BookReviewDAO();
   int result = bookReviewDAO.deleteReview(userNo, reviewNo);

   if(result > 0 ){
      script.println("<script>");
      script.println("alert('삭제되었습니다.');");
      script.println("location.href = document.referrer;");
      script.println("</script>");
      script.close();
      return;
   } else{
      script.println("<script>");
      script.println("alert('삭제에 실패했습니다.');");
      script.println("location.href = document.referrer;");
      script.println("</script>");
      script.close();
   }
%>