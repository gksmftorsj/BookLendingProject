package userInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bookManagement.BookManagementDTO;
import userManagement.UserManagementDTO;
import util.DatabaseUtil;

public class UserInfoDAO {

	public List<UserInfoDTO> selectAdminUserInfo(){
		
	String sqlQuery = "SELECT * FROM user_info;";
    

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    List<UserInfoDTO> userInfoList = null;

    try {
       conn = DatabaseUtil.getConnection();
       psmt = conn.prepareStatement(sqlQuery);
       rs = psmt.executeQuery();

       userInfoList = new ArrayList<UserInfoDTO>();
       
       while(rs.next()) {
    	   UserInfoDTO ui = new UserInfoDTO();
    	   ui.userNo = rs.getString("userNo");
    	   ui.setUserName(rs.getString("userName"));
    	   ui.setUserID(rs.getString("userId"));
    	   ui.setUserEmail(rs.getString("userEmail"));
    	   ui.setUserTel(rs.getString("userTel"));
    	   ui.setUserAddress(rs.getString("userAddress"));

    	   userInfoList.add(ui);
         }
    } catch (Exception e) {
       e.printStackTrace();
    } finally {
       try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
    }
    return userInfoList;
 }
	
	
	public String selectUserNo(String userID) {
        String sqlQuery = "SELECT userNo FROM USER_INFO WHERE userId = ?";
        
        String getUserNo = null;
        
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        try {
           conn = DatabaseUtil.getConnection();
           psmt = conn.prepareStatement(sqlQuery);
           psmt.setString(1, userID);
           rs = psmt.executeQuery();
           
           if(rs.next()) {
        	  getUserNo = rs.getString("userNo");
           }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
        }
        return getUserNo;
     }
	
	public String selectUserName(String userID) {
        String sqlQuery = "SELECT userName FROM USER_INFO WHERE userId = ?";
        
        String getUserName = null;
        
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        try {
           conn = DatabaseUtil.getConnection();
           psmt = conn.prepareStatement(sqlQuery);
           psmt.setString(1, userID);
           rs = psmt.executeQuery();
           
           if(rs.next()) {
        	 getUserName = rs.getString("userName");
           }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
        }
        return getUserName;
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
						return 1; 
					} else {
						return 0; 
					}
				}
				return -1; 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			}
			return -2; // 占쏙옙占쏙옙占싶븝옙占싱쏙옙 占쏙옙占쏙옙
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
