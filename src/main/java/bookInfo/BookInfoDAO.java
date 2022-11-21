package bookInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.DatabaseUtil;

public class BookInfoDAO {

   public void insertBookInfo(BookInfoDTO bi) {
      String sqlQuery = "INSERT INTO BOOK_INFO VALUES(BRK_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, SUBSTR(?, 6, INSTR(?, '>', 1, 2)-6), ?, ?, ?)";

      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;

      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         
         psmt.setString(1, bi.title);
         psmt.setString(2, bi.author);
         psmt.setString(3, bi.pubDate);
         psmt.setString(4, bi.description);
         psmt.setString(5, bi.isbn);
         psmt.setString(6, bi.cover);
         psmt.setString(7, bi.categoryName);
         psmt.setString(8, bi.categoryName);
         psmt.setString(9, bi.publisher);
         psmt.setInt(10, bi.bookCnt);
         psmt.setInt(11, bi.bookTotalLendingCnt);
         int resultCnt = psmt.executeUpdate();
         if(resultCnt>0) {
            System.out.println("insert 성공");
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
      }
   }
   
   public List<BookInfoDTO> selectBookInfoByIndex(int startIndex, int endIndex) {
      String sqlQuery = "SELECT * "
            + "FROM("
            + "      SELECT ROWNUM AS RN, BI.* "
            + "     FROM (SELECT * "
            + "           FROM BOOK_INFO "
            + "           ORDER BY BOOKRANK) BI) "
            + "WHERE RN BETWEEN ? AND ?";

      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      List<BookInfoDTO> BookInfoList = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         
         psmt.setInt(1, startIndex);
         psmt.setInt(2, endIndex);
         
         rs = psmt.executeQuery();
         
         BookInfoList = new ArrayList<BookInfoDTO>();
         
         while (rs.next()) {
            BookInfoDTO bi = new BookInfoDTO();
            bi.rank = rs.getInt("bookRank");
            bi.title = rs.getString("bookTitle");
            bi.author = rs.getString("bookAuthor");
            bi.cover = rs.getString("bookCover");
            bi.categoryName = rs.getString("bookCategoryName");
            bi.isbn = rs.getString("bookIsbn");
            bi.publisher = rs.getString("bookPublisher");
            bi.bookCnt = rs.getInt("bookCnt");

            BookInfoList.add(bi);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
      }
      return BookInfoList;
   }
   
   public List<BookInfoDTO> selectBookInfoSortRank(int startIndex, int endIndex) {
      String sqlQuery = "SELECT * "
            + "FROM("
            + "      SELECT ROWNUM AS RN, BI.* "
            + "     FROM (SELECT * FROM BOOK_INFO ORDER BY BOOKRANK) BI) "
            + "WHERE RN BETWEEN ? AND ?";
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      List<BookInfoDTO> BookInfoList = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         
         psmt.setInt(1, startIndex);
         psmt.setInt(2, endIndex);
         
         rs = psmt.executeQuery();
         
         BookInfoList = new ArrayList<BookInfoDTO>();
         
         while (rs.next()) {
            BookInfoDTO bi = new BookInfoDTO();
            bi.rank = rs.getInt("bookRank");
            bi.title = rs.getString("bookTitle");
            bi.author = rs.getString("bookAuthor");
            bi.cover = rs.getString("bookCover");
            bi.categoryName = rs.getString("bookCategoryName");
            bi.isbn = rs.getString("bookIsbn");
            bi.publisher = rs.getString("bookPublisher");
            bi.bookCnt = rs.getInt("bookCnt");
            
            BookInfoList.add(bi);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
      }
      return BookInfoList;
   }
   
