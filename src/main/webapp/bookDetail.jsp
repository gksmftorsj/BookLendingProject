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

	String bookIsbn = bookInfoDTO.getIsbn();
	BookReviewDAO bookReviewDAO = new BookReviewDAO();
	List<BookReviewDTO> selectReviewList = bookReviewDAO.selectReviewList(bookIsbn);
	
	%>
	<%=bookIsbn %>
	<%-- <%@ include file="userNavbar.jsp"%>
	
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
					<p style="font-weight: bold;">출간일<%=bookInfoDTO.getPubDate()%></p>
					<p style="font-weight: bold;">책소개</p>
					<p><%=bookInfoDTO.getDescription()%></p>
					<p>대출 가능한 권 수 <%=bookInfoDTO.getBookCnt()%>
						<button type="button" class="btn btn-outline-secondary">대출하기</button>
						<button type="button" class="btn btn-outline-secondary">예약하기</button>
						<button type="button" class="btn btn-outline-secondary" style>찜하기 
							<img src="https://cdn-icons-png.flaticon.com/512/138/138533.png" style="width: 10px; height: 10px;"></img></button>
					</p>
				</div>
			</div>
<!-- 리뷰 Section -->
		<div class="bar mt-5" style="border-bottom: 1px solid gray; width: 100%;"></div>
		<div class="reviewSection form-floating" style="width: 100%; height: 100%;">
			<h5 style="margin: 50px; font-weight: bold;">한 줄 리뷰</h5>
			<form id="reviewForm" name="reviewForm" class="row g-3" method="post" action="reviewAction.jsp">
				<div class="container">
					<div class="row">
						<div class="col-sm-10">
							<input type="hidden" name="isbn" value="<%=bookIsbn%>">
							<textarea class="form-control" placeholder="Leave a comment here"
								id="floatingTextarea2" name="reviewContent"
								style="display: inline-block; height: 100px">
								
							지니북스가 훈훈해지는 댓글 부탁드립니다.
							<%=userID%>
							</textarea>

						</div>
						<div class="col-sm-2">
							<button type="submit" class="btn btn-outline-secondary"
								value="페이지 새로 고침">
								등록
															<%=

								// if ()
															%>
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<table class="table" style="position: relative; top: 50px;">
			<thead>
				<tr>
					<th scope="col" style="text-align: center;">book</th>
					<th scope="col" style="text-align: center;">리뷰</th>
				</tr>
			</thead>
			<tbody>


				<%
				if (selectReviewList != null && selectReviewList.size() > 0) {
					for (BookReviewDTO srl : selectReviewList) {
				%>
				<tr>
					<td scope="row"><img src=<%=bookInfoDTO.getCover()%>
						style="width: 100px; height: 100px"></td>
					<td scope="row"><%=srl.getReviewContent()%></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
	</div> --%>
	<script>
		window.location.href;
	</script>
</body>
</html>
