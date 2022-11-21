package bookReservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bookLend.BookLendDTO;
import util.DatabaseUtil;

public class BookReservationDAO {

	public int insertBookReservation(String userNo, String bookIsbn) {
		String sqlQuery = "INSERT INTO BOOK_RESERVATION "
						+ "VALUES(?, to_char(sysdate, 'yyyymmdd')||'RSN'||LPAD(RSN_SEQ.NEXTVAL, 4, 0), ?, sysdate, 'false')";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int result = 0;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);

			psmt.setString(1, userNo);
			psmt.setString(2, bookIsbn);
			result = psmt.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (psmt != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int updateLendStatus(String userNo, String bookIsbn) {
		   String sqlQuery = "UPDATE BOOK_RESERVATION "
		   				   + "SET LENDSTATUS = 'true' "
		   				   + "WHERE userNo = ? AND bookIsbn = ?";
		   
		   int result = 0;
		   
		   Connection conn = null;
		   PreparedStatement psmt = null;
		   ResultSet rs = null;
		   
		   try {
			   conn = DatabaseUtil.getConnection();
			   psmt = conn.prepareStatement(sqlQuery);
			   psmt.setString(1, userNo);
			   psmt.setString(2, bookIsbn);
			   result = psmt.executeUpdate();
		   } catch (Exception e) {
			   e.printStackTrace();
		   } finally {
			   try {
				   if (conn != null)
					   conn.close();
			   } catch (Exception e) {
				   e.printStackTrace();
			   }
			   try {
				   if (psmt != null)
					   conn.close();
			   } catch (Exception e) {
				   e.printStackTrace();
			   }
			   try {
				   if (rs != null)
					   conn.close();
			   } catch (Exception e) {
				   e.printStackTrace();
			   }
		   } 
		   return result;
	   }
	
	public String selectUserReservationStatus(String userNo, String isbn) {
		String sqlQuery = "SELECT * FROM BOOK_RESERVATION WHERE USERNO = ? AND bookIsbn = ?";
		
		String reservationNo = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setString(1, userNo);
			psmt.setString(2, isbn);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				reservationNo = rs.getString("reservationNo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (psmt != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return reservationNo;
	}
	
	public String selectUserNo(String bookIsbn) {
		String sqlQuery = "SELECT userNo FROM BOOK_RESERVATION WHERE lendStatus = 'false' AND bookIsbn = ? AND ROWNUM = 1 ORDER BY RESERVATIONDATE";
		
		String userNo = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setString(1, bookIsbn);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				userNo = rs.getString("userNo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (psmt != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userNo;
	}
	
	public int deleteReservation(String userNo, String bookIsbn) {
		String sqlQuery = "DELETE FROM BOOK_RESERVATION WHERE USERNO = ? AND bookIsbn = ?";
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setString(1, userNo);
			psmt.setString(2, bookIsbn);
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (psmt != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	

	public List<BookReservationDTO> selectAdminNotLendBookReservationDetailByUserNo(String userNo) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
	      		+ " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
	      		+ " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
	      		+ " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
	      		+ " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
	      		+ " FROM book_reservation rs, book_info bi, user_info ui,"
	      		+ " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
	      		+ " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
	      		+ " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND rs.userNo LIKE '%'||?||'%' AND rs.lendStatus = 'false' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);				
				psmt.setString(1, userNo);
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();
				
				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	

	public List<BookReservationDTO> selectAdminBookReservationDetail() {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
		      	+ " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
		      	+ " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
		      	+ " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
		      	+ " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
		      	+ " FROM book_reservation rs, book_info bi, user_info ui,"
		      	+ " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
		      	+ " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
		      	+ " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	

	public List<BookReservationDTO> selectAdminBookReservationDetailThisMonth() {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE TO_CHAR(sysdate, 'yyyymm') ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	

	public List<BookReservationDTO> selectAdminBookReservationDetailByRsNo(String rsNo) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND rs.reservationNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, rsNo);
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}

	public List<BookReservationDTO> selectAdminBookReservationDetailByUserNo(String userNo) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND rs.userNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, userNo);
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	

	public List<BookReservationDTO> selectAdminBookReservationDetailByTitle(String title) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND bi.bookTitle LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, title);
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	
	
	public List<BookReservationDTO> selectAdminBookReservationDetailByRsDate(String rsDate) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE ? ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, rsDate);
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}

	public List<BookReservationDTO> selectAdminBookReservationDetailByRsDateAndRsNo(String rsDate, String rsNo) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE ? AND rs.reservationNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, rsDate);
				psmt.setString(2, rsNo);
				rs = psmt.executeQuery();
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}

	public List<BookReservationDTO> selectAdminBookReservationDetailByRsDateAndUserNo(String rsDate, String userNo) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE ? AND rs.userNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, rsDate);
				psmt.setString(2, userNo);
				rs = psmt.executeQuery();
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	

	public List<BookReservationDTO> selectAdminBookReservationDetailByRsDateAndTitle(String rsDate, String title) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE ? AND bi.bookTitle LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, rsDate);
				psmt.setString(2, title);
				rs = psmt.executeQuery();
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	
	

	public List<BookReservationDTO> selectAdminBookReservationDetailThisMonthByUserNo(String userNo) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE TO_CHAR(sysdate, 'yyyymm')"
	      		+ " AND rs.userNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, userNo);
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	

	public List<BookReservationDTO> selectAdminBookReservationDetailByRsNoAndUserNo(String rsNo, String userNo) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND rs.reservationNo LIKE '%'||?||'%' AND rs.userNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, rsNo);
				psmt.setString(2, userNo);
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}

	public List<BookReservationDTO> selectAdminBookReservationDetailByTitleAndUserNo(String title, String userNo) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND bi.bookTitle LIKE '%'||?||'%' AND rs.userNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, title);
				psmt.setString(2,  userNo);
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	

	public List<BookReservationDTO> selectAdminBookReservationDetailByRsDateAndRsNoAndUserNo(String rsDate, String rsNo, String userNo) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE ? AND rs.reservationNo LIKE '%'||?||'%'"
	      		+ " AND rs.userNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, rsDate);
				psmt.setString(2, rsNo);
				psmt.setString(3, userNo);
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	

	public List<BookReservationDTO> selectAdminBookReservationDetailByRsDateAndTitleAndUserNo(String rsDate, String title, String userNo) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo, bl.expectedReturnDate,"
			    + " rs.bookIsbn, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate,"
			    + " CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
			    + " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
			    + " TO_CHAR(rs.reservationDate, 'yy/mm/dd') rsDate, rs.lendingStatus, ui.userName"
			    + " FROM book_reservation rs, book_info bi, user_info ui,"
			    + " (SELECT * FROM book_lend WHERE ROWNUM = 1 ORDER BY EXPECTEDRETURNDATE) bl"
			    + " WHERE rs.bookIsbn = SUBSTR(bl.bookNo, 1, 10)"
			    + " AND rs.bookIsbn = bi.bookIsbn AND rs.userNo = ui.userNo"
	      		+ " AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE ? AND bi.bookTitle LIKE '%'||?||'%'"
	      		+ " AND rs.userNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, rsDate);
				psmt.setString(2, title);
				psmt.setString(3, userNo);
				
				rs = psmt.executeQuery();
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookIsbn = rs.getString("bookIsbn");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					brs.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					brs.rsDate = rs.getString("rsDate");
					brs.lendingStatus = rs.getString("lendingStatus");
					
					BookReservationList.add(brs);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (psmt != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (rs != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return BookReservationList;
	}	
	
	

}