   public List<BookInfoDTO> selectBookInfoSortLatest(int startIndex, int endIndex) {
      String sqlQuery = "SELECT * "
            + "FROM("
            + "      SELECT ROWNUM AS RN, BI.* "
            + "     FROM (SELECT * "
            + "FROM (SELECT BOOKRANK, BOOKTITLE, BOOKAUTHOR, BOOKPUBDATE, BOOKDESCRIPTION, BOOKISBN, BOOKCOVER, BOOKCATEGORYNAME, BOOKPUBLISHER, BOOKCNT, BOOKTOTALLENDINGCNT, TO_DATE(BOOKPUBDATE, 'YYYY-MM-DD') PUBDATE FROM BOOK_INFO) "
            + "ORDER BY PUBDATE DESC) BI) "
            + "WHERE RN BETWEEN ? AND ?";
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      List<BookInfoDTO> BookInfoList = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         
         psmt.setInt(1, startIndex);
         psmt.setInt(2, endIndex);
         
         rs = psmt.executeQuery();
         
         BookInfoList = new ArrayList<BookInfoDTO>();
         
         while (rs.next()) {
            BookInfoDTO bi = new BookInfoDTO();
            bi.rank = rs.getInt("bookRank");
            bi.title = rs.getString("bookTitle");
            bi.author = rs.getString("bookAuthor");
            bi.cover = rs.getString("bookCover");
            bi.categoryName = rs.getString("bookCategoryName");
            bi.isbn = rs.getString("bookIsbn");
            bi.publisher = rs.getString("bookPublisher");
            bi.bookCnt = rs.getInt("bookCnt");
            
            BookInfoList.add(bi);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
      }
      return BookInfoList;
   }
   
   public List<BookInfoDTO> selectBookInfoSortName(int startIndex, int endIndex) {
      String sqlQuery = "SELECT * "
            + "FROM("
            + "      SELECT ROWNUM AS RN, BI.* "
            + "     FROM (SELECT * FROM BOOK_INFO ORDER BY BOOKTITLE) BI) "
            + "WHERE RN BETWEEN ? AND ?";
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      List<BookInfoDTO> BookInfoList = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         
         psmt.setInt(1, startIndex);
         psmt.setInt(2, endIndex);
         
         rs = psmt.executeQuery();
         
         BookInfoList = new ArrayList<BookInfoDTO>();
         
         while (rs.next()) {
            BookInfoDTO bi = new BookInfoDTO();
            bi.rank = rs.getInt("bookRank");
            bi.title = rs.getString("bookTitle");
            bi.author = rs.getString("bookAuthor");
            bi.cover = rs.getString("bookCover");
            bi.categoryName = rs.getString("bookCategoryName");
            bi.isbn = rs.getString("bookIsbn");
            bi.publisher = rs.getString("bookPublisher");
            bi.bookCnt = rs.getInt("bookCnt");
            
            BookInfoList.add(bi);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
      }
      return BookInfoList;
   }
   
   public List<BookInfoDTO> selectBookInfoByCategoryName(int startIndex, int endIndex, String categoryName) {
      String sqlQuery = "SELECT * "
            + "FROM("
            + "      SELECT ROWNUM AS RN, BI.* "
            + "     FROM (SELECT * FROM BOOK_INFO WHERE BOOKCATEGORYNAME LIKE ?) BI) "
            + "WHERE RN BETWEEN ? AND ?";
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      List<BookInfoDTO> BookInfoList = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         
         psmt.setString(1, "%"+categoryName+"%");
         psmt.setInt(2, startIndex);
         psmt.setInt(3, endIndex);
         
         
         rs = psmt.executeQuery();
         
         BookInfoList = new ArrayList<BookInfoDTO>();
         
         while (rs.next()) {
            BookInfoDTO bi = new BookInfoDTO();
            bi.rank = rs.getInt("bookRank");
            bi.title = rs.getString("bookTitle");
            bi.author = rs.getString("bookAuthor");
            bi.cover = rs.getString("bookCover");
            bi.categoryName = rs.getString("bookCategoryName");
            bi.isbn = rs.getString("bookIsbn");
            bi.publisher = rs.getString("bookPublisher");
            bi.bookCnt = rs.getInt("bookCnt");
            
            BookInfoList.add(bi);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
      }
      return BookInfoList;
   }
   
   public List<BookInfoDTO> selectBookInfoBySearch(int startIndex, int endIndex, String search) {
      String sqlQuery = "SELECT * "
            + "FROM("
            + "      SELECT ROWNUM AS RN, BI.* "
            + "     FROM (SELECT * FROM BOOK_INFO WHERE BOOKTITLE LIKE ?) BI) "
            + "WHERE RN BETWEEN ? AND ?";
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      List<BookInfoDTO> BookInfoList = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         
         psmt.setString(1, "%"+search+"%");
         psmt.setInt(2, startIndex);
         psmt.setInt(3, endIndex);
         
         
         rs = psmt.executeQuery();
         
         BookInfoList = new ArrayList<BookInfoDTO>();
         
         while (rs.next()) {
            BookInfoDTO bi = new BookInfoDTO();
            bi.rank = rs.getInt("bookRank");
            bi.title = rs.getString("bookTitle");
            bi.author = rs.getString("bookAuthor");
            bi.cover = rs.getString("bookCover");
            bi.categoryName = rs.getString("bookCategoryName");
            bi.isbn = rs.getString("bookIsbn");
            bi.publisher = rs.getString("bookPublisher");
            bi.bookCnt = rs.getInt("bookCnt");
            
            BookInfoList.add(bi);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
      }
      return BookInfoList;
   }

