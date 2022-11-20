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
				+ " ?, ?, sysdate, ?, ?, sysdate+10, 'false')";

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
	
	public int updateExtension(String bookNo) {
		   String sqlQuery = "UPDATE BOOK_LEND "
				   + "SET extensionStatus = 'false', expectedReturnDate = expectedReturnDate + 5 "
				   + "WHERE bookNo = ?";
		   
		   int result = 0;
		   
		   Connection conn = null;
		   PreparedStatement psmt = null;
		   ResultSet rs = null;
		   
		   try {
			   conn = DatabaseUtil.getConnection();
			   psmt = conn.prepareStatement(sqlQuery);
			   psmt.setString(1, bookNo);
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

	public List<BookLendDTO> selectAdminBookLendDetailThisMonth() {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate,"
		      		+ " bl.extensionStatus, bl.extensionavailabilitycnt, bl.expectedReturnDate,"
		      		+ " bl.returnStatus, ui.userName, bm.bookIsbn, CASE WHEN length(bi.bookTitle) < 20"
		      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle"
		      		+ " FROM book_lend bl, book_management bm, book_info bi, user_info ui"
		      		+ " WHERE bl.bookNo = bm.bookNo AND bl.userNo = ui.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND TO_CHAR(bl.lendDate, 'yyyymm') LIKE TO_CHAR(sysdate, 'yyyymm') ORDER BY lendNo DESC";

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
							bl.setUserName(rs.getString("userName"));
							bl.bookIsbn = rs.getString("bookIsbn");					
							bl.title = rs.getString("bookTitle");

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
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate, bl.extensionStatus,"
	      		+ " bl.extensionavailabilitycnt, bl.expectedReturnDate, bl.returnStatus, ui.userName,"
	      		+ " bm.bookIsbn, CASE WHEN length(bi.bookTitle) < 20 THEN bi.bookTitle"
	      		+ " ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle FROM book_lend bl,"
	      		+ " book_management bm, book_info bi, user_info ui WHERE bl.bookNo = bm.bookNo"
	      		+ " AND bl.userNo = ui.userNo AND bm.bookIsbn = bi.bookIsbn ORDER BY lendNo DESC";

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
					bl.expectedReturnDate = rs.getTimestamp("expectedReturnDate");
					bl.returnStatus = rs.getString("returnStatus");
					bl.setUserName(rs.getString("userName"));
					bl.bookIsbn = rs.getString("bookIsbn");					
					bl.title = rs.getString("bookTitle");

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

	public List<BookLendDTO> selectAdminBookLendDetailByLendNo(String lendNo) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate,"
		      		+ " bl.extensionStatus, bl.extensionavailabilitycnt, bl.expectedReturnDate,"
		      		+ " bl.returnStatus, ui.userName, bm.bookIsbn, CASE WHEN length(bi.bookTitle) < 20"
		      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle"
		      		+ " FROM book_lend bl, book_management bm, book_info bi, user_info ui"
		      		+ " WHERE bl.bookNo = bm.bookNo AND bl.userNo = ui.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND bl.lendNo LIKE '%'||?||'%' ORDER BY lendNo DESC";

					Connection conn = null;
					PreparedStatement psmt = null;
					ResultSet rs = null;
					
					List<BookLendDTO> BookLendList = null;

					try {
						conn = DatabaseUtil.getConnection();
						psmt = conn.prepareStatement(sqlQuery);
						psmt.setString(1, lendNo);
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
							bl.setUserName(rs.getString("userName"));
							bl.bookIsbn = rs.getString("bookIsbn");					
							bl.title = rs.getString("bookTitle");

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

	public List<BookLendDTO> selectAdminBookLendDetailByUserNo(String userNo) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate,"
		      		+ " bl.extensionStatus, bl.extensionavailabilitycnt, bl.expectedReturnDate,"
		      		+ " bl.returnStatus, ui.userName, bm.bookIsbn, CASE WHEN length(bi.bookTitle) < 20"
		      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle"
		      		+ " FROM book_lend bl, book_management bm, book_info bi, user_info ui"
		      		+ " WHERE bl.bookNo = bm.bookNo AND bl.userNo = ui.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND bl.userNo LIKE '%'||?||'%' ORDER BY lendNo DESC";

					Connection conn = null;
					PreparedStatement psmt = null;
					ResultSet rs = null;
					
					List<BookLendDTO> BookLendList = null;

					try {
						conn = DatabaseUtil.getConnection();
						psmt = conn.prepareStatement(sqlQuery);
						psmt.setString(1, userNo);
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
							bl.setUserName(rs.getString("userName"));
							bl.bookIsbn = rs.getString("bookIsbn");					
							bl.title = rs.getString("bookTitle");

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

	public List<BookLendDTO> selectAdminBookLendDetailByTitle(String title) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate,"
		      		+ " bl.extensionStatus, bl.extensionavailabilitycnt, bl.expectedReturnDate,"
		      		+ " bl.returnStatus, ui.userName, bm.bookIsbn, CASE WHEN length(bi.bookTitle) < 20"
		      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle"
		      		+ " FROM book_lend bl, book_management bm, book_info bi, user_info ui"
		      		+ " WHERE bl.bookNo = bm.bookNo AND bl.userNo = ui.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND bi.bookTitle LIKE '%'||?||'%' ORDER BY lendNo DESC";

					Connection conn = null;
					PreparedStatement psmt = null;
					ResultSet rs = null;
					
					List<BookLendDTO> BookLendList = null;

					try {
						conn = DatabaseUtil.getConnection();
						psmt = conn.prepareStatement(sqlQuery);
						psmt.setString(1, title);
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
							bl.setUserName(rs.getString("userName"));
							bl.bookIsbn = rs.getString("bookIsbn");					
							bl.title = rs.getString("bookTitle");

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

	public List<BookLendDTO> selectAdminBookLendDetailByLendDate(String lendDate) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate,"
	      		+ " bl.extensionStatus, bl.extensionavailabilitycnt, bl.expectedReturnDate,"
	      		+ " bl.returnStatus, ui.userName, bm.bookIsbn, CASE WHEN length(bi.bookTitle) < 20"
	      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle"
	      		+ " FROM book_lend bl, book_management bm, book_info bi, user_info ui"
	      		+ " WHERE bl.bookNo = bm.bookNo AND bl.userNo = ui.userNo AND bm.bookIsbn = bi.bookIsbn"
	      		+ " AND TO_CHAR(bl.lendDate, 'yyyymm') LIKE ? ORDER BY lendNo DESC";

				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				
				List<BookLendDTO> BookLendList = null;

				try {
					conn = DatabaseUtil.getConnection();
					psmt = conn.prepareStatement(sqlQuery);
					psmt.setString(1, lendDate);
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
						bl.setUserName(rs.getString("userName"));
						bl.bookIsbn = rs.getString("bookIsbn");					
						bl.title = rs.getString("bookTitle");

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

	public List<BookLendDTO> selectAdminBookLendDetailByLendDateAndLendNo(String lendDate, String lendNo) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate,"
		      		+ " bl.extensionStatus, bl.extensionavailabilitycnt, bl.expectedReturnDate,"
		      		+ " bl.returnStatus, ui.userName, bm.bookIsbn, CASE WHEN length(bi.bookTitle) < 20"
		      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle"
		      		+ " FROM book_lend bl, book_management bm, book_info bi, user_info ui"
		      		+ " WHERE bl.bookNo = bm.bookNo AND bl.userNo = ui.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND TO_CHAR(bl.lendDate, 'yyyymm') LIKE ? AND bl.lendNo LIKE '%'||?||'%' ORDER BY lendNo DESC";

					Connection conn = null;
					PreparedStatement psmt = null;
					ResultSet rs = null;
					
					List<BookLendDTO> BookLendList = null;

					try {
						conn = DatabaseUtil.getConnection();
						psmt = conn.prepareStatement(sqlQuery);
						psmt.setString(1, lendDate);
						psmt.setString(2, lendNo);
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
							bl.setUserName(rs.getString("userName"));
							bl.bookIsbn = rs.getString("bookIsbn");					
							bl.title = rs.getString("bookTitle");

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

	public List<BookLendDTO> selectAdminBookLendDetailByLendDateAndUserNo(String lendDate, String userNo) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate,"
		      		+ " bl.extensionStatus, bl.extensionavailabilitycnt, bl.expectedReturnDate,"
		      		+ " bl.returnStatus, ui.userName, bm.bookIsbn, CASE WHEN length(bi.bookTitle) < 20"
		      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle"
		      		+ " FROM book_lend bl, book_management bm, book_info bi, user_info ui"
		      		+ " WHERE bl.bookNo = bm.bookNo AND bl.userNo = ui.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND TO_CHAR(bl.lendDate, 'yyyymm') LIKE ? AND bl.userNo LIKE '%'||?||'%' ORDER BY lendNo DESC";

					Connection conn = null;
					PreparedStatement psmt = null;
					ResultSet rs = null;
					
					List<BookLendDTO> BookLendList = null;

					try {
						conn = DatabaseUtil.getConnection();
						psmt = conn.prepareStatement(sqlQuery);
						psmt.setString(1, lendDate);
						psmt.setString(2, userNo);
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
							bl.setUserName(rs.getString("userName"));
							bl.bookIsbn = rs.getString("bookIsbn");					
							bl.title = rs.getString("bookTitle");

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

	public List<BookLendDTO> selectAdminBookLendDetailByLendDateAndTitle(String lendDate, String title) {
	      String sqlQuery = "SELECT bl.lendNo, bl.userNo, bl.bookNo, bl.lendDate,"
		      		+ " bl.extensionStatus, bl.extensionavailabilitycnt, bl.expectedReturnDate,"
		      		+ " bl.returnStatus, ui.userName, bm.bookIsbn, CASE WHEN length(bi.bookTitle) < 20"
		      		+ " THEN bi.bookTitle ELSE SUBSTR(bi.bookTitle, 1, 20)||'...' END bookTitle"
		      		+ " FROM book_lend bl, book_management bm, book_info bi, user_info ui"
		      		+ " WHERE bl.bookNo = bm.bookNo AND bl.userNo = ui.userNo AND bm.bookIsbn = bi.bookIsbn"
		      		+ " AND TO_CHAR(bl.lendDate, 'yyyymm') LIKE ? AND bi.bookTitle LIKE '%'||?||'%' ORDER BY lendNo DESC";

					Connection conn = null;
					PreparedStatement psmt = null;
					ResultSet rs = null;
					
					List<BookLendDTO> BookLendList = null;

					try {
						conn = DatabaseUtil.getConnection();
						psmt = conn.prepareStatement(sqlQuery);
						psmt.setString(1, lendDate);
						psmt.setString(2, title);
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
							bl.setUserName(rs.getString("userName"));
							bl.bookIsbn = rs.getString("bookIsbn");					
							bl.title = rs.getString("bookTitle");

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

	public String selectExpectedReturnDateByBookNo(String bookNo) {
		String sqlQuery = "SELECT TO_CHAR(expectedReturnDate, 'yy/mm/dd') expectedReturnDate FROM book_lend WHERE bookNo = ?";

		String expectedReturnDate = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setString(1, bookNo);

			rs = psmt.executeQuery();

			if (rs.next()) {
				expectedReturnDate = rs.getString("expectedReturnDate");
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
		return expectedReturnDate;
	}
	
	
	
	
public BookLendDTO selectUserLendingData(String userNo, String isbn) {
		String sqlQuery = "SELECT * FROM BOOK_LEND WHERE USERNO = ? AND SUBSTR(BOOKNO, 1, 10) = ?";

		BookLendDTO bookLendDTO = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;


		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setString(1, userNo);
			psmt.setString(2, isbn);

			rs = psmt.executeQuery();

			bookLendDTO = new BookLendDTO();

			if (rs.next()) {

				bookLendDTO.setLendNo(rs.getString("lendNo"));
				bookLendDTO.setUserNo(rs.getString("userNo"));
				bookLendDTO.setBookNo(rs.getString("bookNo"));
				bookLendDTO.setLendDate(rs.getTimestamp("lendDate"));
				bookLendDTO.setExtensionStatus(rs.getString("extensionStatus"));
				bookLendDTO.setExpectedReturnDate(rs.getTimestamp("expectedReturnDate"));
				bookLendDTO.setReturnStatus(rs.getString("returnStatus"));
			}
		} catch (Exception  e) {
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
		return bookLendDTO;
	}

	public String selectBookNo(String userNo, String isbn) {
		String sqlQuery = "SELECT * FROM BOOK_LEND WHERE USERNO = ? AND SUBSTR(BOOKNO, 1, 10) = ?";

		String bookNo = null;

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
				bookNo = rs.getString("bookNo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookNo;
		}
	}
