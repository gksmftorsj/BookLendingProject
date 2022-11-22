package bookManagement;

public class BookManagementDTO {

	public String bookNo;
	public String bookLendingAvailability;
	public String bookReservationAvailability;
	public int bookLendingCnt;
	public String bookIsbn;

	public int rank;
	public String title;
	public String author;
	public String pubDate;
	public String description;
	public String isbn;
	public String cover;
	public String categoryName;
	public String publisher;
	public int bookCnt;
	public int bookTotalLendingCnt;

	public BookManagementDTO() {

	}

	public BookManagementDTO(String bookNo, String bookLendingAvailability,
			String bookReservationAvailability, int bookLendingCnt, String bookIsbn) {
		super();
		this.bookNo = bookNo;
		this.bookLendingAvailability = bookLendingAvailability;
		this.bookReservationAvailability = bookReservationAvailability;
		this.bookLendingCnt = bookLendingCnt;
		this.bookIsbn = bookIsbn;
	}

	public BookManagementDTO(int rank, String title, String author, String pubDate, String description, String isbn,
			String cover, String categoryName, String publisher, int bookCnt, int bookTotalLendingCnt, String bookNo,
			String bookLendingAvailability, String bookReservationAvailability, int bookLendingCnt, String bookIsbn) {
		super();
		this.rank = rank;
		this.title = title;
		this.author = author;
		this.pubDate = pubDate;
		this.description = description;
		this.isbn = isbn;
		this.cover = cover;
		this.categoryName = categoryName;
		this.publisher = publisher;
		this.bookCnt = bookCnt;
		this.bookTotalLendingCnt = bookTotalLendingCnt;
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getBookCnt() {
		return bookCnt;
	}

	public void setBookCnt(int bookCnt) {
		this.bookCnt = bookCnt;
	}

	public int getBookTotalLendingCnt() {
		return bookTotalLendingCnt;
	}

	public void setBookTotalLendingCnt(int bookTotalLendingCnt) {
		this.bookTotalLendingCnt = bookTotalLendingCnt;
	}

}
