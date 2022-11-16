<%@page import="userInfo.UserInfoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="bookReview.*"%>
<%@ page import="bookInfo.*"%>
<%@ page import="java.util.*"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 JSP action 페이지</title>
</head>
<body>
   <%
      // servelet으로 요청 응답 스트림에 텍스트 기록. 호출
      PrintWriter script = response.getWriter();
      // 요청 파라미터 bookInfo에 들어있는 String값
      String userID = null;
      if (session.getAttribute("userID") != null) {
         userID = (String) session.getAttribute("userID");
      }
   
      // 요청 파라미터 bookReview에 들어있는 String값
      String reviewContent = (String) request.getParameter("reviewContent");
      String isbn = (String) request.getParameter("isbn");
   
      // 로그인 파라미터 값 받기
      if (userID == null) {
         script.println("<script>");
         script.println("alert('로그인을 하세요.');");
         script.println("location.href = './userLogin.jsp';");
         script.println("</script>");
      } else {
         // 로그인이 되었을 시
         if (reviewContent == null) {
            script.println("<script>");
            script.println("alert('리뷰를 입력해주세요.')");
            script.println("</script>");
         } else {
            // bookReviewDao와 , reviewDTO를 불러서 값을 넣어줌.
            UserInfoDAO userInfoDAO = new UserInfoDAO();
            String userName = userInfoDAO.selectUserName(userID);
            String userNo = userInfoDAO.selectUserNo(userID);
            BookReviewDAO bookReviewDAO = new BookReviewDAO();
            BookReviewDTO bookReviewDTO = new BookReviewDTO(isbn, reviewContent);
            int result = bookReviewDAO.insertReview(userName, userNo, bookReviewDTO);
            if (result > 0) {
         script.println("<script>");
         script.println("location.href = document.referrer");
         script.println("</script>");
         script.close();
            } else {
         script.println("<script>");
         script.println("alert('리뷰등록에 실패했습니다.')");
         script.println("</script>");
         script.close();
            }
         }
      }
   %>
</body>
</html>