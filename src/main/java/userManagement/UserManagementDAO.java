package userManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bookInfo.BookInfoDTO;
import userInfo.UserInfoDTO;
import util.DatabaseUtil;

public class UserManagementDAO {

	public int insertUserManagement(UserManagementDTO um) {
		String SQL = "INSERT INTO USER_MANAGEMENT "
				+ "VALUES(?, TO_DATE(SUBSTR(?, 1, 8), 'YYYYMMDD'), ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, um.getUserNo());
			psmt.setString(2, um.getUserNo());
			psmt.setString(3, um.getOverDueStatus());
			psmt.setInt(4, um.getOverDueCnt());
			psmt.setInt(5, um.getCurrentLendingCnt());
			psmt.setInt(6, um.getCurrentReservationCnt());
			psmt.setInt(7, um.getCurrentLendingCnt());
			return psmt.executeUpdate();
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
		return -1;
	}

	public int updateUserManagement(String userNo) {
		String sqlQuery = "UPDATE USER_MANAGEMENT "
				+ "SET currentLendingCnt = currentLendingCnt + 1, totalLendingCnt = totalLendingCnt +1 "
				+ "WHERE userNo = ?";

		int result = 0;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setString(1, userNo);
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

	public int selectCurrentLendingCnt(String userNo) {
		String sqlQuery = "SELECT currentLendingCnt FROM USER_MANAGEMENT WHERE userNo = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int currentLendingCnt = 0;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);

			psmt.setString(1, userNo);

			rs = psmt.executeQuery();

			if (rs.next()) {
				currentLendingCnt = rs.getInt("currentLendingCnt");

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
		return currentLendingCnt;
	}
}
