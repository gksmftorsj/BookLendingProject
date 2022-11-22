package bookReview;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BookReviewDTO {
   public String reviewNo;
   public String userName;
   public String userNo;
   public String bookIsbn;
   public String reviewDate;
   public String reviewContent;
   public int reviewStar;

   public BookReviewDTO() {

   }

   public BookReviewDTO(String reviewNo, String userName, String userNo, String bookIsbn, String reviewDate,
         String reviewContent, int reviewStar) {
      super();
      this.reviewNo = reviewNo;
      this.userName = userName;
      this.userNo = userNo;
      this.bookIsbn = bookIsbn;
      this.reviewDate = reviewDate;
      this.reviewContent = reviewContent;
      this.reviewStar = reviewStar;
   }

   public String getReviewNo() {
      return reviewNo;
   }

   public void setReviewNo(String reviewNo) {
      this.reviewNo = reviewNo;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getUserNo() {
      return userNo;
   }

   public void setUserNo(String userNo) {
      this.userNo = userNo;
   }

   public String getBookIsbn() {
      return bookIsbn;
   }

   public void setBookIsbn(String bookIsbn) {
      this.bookIsbn = bookIsbn;
   }

   public String getReviewDate() {
      return reviewDate;
   }

   public void setReviewDate(String reviewDate) {
      this.reviewDate = reviewDate;
   }

   public String getReviewContent() {
      return reviewContent;
   }

   public void setReviewContent(String reviewContent) {
      this.reviewContent = reviewContent;
   }

   public int getReviewStar() {
      return reviewStar;
   }

   public void setReviewStar(int reviewStar) {
      this.reviewStar = reviewStar;
   }

}
