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
<title>리뷰 등록 action 페이지</title>
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
   String isbn = (String) request.getParameter("isbn");
   String reviewContent = (String) request.getParameter("reviewContent");
   String reviewStar = (String) request.getParameter("reviewStar");

   String reviewNo = null;
   if (request.getParameter("reviewNo") != null) {
      reviewNo = request.getParameter("reviewNo");
   }

   String title = null;
   if (request.getParameter("title") != null) {
      title = request.getParameter("title");
   }

   // 로그인 파라미터 값 받기
   // 로그인이 되지 않았을 시
   if (userID == null) {
      script.println("<script>");
      script.println("alert('로그인을 하세요.');");
      script.println("location.href = './userLogin.jsp';");
      script.println("</script>");
   } else if (reviewStar == null) {
      script.println("<script>");
      script.println("alert('별점을 등록해주세요.');");
      script.println("history.back()");
      script.println("</script>");
   } else {
      // 로그인이 되었을 시
      // 리뷰 컨텐츠 값이 null
      //                if (reviewContent == null) {
      //                   script.println("<script>");
      //                   script.println("alert('리뷰를 입력해주세요.')");
      //                   script.println("</script>");
      //                } else {
      // bookReviewDao와 , reviewDTO를 불러서 값을 넣어줌.
      UserInfoDAO userInfoDAO = new UserInfoDAO();
      String userName = userInfoDAO.selectUserName(userID);
      String userNo = userInfoDAO.selectUserNo(userID);

      BookReviewDAO bookReviewDAO = new BookReviewDAO();
      BookReviewDTO bookReviewDTO = new BookReviewDTO();
      bookReviewDTO.setBookIsbn(isbn);
      bookReviewDTO.setReviewContent(reviewContent);
      bookReviewDTO.setReviewStar(Integer.parseInt(reviewStar));
      bookReviewDTO.setReviewNo(reviewNo);

      out.print(reviewStar);
      out.print(reviewContent);
      String url = "./bookDetail.jsp?title=" + title;
      if (reviewNo == null) {
         if (reviewContent == "") {
      script.println("<script>");
      script.println("alert('리뷰를 작성해주세요.')");
      script.println("history.back()");
      script.println("</script>");
      script.close();
         } else {

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

      } else {
         if (reviewContent == "") {
      script.println("<script>");
      script.println("alert('리뷰를 작성해주세요.')");
      script.println("history.back()");
      script.println("</script>");
      script.close();
         } else {
      int result = bookReviewDAO.updateReview(bookReviewDTO);

      if (result > 0) {
         script.println("<script>");
         script.println("location.href = " + "'" + url + "'");
         script.println("</script>");
         script.close();
      } else {
         script.println("<script>");
         script.println("alert('리뷰수정에 실패했습니다.')");
         script.println("</script>");
         script.close();
      }
         }
      }
   }
   %>

</body>
</html>