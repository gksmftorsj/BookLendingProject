package bookReturn;

import java.sql.Date;
import java.sql.Timestamp;

public class BookReturnDTO {

	public String userNo;
	public String bookNo;
	public String lendNo;
	public String returnNo;
	public Timestamp lendDate;
	public Timestamp expectedReturnDate;
	public Timestamp returnDate;
	
	public BookReturnDTO() {
		
	}

	public BookReturnDTO(String userNo, String bookNo, String lendNo, String returnNo, Timestamp lendDate,
			Timestamp expectedReturnDate, Timestamp returnDate) {
		super();
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.lendNo = lendNo;
		this.returnNo = returnNo;
		this.lendDate = lendDate;
		this.expectedReturnDate = expectedReturnDate;
		this.returnDate = returnDate;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getLendNo() {
		return lendNo;
	}

	public void setLendNo(String lendNo) {
		this.lendNo = lendNo;
	}

	public String getReturnNo() {
		return returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	public Timestamp getLendDate() {
		return lendDate;
	}

	public void setLendDate(Timestamp lendDate) {
		this.lendDate = lendDate;
	}

	public Timestamp getExpectedReturnDate() {
		return expectedReturnDate;
	}

	public void setExpectedReturnDate(Timestamp expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}

	public Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}
	
	

}
