package userInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bookLend.BookLendDTO;
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
	
	public String selectUserID(String userNo) {
		String sqlQuery = "SELECT userId FROM USER_INFO WHERE userNo = ?";
		
		String getUserID = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sqlQuery);
			psmt.setString(1, userNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				getUserID = rs.getString("userId");
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
		
		public String getUserId(String userName, String userEmail) {
			String SQL = "SELECT userId FROM USER_INFO WHERE userName = ? AND userEmail = ?";
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(SQL);
				psmt.setString(1, userName);
				psmt.setString(2, userEmail);
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
		
		public String getRamdomPassword(int len) {
			  char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
					'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
					'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
					'U', 'V', 'W', 'X', 'Y', 'Z' };

			  int idx = 0;
			  StringBuffer sb = new StringBuffer();
			  
			  System.out.println("charSet.length :::: "+charSet.length);
			  
			  for (int i = 0; i < len; i++) {
				
				  idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를  Int로 추출 (소숫점제거)
				  System.out.println("idx :::: "+idx);
				  sb.append(charSet[idx]);
			  }

			  return sb.toString();
		}
		
		public int updateRandomPassword(String randomPassword, String userID) {
			String sqlQuery = "UPDATE USER_INFO "
					+ "SET userPassword = ? "
					+ "WHERE userID = ?";

			int result = 0;

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, randomPassword);
				psmt.setString(2, userID);
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
		
		public UserInfoDTO selectUserInfoByUserID(String userID) {
			String sqlQuery = "SELECT * FROM USER_INFO WHERE userId = ?";

			UserInfoDTO userInfoDTO = null;

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			
			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, userID);

				rs = psmt.executeQuery();
				
				userInfoDTO = new UserInfoDTO();

				if (rs.next()) {
					
					userInfoDTO.setUserName(rs.getString("userName"));
					userInfoDTO.setUserPassword(rs.getString("userPassword"));
					userInfoDTO.setUserEmail(rs.getString("userEmail"));
					userInfoDTO.setUserAddress(rs.getString("userAddress"));
					userInfoDTO.setUserTel(rs.getString("userTel"));
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
			return userInfoDTO;
		}
		
		public int updateUserName(String userName, String userID) {
			String sqlQuery = "UPDATE USER_INFO "
					+ "SET userName = ? "
					+ "WHERE userID = ?";

			int result = 0;

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, userName);
				psmt.setString(2, userID);
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
		public int updateUserEmail(String userEmail, String userID) {
			String sqlQuery = "UPDATE USER_INFO "
					+ "SET userEmail = ? "
					+ "WHERE userID = ?";

			int result = 0;

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, userEmail);
				psmt.setString(2, userID);
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
		public int updateUserAddress(String userAddress, String userID) {
			String sqlQuery = "UPDATE USER_INFO "
					+ "SET userAddress = ? "
					+ "WHERE userID = ?";

			int result = 0;

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, userAddress);
				psmt.setString(2, userID);
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
		public int updateUserTel(String userTel, String userID) {
			String sqlQuery = "UPDATE USER_INFO "
					+ "SET userTel = ? "
					+ "WHERE userID = ?";
			
			int result = 0;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, userTel);
				psmt.setString(2, userID);
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
		public int updateUserPassword(String userCurrentPassword, String userModifyPassword, String userID) {
			String sqlQuery = "UPDATE USER_INFO "
					+ "SET userPassword = ? "
					+ "WHERE userPassword = ? AND userID = ?";
			
			int result = 0;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			try {
				conn = DatabaseUtil.getConnection();
				psmt = conn.prepareStatement(sqlQuery);
				psmt.setString(1, userModifyPassword);
				psmt.setString(2, userCurrentPassword);
				psmt.setString(3, userID);
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
