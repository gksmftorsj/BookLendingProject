package bookLend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import util.DatabaseUtil;

public class BookLendDAO {
	

	public int insertBookLend(BookLendDTO bld) {
		String sqlQuery = "INSERT INTO BOOK_LEND "
				+ "VALUES(to_char(sysdate, 'yyyymmdd')||'LDN'||LPAD(LDN_SEQ.NEXTVAL, 4, 0),"
				+ " ?, ?, sysdate, ?, ?)";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int result = 0;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);

			psmt.setString(1, bld.userNo);
			psmt.setString(2, bld.bookNo);
			psmt.setString(3, bld.extensionStatus);
			psmt.setInt(4, bld.extensionAvailabilityCnt);
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

	public List<BookLendDTO> selectAdminBookLendDetailToday() {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate, bl.ExtensionStatus,"
		      		+ " bl.extensionAvailabilityCnt, br.returnNo, br.reservationNo, br.returnDate,"
		      		+ " ui.userName, ui.userTel, ui.userID, ui.userEmail, ui.userAddress, um.userRegistrationDate,"
		      		+ " um.overDueStatus, um.overDueCnt, um.currentLendingCnt, um.currentReservationCnt,"
		      		+ " um.totalLendingCnt, bm.bookLendingAvailability, bm.bookReservationAvailability,"
		      		+ " bm.bookLendingCnt, bm.bookIsbn, bi.bookRank, CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
		      		+ " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle, bi.bookAuthor, bi.bookPubDate,"
		      		+ " bi.bookDescription, bi.bookCover, bi.bookCategoryName, bi.bookPublisher, bi.bookCnt,"
		      		+ " bi.bookTotalLendingCnt FROM book_lend bl, book_return br, user_info ui, user_management um,"
		      		+ "  book_management bm, book_info bi WHERE bl.lendNo = br.lendNo(+) AND bl.bookNo = bm.bookNo"
		      		+ " AND bl.userNo = ui.userNo AND ui.userNo = um.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND bl.lendDate = sysdate ORDER BY lendNO DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookLendDTO> BookLendList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				
				BookLendList = new ArrayList<BookLendDTO>();

				while (rs.next()) {
					BookLendDTO bl = new BookLendDTO();

					bl.lendNo = rs.getString("lendNo");
					bl.userNo = rs.getString("userNo");
					bl.bookNo = rs.getString("bookNo");
					bl.lendDate = rs.getTimestamp("lendDate");
					bl.extensionStatus = rs.getString("extensionStatus");
					bl.extensionAvailabilityCnt = rs.getInt("extensionAvailabilityCnt");
					
					bl.returnNo = rs.getString("returnNo");
					bl.returnDate = rs.getTimestamp("returnDate");

					bl.setUserName(rs.getString("userName"));
					bl.setUserID(rs.getString("userID"));
					bl.setUserEmail(rs.getString("userEmail"));
					bl.setUserAddress(rs.getString("userAddress"));
					bl.setUserTel(rs.getString("userTel"));

					bl.userRegistrationDate = rs.getTimestamp("userRegistrationDate");
					bl.overDueStatus = rs.getString("overDueStatus");
					bl.overDueCnt = rs.getInt("overDueCnt");
					bl.currentLendingCnt = rs.getInt("currentLendingCnt");
					bl.currentReservationCnt = rs.getInt("currentReservationCnt");
					bl.totalLendingCnt = rs.getInt("totalLendingCnt");

					bl.bookLendingAvailability = rs.getString("bookLendingAvailability");
					bl.bookReservationAvailability = rs.getString("bookReservationAvailability");
					bl.bookLendingCnt = rs.getInt("bookLendingCnt");
					bl.bookIsbn = rs.getString("bookIsbn");
					
					bl.rank = rs.getInt("bookRank");
					bl.title = rs.getString("bookTitle");
					bl.author = rs.getString("bookAuthor");
					bl.pubDate = rs.getString("bookPubDate");
					bl.description = rs.getString("bookDescription");
					bl.cover = rs.getString("bookCover");
					bl.categoryName = rs.getString("bookCategoryName");
					bl.publisher = rs.getString("bookPublisher");
					bl.bookCnt = rs.getInt("bookCnt");
					bl.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");

					BookLendList.add(bl);
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
			return BookLendList;
	}

	public List<BookLendDTO> selectAdminBookLendDetail() {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate, bl.ExtensionStatus,"
		      		+ " bl.extensionAvailabilityCnt, br.returnNo, br.reservationNo, br.returnDate,"
		      		+ " ui.userName, ui.userTel, ui.userID, ui.userEmail, ui.userAddress, um.userRegistrationDate,"
		      		+ " um.overDueStatus, um.overDueCnt, um.currentLendingCnt, um.currentReservationCnt,"
		      		+ " um.totalLendingCnt, bm.bookLendingAvailability, bm.bookReservationAvailability,"
		      		+ " bm.bookLendingCnt, bm.bookIsbn, bi.bookRank, CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
		      		+ " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle, bi.bookAuthor, bi.bookPubDate,"
		      		+ " bi.bookDescription, bi.bookCover, bi.bookCategoryName, bi.bookPublisher, bi.bookCnt,"
		      		+ " bi.bookTotalLendingCnt FROM book_lend bl, book_return br, user_info ui, user_management um,"
		      		+ "  book_management bm, book_info bi WHERE bl.lendNo = br.lendNo(+) AND bl.bookNo = bm.bookNo"
		      		+ " AND bl.userNo = ui.userNo AND ui.userNo = um.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " ORDER BY lendNO DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookLendDTO> BookLendList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				
				BookLendList = new ArrayList<BookLendDTO>();

				while (rs.next()) {
					BookLendDTO bl = new BookLendDTO();

					bl.lendNo = rs.getString("lendNo");
					bl.userNo = rs.getString("userNo");
					bl.bookNo = rs.getString("bookNo");
					bl.lendDate = rs.getTimestamp("lendDate");
					bl.extensionStatus = rs.getString("extensionStatus");
					bl.extensionAvailabilityCnt = rs.getInt("extensionAvailabilityCnt");
					bl.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					bl.returnStatus = rs.getString("returnStatus");
					
					bl.returnNo = rs.getString("returnNo");
					bl.returnDate = rs.getTimestamp("returnDate");

					bl.setUserName(rs.getString("userName"));
					bl.setUserID(rs.getString("userID"));
					bl.setUserEmail(rs.getString("userEmail"));
					bl.setUserAddress(rs.getString("userAddress"));
					bl.setUserTel(rs.getString("userTel"));

					bl.userRegistrationDate = rs.getTimestamp("userRegistrationDate");
					bl.overDueStatus = rs.getString("overDueStatus");
					bl.overDueCnt = rs.getInt("overDueCnt");
					bl.currentLendingCnt = rs.getInt("currentLendingCnt");
					bl.currentReservationCnt = rs.getInt("currentReservationCnt");
					bl.totalLendingCnt = rs.getInt("totalLendingCnt");

					bl.bookLendingAvailability = rs.getString("bookLendingAvailability");
					bl.bookReservationAvailability = rs.getString("bookReservationAvailability");
					bl.bookLendingCnt = rs.getInt("bookLendingCnt");
					bl.bookIsbn = rs.getString("bookIsbn");
					
					bl.rank = rs.getInt("bookRank");
					bl.title = rs.getString("bookTitle");
					bl.author = rs.getString("bookAuthor");
					bl.pubDate = rs.getString("bookPubDate");
					bl.description = rs.getString("bookDescription");
					bl.cover = rs.getString("bookCover");
					bl.categoryName = rs.getString("bookCategoryName");
					bl.publisher = rs.getString("bookPublisher");
					bl.bookCnt = rs.getInt("bookCnt");
					bl.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");

					BookLendList.add(bl);
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
			return BookLendList;
	}
	
	public List<BookLendDTO> selectAdminBookLendDetailByLendDate(Timestamp lendDate) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate, bl.ExtensionStatus,"
		      		+ " bl.extensionAvailabilityCnt, br.returnNo, br.reservationNo, br.returnDate,"
		      		+ " ui.userName, ui.userTel, ui.userID, ui.userEmail, ui.userAddress, um.userRegistrationDate,"
		      		+ " um.overDueStatus, um.overDueCnt, um.currentLendingCnt, um.currentReservationCnt,"
		      		+ " um.totalLendingCnt, bm.bookLendingAvailability, bm.bookReservationAvailability,"
		      		+ " bm.bookLendingCnt, bm.bookIsbn, bi.bookRank, CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
		      		+ " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle, bi.bookAuthor, bi.bookPubDate,"
		      		+ " bi.bookDescription, bi.bookCover, bi.bookCategoryName, bi.bookPublisher, bi.bookCnt,"
		      		+ " bi.bookTotalLendingCnt FROM book_lend bl, book_return br, user_info ui, user_management um,"
		      		+ "  book_management bm, book_info bi WHERE bl.lendNo = br.lendNo(+) AND bl.bookNo = bm.bookNo"
		      		+ " AND bl.userNo = ui.userNo AND ui.userNo = um.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND bl.lendDate = ? ORDER BY lendNO DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookLendDTO> BookLendList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setTimestamp(1, lendDate);

				BookLendList = new ArrayList<BookLendDTO>();

				while (rs.next()) {
					BookLendDTO bl = new BookLendDTO();

					bl.lendNo = rs.getString("lendNo");
					bl.userNo = rs.getString("userNo");
					bl.bookNo = rs.getString("bookNo");
					bl.lendDate = rs.getTimestamp("lendDate");
					bl.extensionStatus = rs.getString("extensionStatus");
					bl.extensionAvailabilityCnt = rs.getInt("extensionAvailabilityCnt");
					
					bl.returnNo = rs.getString("returnNo");
					bl.returnDate = rs.getTimestamp("returnDate");

					bl.setUserName(rs.getString("userName"));
					bl.setUserID(rs.getString("userID"));
					bl.setUserEmail(rs.getString("userEmail"));
					bl.setUserAddress(rs.getString("userAddress"));
					bl.setUserTel(rs.getString("userTel"));

					bl.userRegistrationDate = rs.getTimestamp("userRegistrationDate");
					bl.overDueStatus = rs.getString("overDueStatus");
					bl.overDueCnt = rs.getInt("overDueCnt");
					bl.currentLendingCnt = rs.getInt("currentLendingCnt");
					bl.currentReservationCnt = rs.getInt("currentReservationCnt");
					bl.totalLendingCnt = rs.getInt("totalLendingCnt");

					bl.bookLendingAvailability = rs.getString("bookLendingAvailability");
					bl.bookReservationAvailability = rs.getString("bookReservationAvailability");
					bl.bookLendingCnt = rs.getInt("bookLendingCnt");
					bl.bookIsbn = rs.getString("bookIsbn");
					
					bl.rank = rs.getInt("bookRank");
					bl.title = rs.getString("bookTitle");
					bl.author = rs.getString("bookAuthor");
					bl.pubDate = rs.getString("bookPubDate");
					bl.description = rs.getString("bookDescription");
					bl.cover = rs.getString("bookCover");
					bl.categoryName = rs.getString("bookCategoryName");
					bl.publisher = rs.getString("bookPublisher");
					bl.bookCnt = rs.getInt("bookCnt");
					bl.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");

					BookLendList.add(bl);
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
			return BookLendList;
	}

	public List<BookLendDTO> selectAdminBookLendDetailByLendDateAndLendNo(Timestamp lendDate, String lendNo) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate, bl.ExtensionStatus,"
		      		+ " bl.extensionAvailabilityCnt, br.returnNo, br.reservationNo, br.returnDate,"
		      		+ " ui.userName, ui.userTel, ui.userID, ui.userEmail, ui.userAddress, um.userRegistrationDate,"
		      		+ " um.overDueStatus, um.overDueCnt, um.currentLendingCnt, um.currentReservationCnt,"
		      		+ " um.totalLendingCnt, bm.bookLendingAvailability, bm.bookReservationAvailability,"
		      		+ " bm.bookLendingCnt, bm.bookIsbn, bi.bookRank, CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
		      		+ " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle, bi.bookAuthor, bi.bookPubDate,"
		      		+ " bi.bookDescription, bi.bookCover, bi.bookCategoryName, bi.bookPublisher, bi.bookCnt,"
		      		+ " bi.bookTotalLendingCnt FROM book_lend bl, book_return br, user_info ui, user_management um,"
		      		+ "  book_management bm, book_info bi WHERE bl.lendNo = br.lendNo(+) AND bl.bookNo = bm.bookNo"
		      		+ " AND bl.userNo = ui.userNo AND ui.userNo = um.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND bl.lendDate = ? AND bl.lendNo = ? ORDER BY lendNO DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookLendDTO> BookLendList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setTimestamp(1, lendDate);
				psmt.setString(2, lendNo);

				BookLendList = new ArrayList<BookLendDTO>();

				while (rs.next()) {
					BookLendDTO bl = new BookLendDTO();

					bl.lendNo = rs.getString("lendNo");
					bl.userNo = rs.getString("userNo");
					bl.bookNo = rs.getString("bookNo");
					bl.lendDate = rs.getTimestamp("lendDate");
					bl.extensionStatus = rs.getString("extensionStatus");
					bl.extensionAvailabilityCnt = rs.getInt("extensionAvailabilityCnt");
					
					bl.returnNo = rs.getString("returnNo");
					bl.returnDate = rs.getTimestamp("returnDate");

					bl.setUserName(rs.getString("userName"));
					bl.setUserID(rs.getString("userID"));
					bl.setUserEmail(rs.getString("userEmail"));
					bl.setUserAddress(rs.getString("userAddress"));
					bl.setUserTel(rs.getString("userTel"));

					bl.userRegistrationDate = rs.getTimestamp("userRegistrationDate");
					bl.overDueStatus = rs.getString("overDueStatus");
					bl.overDueCnt = rs.getInt("overDueCnt");
					bl.currentLendingCnt = rs.getInt("currentLendingCnt");
					bl.currentReservationCnt = rs.getInt("currentReservationCnt");
					bl.totalLendingCnt = rs.getInt("totalLendingCnt");

					bl.bookLendingAvailability = rs.getString("bookLendingAvailability");
					bl.bookReservationAvailability = rs.getString("bookReservationAvailability");
					bl.bookLendingCnt = rs.getInt("bookLendingCnt");
					bl.bookIsbn = rs.getString("bookIsbn");
					
					bl.rank = rs.getInt("bookRank");
					bl.title = rs.getString("bookTitle");
					bl.author = rs.getString("bookAuthor");
					bl.pubDate = rs.getString("bookPubDate");
					bl.description = rs.getString("bookDescription");
					bl.cover = rs.getString("bookCover");
					bl.categoryName = rs.getString("bookCategoryName");
					bl.publisher = rs.getString("bookPublisher");
					bl.bookCnt = rs.getInt("bookCnt");
					bl.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");

					BookLendList.add(bl);
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
			return BookLendList;
	}

	public List<BookLendDTO> selectBookAdminLendDetailByLendDateAndUserNo(Timestamp lendDate, String userNo) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate, bl.ExtensionStatus,"
		      		+ " bl.extensionAvailabilityCnt, br.returnNo, br.reservationNo, br.returnDate,"
		      		+ " ui.userName, ui.userTel, ui.userID, ui.userEmail, ui.userAddress, um.userRegistrationDate,"
		      		+ " um.overDueStatus, um.overDueCnt, um.currentLendingCnt, um.currentReservationCnt,"
		      		+ " um.totalLendingCnt, bm.bookLendingAvailability, bm.bookReservationAvailability,"
		      		+ " bm.bookLendingCnt, bm.bookIsbn, bi.bookRank, CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
		      		+ " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle, bi.bookAuthor, bi.bookPubDate,"
		      		+ " bi.bookDescription, bi.bookCover, bi.bookCategoryName, bi.bookPublisher, bi.bookCnt,"
		      		+ " bi.bookTotalLendingCnt FROM book_lend bl, book_return br, user_info ui, user_management um,"
		      		+ "  book_management bm, book_info bi WHERE bl.lendNo = br.lendNo(+) AND bl.bookNo = bm.bookNo"
		      		+ " AND bl.userNo = ui.userNo AND ui.userNo = um.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND bl.lendDate = ? AND bl.userNo = ? ORDER BY lendNO DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookLendDTO> BookLendList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setTimestamp(1, lendDate);
				psmt.setString(2, userNo);

				BookLendList = new ArrayList<BookLendDTO>();

				while (rs.next()) {
					BookLendDTO bl = new BookLendDTO();

					bl.lendNo = rs.getString("lendNo");
					bl.userNo = rs.getString("userNo");
					bl.bookNo = rs.getString("bookNo");
					bl.lendDate = rs.getTimestamp("lendDate");
					bl.extensionStatus = rs.getString("extensionStatus");
					bl.extensionAvailabilityCnt = rs.getInt("extensionAvailabilityCnt");
					
					bl.returnNo = rs.getString("returnNo");
					bl.returnDate = rs.getTimestamp("returnDate");

					bl.setUserName(rs.getString("userName"));
					bl.setUserID(rs.getString("userID"));
					bl.setUserEmail(rs.getString("userEmail"));
					bl.setUserAddress(rs.getString("userAddress"));
					bl.setUserTel(rs.getString("userTel"));

					bl.userRegistrationDate = rs.getTimestamp("userRegistrationDate");
					bl.overDueStatus = rs.getString("overDueStatus");
					bl.overDueCnt = rs.getInt("overDueCnt");
					bl.currentLendingCnt = rs.getInt("currentLendingCnt");
					bl.currentReservationCnt = rs.getInt("currentReservationCnt");
					bl.totalLendingCnt = rs.getInt("totalLendingCnt");

					bl.bookLendingAvailability = rs.getString("bookLendingAvailability");
					bl.bookReservationAvailability = rs.getString("bookReservationAvailability");
					bl.bookLendingCnt = rs.getInt("bookLendingCnt");
					bl.bookIsbn = rs.getString("bookIsbn");
					
					bl.rank = rs.getInt("bookRank");
					bl.title = rs.getString("bookTitle");
					bl.author = rs.getString("bookAuthor");
					bl.pubDate = rs.getString("bookPubDate");
					bl.description = rs.getString("bookDescription");
					bl.cover = rs.getString("bookCover");
					bl.categoryName = rs.getString("bookCategoryName");
					bl.publisher = rs.getString("bookPublisher");
					bl.bookCnt = rs.getInt("bookCnt");
					bl.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");

					BookLendList.add(bl);
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
			return BookLendList;
	}

	public List<BookLendDTO> selectAdminBookLendDetailByLendDateAndTitle(Timestamp lendDate, String title) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate, bl.ExtensionStatus,"
		      		+ " bl.extensionAvailabilityCnt, br.returnNo, br.reservationNo, br.returnDate,"
		      		+ " ui.userName, ui.userTel, ui.userID, ui.userEmail, ui.userAddress, um.userRegistrationDate,"
		      		+ " um.overDueStatus, um.overDueCnt, um.currentLendingCnt, um.currentReservationCnt,"
		      		+ " um.totalLendingCnt, bm.bookLendingAvailability, bm.bookReservationAvailability,"
		      		+ " bm.bookLendingCnt, bm.bookIsbn, bi.bookRank, CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
		      		+ " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle, bi.bookAuthor, bi.bookPubDate,"
		      		+ " bi.bookDescription, bi.bookCover, bi.bookCategoryName, bi.bookPublisher, bi.bookCnt,"
		      		+ " bi.bookTotalLendingCnt FROM book_lend bl, book_return br, user_info ui, user_management um,"
		      		+ "  book_management bm, book_info bi WHERE bl.lendNo = br.lendNo(+) AND bl.bookNo = bm.bookNo"
		      		+ " AND bl.userNo = ui.userNo AND ui.userNo = um.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND bl.lendDate = ? AND bi.bookTitle LIKE '%'||?||'%' ORDER BY lendNO DESC";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			List<BookLendDTO> BookLendList = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				rs = psmt.executeQuery();
				psmt.setTimestamp(1, lendDate);
				psmt.setString(2, title);

				BookLendList = new ArrayList<BookLendDTO>();

				while (rs.next()) {
					BookLendDTO bl = new BookLendDTO();

					bl.lendNo = rs.getString("lendNo");
					bl.userNo = rs.getString("userNo");
					bl.bookNo = rs.getString("bookNo");
					bl.lendDate = rs.getTimestamp("lendDate");
					bl.extensionStatus = rs.getString("extensionStatus");
					bl.extensionAvailabilityCnt = rs.getInt("extensionAvailabilityCnt");
					
					bl.returnNo = rs.getString("returnNo");
					bl.returnDate = rs.getTimestamp("returnDate");

					bl.setUserName(rs.getString("userName"));
					bl.setUserID(rs.getString("userID"));
					bl.setUserEmail(rs.getString("userEmail"));
					bl.setUserAddress(rs.getString("userAddress"));
					bl.setUserTel(rs.getString("userTel"));

					bl.userRegistrationDate = rs.getTimestamp("userRegistrationDate");
					bl.overDueStatus = rs.getString("overDueStatus");
					bl.overDueCnt = rs.getInt("overDueCnt");
					bl.currentLendingCnt = rs.getInt("currentLendingCnt");
					bl.currentReservationCnt = rs.getInt("currentReservationCnt");
					bl.totalLendingCnt = rs.getInt("totalLendingCnt");

					bl.bookLendingAvailability = rs.getString("bookLendingAvailability");
					bl.bookReservationAvailability = rs.getString("bookReservationAvailability");
					bl.bookLendingCnt = rs.getInt("bookLendingCnt");
					bl.bookIsbn = rs.getString("bookIsbn");
					
					bl.rank = rs.getInt("bookRank");
					bl.title = rs.getString("bookTitle");
					bl.author = rs.getString("bookAuthor");
					bl.pubDate = rs.getString("bookPubDate");
					bl.description = rs.getString("bookDescription");
					bl.cover = rs.getString("bookCover");
					bl.categoryName = rs.getString("bookCategoryName");
					bl.publisher = rs.getString("bookPublisher");
					bl.bookCnt = rs.getInt("bookCnt");
					bl.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");

					BookLendList.add(bl);
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
			} return BookLendList;
		}

	public List<BookLendDTO> selectBookLendByLendDateAndLendNo(LocalDateTime localDate, int lendNo) {
		String sqlQuery = "SELECT * FROM BOOK_LEND WHERE (lendDate=?) AND (lendNo=?)";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		LocalDateTime date = localDate;
		java.sql.Timestamp lendDate = java.sql.Timestamp.valueOf(localDate);

		List<BookLendDTO> BookLendList = null;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setTimestamp(1, lendDate);
			psmt.setInt(2, lendNo);
			rs = psmt.executeQuery();

			BookLendList = new ArrayList<BookLendDTO>();

			while (rs.next()) {
				BookLendDTO bld = new BookLendDTO();
				bld.lendNo = rs.getString("lendNo");
				bld.userNo = rs.getString("userNo");
				bld.bookNo = rs.getString("bookNo");
				bld.extensionStatus = rs.getString("extensionStatus");
				bld.extensionAvailabilityCnt = rs.getInt("extensionAvailabilityCnt");

				BookLendList.add(bld);
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
		return BookLendList;
	}

	public List<BookLendDTO> selectBookLendByLendDateAndUserNo(LocalDateTime localDate, int userNo) {
		String sqlQuery = "SELECT * FROM BOOK_LEND WHERE (lendDate=?) AND (userNo=?)";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		LocalDateTime date = localDate;
		java.sql.Timestamp lendDate = java.sql.Timestamp.valueOf(localDate);

		List<BookLendDTO> BookLendList = null;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setTimestamp(1, lendDate);
			psmt.setInt(2, userNo);
			rs = psmt.executeQuery();

			BookLendList = new ArrayList<BookLendDTO>();

			while (rs.next()) {
				BookLendDTO bld = new BookLendDTO();
				bld.lendNo = rs.getString("lendNo");
				bld.userNo = rs.getString("userNo");
				bld.bookNo = rs.getString("bookNo");
				bld.extensionStatus = rs.getString("extensionStatus");
				bld.extensionAvailabilityCnt = rs.getInt("extensionAvailabilityCnt");

				BookLendList.add(bld);
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
		return BookLendList;
	}

	public List<BookLendDTO> selectBookLendByLendDateAndTitle(LocalDateTime localDate, String title) {
		String sqlQuery = "SELECT * FROM BOOK_LEND WHERE (lendDate=?) AND "
				+ "(bookNo = (SELECT bookNo FROM  BOOK_MANAGEMENT "
				+ "WHERE bookIsbn = (SELECT bookIsbn FROM BOOK_INFO WHERE bookTitle like %?%)))";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		LocalDateTime date = localDate;
		java.sql.Timestamp lendDate = java.sql.Timestamp.valueOf(localDate);

		List<BookLendDTO> BookLendList = null;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setTimestamp(1, lendDate);
			psmt.setString(2, title);
			rs = psmt.executeQuery();

			BookLendList = new ArrayList<BookLendDTO>();

			while (rs.next()) {
				BookLendDTO bld = new BookLendDTO();
				bld.lendNo = rs.getString("lendNo");
				bld.userNo = rs.getString("userNo");
				bld.bookNo = rs.getString("bookNo");
				bld.extensionStatus = rs.getString("extensionStatus");
				bld.extensionAvailabilityCnt = rs.getInt("extensionAvailabilityCnt");

				BookLendList.add(bld);
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
		return BookLendList;
	}

	public String selectUserLendingStatus(String userNo, String isbn) {
		String sqlQuery = "SELECT * FROM BOOK_LEND WHERE USERNO = ? AND SUBSTR(BOOKNO, 1, 10) = ?";

		String lendNo = null;

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
				lendNo = rs.getString("lendNo");
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
		return lendNo;
	}

}
