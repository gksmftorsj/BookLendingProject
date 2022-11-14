package userInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bookManagement.BookManagementDTO;
import util.DatabaseUtil;

public class UserInfoDAO {
	
	public String selectUserNo(String userID) {
        String sqlQuery = "SELECT userNo FROM USER_INFO WHERE userId = ?";
        
        String getUserID = null;
        
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        try {
           conn = DatabaseUtil.getConnection();
           psmt = conn.prepareStatement(sqlQuery);
           psmt.setString(1, userID);
           rs = psmt.executeQuery();
           
           if(rs.next()) {
        	 getUserID = rs.getString("userNo");
           }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
        }
        return getUserID;
     }
	
		
	
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
						return 1; // �α��� ����
					} else {
						return 0; // ��й�ȣ Ʋ��
					}
				}
				return -1; // ���̵� ����
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			}
			return -2; // �����ͺ��̽� ����
		}

	public int insertUserInfo(UserInfoDTO ud) {
		String SQL = "INSERT INTO USER_INFO VALUES(to_char(sysdate, 'yyyymmdd')||'URN'||LPAD(URN_SEQ.NEXTVAL, 4, 0), ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, ud.getUserName());
			psmt.setString(2, ud.getUserTel());
			psmt.setString(3, ud.getUserID());
			psmt.setString(4, ud.getUserEmail());
			psmt.setString(5, ud.getUserEmailHash());
			psmt.setString(6, ud.getUserEmailChecked());
			psmt.setString(7, ud.getUserPassword());
			psmt.setString(8, ud.getUserAddress());
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