   public List<BookInfoDTO> selectAdminBookInfo(){
      String sqlQuery = "SELECT * FROM book_info bi, (SELECT bookIsbn, count(*) bookLendingStatus FROM book_management WHERE bookLendingAvailability = 'false' GROUP BY bookIsbn) bm WHERE bi.bookIsbn = bm.bookIsbn(+)";

       List<BookInfoDTO> bookInfoList = null;
       
       Connection conn = null;
       PreparedStatement psmt = null;
       ResultSet rs = null;
       
       try {
          conn = DatabaseUtil.getConnection();
          psmt = conn.prepareStatement(sqlQuery);
          rs = psmt.executeQuery();
          
          bookInfoList = new ArrayList<BookInfoDTO>();
          
          while(rs.next()) {
             BookInfoDTO bi = new BookInfoDTO();
             bi.rank = rs.getInt("bookRank");
             bi.title = rs.getString("bookTitle");
             bi.author = rs.getString("bookAuthor");
             bi.pubDate = rs.getString("bookPubDate");
             bi.description = rs.getString("bookDescription");
             bi.isbn = rs.getString("bookIsbn");
             bi.cover = rs.getString("bookCover");
             bi.categoryName = rs.getString("bookcategoryName");
             bi.publisher = rs.getString("bookPublisher");
             bi.bookCnt = rs.getInt("bookCnt");
             bi.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");
             bi.bookLendingCnt = rs.getInt("bookLendingStatus");
             
             bookInfoList.add(bi);
          }
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
          try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
          try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       }
       return bookInfoList;
    }

   public List<BookInfoDTO> selectBookInfoByTitle(String title) {
         String sqlQuery = "SELECT * FROM book_info bi, (SELECT bookIsbn, count(*) bookLendingStatus FROM book_management WHERE bookLendingAvailability = 'false' GROUP BY bookIsbn) bm WHERE bi.bookIsbn = bm.bookIsbn(+) AND bi.BOOKTITLE like '%'||?||'%'";
         
         List<BookInfoDTO> bookInfoList = null;
         
         Connection conn = null;
         PreparedStatement psmt = null;
         ResultSet rs = null;
         
         try {
            conn = DatabaseUtil.getConnection();
            psmt = conn.prepareStatement(sqlQuery);
            psmt.setString(1, title);
            rs = psmt.executeQuery();
            
            bookInfoList = new ArrayList<BookInfoDTO>();
            
            while(rs.next()) {
               BookInfoDTO bi = new BookInfoDTO();
               bi.rank = rs.getInt("bookRank");
               bi.title = rs.getString("bookTitle");
               bi.author = rs.getString("bookAuthor");
               bi.pubDate = rs.getString("bookPubDate");
               bi.description = rs.getString("bookDescription");
               bi.isbn = rs.getString("bookIsbn");
               bi.cover = rs.getString("bookCover");
               bi.categoryName = rs.getString("bookcategoryName");
               bi.publisher = rs.getString("bookPublisher");
               bi.bookCnt = rs.getInt("bookCnt");
               bi.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");
               bi.bookLendingCnt = rs.getInt("bookLendingStatus");
              
               bookInfoList.add(bi);
            }
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         }
         return bookInfoList;
      }

