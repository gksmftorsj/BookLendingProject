package bookLend;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BookLendDTO {

	public String lendNo;
	public String userNo;
	public String bookNo;
	public Timestamp lendDate;
	public String extensionStatus;
	public Timestamp expectedReturnDate;
	public String returnStatus;

	public String bookIsbn;

	public String title;

	private String userName;
	
	public String ldDate;
	public String rtDate;


	public BookLendDTO() {
	}
	
	public BookLendDTO(String lendNo, String userNo, String bookNo, Timestamp lendDate, String extensionStatus) {
		super();
		this.lendNo = lendNo;
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.lendDate = lendDate;
		this.extensionStatus = extensionStatus;
	}

	
	public BookLendDTO(String lendNo, String userNo, String bookNo, Timestamp lendDate, String extensionStatus,
			Timestamp expectedReturnDate, String returnStatus, String bookIsbn,
			String title, String userName, String ldDate, String rtDate) {
		super();
		this.lendNo = lendNo;
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.lendDate = lendDate;
		this.extensionStatus = extensionStatus;
		this.expectedReturnDate = expectedReturnDate;
		this.returnStatus = returnStatus;
		this.bookIsbn = bookIsbn;
		this.title = title;
		this.userName = userName;
		this.ldDate = ldDate;
		this.rtDate = rtDate;
		
	}

	

	public String getLendNo() {
		return lendNo;
	}

	public void setLendNo(String lendNo) {
		this.lendNo = lendNo;
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

	public Timestamp getLendDate() {
		return lendDate;
	}

	public void setLendDate(Timestamp lendDate) {
		this.lendDate = lendDate;
	}

	public String getExtensionStatus() {
		return extensionStatus;
	}

	public void setExtensionStatus(String extensionStatus) {
		this.extensionStatus = extensionStatus;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public Timestamp getExpectedReturnDate() {
		return expectedReturnDate;
	}

	public void setExpectedReturnDate(Timestamp expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}	
	
	
	public String getLdDate() {
		return ldDate;
	}

	public void setLdDate(String ldDate) {
		this.ldDate = ldDate;
	}
	

	public String getRtDate() {
		return rtDate;
	}

	public void setRtDate(String rtDate) {
		this.rtDate = rtDate;
	}

	
}
