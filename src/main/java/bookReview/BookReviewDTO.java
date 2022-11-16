package bookReview;

import java.sql.Date;

public class BookReviewDTO {
	public String bookIsbn;
	public String reviewContent;
	
	public BookReviewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public BookReviewDTO(String bookIsbn, String reviewContent) {
		super();
		this.bookIsbn = bookIsbn;
		this.reviewContent = reviewContent;
	}
	
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

}

