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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	         int result = psmt.executeUpdate();
	         if(result > 0) {
	            System.out.println("insert 占쎄쉐�⑨옙");
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(psmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	         try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	      }
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
	            String bookReservationAvailability = "false";
	            int bookLendingCnt = 0;
	            String isbn = (String) itemObj.get("isbn");
	            
	            BookManagementDAO bookManagementDAO = new BookManagementDAO();
	            BookManagementDTO bookManagementDTO = new BookManagementDTO(bookNo, bookLendingAvailability, bookReservationAvailability, bookLendingCnt, isbn);
	            bookManagementDAO.insertBookManagement(bookManagementDTO);
	            
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
