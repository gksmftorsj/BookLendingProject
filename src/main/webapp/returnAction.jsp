<%@page import="bookReturn.BookReturnDTO"%>
<%@page import="bookReturn.BookReturnDAO"%>
<%@page import="bookReservation.BookReservationDAO"%>
<%@page import="userManagement.UserManagementDAO"%>
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
		PrintWriter script = response.getWriter();
		
		UserManagementDAO userManagementDAO = new UserManagementDAO();
		BookManagementDAO bookManagementDAO = new BookManagementDAO();
		BookReservationDAO bookReservationDAO = new BookReservationDAO();
		BookLendDAO bookLendDAO = new BookLendDAO();
		BookReturnDAO bookReturnDAO = new BookReturnDAO();

		String bookNo = null;
		if (request.getParameter("bookNo") != null) {
			bookNo = request.getParameter("bookNo");
		}
		
		String bookIsbn = null;
		if (request.getParameter("bookIsbn") != null) {
			bookIsbn = request.getParameter("bookIsbn");
		}

		// 반납하는 사람 유저넘버
		String returnUserNo = null;
		if (request.getParameter("userNo") != null) {
			returnUserNo = request.getParameter("userNo");
		}
		
		// 빌리는 사람 유저넘버
		String lendUserNo = bookReservationDAO.selectUserNo(bookNo);
		out.print(lendUserNo);
		out.print(bookNo);
		int clc = 0; // 대여에서 반납하는 사람(USER_MANAGEMENT에서 currentLendingCnt -1)
		int crc = 0; // 예약에서 대여하는 사람(USER_MANAGEMENT에서 currentLendingCnt +1, currentReservationCnt -1, totalLendingCnt +1)
		int uum = 0; // 예약에서 대여하는 사람(USER_MANAGEMENT에서 currentLendingCnt +1, currentReservationCnt -1, totalLendingCnt +1)
		int urs = 0; // 대여에서 반납하는 사람(BOOK_LEND에서 returnStatus -> true로 변경)
		int ibl = 0; // 예약에서 대여하는 사람(BOOK_LEND에 insert)
		int ibr = 0; // ---> 대여에서 반납하는 사람(BOOK_RETURN에 반납하는 도서 데이터 insert)
		int ubmrtn = 0; // 예약에서 대여하는 사람(BOOK_MANAGEMENT에서 bookLendingAvailability -> 'false', bookReservationAvailability -> true, bookLendingCnt +1)
		int ulat = 0; // 대여에서 반납하는 사람(BOOK_MANAGEMENT에서 bookLendingAvailability -> true로 변경)
		int uls = 0; // 예약에서 대여하는 사람(대여하면 bookReservation에서 lendStatus true로 변경)
						
		if(lendUserNo == null){ // 예약대기중인 사람이 없을 때
			// 대여에서 반납하는 사람(USER_MANAGEMENT에서 currentLendingCnt -1)
			clc = userManagementDAO.updateCurrentLendingCntMinus(returnUserNo);
			
			// 대여에서 반납하는 사람(BOOK_LEND에서 returnStatus -> true로 변경)
			urs = bookLendDAO.updateReturnStatus(bookNo);
			
			// BookLendDTO 초기화 시켜주고 lend테이블에서 반납하는 사람의 lendingData 가져오기 -->
			BookLendDTO bookLendDTO = bookLendDAO.selectUserLendingData(returnUserNo, bookIsbn);
			BookReturnDTO bookReturnDTO = new BookReturnDTO();
			bookReturnDTO.setUserNo(bookLendDTO.getUserNo());
			bookReturnDTO.setBookNo(bookLendDTO.getBookNo());
			bookReturnDTO.setLendNo(bookLendDTO.getLendNo());
			bookReturnDTO.setLendDate(bookLendDTO.getLendDate());
			bookReturnDTO.setExpectedReturnDate(bookLendDTO.getExpectedReturnDate());
			// ---> 대여에서 반납하는 사람(BOOK_RETURN에 반납하는 도서 데이터 insert)
			ibr = bookReturnDAO.insertBookReturn(bookReturnDTO);
			
			// 대여에서 반납하는 사람(BOOK_MANAGEMENT에서 bookLendingAvailability -> true로 변경)
			ulat = bookManagementDAO.updateLendingAvailabilityTrue(bookNo);
			
			if(clc > 0 && urs > 0 && ibr > 0 && ulat > 0){
				script.println("<script>");
				script.println("alert('예약대기중인 사람이 없을 때 반납 완료되었습니다.');");
				script.println("location.href = document.referrer");
				script.println("</script>");
				script.close();
			} else{
				script.println("<script>");
				script.println("alert('예약대기중인 사람이 없을 때 반납에 실패했습니다. 다시 시도해주세요.');");
				script.println("location.href = document.referrer");
				script.println("</script>");
				script.close();
			}
		} else { // 예약 대기중인 사람 있을 때
			// 대여에서 반납하는 사람(USER_MANAGEMENT에서 currentLendingCnt -1)
			clc = userManagementDAO.updateCurrentLendingCntMinus(returnUserNo);

			// 대여에서 반납하는 사람(BOOK_LEND에서 returnStatus -> true로 변경)
			urs = bookLendDAO.updateReturnStatus(bookNo);
			
			// BookLendDTO 초기화 시켜주고 lend테이블에서 반납하는 사람의 lendingData 가져오기 -->
			BookLendDTO bookLendDTO = bookLendDAO.selectUserLendingData(returnUserNo, bookIsbn);
			BookReturnDTO bookReturnDTO = new BookReturnDTO();
			bookReturnDTO.setUserNo(bookLendDTO.getUserNo());
			bookReturnDTO.setBookNo(bookLendDTO.getBookNo());
			bookReturnDTO.setLendNo(bookLendDTO.getLendNo());
			bookReturnDTO.setLendDate(bookLendDTO.getLendDate());
			bookReturnDTO.setExpectedReturnDate(bookLendDTO.getExpectedReturnDate());
			// ---> 대여에서 반납하는 사람(BOOK_RETURN에 반납하는 도서 데이터 insert)
			ibr = bookReturnDAO.insertBookReturn(bookReturnDTO);
			
//--------------------------------------------------------------------------------------------------------------------------

			// 예약에서 대여하는 사람(USER_MANAGEMENT에서 currentLendingCnt +1, currentReservationCnt -1, totalLendingCnt +1)
			crc = userManagementDAO.updateCurrentReservationCntMinus(lendUserNo);
			uum = userManagementDAO.updateUserManagement(lendUserNo);

			// 예약에서 대여하는 사람(BOOK_MANAGEMENT에서 bookLendingAvailability -> 'false', bookReservationAvailability -> true, bookLendingCnt +1)
			ubmrtn = bookManagementDAO.updateBookManagementReturnToLending(bookNo);

 			// 예약에서 대여하는 사람(대여하면 bookReservation에서 lendStatus true로 변경)
			uls = bookReservationDAO.updateLendStatus(lendUserNo, bookNo);
			
 			// 예약에서 대여하는 사람(BOOK_LEND에 insert)
			bookLendDTO = new BookLendDTO();
			bookLendDTO.setUserNo(lendUserNo);
			bookLendDTO.setBookNo(bookNo);
			ibl = bookLendDAO.insertBookLend(bookLendDTO);
			
			if(clc > 0 && urs > 0 && ibr > 0 && crc > 0 && uum > 0 && ubmrtn > 0 && uls > 0 && ibl > 0){
				script.println("<script>");
				script.println("alert('반납 완료되었습니다.');");
				script.println("location.href = document.referrer");
				script.println("</script>");
				script.close();
			} else{
				script.println("<script>");
				script.println("alert('반납에 실패했습니다. 다시 시도해주세요.');");
				script.println("location.href = document.referrer");
				script.println("</script>");
				script.close();
			}
		} 
			
%>
