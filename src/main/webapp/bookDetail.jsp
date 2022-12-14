<%@page import="bookReservation.BookReservationDAO"%>
<%@page import="bookReview.*"%>

<%@page import="bookInfo.BookInfoDTO"%>
<%@page import="bookInfo.BookInfoDAO"%>
<%@page import="userInfo.UserInfoDAO"%>
<%@page import="userInfo.UserInfoDTO"%>
<%@page import="bookLend.BookLendDTO"%>
<%@page import="bookLend.BookLendDAO"%>
<%@page import="bookManagement.BookManagementDAO"%>
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
   BookLendDAO bookLendDAO = new BookLendDAO();
   BookManagementDAO bookManagementDAO = new BookManagementDAO();
   BookReservationDAO bookReservationDAO = new BookReservationDAO();
   BookLendDTO bookLendDTO = bookLendDAO.selectUserLendingData(userNo, bookIsbn);
   int bookLendingCnt = bookManagementDAO.selectBookLendingCnt(bookIsbn);
   String userReservationStatus = bookReservationDAO.selectUserReservationStatus(userNo, bookIsbn);
   int extensionCnt = 0;
   %>

   <%@ include file="userNavbar.jsp"%>

<!-- ????????? Section -->
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
               <p>?????? > <%=bookInfoDTO.getCategoryName()%></p>
               <p>????????? <%=bookInfoDTO.getPubDate()%></p>
               <p style="font-weight: bold;">?????????</p>
               <p><%=bookInfoDTO.getDescription()%></p>
               <p>??????  
                  <%
                     for (int i = 0; i < reviewStarAVG; i++) {
                  %>
                  <span class="checkedStar">???</span>
                  <%
                  }
                  %>
                  <%
                     for(int i = 0; i< 5- reviewStarAVG; i++){
                  %>
                  <span class="uncheckedStar">???</span>
                  <%
                     }
                  %>
                  ( <%=reviewStarAVG%> )
               </p>
               <p>?????? ( <%=reviewCnt%> )</p>
	               <p>?????? ????????? ??? ??? ( <%=bookInfoDTO.getBookCnt()%> )
	               <%
	               if(userID == null){
	            	   if(bookLendingCnt < 5){
	               %>
	               	  <button type="button" class="btn btn-primary lendBtn" style="width: 100px;">????????????</button>
	               <%
	            	   } else {
	               %>
					  <button type="button" class="btn btn-info lendBtn" style="width: 100px;">????????????</button>
	               <%
	                   }
	               } else { 
		               if(bookLendDTO.getLendNo() == null){
							
							if(bookLendingCnt < 5){
	               %>
	                  <button type="button" class="btn btn-primary lendBtn"
							style="width: 100px;" data-bs-toggle="modal"
							data-bs-target="#exampleModal">????????????</button>
						
						<form id="lendForm" method="post" name="lendForm">
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="exampleModalLabel">????????????</h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body lend-body d-flex justify-content-center">
										<%=bookInfoDTO.getTitle()%>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary"
												onclick="checkLendBtn()">????????????</button>
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">??????</button>
										</div>
									</div>
								</div>
							</div>
						</form>  
	               <%
						} else {
							if(userReservationStatus == null){
				   %>
   					  <button type="button" class="btn btn-info lendBtn" style="width: 100px;" data-bs-toggle="modal"
							data-bs-target="#exampleModal1">????????????</button>
							
					  <form id="reservateForm" method="post" name="reservateForm"> 
 							<div class="modal fade" id="exampleModal1" tabindex="-1" 
								aria-labelledby="exampleModalLabel1" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="exampleModalLabel1">????????????</h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body reservate-body d-flex justify-content-center">
										<%=bookInfoDTO.getTitle()%>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary"
												onclick="checkReservateBtn()">????????????</button>
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">??????</button>
										</div>
									</div>
								</div>
							</div>
						</form>  
				   <% 
						} else {
				   %>		
				   	<form action="./reservationCancelAction.jsp?isbn=<%=bookIsbn%>" method="post">
							<button type="submit" class="btn btn-warning lendTitle" style="width: 100px;">????????????</button>						
						</form>
				   <%
							}
						}
	               } else{
	            	   if(bookLendDTO.getReturnStatus().equals("true")){
					%>
						<button type="button" class="btn btn-dark lendBtn" style="width: 100px;">????????????</button>		
					<%
						} else {
					%>
						<button type="button" class="btn btn-success lendBtn" style="width: 100px;" style="width: 100px;" data-bs-toggle="modal" data-bs-target="#staticBackdrop">?????????</button>
	               		<form id="extensionForm" method="post" name="extensionForm"> 
							<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h1 class="modal-title fs-5" id="staticBackdropLabel">????????????</h1>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							      </div>
					<%
						if(bookLendDTO.getExtensionStatus().equals("true")){
							extensionCnt = 1;
					%>
							      <div class="modal-body extension-body">
							      <%=bookInfoDTO.getTitle()%><br>?????????????????? <%=bookLendDTO.expectedReturnDate%>???????????????. <br>?????? ?????? ?????? ????????? <%=extensionCnt%>????????????.
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-primary extensionBtn"
							        onclick="checkExtensionBtn()">????????????</button>
							        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">??????</button>
							      </div>
					<% 
						} else{
					%>
								  <div class="modal-body extension-body">
							      <%=bookInfoDTO.getTitle()%><br>?????????????????? <%=bookLendDTO.expectedReturnDate%>???????????????. <br>?????? ?????? ?????? ????????? <%=extensionCnt%>????????????.
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">??????</button>
							      </div>						
					<%
						}
					%>	
							    </div>
							  </div>
							</div>
						</form>
	               	<%
						}}}
	               	%>
	               </p>
            </div>
         </div>
