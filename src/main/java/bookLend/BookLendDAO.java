package bookLend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public List<BookLendDTO> selectBookLend() {
	      String sqlQuery = "SELECT * FROM BOOK_LEND";
	      
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
	         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	      }
	  return BookLendList;
	}

	public List<BookLendDTO> selectBookLendByLendDate(LocalDateTime localDate) {
	      String sqlQuery = "SELECT * FROM BOOK_LEND WHERE lendDate = ?";

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
	         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	      }
	  return BookLendList;
	}

	public List<BookLendDTO> selectBookLendByUserNo(int userNo) {
	      String sqlQuery = "SELECT * FROM BOOK_LEND WHERE userNo = ?";

	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      
	      List<BookLendDTO> BookLendList = null;
	      
	      try {
	         conn = DatabaseUtil.getConnection();
	         psmt = conn.prepareStatement(sqlQuery);
	         psmt.setInt(1, userNo);
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
	         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	      }
	  return BookLendList;
	}

	public List<BookLendDTO> selectBookLendByLendNo(int lendNo) {
	      String sqlQuery = "SELECT * FROM BOOK_LEND WHERE lendNo = ?";

	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      
	      List<BookLendDTO> BookLendList = null;
	      
	      try {
	         conn = DatabaseUtil.getConnection();
	         psmt = conn.prepareStatement(sqlQuery);
	         psmt.setInt(1, lendNo);
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
	         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	      }
	  return BookLendList;
	}

	public List<BookLendDTO> selectBookLendByBookByTitle(String title) {
	      String sqlQuery = "SELECT * FROM BOOK_LEND "
	      		+ "WHERE bookNo = (SELECT bookNo FROM  BOOK_MANAGEMENT "
	      		+ "WHERE bookIsbn = (SELECT bookIsbn FROM BOOK_INFO WHERE bookTitle = ?));";

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
	         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	      }
	  return BookLendList;
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
	         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
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
	         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	      }
	  return BookLendList;
	}

	public List<BookLendDTO> selectBookLendByLendDateAndTitle(LocalDateTime localDate, String title) {
	      String sqlQuery = "SELECT * FROM BOOK_LEND WHERE (lendDate=?) AND "
		      			+ "(bookNo = (SELECT bookNo FROM  BOOK_MANAGEMENT "
		    	      		+ "WHERE bookIsbn = (SELECT bookIsbn FROM BOOK_INFO WHERE bookTitle = ?)))";

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
	         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	      }
	  return BookLendList;
	}

}
