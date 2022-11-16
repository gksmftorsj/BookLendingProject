package bookReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseUtil;

import bookInfo.BookInfoDTO;
import userInfo.UserInfoDTO;


public class BookReviewDAO {

	   public int insertReview (String userId, String userName, String UserNo, BookReviewDTO brd) {
//		      String sqlQuery = 
//		    		  "INSERT INTO BOOK_REVIEW "
//		    		  + "VALUES((SELECT userno FROM user_info "
//		    		  + "WHERE userID = ?),  ? , SYSDATE, ?) ";
		      String sqlQuery = 
		    		  "INSERT INTO BOOK_REVIEW"
		    		  + "SELECT username, userno, "
		    		  + " ? , SYSDATE, "
		    		  + " ? FROM user_info "
		    		  + "WHERE userId = ?;";
		      
		      Connection conn = null;
		      PreparedStatement psmt = null;
		      ResultSet rs = null;
		      
		      int result = 0;
		      
		      try {
		         conn = DatabaseUtil.getConnection();
		         psmt = conn.prepareStatement(sqlQuery);
		         
		         psmt.setString(1, brd.bookIsbn);
		         psmt.setString(2, brd.reviewContent);
		         psmt.setString(3, userId);
		 
		         result= psmt.executeUpdate();
//		         int resultCnt = psmt.executeUpdate();
//		         if(resultCnt>0) {
//		            System.out.println("insert ����");
//		         }
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		      }
		      return result;
		   }
	   
	   public List<BookReviewDTO> selectReviewList (String bookIsbn) {
		      String sqlQuery = 
		    		  "SELECT * FROM BOOK_REVIEW WHERE bookisbn =  ?";

		      Connection conn = null;
		      PreparedStatement psmt = null;
		      ResultSet rs = null;
		      
		      List<BookReviewDTO> selectReviewList = null;
		      
		      try {
		         conn = DatabaseUtil.getConnection();
		         psmt = conn.prepareStatement(sqlQuery);
		 
		         psmt.setString(1, bookIsbn);
	
		         rs = psmt.executeQuery();
		         
		         selectReviewList = new ArrayList<BookReviewDTO>();
		         
		         while (rs.next()) {
		        	BookReviewDTO brd = new BookReviewDTO();
		        	brd.bookIsbn = rs.getString("bookIsbn");
		        	brd.reviewContent = rs.getString("reviewcontent");
		        			
		        	selectReviewList.add(brd);
		         }
		        
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		      }
		      return selectReviewList;
		   }
}
