package bookReturn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bookLend.BookLendDTO;
import util.DatabaseUtil;

public class BookReturnDAO {

	public int insertBookReturn(BookReturnDTO brd) {
		String sqlQuery = "INSERT INTO BOOK_RETURN "
				+ "VALUES(?, ?, ?, to_char(sysdate, 'yyyymmdd')||'RTN'||LPAD(RTN_SEQ.NEXTVAL, 4, 0), "
				+ "?, ?, sysdate)";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int result = 0;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);

			psmt.setString(1, brd.userNo);
			psmt.setString(2, brd.bookNo);
			psmt.setString(3, brd.lendNo);
			psmt.setTimestamp(4, brd.lendDate);
			psmt.setTimestamp(5, brd.expectedReturnDate);
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

}
