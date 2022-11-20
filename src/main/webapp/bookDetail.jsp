<%@page import="bookReview.*"%>

<%@page import="bookInfo.BookInfoDTO"%>
<%@page import="bookInfo.BookInfoDAO"%>
<%@page import="userInfo.UserInfoDAO"%>
<%@page import="userInfo.UserInfoDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bookDetail, review</title>

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

   String userID = null;
   if (session.getAttribute("userID") != null) {
      userID = (String) session.getAttribute("userID");
   }

   UserInfoDAO userInfoDAO = new UserInfoDAO();
   UserInfoDTO userInfoDTO = new UserInfoDTO();
   
   String userNo = null;
   userNo = userInfoDAO.selectUserNo(userID);

   String userName = null;
   userName = userInfoDAO.selectUserName(userID);

   String bookIsbn = bookInfoDTO.getIsbn();
   
   BookReviewDAO bookReviewDAO = new BookReviewDAO();
   List<BookReviewDTO> selectReviewList = bookReviewDAO.selectReviewList(bookIsbn);
   int reviewCnt = bookReviewDAO.selectReviewCnt(bookIsbn);
   int reviewStarAVG = bookReviewDAO.selectReviewStarAVG(bookIsbn);
//    String reviewDateMH = bookInfoDAO.selectReviewDate(BookReviewDTO brd);
   
   //String reviewDate = bookReviewDAO.reviewGetDate();
   %>

   <%@ include file="userNavbar.jsp"%>

<!-- 책설명 Section -->
   <div style="margin: 50px;">
      <div class="bookTitle text-center">
         <h2> " <%=bookInfoDTO.getTitle()%> "
         </h2>
      </div>
      <div class="container text-center">
         <h5 class="writerAndPubilsher"> <%=bookInfoDTO.getAuthor()%> / <%=bookInfoDTO.getPublisher()%></h5>
      </div>
      <div class="bar mt-5" style="border-bottom: 1px solid gray; width: 100%;"></div>
         <div class="imgArray row" style="margin: 80px;">
            <div class="col">
               <img src=<%=bookInfoDTO.getCover()%> style="height: 400px; width: 300px; margin-left: 30px; box-shadow: 0 5px 18px -7px rgba(0, 0, 0, 1);"></img>
            </div>
            <div class="col">
               <p>분야 > <%=bookInfoDTO.getCategoryName()%></p>
               <p>출간일 <%=bookInfoDTO.getPubDate()%></p>
               <p style="font-weight: bold;">책소개</p>
               <p><%=bookInfoDTO.getDescription()%></p>
               <p>별점  
                  <%
                     for (int i = 0; i < reviewStarAVG; i++) {
                  %>
                  <span class="checkedStar">★</span>
                  <%
                  }
                  %>
                  <%
                     for(int i = 0; i< 5- reviewStarAVG; i++){
                  %>
                  <span class="uncheckedStar">★</span>
                  <%
                     }
                  %>
                  ( <%=reviewStarAVG%> )
               </p>
               <p>리뷰 ( <%=reviewCnt%> )</p>
               <p>대여 가능한 권 수 ( <%=bookInfoDTO.getBookCnt()%> )
                  <button type="button" class="btn btn-outline-secondary">대여하기</button>
                  <button type="button" class="btn btn-outline-secondary">예약하기</button>
               </p>
<!--                <p>찜 갯수 -->
<!--                   <button type="button" class="btn btn-outline-secondary" style>찜하기  -->
<!--                      <img src="https://cdn-icons-png.flaticon.com/512/138/138533.png" style="width: 10px; height: 10px;"></img></button> -->
<!--                </p> -->
            </div>
         </div>