<!-- ?????? Section -->
      <div class="bar mt-5" style="border-bottom: 1px solid gray; width: 100%;"></div>
      <div class="reviewSection form-floating" style="width: 100%; height: 100%; ">
         <h5 style="margin: 50px; font-weight: bold;">?????? ??????</h5>
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
                                       out.println("???????????? ????????????");
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
                                                for="rate1">???</label>
                                             <input type="radio" name="reviewStar" value="4" id="rate2"><label
                                                for="rate2">???</label>
                                             <input type="radio" name="reviewStar" value="3" id="rate3"><label
                                                for="rate3">???</label>
                                             <input type="radio" name="reviewStar" value="2" id="rate4"><label
                                                for="rate4">???</label>
                                             <input type="radio" name="reviewStar" value="1" id="rate5"><label
                                                for="rate5">???</label>
                                          </fieldset>
                                       </div>
                              </div>
                           <div style="margin-left:30px">
                              <textarea name="reviewContent" class="form-control" style="height: 100px; color: gray" placeholder="??????????????? ??????????????? ?????? ??????????????????."></textarea>
                           </div>
                     </div>
                  <div class="col-sm-2" style="margin:auto; margin-bottom:40px;">
                  <button type="submit" class="btn btn-outline-secondary row" value="??????" style="margin: auto; margin-left: 40px;">????????????</button>
                  </div>
               </div>
            </div>
         </form>
      </div>
<!-- ??? ??? ?????? ??????-->
      <h5 style="margin: 50px; font-weight: bold;">??? ??? ??????</h5>
         <table class="table" style="position: relative;">
            <thead>
               <tr>
                  <th scope="col" style="text-align: center;">??????</th>
                  <th scope="col" style="text-align: center;">??????</th>
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
                           <span class="checkedStar">???</span>
                        <%
                           }
                        %>
                        <%
                           for(int i = 0; i< 5- srd.reviewStar; i++){
                        %>
                           <span class="uncheckedStar">???</span>
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
<!-- ?????? ???????????????, ??? ??? ?????? ??????, ?????? ??? ??? ????????? -->
               <%
                  if(userID != null ){

                  if(userNo.equals(srd.getUserNo())){                     
               %>
                  <div style="margin-top:15px">
                     <form class="row" action="./reviewUpdate.jsp?reviewNo=<%=srd.getReviewNo()%>&title=<%=bookInfoDTO.getTitle()%>" method="post">
                        <button id= "updateBtn" class="btn btn-outline-secondary">??????</button>
                     </form>
                     <form class="row" action="./reviewDeleteAction.jsp?reviewNo=<%=srd.getReviewNo()%>" method="post">
                        <button id= "deleteBtn" class="btn btn-outline-secondary">??????</button>
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
      <p class="mb-1">&copy; 2022 ????????????</p>
   </footer>
   <script>
   
   const userID = "<%=userID%>";
	
	if(userID === "null"){
		const lendBtn = document.querySelector(".lendBtn");
		    lendBtn.addEventListener("click", ()=>{
		    	alert("????????? ??? ?????????????????????.");
		    })						
	}
   
   const lendForm = document.getElementById("lendForm");
   function checkLendBtn() {
       if (!confirm("??????????????? ??? 10????????????. ??????????????? ??? 1??? ???????????? ???????????? ????????? ??? 5????????????. ?????????????????????????")) {
       	alert("????????? ?????????????????????.");
       } else {
			lendForm.setAttribute("action", "./lendingAction.jsp?isbn=<%=bookIsbn%>");
       	lendForm.submit();
       }
   }
   
   const reservateForm = document.getElementById("reservateForm");
   function checkReservateBtn() {
       if (!confirm("???????????????")) {
       	alert("????????? ?????????????????????..");
       } else {
			const isbn = localStorage.getItem("isbn");
			reservateForm.setAttribute("action", "./reservationAction.jsp?isbn=<%=bookIsbn%>");
			reservateForm.submit();
       	}
       }
   
   const extensionForm = document.getElementById("extensionForm");
   function checkExtensionBtn() {
   if (!confirm("???????????????")) {
   	alert("????????? ?????????????????????..");
   } else {
		const isbn = localStorage.getItem("isbn");
		extensionForm.setAttribute("action", "./extensionAction.jsp?isbn=<%=bookIsbn%>");
		extensionForm.submit();
   	}
   }
   </script>
</body>
</html>