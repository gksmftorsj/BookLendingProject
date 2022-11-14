package bookManagement;

public class BookManagementDTO {
	// 대여가능여부가 true일 때 예약가능여부는 false
	public String bookNo;
	public String bookLendingAvailability; // boolean 대여가능여부
	public int bookLendingStatus; // 몇권대여중인지
	public String bookReservationAvailability; // boolean 예약가능여부
	public int bookReservationStatus; // 몇명대여중인지
	public int bookLendingCnt; // 이 책이 총 몇번 대여되었는지
	public String bookIsbn;
	
	public BookManagementDTO() {
	}
	
	public BookManagementDTO(String bookNo, String bookLendingAvailability, int bookLendingStatus,
			String bookReservationAvailability, int bookReservationStatus, int bookLendingCnt, String bookIsbn) {
		super();
		this.bookNo = bookNo;
		this.bookLendingAvailability = bookLendingAvailability;
		this.bookLendingStatus = bookLendingStatus;
		this.bookReservationAvailability = bookReservationAvailability;
		this.bookReservationStatus = bookReservationStatus;
		this.bookLendingCnt = bookLendingCnt;
		this.bookIsbn = bookIsbn;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookLendingAvailability() {
		return bookLendingAvailability;
	}

	public void setBookLendingAvailability(String bookLendingAvailability) {
		this.bookLendingAvailability = bookLendingAvailability;
	}

	public int getBookLendingStatus() {
		return bookLendingStatus;
	}

	public void setBookLendingStatus(int bookLendingStatus) {
		this.bookLendingStatus = bookLendingStatus;
	}

	public String getBookReservationAvailability() {
		return bookReservationAvailability;
	}

	public void setBookReservationAvailability(String bookReservationAvailability) {
		this.bookReservationAvailability = bookReservationAvailability;
	}

	public int getBookReservationStatus() {
		return bookReservationStatus;
	}

	public void setBookReservationStatus(int bookReservationStatus) {
		this.bookReservationStatus = bookReservationStatus;
	}

	public int getBookLendingCnt() {
		return bookLendingCnt;
	}

	public void setBookLendingCnt(int bookLendingCnt) {
		this.bookLendingCnt = bookLendingCnt;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	
}