   public List<BookInfoDTO> selectBookInfoByAuthor(String author) {
         String sqlQuery = "SELECT * FROM book_info bi, (SELECT bookIsbn, count(*) bookLendingStatus FROM book_management WHERE bookLendingAvailability = 'false' GROUP BY bookIsbn) bm WHERE bi.bookIsbn = bm.bookIsbn(+) AND bi.BOOKAUTHOR like '%'||?||'%'";
         
         List<BookInfoDTO> bookInfoList = null;
         
         Connection conn = null;
         PreparedStatement psmt = null;
         ResultSet rs = null;
         
         try {
            conn = DatabaseUtil.getConnection();
            psmt = conn.prepareStatement(sqlQuery);
            psmt.setString(1, author);
            rs = psmt.executeQuery();
            
            bookInfoList = new ArrayList<BookInfoDTO>();
            
            while(rs.next()) {
               BookInfoDTO bi = new BookInfoDTO();
               bi.rank = rs.getInt("bookRank");
               bi.title = rs.getString("bookTitle");
               bi.author = rs.getString("bookAuthor");
               bi.pubDate = rs.getString("bookPubDate");
               bi.description = rs.getString("bookDescription");
               bi.isbn = rs.getString("bookIsbn");
               bi.cover = rs.getString("bookCover");
               bi.categoryName = rs.getString("bookcategoryName");
               bi.publisher = rs.getString("bookPublisher");
               bi.bookCnt = rs.getInt("bookCnt");
               bi.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");
                 bi.bookLendingCnt = rs.getInt("bookLendingStatus");
              
               bookInfoList.add(bi);
            }
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         }
         return bookInfoList;
      }

   public List<BookInfoDTO> selectBookInfoByIsbn(String isbn) {
       String sqlQuery = "SELECT * FROM book_info bi, (SELECT bookIsbn, count(*) bookLendingStatus FROM book_management WHERE bookLendingAvailability = 'false' GROUP BY bookIsbn) bm WHERE bi.bookIsbn = bm.bookIsbn(+) AND bi.bookisbn LIKE '%'||UPPER(?)||'%'";
       
       List<BookInfoDTO> bookInfoList = null;
       
       Connection conn = null;
       PreparedStatement psmt = null;
       ResultSet rs = null;
       
       try {
          conn = DatabaseUtil.getConnection();
          psmt = conn.prepareStatement(sqlQuery);
          psmt.setString(1, isbn);
          rs = psmt.executeQuery();
          
          bookInfoList = new ArrayList<BookInfoDTO>();
          
          while(rs.next()) {
             BookInfoDTO bi = new BookInfoDTO();
             bi.rank = rs.getInt("bookRank");
             bi.title = rs.getString("bookTitle");
             bi.author = rs.getString("bookAuthor");
             bi.pubDate = rs.getString("bookPubDate");
             bi.description = rs.getString("bookDescription");
             bi.isbn = rs.getString("bookIsbn");
             bi.cover = rs.getString("bookCover");
             bi.categoryName = rs.getString("bookcategoryName");
             bi.publisher = rs.getString("bookPublisher");
             bi.bookCnt = rs.getInt("bookCnt");
             bi.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");
             bi.bookLendingCnt = rs.getInt("bookLendingStatus");
            
             bookInfoList.add(bi);
          }
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
          try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
          try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       }
       return bookInfoList;
    }

   public List<BookInfoDTO> selectAdminBookInfoDetail() {
       String sqlQuery = "SELECT * FROM book_info bi, (SELECT bookIsbn, count(*) bookLendingStatus FROM book_management WHERE bookLendingAvailability = 'false' GROUP BY bookIsbn) bm WHERE bi.bookIsbn = bm.bookIsbn(+)";
       
       List<BookInfoDTO> bookInfoList = null;
       
       Connection conn = null;
       PreparedStatement psmt = null;
       ResultSet rs = null;
       
       try {
          conn = DatabaseUtil.getConnection();
          psmt = conn.prepareStatement(sqlQuery);

          rs = psmt.executeQuery();
          
          bookInfoList = new ArrayList<BookInfoDTO>();
          
          if(rs.next()) {
            BookInfoDTO bookInfoDTO = new BookInfoDTO();
            bookInfoDTO.rank = rs.getInt("bookRank");
            bookInfoDTO.title = rs.getString("bookTitle");
            bookInfoDTO.author = rs.getString("bookAuthor");
            bookInfoDTO.pubDate = rs.getString("bookPubDate");
            bookInfoDTO.description = rs.getString("bookDescription");
            bookInfoDTO.isbn = rs.getString("bookIsbn");
            bookInfoDTO.cover = rs.getString("bookCover");
            bookInfoDTO.categoryName = rs.getString("bookcategoryName");
            bookInfoDTO.publisher = rs.getString("bookPublisher");
            bookInfoDTO.bookCnt = rs.getInt("bookCnt");
            bookInfoDTO.bookNo = rs.getString("bookNo");
              if(rs.getString("bookLendingAvailability").equals("false")) {
                 bookInfoDTO.bookLendingAvailability = "대여중";
              } else {
                 bookInfoDTO.bookLendingAvailability = "정상";
              };
              if(rs.getString("bookReservationAvailability").equals("true")) {
                 bookInfoDTO.bookReservationAvailability = "예약가능";
              } else {
                 bookInfoDTO.bookReservationAvailability = "";
              };
              bookInfoDTO.bookLendingCnt = rs.getInt("bookLendingCnt");
              
              bookInfoList.add(bookInfoDTO);
          }
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
          try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
          try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       }
       return bookInfoList;
    }

