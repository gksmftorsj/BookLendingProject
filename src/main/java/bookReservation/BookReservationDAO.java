package bookReservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	

}
