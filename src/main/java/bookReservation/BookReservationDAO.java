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

	public int insertBookReservation(String userNo, String bookNo) {
		String sqlQuery = "INSERT INTO BOOK_RESERVATION "
						+ "VALUES(?, to_char(sysdate, 'yyyymmdd')||'RSN'||LPAD(RSN_SEQ.NEXTVAL, 4, 0), ?, sysdate)";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int result = 0;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);

			psmt.setString(1, userNo);
			psmt.setString(2, bookNo);
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
	
	public String selectUserReservationStatus(String userNo, String isbn) {
		String sqlQuery = "SELECT * FROM BOOK_RESERVATION WHERE USERNO = ? AND SUBSTR(BOOKNO, 1, 10) = ?";
		
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
	
	public int deleteReservation(String userNo, String bookNo) {
		String sqlQuery = "DELETE FROM BOOK_RESERVATION WHERE USERNO = ? AND BOOKNO = ?";
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setString(1, userNo);
			psmt.setString(2, bookNo);
			
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

	public List<BookReservationDTO> selectAdminBookReservationDetail() {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo,"
	      		+ " rs.bookNo, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate, CASE WHEN length(bi.bookTitle) < 20"
	      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
	      		+ " ui.userName FROM book_reservation rs, book_lend bl, book_info bi, user_info ui"
	      		+ " WHERE rs.bookNo = bl.bookNo AND rs.userNo = ui.userNo AND SUBSTR(rs.bookNo, 1, 10)"
	      		+ " = bi.bookIsbn ORDER BY reservationNo DESC";

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
					brs.bookNo = rs.getString("bookNo");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					
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
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo,"
	      		+ " rs.bookNo, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate, CASE WHEN length(bi.bookTitle) < 20"
	      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
	      		+ " ui.userName FROM book_reservation rs, book_lend bl, book_info bi, user_info ui"
	      		+ " WHERE rs.bookNo = bl.bookNo AND rs.userNo = ui.userNo AND SUBSTR(rs.bookNo, 1, 10)"
	      		+ " = bi.bookIsbn AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE TO_CHAR(sysdate, 'yyyymm') ORDER BY reservationNo DESC";

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
					brs.bookNo = rs.getString("bookNo");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					
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
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo,"
	      		+ " rs.bookNo, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate, CASE WHEN length(bi.bookTitle) < 20"
	      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
	      		+ " ui.userName FROM book_reservation rs, book_lend bl, book_info bi, user_info ui"
	      		+ " WHERE rs.bookNo = bl.bookNo AND rs.userNo = ui.userNo AND SUBSTR(rs.bookNo, 1, 10)"
	      		+ " = bi.bookIsbn AND rs.reservationNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setString(1, rsNo);
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookNo = rs.getString("bookNo");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					
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
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo,"
	      		+ " rs.bookNo, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate, CASE WHEN length(bi.bookTitle) < 20"
	      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
	      		+ " ui.userName FROM book_reservation rs, book_lend bl, book_info bi, user_info ui"
	      		+ " WHERE rs.bookNo = bl.bookNo AND rs.userNo = ui.userNo AND SUBSTR(rs.bookNo, 1, 10)"
	      		+ " = bi.bookIsbn AND rs.userNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setString(1, userNo);
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookNo = rs.getString("bookNo");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					
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
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo,"
	      		+ " rs.bookNo, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate, CASE WHEN length(bi.bookTitle) < 20"
	      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
	      		+ " ui.userName FROM book_reservation rs, book_lend bl, book_info bi, user_info ui"
	      		+ " WHERE rs.bookNo = bl.bookNo AND rs.userNo = ui.userNo AND SUBSTR(rs.bookNo, 1, 10)"
	      		+ " = bi.bookIsbn AND bi.bookTitle LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setString(1, title);
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookNo = rs.getString("bookNo");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					
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
	
	public List<BookReservationDTO> selectAdminBookReservationDetailByRsDateAndRsNo(String rsDate) {
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo,"
	      		+ " rs.bookNo, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate, CASE WHEN length(bi.bookTitle) < 20"
	      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
	      		+ " ui.userName FROM book_reservation rs, book_lend bl, book_info bi, user_info ui"
	      		+ " WHERE rs.bookNo = bl.bookNo AND rs.userNo = ui.userNo AND SUBSTR(rs.bookNo, 1, 10)"
	      		+ " = bi.bookIsbn AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE ? ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setString(1, rsDate);
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookNo = rs.getString("bookNo");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					
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
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo,"
	      		+ " rs.bookNo, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate, CASE WHEN length(bi.bookTitle) < 20"
	      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
	      		+ " ui.userName FROM book_reservation rs, book_lend bl, book_info bi, user_info ui"
	      		+ " WHERE rs.bookNo = bl.bookNo AND rs.userNo = ui.userNo AND SUBSTR(rs.bookNo, 1, 10)"
	      		+ " = bi.bookIsbn AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE ? AND rs.reservationNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setString(1, rsDate);
				psmt.setString(2, rsNo);
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookNo = rs.getString("bookNo");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					
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
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo,"
	      		+ " rs.bookNo, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate, CASE WHEN length(bi.bookTitle) < 20"
	      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
	      		+ " ui.userName FROM book_reservation rs, book_lend bl, book_info bi, user_info ui"
	      		+ " WHERE rs.bookNo = bl.bookNo AND rs.userNo = ui.userNo AND SUBSTR(rs.bookNo, 1, 10)"
	      		+ " = bi.bookIsbn AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE ? AND rs.userNo LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setString(1, rsDate);
				psmt.setString(2, userNo);
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookNo = rs.getString("bookNo");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					
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
	      String sqlQuery = "SELECT rs.reservationDate, rs.reservationNo, rs.userNo,"
	      		+ " rs.bookNo, TO_CHAR(bl.expectedReturnDate, 'yy/mm/dd') expectedLendingDate, CASE WHEN length(bi.bookTitle) < 20"
	      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle,"
	      		+ " ui.userName FROM book_reservation rs, book_lend bl, book_info bi, user_info ui"
	      		+ " WHERE rs.bookNo = bl.bookNo AND rs.userNo = ui.userNo AND SUBSTR(rs.bookNo, 1, 10)"
	      		+ " = bi.bookIsbn AND TO_CHAR(rs.reservationDate, 'yyyymm') LIKE ? AND bi.bookTitle LIKE '%'||?||'%' ORDER BY reservationNo DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookReservationDTO> BookReservationList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setString(1, rsDate);
				psmt.setString(2, title);
				
				BookReservationList = new ArrayList<BookReservationDTO>();

				while (rs.next()) {
					BookReservationDTO brs = new BookReservationDTO();

					brs.userNo = rs.getString("userNo");
					brs.reservationNo = rs.getString("reservationNo");
					brs.bookNo = rs.getString("bookNo");
					brs.reservationDate = rs.getTimestamp("reservationDate");
					brs.userName = rs.getString("userName");
					brs.title = rs.getString("bookTitle");
					brs.expectedLendingDate = rs.getString("expectedLendingDate");
					
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
