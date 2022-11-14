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
	      String sqlQuery = "INSERT INTO BOOK_MANAGEMENT VALUES(?||5, ?, ?, ?, ?, ?, ?)";

	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = DatabaseUtil.getConnection();
	         psmt = conn.prepareStatement(sqlQuery);
	         
	         psmt.setString(1, bm.bookIsbn);
	         psmt.setString(2, bm.bookLendingAvailability);
	         psmt.setInt(3, bm.bookLendingStatus);
	         psmt.setString(4, bm.bookReservationAvailability);
	         psmt.setInt(5, bm.bookReservationStatus);
	         psmt.setInt(6, bm.bookLendingCnt);
	         psmt.setString(7, bm.bookIsbn);
	         int resultCnt = psmt.executeUpdate();
	         if(resultCnt>0) {
	            System.out.println("insert �꽦怨�");
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
        				+ "ROWNUM = 1";
        
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
        	 bookManagementDTO.setBookLendingStatus(rs.getInt("bookLendingStatus"));
        	 bookManagementDTO.setBookReservationAvailability(rs.getString("bookReservationAvailability"));
        	 bookManagementDTO.setBookReservationStatus(rs.getInt("bookReservationStatus"));
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
	            int bookLendingStatus = 0;
	            String bookReservationAvailability = "false";
	            int bookReservationStatus = 0;
	            int bookLendingCnt = 0;
	            String isbn = (String) itemObj.get("isbn");
	            
	            BookManagementDAO bookManagementDAO = new BookManagementDAO();
	            BookManagementDTO bookManagementDTO = new BookManagementDTO(bookNo, bookLendingAvailability, bookLendingStatus, bookReservationAvailability, bookReservationStatus, bookLendingCnt, isbn);
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
