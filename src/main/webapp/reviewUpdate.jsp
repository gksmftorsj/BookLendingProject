<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@page import="bookReview.*"%>
<%@page import="bookInfo.BookInfoDTO"%>
<%@page import="bookInfo.BookInfoDAO"%>
<%@page import="userInfo.UserInfoDAO"%>
<%@page import="userInfo.UserInfoDTO"%>
<%-- <%@page language="java" contentType="text/html; charset=UTF-8" --%>
<%--    pageEncoding="UTF-8"%> --%>
<%@page import="java.util.*"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정 페이지</title>

<style type="text/css">
.reviewTable {
   position: relative;
   top: 50px;
}
.reviewStar fieldset{
    display: inline-block;
    direction: rtl;
    border:0;
}
.reviewStar fieldset legend{
    text-align: right;
}
.reviewStar input[type=radio]{
    display: none;
}
.reviewStar label{
    font-size: 2em;
    color: transparent;
    text-shadow: 0 0 0 #f0f0f0;
}
.reviewStar label:hover{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
.reviewStar label:hover ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
.reviewStar input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}

.uncheckedStar{
    font-size: 2em;
    color: transparent;
    text-shadow: 0 0 0 #f0f0f0;
}

.checkedStar {
    font-size: 2em;
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
    color: #FAD103;
}

</style>
</head>
<body>
   <%
   String title = (String) request.getParameter("title");
   
   BookInfoDAO bookInfoDAO = new BookInfoDAO();
   BookInfoDTO bookInfoDTO = bookInfoDAO.selectBookDetail(title);
   
   BookReviewDAO bookReviewDAO = new BookReviewDAO();
   
   String reviewNo = (String) request.getParameter("reviewNo");
   
   BookReviewDTO bookReviewDTO = bookReviewDAO.selectReviewUpdate(reviewNo);
//    out.print(bookReviewDTO.getReviewContent());
   %>

   <%@ include file="userNavbar.jsp"%>
   
      <div style="margin:50px">
         <form name="reviewUpdateForm" class="row g-3" method="post" action="./reviewAction.jsp?reviewNo=<%=reviewNo%>&title=<%=title%>">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col" style="text-align: center; font-weight: bold;">리뷰 수정</th>
                     <th scope="col">
                        <%=title%> / <%=bookInfoDTO.getAuthor()%> - <%=bookInfoDTO.getPublisher()%>
                        
                     </th>
                  <th scope="col" style="text-align: center;" style="width: 300px;"><%=bookReviewDTO.getReviewDate() %></th>
                  <th scope="col" style="text-align: center;" style="width: 300px;"><%= bookReviewDTO.getUserName()%></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th rowspan="2" scope="row" style="width: 300px;">
                     <img src=<%=bookInfoDTO.getCover()%> style="height: 250px; width: 200px; margin-left: 50px; box-shadow: 0 5px 18px -7px rgba(0, 0, 0, 1);"></img>
                  </th>
                  <td colspan="3" style="height:50px">
                     평점 : 
                        <%
                           for(int i = 0; i< bookReviewDTO.reviewStar; i++){
                        %>
                           <span class="checkedStar">★</span>
                        <%
                           }
                        %>
                        <%
                           for(int i = 0; i< 5- bookReviewDTO.reviewStar; i++){
                        %>
                           <span class="uncheckedStar">★</span>
                        <%
                           }
                        %>
                  </td>
                </tr>
                <tr>
                  <td colspan="3" >
                        <div class="reviewStar" style="width: 300px;">
                           <fieldset>
                              <input type="radio" name="reviewStar" value="5" id="rate1"><label
                                 for="rate1">★</label>
                              <input type="radio" name="reviewStar" value="4" id="rate2"><label
                                 for="rate2">★</label>
                              <input type="radio" name="reviewStar" value="3" id="rate3"><label
                                 for="rate3">★</label>
                              <input type="radio" name="reviewStar" value="2" id="rate4"><label
                                 for="rate4">★</label>
                              <input type="radio" name="reviewStar" value="1" id="rate5"><label
                                 for="rate5">★</label>
                           </fieldset>
                     </div>
                     <textarea name="reviewContent" style="width:100%; height: 100px; margin-top:20px;"><%=bookReviewDTO.getReviewContent()%></textarea>
                  </td>
                </tr>
              </tbody>
            </table>
            <div class="col-auto" style="margin:auto;">
               <button id="updateBtn" type="submit" class="btn btn-outline-secondary">수정하기</button>
            </div>
         </form>
         </div>
</body>
</html>