<!-- 리뷰 Section -->
      <div class="bar mt-5" style="border-bottom: 1px solid gray; width: 100%;"></div>
      <div class="reviewSection form-floating" style="width: 100%; height: 100%; ">
         <h5 style="margin: 50px; font-weight: bold;">리뷰 작성</h5>
         <form id="reviewForm" name="reviewForm" class="row g-3" method="post" action="reviewAction.jsp">
            <div class="container" style="border: 1px solid gray; height:250px; border-radius: 10px; ">
               <div class="row">
                     <div class="col-sm-10">
                        <input type="hidden" name="isbn" value="<%=bookIsbn%>">
                           <div style="margin: 20px;">
                              <img class="me-2" src="https://coresos-phinf.pstatic.net/a/33gied/8_0edUd018svc6o3jvhuxdzgd_adzttp.jpg?type=s276_gif" 
                              width="30px" height="30px" style="position: relative; border-radius:50%; vertical-align: top;"> 
                                 <span>
                                    <% 
                                    if (userName == null){
                                       out.println("로그인을 해주세요");
                                    } else {
                                    %>
                                    <%=userName%>
                                    <%   
                                    }
                                    %>
                                 </span>

                                       <div class="reviewStar">
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
                              </div>
                           <div style="margin-left:30px">
                              <textarea name="reviewContent" class="form-control" style="height: 100px; color: gray" placeholder="지니북스가 훈훈해지는 댓글 부탁드립니다."></textarea>
                           </div>
                     </div>
                  <div class="col-sm-2" style="margin:auto; margin-bottom:40px;">
                  <button type="submit" class="btn btn-outline-secondary row" value="등록" style="margin: auto; margin-left: 40px;">리뷰등록</button>
                  </div>
               </div>
            </div>
         </form>
      </div>
<!-- 한 줄 리뷰 작성-->
      <h5 style="margin: 50px; font-weight: bold;">한 줄 리뷰</h5>
         <table class="table" style="position: relative;">
            <thead>
               <tr>
                  <th scope="col" style="text-align: center;">별점</th>
                  <th scope="col" style="text-align: center;">리뷰</th>
                  <th scope="col" style="width:100px;"> </th> 
               </tr>
            </thead>
            <tbody>
               <%
                  if (selectReviewList != null && selectReviewList.size() > 0) {
                     for (BookReviewDTO srd : selectReviewList) {
               %>
               <tr>
                  <td scope="row" style="width:250px;">
                     <div class="savedReviewStar" style = "margin-top:20px; margin-left:20px">
<%--                      <%=srd.reviewStar %> --%>
                        <%
                           for(int i = 0; i< srd.reviewStar; i++){
                        %>
                           <span class="checkedStar">★</span>
                        <%
                           }
                        %>
                        <%
                           for(int i = 0; i< 5- srd.reviewStar; i++){
                        %>
                           <span class="uncheckedStar">★</span>
                        <%
                           }
                        %>
                     </div>
                  </td>
                  <td scope="row">
                     <div style="height:80px;"><%=srd.getReviewContent()%></div>
                     <span style="color:gray; width: 200px;"> <%=srd.getUserName() %></span>                  
                     <span style="color:gray;"> 
                        <%=srd.getReviewDate()%> </span>
                  </td>
                  <td scope="row" style="text-align: center;">
<!-- 나의 아이디라면, 한 줄 리뷰 수정, 삭제 할 수 있도록 -->
               <%
                  if(userID != null ){

                  if(userNo.equals(srd.getUserNo())){                     
               %>
                  <div style="margin-top:15px">
                     <form class="row" action="./reviewUpdate.jsp?reviewNo=<%=srd.getReviewNo()%>&title=<%=bookInfoDTO.getTitle()%>" method="post">
                        <button id= "updateBtn" class="btn btn-outline-secondary">수정</button>
                     </form>
                     <form class="row" action="./reviewDeleteAction.jsp?reviewNo=<%=srd.getReviewNo()%>" method="post">
                        <button id= "deleteBtn" class="btn btn-outline-secondary">삭제</button>
                     </form>
                  </div>
                  </td> 
               </tr>
               <%
                  }
               }
            }
         }
               %>
            </tbody>
         </table>
      </div>
   <footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2022 지니북스</p>
   </footer>
</body>
</html>