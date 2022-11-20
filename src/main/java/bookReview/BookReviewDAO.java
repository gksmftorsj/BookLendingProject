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

// 한 줄 리뷰 (작성)
      public int insertReview (String userName, String userNo, BookReviewDTO brd) {
            String sqlQuery = "INSERT INTO BOOK_REVIEW "
                          + "VALUES(?||'RWN'||RWN_SEQ.NEXTVAl, ?, ?, ?, "
                          + "TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI') , ?, ?)";
                  
            Connection conn = null;
            PreparedStatement psmt = null;
            ResultSet rs = null;
            
            int result = 0;
            
            try {
               conn = DatabaseUtil.getConnection();
               psmt = conn.prepareStatement(sqlQuery);
               
               psmt.setString(1, brd.bookIsbn);
               psmt.setString(2, userName);
               psmt.setString(3, userNo);
               psmt.setString(4, brd.bookIsbn);
               psmt.setString(5, brd.reviewContent);
               psmt.setInt(6, brd.reviewStar);
       
               result= psmt.executeUpdate();

               
            } catch (Exception e) {
               e.printStackTrace();
            } finally {
               try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
               try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
               try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            }
            return result;
         }
      
// 한 줄 리뷰 (화면에 띄우기)      
      public List<BookReviewDTO> selectReviewList (String bookIsbn) {
            String sqlQuery = 
//                  "SELECT reviewNo, userName, userNo, bookIsbn"
//                  + ", reviewDate, reviewContent"
//                  + ", reviewStar FROM BOOK_REVIEW WHERE bookIsbn = ? ORDER BY reviewDate DESC";
                  "SELECT * FROM BOOK_REVIEW "
                  + "WHERE bookisbn =  ?"
                  + " ORDER BY reviewNo DESC";

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
                 // 컬럼 값 가지고 오기
                 brd.reviewContent = rs.getString("reviewContent");
                 brd.userName = rs.getString("userName");
                 brd.userNo = rs.getString("userNo");
                 brd.reviewDate = rs.getString("reviewDate");
                 brd.reviewNo = rs.getString("reviewNo");
                 brd.reviewStar = rs.getInt("reviewStar");
                 
//                 java.sql.String today = (String) new java.util.Date(); 
//                 java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
//                 System.out.println(sdf.format(today));
//                 
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
      
// 한 줄 리뷰 (카운트)      
      public int selectReviewCnt (String bookIsbn) {
            String sqlQuery = "SELECT cnt FROM (SELECT COUNT(*) cnt FROM BOOK_REVIEW WHERE BOOKISBN = ?)";
                  
            Connection conn = null;
            PreparedStatement psmt = null;
            ResultSet rs = null;
            
            int result = 0;
            
            try {
               conn = DatabaseUtil.getConnection();
               psmt = conn.prepareStatement(sqlQuery);
               
               psmt.setString(1, bookIsbn);
               // 찾은 값
               rs = psmt.executeQuery();
               if(rs.next()) {
                 result = rs.getInt("cnt");
               }

               
            } catch (Exception e) {
               e.printStackTrace();
            } finally {
               try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
               try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
               try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            }
            return result;
         }
      
// 한 줄 리뷰 (좋아요 평점)
      public int selectReviewStarAVG (String bookIsbn) {
            String sqlQuery = "SELECT CEIL(AVG(reviewStar)) as avg FROM BOOK_REVIEW WHERE BOOKISBN = ?";
                  
            Connection conn = null;
            PreparedStatement psmt = null;
            ResultSet rs = null;
            
            int result = 0;
            
            try {
               conn = DatabaseUtil.getConnection();
               psmt = conn.prepareStatement(sqlQuery);
               
               psmt.setString(1, bookIsbn);
               // 찾은 값
               rs = psmt.executeQuery();
               if(rs.next()) {
                 result = rs.getInt("avg");
               }

               
            } catch (Exception e) {
               e.printStackTrace();
            } finally {
               try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
               try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
               try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            }
            return result;
         }
      
// 한 줄 리뷰 (날짜 함수)
//      public String selectReviewDate () {
//         String sqlQuery = "SELECT reviewDateMH FROM (SELECT TO_CHAR(REVIEWDATE, 'YYYY-MM-DD HH:MI')reviewDateMH, BOOKISBN, reviewNo FROM BOOK_REVIEW)"
//               + " WHERE reviewNo = ?";
//            
//              Connection conn = null;
//            PreparedStatement psmt = null;
//            ResultSet rs = null;
//            
//            String result = null;
//            
//            try {
//               conn = DatabaseUtil.getConnection();
//               // 실행 준비 단계
//               psmt = conn.prepareStatement(sqlQuery);
//               psmt.setString(1, reviewNo);
//               
//               // 실행 할 수 있도록 만든다.
//               rs = psmt.executeQuery();
//               if(rs.next()) {
//                  result = rs.getString("reviewDateMH");
//               }
//   
//            } catch (Exception e) {
//               e.printStackTrace();
//            } finally {
//               try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
//               try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
//               try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
//            }
//            return result;   // 데이터베이스 오류 
//      }
      
// 한 줄 리뷰 (수정)         
      public BookReviewDTO selectReviewUpdate (String reviewNo) {
         String sqlQuery = "SELECT * FROM BOOK_REVIEW WHERE reviewNo = ?";
        
         Connection conn = null;
         PreparedStatement psmt = null;
         ResultSet rs = null;

           BookReviewDTO brd = null;
         
         
         try {
            conn = DatabaseUtil.getConnection();
            psmt = conn.prepareStatement(sqlQuery);
            
            psmt.setString(1, reviewNo);
            // 찾은 값
            rs = psmt.executeQuery();
            
            if(rs.next()) {
                  brd = new BookReviewDTO();
                 brd.setBookIsbn(rs.getString("bookIsbn"));
                 // 컬럼 값 가지고 오기
                 brd.setReviewContent(rs.getString("reviewContent"));
                 brd.setUserName(rs.getString("userName"));
                 brd.setUserNo(rs.getString("userNo"));
                 brd.setReviewDate(rs.getString("reviewDate"));
                 brd.setReviewNo(rs.getString("reviewNo"));
                 brd.setReviewStar(rs.getInt("reviewStar"));
            }

            
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         }
         return brd;
      }
      
// 제목과 내용으로 바꿔치기 해주겠다. (데이터 베이스의 값을 바꾸거나 추가해주는 성격)
      public int updateReview(BookReviewDTO brd) {
            String sqlQuery = "UPDATE BOOK_REVIEW "
                    + "SET reviewContent = ? ,reviewStar = ?, reviewDate = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI') WHERE reviewNo =? ";
               
         Connection conn = null;
         PreparedStatement psmt = null;
         ResultSet rs = null;
         
         int result = 0;
         
         try {
            conn = DatabaseUtil.getConnection();
            psmt = conn.prepareStatement(sqlQuery);
            
            psmt.setString(1, brd.reviewContent);
            psmt.setInt(2, brd.reviewStar);
            psmt.setString(3, brd.reviewNo);
    
            result= psmt.executeUpdate();
   
            
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         }
         return result; 
      }
      
// 한 줄 리뷰 (삭제)
      public int deleteReview(String userNo, String reivewNo ) {
            String sqlQuery = "DELETE FROM BOOK_REVIEW "
                 + " WHERE userNo = ? AND reviewNo =? ";
         Connection conn = null;
         PreparedStatement psmt = null;
         ResultSet rs = null;
         
         int result = 0;
         
         try {
            conn = DatabaseUtil.getConnection();
            psmt = conn.prepareStatement(sqlQuery);
            
            psmt.setString(1, userNo);
            psmt.setString(2, reivewNo);
    
            result= psmt.executeUpdate();
   
            
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         }
         return result;
      }
}