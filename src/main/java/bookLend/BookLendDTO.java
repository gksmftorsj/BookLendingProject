package bookLend;

import java.time.LocalDateTime;

public class BookLendDTO {
	public String lendNo;
	public String userNo;
	public String bookNo;
	public LocalDateTime lendDate;
	public String extensionStatus; // �뿰�옣�뿬遺� default 媛� false
	public int extensionAvailabilityCnt; // �뿰�옣媛��뒫 �슏�닔 default媛� 1
	
	public BookLendDTO() {
	}
	
	public BookLendDTO(String lendNo, String userNo, String bookNo, LocalDateTime lendDate, String extensionStatus,
			int extensionAvailabilityCnt) {
		super();
		this.lendNo = lendNo;
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.lendDate = lendDate;
		this.extensionStatus = extensionStatus;
		this.extensionAvailabilityCnt = extensionAvailabilityCnt;
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

	public LocalDateTime getLendDate() {
		return lendDate;
	}

	public void setLendDate(LocalDateTime lendDate) {
		this.lendDate = lendDate;
	}

	public String getExtensionStatus() {
		return extensionStatus;
	}

	public void setExtensionStatus(String extensionStatus) {
		this.extensionStatus = extensionStatus;
	}

	public int getExtensionAvailabilityCnt() {
		return extensionAvailabilityCnt;
	}

	public void setExtensionAvailabilityCnt(int extensionAvailabilityCnt) {
		this.extensionAvailabilityCnt = extensionAvailabilityCnt;
	}
	
	
	
}
