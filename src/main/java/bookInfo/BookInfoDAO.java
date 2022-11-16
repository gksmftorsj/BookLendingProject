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
      String sqlQuery = "INSERT INTO BOOK_INFO VALUES(?, ?, ?, ?, ?, ?, ?, SUBSTR(?, 6, INSTR(?, '>', 1, 2)-6), ?, ?, ?)";

      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      try {
         conn = DatabaseUtil.getConnection();
         psmt = conn.prepareStatement(sqlQuery);
         
         psmt.setInt(1, bi.rank);
         psmt.setString(2, bi.title);
         psmt.setString(3, bi.author);
         psmt.setString(4, bi.pubDate);
         psmt.setString(5, bi.description);
         psmt.setString(6, bi.isbn);
         psmt.setString(7, bi.cover);
         psmt.setString(8, bi.categoryName);
         psmt.setString(9, bi.categoryName);
         psmt.setString(10, bi.publisher);
         psmt.setInt(11, bi.bookCnt);
         psmt.setInt(12, bi.bookTotalLendingCnt);
         int resultCnt = psmt.executeUpdate();
         if(resultCnt>0) {
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
   
   public List<BookInfoDTO> selectBookInfo(int startIndex, int endIndex) {
      String sqlQuery = "SELECT * "
      		+ "FROM("
      		+ "		SELECT ROWNUM AS RN, BI.* "
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
   
   public static void main(String[] args) throws java.text.ParseException{
       String book = getBookData();
       JSONParser parser = new JSONParser();
      try {
         JSONObject bookObj = (JSONObject)parser.parse(book);
         JSONArray itemArr = (JSONArray) bookObj.get("item");
         for(int i=0; i<itemArr.size(); i++) {
            JSONObject itemObj = (JSONObject)itemArr.get(i);
            
            int rank = 1;
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
            
            BookInfoDTO bookInfoDTO = new BookInfoDTO(rank, title, author, pubDate, description, isbn, cover, categoryName, publisher, bookCnt, bookTotalLendingCnt);
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