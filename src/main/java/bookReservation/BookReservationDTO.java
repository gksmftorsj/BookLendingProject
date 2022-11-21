package bookReservation;

import java.sql.Timestamp;

public class BookReservationDTO {

	public String userNo;
	public String reservationNo;
	public String bookIsbn;
	public Timestamp reservationDate;
	public String userName;
	public String title;
	public String expectedLendingDate;

	public BookReservationDTO() {

	}
	
	public BookReservationDTO(String userNo, String reservationNo, String bookIsbn, Timestamp reservationDate,
			String userName, String title, String expectedLendingDate) {
		super();
		this.userNo = userNo;
		this.reservationNo = reservationNo;
		this.bookIsbn = bookIsbn;
		this.reservationDate = reservationDate;
		this.userName = userName;
		this.title = title;
		this.expectedLendingDate = expectedLendingDate;
	}
	

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public Timestamp getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Timestamp reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExpectedLendingDate() {
		return expectedLendingDate;
	}

	public void setExpectedLendingDate(String expectedLendingDate) {
		this.expectedLendingDate = expectedLendingDate;
	}

}
