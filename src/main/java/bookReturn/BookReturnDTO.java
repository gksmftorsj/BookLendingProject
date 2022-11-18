package bookReturn;

import java.sql.Timestamp;

public class BookReturnDTO {

	public String userNo;
	public String bookNo;
	public String lendNo;
	public String returnNo;
	public String reservationNo;
	public Timestamp lendDate;
	public Timestamp returnDate;
	
	public BookReturnDTO() {
		
	}
	
	public BookReturnDTO(String userNo, String bookNo, String lendNo, String returnNo, String reservationNo,
			Timestamp lendDate, Timestamp returnDate) {
		super();
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.lendNo = lendNo;
		this.returnNo = returnNo;
		this.reservationNo = reservationNo;
		this.lendDate = lendDate;
		this.returnDate = returnDate;
	}
}
