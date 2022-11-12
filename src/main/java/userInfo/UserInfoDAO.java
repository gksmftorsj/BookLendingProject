package userInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserInfoDAO {
	
	// 아이디와 비밀번호를 받아서 로그인을 시도해주는 함수 결과는 정수형으로 반환
		public int login(String userID, String userPassword) {
			String SQL = "SELECT userPassword FROM USER_INFO WHERE userID = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DatabaseUtil.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, userID);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString(1).equals(userPassword)) {
						return 1; // 로그인 성공
					} else {
						return 0; // 비밀번호 틀림
					}
				}
				return -1; // 아이디 없음
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			}
			return -2; // 데이터베이스 오류
		}

	public int register(UserInfoDTO user) {
		String SQL = "INSERT INTO USER_INFO VALUES(userNo_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getUserTel());
			psmt.setString(3, user.getUserID());
			psmt.setString(4, user.getUserEmail());
			psmt.setString(5, user.getUserEmailHash());
			psmt.setString(6, user.getUserEmailChecked());
			psmt.setString(7, user.getUserPassword());
			psmt.setString(8, user.getUserAddress());
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	
		public String getUserEmail(String userID) {
			String SQL = "SELECT userEmail FROM USER_INFO WHERE userID = ?";
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(SQL);
				psmt.setString(1, userID);
				rs = psmt.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			}
			return null;
		}

		public String getUserEmailChecked(String userID) {
			String SQL = "SELECT userEmailChecked FROM USER_INFO WHERE userID = ?";
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(SQL);
				psmt.setString(1, userID);
				rs = psmt.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			}
			return "false";
		}
		
		public String setUserEmailChecked(String userID) {
			String SQL = "UPDATE USER_INFO SET userEmailChecked = 'true' WHERE userID = ?";
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(SQL);
				psmt.setString(1, userID);
				psmt.executeUpdate();
				return "true";
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			}
			return "false";
		}
	
}
