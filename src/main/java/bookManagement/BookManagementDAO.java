package bookManagement;

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

import bookInfo.BookInfoDTO;
import util.DatabaseUtil;

public class BookManagementDAO {

   public void insertBookManagement(BookManagementDTO bm) {
      String sqlQuery = "INSERT INTO BOOK_MANAGEMENT VALUES(?||5, ?, ?, ?, ?)";
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;

      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);

         psmt.setString(1, bm.bookIsbn);
         psmt.setString(2, bm.bookLendingAvailability);
         psmt.setString(3, bm.bookReservationAvailability);
         psmt.setInt(4, bm.bookLendingCnt);
         psmt.setString(5, bm.bookIsbn);
         int resultCnt = psmt.executeUpdate();
         if (resultCnt > 0) {
            System.out.println("insert 성공");
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
   }

   public String selectBookReservationTrue(String isbn) {
	      String sqlQuery = "SELECT * "
	            + "FROM BOOK_MANAGEMENT "
	            + "WHERE BOOKISBN = ? AND "
	            + "BOOKRESERVATIONAVAILABILITY = 'true' AND "
	            + "ROWNUM = 1 "
	            + "ORDER BY BOOKNO";

	      String bookNo = null;

	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;

	      try {
	         conn = DatabaseUtil.getConnection();
	         psmt = conn.prepareStatement(sqlQuery);
	         psmt.setString(1, isbn);
	         rs = psmt.executeQuery();
	         System.out.println("여기들어옴");
	         if (rs.next()) {
	            bookNo = rs.getString("bookNo");
	            System.out.println(bookNo);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
            try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	      }
	      return bookNo;
	   }

	public BookManagementDTO selectBookManagementDetail(String isbn) {
        String sqlQuery = "SELECT * "
        				+ "FROM BOOK_MANAGEMENT "
        				+ "WHERE BOOKISBN = ? AND "
        				+ "BOOKLENDINGAVAILABILITY = 'true' AND "
        				+ "ROWNUM = 1 "
        				+ "ORDER BY BOOKNO";
        
        BookManagementDTO bookManagementDTO = null;
        
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        try {
           conn = DatabaseUtil.getConnection();
           psmt = conn.prepareStatement(sqlQuery);
           psmt.setString(1, isbn);
           rs = psmt.executeQuery();
           
           bookManagementDTO = new BookManagementDTO();
           
           if(rs.next()) {
        	 bookManagementDTO.setBookNo(rs.getString("bookNo"));
        	 bookManagementDTO.setBookLendingAvailability(rs.getString("bookLendingAvailability"));
        	 bookManagementDTO.setBookReservationAvailability(rs.getString("bookReservationAvailability"));
        	 bookManagementDTO.setBookLendingCnt(rs.getInt("bookLendingCnt"));
        	 bookManagementDTO.setBookIsbn(isbn);
           }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
        }
        return bookManagementDTO;
     }
	
	public List<BookManagementDTO> selectAdminBookManagementDetailByIsbn(String isbn) {
		String sqlQuery = "SELECT * FROM book_info bi, book_management bm, (SELECT bookIsbn, count(*) bookLendingStatus FROM book_management WHERE bookLendingAvailability = 'false' GROUP BY bookIsbn) bm2 WHERE bi.bookIsbn = bm.bookIsbn AND bm.bookIsbn = bm2.bookIsbn(+) AND bi.bookIsbn LIKE '%'||UPPER(?)||'%'";

        List<BookManagementDTO> bookManagementList = null;
		
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        

        try {
           conn = DatabaseUtil.getConnection();
           psmt = conn.prepareStatement(sqlQuery);
           psmt.setString(1, isbn);
           psmt.setString(1, isbn);
           rs = psmt.executeQuery();

           bookManagementList = new ArrayList<BookManagementDTO>();
           
           while(rs.next()) {
          	 BookManagementDTO bmd = new BookManagementDTO();
               bmd.rank = rs.getInt("bookRank");
               bmd.title = rs.getString("bookTitle");
               bmd.author = rs.getString("bookAuthor");
               bmd.pubDate = rs.getString("bookPubDate");
               bmd.description = rs.getString("bookDescription");
               bmd.isbn = rs.getString("bookIsbn");
               bmd.cover = rs.getString("bookCover");
               bmd.categoryName = rs.getString("bookcategoryName");
               bmd.publisher = rs.getString("bookPublisher");
               bmd.bookCnt = rs.getInt("bookCnt");
               bmd.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");
               bmd.bookNo = rs.getString("bookNo");
          	 	if(rs.getString("bookLendingAvailability").equals("false")) {
          	 		bmd.bookLendingAvailability = "대여중";
          	 	} else {
          	 		bmd.bookLendingAvailability = "대여가능";
          	 	};
          	 	if(rs.getString("bookReservationAvailability").equals("true")) {
          	 		bmd.bookReservationAvailability = "예약가능";
          	 	} else {
          	 		bmd.bookReservationAvailability = "예약중";
          	 	};
              	bmd.bookLendingCnt = rs.getInt("bookLendingStatus");
          	 	
          	 	bookManagementList.add(bmd);
             }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
        }
        return bookManagementList;
     }

	public List<BookManagementDTO> selectAdminBookManagementDetailByTitle(String title) {
		String sqlQuery = "SELECT * FROM book_info bi, book_management bm, (SELECT bookIsbn, count(*) bookLendingStatus FROM book_management WHERE bookLendingAvailability = 'false' GROUP BY bookIsbn) bm2 WHERE bi.bookIsbn = bm.bookIsbn AND bm.bookIsbn = bm2.bookIsbn(+) AND bi.booktitle LIKE '%'||?||'%'";

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
     
        List<BookManagementDTO> bookManagementList = null;

        try {
           conn = DatabaseUtil.getConnection();
           psmt = conn.prepareStatement(sqlQuery);
           psmt.setString(1, title);
           rs = psmt.executeQuery();

           bookManagementList = new ArrayList<BookManagementDTO>();
           
           while(rs.next()) {
          	 BookManagementDTO bmd = new BookManagementDTO();
               bmd.rank = rs.getInt("bookRank");
               bmd.title = rs.getString("bookTitle");
               bmd.author = rs.getString("bookAuthor");
               bmd.pubDate = rs.getString("bookPubDate");
               bmd.description = rs.getString("bookDescription");
               bmd.isbn = rs.getString("bookIsbn");
               bmd.cover = rs.getString("bookCover");
               bmd.categoryName = rs.getString("bookcategoryName");
               bmd.publisher = rs.getString("bookPublisher");
               bmd.bookCnt = rs.getInt("bookCnt");
               bmd.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");
               bmd.bookNo = rs.getString("bookNo");
          	 	if(rs.getString("bookLendingAvailability").equals("false")) {
          	 		bmd.bookLendingAvailability = "대여중";
          	 	} else {
          	 		bmd.bookLendingAvailability = "대여가능";
          	 	};
          	 	if(rs.getString("bookReservationAvailability").equals("true")) {
          	 		bmd.bookReservationAvailability = "예약가능";
          	 	} else {
          	 		bmd.bookReservationAvailability = "예약중";
          	 	};
          	 	bmd.bookLendingCnt = rs.getInt("bookLendingStatus");
          	 	
          	 	bookManagementList.add(bmd);
             }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
        }
        return bookManagementList;
     }

	public List<BookManagementDTO> selectAdminBookManagementDetailByAuthor(String author) {
		String sqlQuery = "SELECT * FROM book_info bi, book_management bm, (SELECT bookIsbn, count(*) bookLendingStatus FROM book_management WHERE bookLendingAvailability = 'false' GROUP BY bookIsbn) bm2 WHERE bi.bookIsbn = bm.bookIsbn AND bm.bookIsbn = bm2.bookIsbn(+) AND bi.bookauthor LIKE '%'||?||'%'";
        

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        List<BookManagementDTO> bookManagementList = null;

        try {
           conn = DatabaseUtil.getConnection();
           psmt = conn.prepareStatement(sqlQuery);
           psmt.setString(1, author);
           rs = psmt.executeQuery();

           bookManagementList = new ArrayList<BookManagementDTO>();
           
           while(rs.next()) {
          	 BookManagementDTO bmd = new BookManagementDTO();
               bmd.rank = rs.getInt("bookRank");
               bmd.title = rs.getString("bookTitle");
               bmd.author = rs.getString("bookAuthor");
               bmd.pubDate = rs.getString("bookPubDate");
               bmd.description = rs.getString("bookDescription");
               bmd.isbn = rs.getString("bookIsbn");
               bmd.cover = rs.getString("bookCover");
               bmd.categoryName = rs.getString("bookcategoryName");
               bmd.publisher = rs.getString("bookPublisher");
               bmd.bookCnt = rs.getInt("bookCnt");
               bmd.bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");
               bmd.bookNo = rs.getString("bookNo");
          	 	if(rs.getString("bookLendingAvailability").equals("false")) {
          	 		bmd.bookLendingAvailability = "대여중";
          	 	} else {
          	 		bmd.bookLendingAvailability = "대여가능";
          	 	};
          	 	if(rs.getString("bookReservationAvailability").equals("true")) {
          	 		bmd.bookReservationAvailability = "예약가능";
          	 	} else {
          	 		bmd.bookReservationAvailability = "예약중";
          	 	};
          	 	bmd.bookLendingCnt = rs.getInt("bookLendingStatus");
          	 	
          	 	bookManagementList.add(bmd);
             }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
        }
        return bookManagementList;
     }
	
	public int selectBookTotalLendingCntByIsbn(String isbn) {
		String sqlQuery = "SELECT sum(bookLendingCnt) bookTotalLendingCnt FROM book_management WHERE bookIsbn LIKE '%'||?||'%' GROUP BY bookIsbn";

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        int bookTotalLendingCnt = 0;

        try {
           conn = DatabaseUtil.getConnection();
           psmt = conn.prepareStatement(sqlQuery);
           psmt.setString(1, isbn);
           rs = psmt.executeQuery();
           
           if(rs.next()) {
          	 bookTotalLendingCnt = rs.getInt("bookTotalLendingCnt");               
             }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
        }
        return bookTotalLendingCnt;
	}
	
	public static void main(String[] args) throws java.text.ParseException{
	       String book = getBookData();
	       JSONParser parser = new JSONParser();
	      try {
	         JSONObject bookObj = (JSONObject)parser.parse(book);
	         JSONArray itemArr = (JSONArray) bookObj.get("item");
	         for(int i=0; i<itemArr.size(); i++) {
	            JSONObject itemObj = (JSONObject)itemArr.get(i);
	            
	            String bookNo = null;
	            String bookLendingAvailability = "true";
	            String bookReservationAvailability = "true";
	            int bookLendingCnt = 0;
	            String isbn = (String) itemObj.get("isbn");
	            
	            BookManagementDAO bookManagementDAO = new BookManagementDAO();
	            BookManagementDTO bookManagementDTO = new BookManagementDTO(bookNo, bookLendingAvailability,
	                  bookReservationAvailability, bookLendingCnt, isbn);
	            bookManagementDAO.insertBookManagement(bookManagementDTO);

	         }
	      } catch (ParseException e) {
	         e.printStackTrace();
	      }
	   }

		public int selectBookLendingCnt(String isbn) {
        String sqlQuery = "SELECT cnt FROM (SELECT COUNT(*) cnt "
                    + "FROM BOOK_MANAGEMENT "
                    + "WHERE BOOKISBN = ? AND "
                    + "BOOKLENDINGAVAILABILITY = 'false')";
        
        int bookLendingCnt = 0;
        
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        try {
           conn = DatabaseUtil.getConnection();
           psmt = conn.prepareStatement(sqlQuery);
           psmt.setString(1, isbn);
           rs = psmt.executeQuery();
           
           if(rs.next()) {
              bookLendingCnt = rs.getInt("cnt");
           }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
           try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
        }
        return bookLendingCnt;
     }
		
		public int updateBookManagementDetail(String bookIsbn) {
		      String sqlQuery = "UPDATE BOOK_MANAGEMENT "
		            + "SET booklendingavailability = 'false', bookReservationAvailability= 'false', bookLendingCnt = bookLendingCnt + 1 "
		            + "WHERE bookIsbn = ? AND "
		            + "bookNo = (SELECT BOOKNO FROM(SELECT * FROM BOOK_MANAGEMENT WHERE bookIsbn = ? AND ROWNUM = 1 AND booklendingavailability = 'true' ORDER BY BOOKNO) BOOK_MANAGEMENT)";
		      
		      int result = 0;
		      
		      Connection conn = null;
		        PreparedStatement psmt = null;
		        ResultSet rs = null;
		      
		        try {
		            conn = DatabaseUtil.getConnection();
		            psmt = conn.prepareStatement(sqlQuery);
		            psmt.setString(1, bookIsbn);
		            psmt.setString(2, bookIsbn);
		            result = psmt.executeUpdate();
		         } catch (Exception e) {
		            e.printStackTrace();
		         } finally {
		            try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		            try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		            try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		         }
		         return result;
		   }
	   
   public List<String> selectBookReservationFalse(String isbn) {
	      String sqlQuery = "SELECT * "
	            + "FROM BOOK_MANAGEMENT "
	            + "WHERE BOOKISBN = ? AND "
	            + "BOOKRESERVATIONAVAILABILITY = 'false' "
	            + "ORDER BY BOOKNO";
	      
	      List<String> falseReservationList = null;

	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;

	      try {
	         conn = DatabaseUtil.getConnection();
	         psmt = conn.prepareStatement(sqlQuery);
	         psmt.setString(1, isbn);
	         rs = psmt.executeQuery();
	         
	         falseReservationList = new ArrayList<String>();
	         
	         while (rs.next()) {
	        	 falseReservationList.add(rs.getString("bookNo"));
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
	      return falseReservationList;
	   }

   public int updateReservationAvailabilityFlase(String bookIsbn) {
	   String sqlQuery = "UPDATE BOOK_MANAGEMENT "
			   + "SET bookReservationAvailability = 'false' "
			   + "WHERE bookIsbn = ? AND "
	           + "bookNo = (SELECT BOOKNO FROM(SELECT * FROM BOOK_MANAGEMENT WHERE bookIsbn = ? AND ROWNUM = 1 AND bookReservationAvailability = 'true' ORDER BY BOOKNO) BOOK_MANAGEMENT)";
	   
	   int result = 0;
	   
	   Connection conn = null;
	   PreparedStatement psmt = null;
	   ResultSet rs = null;
	   
	   try {
		   conn = DatabaseUtil.getConnection();
		   psmt = conn.prepareStatement(sqlQuery);
		   psmt.setString(1, bookIsbn);
		   psmt.setString(2, bookIsbn);
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

   public int updateReservationAvailabilityTrue(String bookNo) {
	   String sqlQuery = "UPDATE BOOK_MANAGEMENT "
			   + "SET bookReservationAvailability = 'true' "
			   + "WHERE bookNo = ?";	
	   
	   int result = 0;
	   
	   Connection conn = null;
	   PreparedStatement psmt = null;
	   ResultSet rs = null;
	   
	   try {
		   conn = DatabaseUtil.getConnection();
		   psmt = conn.prepareStatement(sqlQuery);
		   psmt.setString(1, bookNo);
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

   public static String getBookData() {
      String bookStr = "";
      try {
         StringBuilder urlBuilder = new StringBuilder("http://www.aladin.co.kr/ttb/api/ItemList.aspx");
         urlBuilder.append("?" + URLEncoder.encode("ttbkey", "UTF-8") + "=ttbksh9909131602002");
         urlBuilder
               .append("&" + URLEncoder.encode("QueryType", "UTF-8") + "=" + URLEncoder.encode("Bestseller", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("MaxResults", "UTF-8") + "=" + URLEncoder.encode("50", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("start", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("SearchTarget", "UTF-8") + "=" + URLEncoder.encode("Book", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("output", "UTF-8") + "=" + URLEncoder.encode("js", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("Version", "UTF-8") + "=" + URLEncoder.encode("20131101", "UTF-8"));
         urlBuilder.append("&" + URLEncoder.encode("Cover", "UTF-8") + "=" + URLEncoder.encode("Big", "UTF-8"));
         URL url = new URL(urlBuilder.toString());
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Content-type", "application/json");
         System.out.println("Response code: " + conn.getResponseCode());
         BufferedReader rd;
         if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
      } /* Service Key */ catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return bookStr;
   }
}

