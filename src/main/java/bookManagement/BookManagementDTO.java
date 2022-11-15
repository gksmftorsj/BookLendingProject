package bookManagement;

public class BookManagementDTO {
	// ���뿬媛��뒫�뿬遺�媛� true�씪 �븣 �삁�빟媛��뒫�뿬遺��뒗 false
	public String bookNo;
	public String bookLendingAvailability; // boolean ���뿬媛��뒫�뿬遺�
	public String bookReservationAvailability; // boolean �삁�빟媛��뒫�뿬遺�
	public int bookLendingCnt; // �씠 梨낆씠 珥� 紐뉖쾲 ���뿬�릺�뿀�뒗吏�
	public String bookIsbn;
	
	public BookManagementDTO() {
	}
	
	public BookManagementDTO(String bookNo, String bookLendingAvailability, String bookReservationAvailability,
			int bookLendingCnt, String bookIsbn) {
		super();
		this.bookNo = bookNo;
		this.bookLendingAvailability = bookLendingAvailability;
		this.bookReservationAvailability = bookReservationAvailability;
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

	public String getBookReservationAvailability() {
		return bookReservationAvailability;
	}

	public void setBookReservationAvailability(String bookReservationAvailability) {
		this.bookReservationAvailability = bookReservationAvailability;
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