   public List<BookInfoDTO> selectAdminBookInfoDetailByIsbn(String isbn) {
       String sqlQuery = "SELECT * FROM book_info bi, book_MANAGEMENT bm WHERE bi.bookisbn = bm.bookisbn AND bi.bookisbn LIKE '%'||UPPER(?)||'%'";
       
       List<BookInfoDTO> bookInfoList = null;
       
       Connection conn = null;
       PreparedStatement psmt = null;
       ResultSet rs = null;
       
       try {
          conn = DatabaseUtil.getConnection();
          psmt = conn.prepareStatement(sqlQuery);
          psmt.setString(1, isbn);
          rs = psmt.executeQuery();
          
          bookInfoList = new ArrayList<BookInfoDTO>();
          
          while(rs.next()) {
            BookInfoDTO bookInfoDTO = new BookInfoDTO();
            bookInfoDTO.rank = rs.getInt("bookRank");
            bookInfoDTO.title = rs.getString("bookTitle");
            bookInfoDTO.author = rs.getString("bookAuthor");
            bookInfoDTO.pubDate = rs.getString("bookPubDate");
            bookInfoDTO.description = rs.getString("bookDescription");
            bookInfoDTO.isbn = rs.getString("bookIsbn");
            bookInfoDTO.cover = rs.getString("bookCover");
            bookInfoDTO.categoryName = rs.getString("bookcategoryName");
            bookInfoDTO.publisher = rs.getString("bookPublisher");
            bookInfoDTO.bookCnt = rs.getInt("bookCnt");
            bookInfoDTO.bookNo = rs.getString("bookNo");
              if(rs.getString("bookLendingAvailability").equals("false")) {
                 bookInfoDTO.bookLendingAvailability = "대여중";
              } else {
                 bookInfoDTO.bookLendingAvailability = "정상";
              };
              if(rs.getString("bookReservationAvailability").equals("true")) {
                 bookInfoDTO.bookReservationAvailability = "예약가능";
              } else {
                 bookInfoDTO.bookReservationAvailability = "";
              };
              bookInfoDTO.bookLendingCnt = rs.getInt("bookLendingCnt");
              
              bookInfoList.add(bookInfoDTO);
          }
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
          try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
          try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
       }
       return bookInfoList;
    }


public BookInfoDTO selectBookDetail(String title) {
         String sqlQuery = "SELECT * FROM BOOK_INFO WHERE BOOKTITLE = ?";
         
         BookInfoDTO bookInfoDTO = null;
         
         Connection conn = null;
         PreparedStatement psmt = null;
         ResultSet rs = null;
         
         try {
            conn = DatabaseUtil.getConnection();
            psmt = conn.prepareStatement(sqlQuery);
            psmt.setString(1, title);
            rs = psmt.executeQuery();
            
            bookInfoDTO = new BookInfoDTO();
            
            if(rs.next()) {
            
              bookInfoDTO.setRank(rs.getInt("bookRank"));
              bookInfoDTO.setTitle(rs.getString("bookTitle"));
              bookInfoDTO.setAuthor(rs.getString("bookAuthor"));
              bookInfoDTO.setPubDate(rs.getString("bookPubDate"));
              bookInfoDTO.setDescription(rs.getString("bookDescription"));
              bookInfoDTO.setIsbn(rs.getString("bookIsbn"));
              bookInfoDTO.setCover(rs.getString("bookCover"));
              bookInfoDTO.setCategoryName(rs.getString("bookcategoryName"));
              bookInfoDTO.setPublisher(rs.getString("bookPublisher"));
              bookInfoDTO.setBookCnt(rs.getInt("bookCnt"));
              
            }
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
            try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         }
         return bookInfoDTO;
      }

   public int selectBookTotal() {
      String sqlQuery = "SELECT count(*) total FROM BOOK_INFO";
      
      int total = 0;
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         rs = psmt.executeQuery();
         
         if(rs.next()) {
            total = rs.getInt("total");
            
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
      }
      return total;
   }
   
