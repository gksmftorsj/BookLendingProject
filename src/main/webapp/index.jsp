<%@page import="bookManagement.BookManagementDAO"%>
<%@ page import="bookInfo.BookInfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bookInfo.BookInfoDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a {
	text-decoration: none;
	color: black;
}
</style>
<body>
	<%
	String viewPage = request.getParameter("viewPage");
	if (viewPage == null) {
		viewPage = "1";
	}

	int view_page = Integer.parseInt(viewPage);

	int startIndex = ((view_page - 1) * 10) + 1;
	int endIndex = view_page * 10;
	BookInfoDAO bookInfoDAO = new BookInfoDAO();

	List<BookInfoDTO> topRankBookInfoList = bookInfoDAO.selectBookInfo(1, 10);
	List<BookInfoDTO> bookInfoList = bookInfoDAO.selectBookInfo(startIndex, endIndex);

	int total = bookInfoDAO.selectBookTotal();
	int endPage = (int) Math.ceil((double) total / 10);
	
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	
	BookManagementDAO bookManagementDAO = new BookManagementDAO();
	%>
	<%@ include file="userNavbar.jsp"%>
	<div id="carouselExampleInterval" class="container carousel slide mt-5"
		data-bs-ride="carousel">
		<div
			class="carousel-inner d-flex justify-content-center align-items-center"
			style="height: 600px; background-color: lightgray;">
			<%
			for (BookInfoDTO bi : topRankBookInfoList) {
			%>
			<div class="carousel-item" datSa-bs-interval="2000"
				style="left: -100px">
				<img src="<%=bi.cover%>" alt="...">
				<p style="width: 200px; text-align: center;"><%=bi.title%></p>
			</div>
			<%
			}
			%>

		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<div class="container sortBook d-flex justify-content-end mt-5">
		<span class="me-3">랭킹순</span> <span class="me-3">최신순</span> <span
			class="me-3">이름순</span> <select class="form-select text-center"
			style="width: 160px;" onchange="selectCategory(this)">
			<option selected>장르</option>
			<option value="유아">유아</option>
			<option value="종교">어린이</option>
			<option value="전집/중고전집">전집/중고전집</option>
			<option value="좋은부모">좋은부모</option>
			<option value="청소년">청소년</option>
			<option value="초등학교참고서">초등학교참고서</option>
			<option value="중학교참고서">중학교참고서</option>
			<option value="고등학교참고서">고등학교참고서</option>
			<option value="여행">여행</option>
			<option value="요리/살림">요리/살림</option>
			<option value="건강/취미">건강/취미</option>
			<option value="달력/기타">달력/기타</option>
			<option value="만화">만화</option>
			<option value="잡지">잡지</option>
			<option value="고전">고전</option>
			<option value="소설/시/희곡">소설/시/희곡</option>
			<option value="장르소설">장르소설</option>
			<option value="에세이">에세이</option>
			<option value="인문학">인문학</option>
			<option value="사회과학">사회과학</option>
			<option value="역사">역사</option>
			<option value="예술/대중문화">예술/대중문화</option>
			<option value="종교/역학">종교/역학</option>
			<option value="경제경영">경제경영</option>
			<option value="자기계발">자기계발</option>
			<option value="외국어">외국어</option>
			<option value="컴퓨터/모바일">컴퓨터/모바일</option>
			<option value="대학교재">대학교재</option>
			<option value="공무원 수험서">공무원 수험서</option>
			<option value="수험서/자격증">수험서/자격증</option>
		</select>
	</div>

	<div class="container">
		<table class="table">
			<thead>
				<tr class="text-center">
					<th scope="col" style="width: 50px;">순위</th>
					<th scope="col">제목</th>
					<th scope="col">저자</th>
					<th scope="col">대여/예약</th>
				</tr>
			</thead>
			<tbody>
				
				<%
				for (BookInfoDTO bi : bookInfoList) {
					int bookLendingCnt = bookManagementDAO.selectBookLendingCnt(bi.isbn);				
				%>
				<tr>
					<th scope="row" class="text-center"><%=bi.rank%></th>
					<td><a class="bookTitle"
						style="text-decoration: none; color: black;"
						href="./bookDetail.jsp?title=<%=bi.title%>"><%=bi.title%></a></td>
					<td><%=bi.author%><%=bookLendingCnt%></td>

					<td>
					<% 
					if(userID == null){
						if(bookLendingCnt < 5){
					%>
						<button type="button" class="btn btn-primary lendBtn"
							style="width: 100px;">대여가능</button> 
					<% 
						} else{
					%>		
						<button type="button" class="btn btn-primary lendBtn"
							style="width: 100px;">예약가능</button> 
					<%
						}
					} else{
						if(bookLendingCnt < 5){
					%>
						<button type="button" class="btn btn-primary"
							style="width: 100px;" data-bs-toggle="modal"
							data-bs-target="#exampleModal" onclick="localStorage.setItem('isbn', '<%=bi.isbn%>')">대여가능</button>
						
						<form id="lendForm" method="post" name="lendForm">
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="exampleModalLabel">대여할
												권수를 선택하세요.</h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body d-flex justify-content-center">
											안녕하세요
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary" id="lendBtn"
												onclick="checkLendBtn()">대여하기</button>
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">닫기</button>
										</div>
									</div>
								</div>
							</div>
						</form>  
					<%
						} else{
					%>	
						<button type="button" class="btn btn-primary"
							style="width: 100px;">예약가능</button>
						
						
						<%
					}
						%>

					</td>
				</tr>

				<%
					}
				}
				%>
			</tbody>


		</table>

		<nav aria-label="Page navigation example"
			class="d-flex justify-content-center">
			<ul class="pagination">
				<li class="page-item"><a class="page-link"
					href="./index.jsp?viewPage=<%=view_page - 1%>"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<%
				for (int i = 1; i <= endPage; i++) {
				%>
				<li class="page-item"><a class="page-link"
					href="./index.jsp?viewPage=<%=i%>"><%=i%></a></li>
				<%
				}
				%>
				<li class="page-item"><a class="page-link"
					href="./index.jsp?viewPage=<%=view_page + 1%>" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>


	<footer class="my-3 text-center text-small">
		<p class="mb-1">&copy; 2022 지니북스</p>
	</footer>
	<script>
		const slideImg = document.querySelector(".carousel-item");
		slideImg.classList.add("active");

		function selectCategory(target) {
			console.log(target.value);
			location.href = `./index.jsp?category=${target.value}`;
		}
		
		const userID = "<%=userID%>";
		
		if(userID === "null"){
			const lendBtn = document.querySelectorAll(".lendBtn");
			for(let i=0; i<<%=bookInfoList.size()%>; i++){
			    lendBtn[i].addEventListener("click", ()=>{
			    	alert("로그인 후 이용가능합니다.");
			    })						
			}
		}
		
		const lendForm = document.getElementById("lendForm");
		function checkLendBtn() {
        if (!confirm("대여기간은 총 10일입니다. 추가연장은 총 1회 가능하며 추가연장 기간은 총 5일입니다. 대여하시겠습니까?")) {
        	alert("대여가 취소되었습니다.");
        } else {
			const isbn = localStorage.getItem("isbn");
			alert(isbn);
			lendForm.setAttribute("action", "./lendingAction.jsp?isbn=" + isbn);
        	lendForm.submit();
        	}
        }
		
	</script>

</body>
</html>