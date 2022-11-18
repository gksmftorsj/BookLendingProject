package userManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import userManagement.UserManagementDTO;
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
	
	
	public List<UserManagementDTO> selectAdminUserManagement(){
		
	String sqlQuery = "SELECT * FROM user_info ui, user_management um WHERE ui.userNo = um.userNo";
    

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    List<UserManagementDTO> userManagementList = null;

    try {
       conn = DatabaseUtil.getConnection();
       psmt = conn.prepareStatement(sqlQuery);
       rs = psmt.executeQuery();

       userManagementList = new ArrayList<UserManagementDTO>();
       
       while(rs.next()) {
    	   UserManagementDTO umd = new UserManagementDTO();
    	   umd.userNo = rs.getString("userNo");
    	   umd.setUserName(rs.getString("userName"));
    	   umd.setUserID(rs.getString("userId"));
    	   umd.setUserEmail(rs.getString("userEmail"));
    	   umd.setUserTel(rs.getString("userTel"));
    	   umd.setUserAddress(rs.getString("userAddress"));
    	   umd.currentLendingCnt = rs.getInt("currentLendingCnt");
    	   umd.totalLendingCnt = rs.getInt("totalLendingCnt");
    	   umd.overDueCnt = rs.getInt("overDueCnt");

    	   userManagementList.add(umd);
         }
    } catch (Exception e) {
       e.printStackTrace();
    } finally {
       try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
    }
    return userManagementList;
 }
	
	public List<UserManagementDTO> selectAdminUserManagementByUserName(String userName){
		
	String sqlQuery = "SELECT * FROM user_info ui, user_management um WHERE ui.userNo = um.userNo AND ui.userName LIKE '%'||?||'%'";

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    List<UserManagementDTO> userManagementList = null;

    try {
       conn = DatabaseUtil.getConnection();
       psmt = conn.prepareStatement(sqlQuery);
       psmt.setString(1, userName);
       rs = psmt.executeQuery();

       userManagementList = new ArrayList<UserManagementDTO>();
       
       while(rs.next()) {
    	   UserManagementDTO umd = new UserManagementDTO();
    	   umd.userNo = rs.getString("userNo");
    	   umd.setUserName(rs.getString("userName"));
    	   umd.setUserID(rs.getString("userId"));
    	   umd.setUserEmail(rs.getString("userEmail"));
    	   umd.setUserTel(rs.getString("userTel"));
    	   umd.setUserAddress(rs.getString("userAddress"));
    	   umd.currentLendingCnt = rs.getInt("currentLendingCnt");
    	   umd.totalLendingCnt = rs.getInt("totalLendingCnt");
    	   umd.overDueCnt = rs.getInt("overDueCnt");

    	   userManagementList.add(umd);
         }
    } catch (Exception e) {
       e.printStackTrace();
    } finally {
       try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
    }
    return userManagementList;
 }
	
	public List<UserManagementDTO> selectAdminUserManagementByUserId(String userId){
		
	String sqlQuery = "SELECT * FROM user_info ui, user_management um WHERE ui.userNo = um.userNo AND ui.userID LIKE '%'||?||'%'";

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    List<UserManagementDTO> userManagementList = null;

    try {
       conn = DatabaseUtil.getConnection();
       psmt = conn.prepareStatement(sqlQuery);
       psmt.setString(1, userId);
       rs = psmt.executeQuery();

       userManagementList = new ArrayList<UserManagementDTO>();
       
       while(rs.next()) {
    	   UserManagementDTO umd = new UserManagementDTO();
    	   umd.userNo = rs.getString("userNo");
    	   umd.setUserName(rs.getString("userName"));
    	   umd.setUserID(rs.getString("userId"));
    	   umd.setUserEmail(rs.getString("userEmail"));
    	   umd.setUserTel(rs.getString("userTel"));
    	   umd.setUserAddress(rs.getString("userAddress"));
    	   umd.currentLendingCnt = rs.getInt("currentLendingCnt");
    	   umd.totalLendingCnt = rs.getInt("totalLendingCnt");
    	   umd.overDueCnt = rs.getInt("overDueCnt");

    	   userManagementList.add(umd);
         }
    } catch (Exception e) {
       e.printStackTrace();
    } finally {
       try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
    }
    return userManagementList;
 }
	
	public List<UserManagementDTO> selectAdminUserManagementByUserNo(String userNo){
		
	String sqlQuery = "SELECT * FROM user_info ui, user_management um WHERE ui.userNo = um.userNo AND ui.userNo LIKE '%'||?||'%'";

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    List<UserManagementDTO> userManagementList = null;

    try {
       conn = DatabaseUtil.getConnection();
       psmt = conn.prepareStatement(sqlQuery);
       psmt.setString(1, userNo);
       rs = psmt.executeQuery();

       userManagementList = new ArrayList<UserManagementDTO>();
       
       while(rs.next()) {
    	   UserManagementDTO umd = new UserManagementDTO();
    	   umd.userNo = rs.getString("userNo");
    	   umd.setUserName(rs.getString("userName"));
    	   umd.setUserID(rs.getString("userId"));
    	   umd.setUserEmail(rs.getString("userEmail"));
    	   umd.setUserTel(rs.getString("userTel"));
    	   umd.setUserAddress(rs.getString("userAddress"));
    	   umd.currentLendingCnt = rs.getInt("currentLendingCnt");
    	   umd.totalLendingCnt = rs.getInt("totalLendingCnt");
    	   umd.overDueCnt = rs.getInt("overDueCnt");

    	   userManagementList.add(umd);
         }
    } catch (Exception e) {
       e.printStackTrace();
    } finally {
       try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
    }
    return userManagementList;
 }
	
	public List<UserManagementDTO> selectAdminUserManagementDetailByUserName(String userName){
		
	String sqlQuery = "SELECT * FROM user_info ui, user_management um WHERE ui.userNo = um.userNo AND ui.userName = ?";

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    List<UserManagementDTO> userManagementList = null;

    try {
       conn = DatabaseUtil.getConnection();
       psmt = conn.prepareStatement(sqlQuery);
       psmt.setString(1, userName);
       rs = psmt.executeQuery();

       userManagementList = new ArrayList<UserManagementDTO>();
       
       while(rs.next()) {
    	   UserManagementDTO umd = new UserManagementDTO();
    	   umd.userNo = rs.getString("userNo");
    	   umd.setUserName(rs.getString("userName"));
    	   umd.setUserID(rs.getString("userId"));
    	   umd.setUserEmail(rs.getString("userEmail"));
    	   umd.setUserTel(rs.getString("userTel"));
    	   umd.setUserAddress(rs.getString("userAddress"));
    	   umd.currentLendingCnt = rs.getInt("currentLendingCnt");
    	   umd.totalLendingCnt = rs.getInt("totalLendingCnt");
    	   umd.overDueCnt = rs.getInt("overDueCnt");

    	   userManagementList.add(umd);
         }
    } catch (Exception e) {
       e.printStackTrace();
    } finally {
       try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
    }
    return userManagementList;
 }
	
	public List<UserManagementDTO> selectAdminUserManagementDetailByUserId(String userId){
		
	String sqlQuery = "SELECT * FROM user_info ui, user_management um WHERE ui.userNo = um.userNo AND ui.userID = ?";

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    List<UserManagementDTO> userManagementList = null;

    try {
       conn = DatabaseUtil.getConnection();
       psmt = conn.prepareStatement(sqlQuery);
       psmt.setString(1, userId);
       rs = psmt.executeQuery();

       userManagementList = new ArrayList<UserManagementDTO>();
       
       while(rs.next()) {
    	   UserManagementDTO umd = new UserManagementDTO();
    	   umd.userNo = rs.getString("userNo");
    	   umd.setUserName(rs.getString("userName"));
    	   umd.setUserID(rs.getString("userId"));
    	   umd.setUserEmail(rs.getString("userEmail"));
    	   umd.setUserTel(rs.getString("userTel"));
    	   umd.setUserAddress(rs.getString("userAddress"));
    	   umd.currentLendingCnt = rs.getInt("currentLendingCnt");
    	   umd.totalLendingCnt = rs.getInt("totalLendingCnt");
    	   umd.overDueCnt = rs.getInt("overDueCnt");

    	   userManagementList.add(umd);
         }
    } catch (Exception e) {
       e.printStackTrace();
    } finally {
       try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
    }
    return userManagementList;
 }
	
	public List<UserManagementDTO> selectAdminUserManagementDetailByUserNo(String userNo){
		
	String sqlQuery = "SELECT * FROM user_info ui, user_management um WHERE ui.userNo = um.userNo AND ui.userNo =?";

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    List<UserManagementDTO> userManagementList = null;

    try {
       conn = DatabaseUtil.getConnection();
       psmt = conn.prepareStatement(sqlQuery);
       psmt.setString(1, userNo);
       rs = psmt.executeQuery();

       userManagementList = new ArrayList<UserManagementDTO>();
       
       while(rs.next()) {
    	   UserManagementDTO umd = new UserManagementDTO();
    	   umd.userNo = rs.getString("userNo");
    	   umd.setUserName(rs.getString("userName"));
    	   umd.setUserID(rs.getString("userId"));
    	   umd.setUserEmail(rs.getString("userEmail"));
    	   umd.setUserTel(rs.getString("userTel"));
    	   umd.setUserAddress(rs.getString("userAddress"));
    	   umd.currentLendingCnt = rs.getInt("currentLendingCnt");
    	   umd.totalLendingCnt = rs.getInt("totalLendingCnt");
    	   umd.overDueCnt = rs.getInt("overDueCnt");

    	   userManagementList.add(umd);
         }
    } catch (Exception e) {
       e.printStackTrace();
    } finally {
       try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
    }
    return userManagementList;
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
	
	public int updateCurrentReservationCntPlus(String userNo) {
		String sqlQuery = "UPDATE USER_MANAGEMENT "
				+ "SET currentReservationCnt = currentReservationCnt + 1 "
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
	
	public int updateCurrentReservationCntMinus(String userNo) {
		String sqlQuery = "UPDATE USER_MANAGEMENT "
				+ "SET currentReservationCnt = currentReservationCnt - 1 "
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
	
	public int selectCurrentReservationCnt(String userNo) {
		String sqlQuery = "SELECT currentReservationCnt FROM USER_MANAGEMENT WHERE userNo = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int currentReservationCnt = 0;

		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);

			psmt.setString(1, userNo);

			rs = psmt.executeQuery();

			if (rs.next()) {
				currentReservationCnt = rs.getInt("currentReservationCnt");

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
		return currentReservationCnt;
	}
}