   public int selectBookCategoryTotal(String category) {
      String sqlQuery = "SELECT count(*) total FROM BOOK_INFO WHERE bookCategoryName LIKE ?";
      
      int total = 0;
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         psmt.setString(1, "%" + category + "%");
         rs = psmt.executeQuery();
         
         if(rs.next()) {
            total = rs.getInt("total");
            
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
      }
      return total;
   }
   
   public int selectBookSearchTotal(String search) {
      String sqlQuery = "SELECT count(*) total FROM BOOK_INFO WHERE bookTitle LIKE ?";
      
      int total = 0;
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         psmt.setString(1, "%" + search + "%");
         rs = psmt.executeQuery();
         
         if(rs.next()) {
            total = rs.getInt("total");
            
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
      }
      return total;
   }

   
   public int updateBookTotalLendingCnt(String bookIsbn) {
      String sqlQuery = "UPDATE BOOK_INFO "
            + "SET bookTotalLendingCnt = bookTotalLendingCnt + 1 "
            + "WHERE bookIsbn = ?";
      
      int result = 0;
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         psmt.setString(1, bookIsbn);
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
   
   public static void main(String[] args) throws java.text.ParseException{
       String book = getBookData();
       JSONParser parser = new JSONParser();
      try {
         JSONObject bookObj = (JSONObject)parser.parse(book);
         JSONArray itemArr = (JSONArray) bookObj.get("item");
         for(int i=0; i<itemArr.size(); i++) {
            JSONObject itemObj = (JSONObject)itemArr.get(i);
            
            String title = (String) itemObj.get("title");
            String author = (String) itemObj.get("author");
            String pubDate = (String) itemObj.get("pubDate");
            String description = (String) itemObj.get("description");
            String isbn = (String) itemObj.get("isbn");
            String cover = (String) itemObj.get("cover");
            String categoryName = (String) itemObj.get("categoryName");
            String publisher = (String) itemObj.get("publisher");
            int bookCnt = 5;
            int bookTotalLendingCnt = 0;
            
            BookInfoDTO bookInfoDTO = new BookInfoDTO(title, author, pubDate, description, isbn, cover, categoryName, publisher, bookCnt, bookTotalLendingCnt);
            BookInfoDAO bookInfoDAO = new BookInfoDAO();
            bookInfoDAO.insertBookInfo(bookInfoDTO);
            
         }
      } catch (ParseException e) {
         e.printStackTrace();
      }
    }
   
   public static String getBookData() {
       String bookStr = "";
        try {
           StringBuilder urlBuilder = new StringBuilder("http://www.aladin.co.kr/ttb/api/ItemList.aspx"); 
            urlBuilder.append("?" + URLEncoder.encode("ttbkey","UTF-8") + "=ttbksh9909131602002"); 
            urlBuilder.append("&" + URLEncoder.encode("QueryType","UTF-8") + "=" + URLEncoder.encode("Bestseller", "UTF-8")); 
            urlBuilder.append("&" + URLEncoder.encode("MaxResults","UTF-8") + "=" + URLEncoder.encode("50", "UTF-8")); 
            urlBuilder.append("&" + URLEncoder.encode("start","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); 
            urlBuilder.append("&" + URLEncoder.encode("SearchTarget","UTF-8") + "=" + URLEncoder.encode("Book", "UTF-8")); 
            urlBuilder.append("&" + URLEncoder.encode("output","UTF-8") + "=" + URLEncoder.encode("js", "UTF-8")); 
            urlBuilder.append("&" + URLEncoder.encode("Version","UTF-8") + "=" + URLEncoder.encode("20131101", "UTF-8")); 
            urlBuilder.append("&" + URLEncoder.encode("Cover","UTF-8") + "=" + URLEncoder.encode("Big", "UTF-8")); 
            URL url = new URL(urlBuilder.toString());
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           conn.setRequestProperty("Content-type", "application/json");
           System.out.println("Response code: " + conn.getResponseCode());
           BufferedReader rd;
           if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
               rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           } else {
               rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
           }
           StringBuilder sb = new StringBuilder();
           String line;
           while ((line = rd.readLine()) != null) {
               sb.append(line);
           }
           rd.close();
           conn.disconnect();
           bookStr = sb.toString();
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } /*Service Key*/ catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
        return bookStr;
   }
}