package bookLend;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BookLendDTO {
	public String lendNo;
	public String userNo;
	public String bookNo;
	public Timestamp lendDate;
	public String extensionStatus;
	public int extensionAvailabilityCnt;

	public Timestamp expectedReturnDate;
	public String returnStatus;
	
	public String returnNo;
	public Timestamp returnDate;
	
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

	public Timestamp userRegistrationDate;
	public String overDueStatus;
	public int overDueCnt;
	public int currentLendingCnt;
	public int currentReservationCnt;
	public int totalLendingCnt;
	
	private String userName;
	private String userID;
	private String userPassword;
	private String userEmail;
	private String userEmailHash;
	private String userEmailChecked;
	private String userAddress;
	private String userTel;
	
	
	
	public BookLendDTO() {
	}
	
	public BookLendDTO(String lendNo, String userNo, String bookNo, Timestamp lendDate, String extensionStatus,
			int extensionAvailabilityCnt) {
		super();
		this.lendNo = lendNo;
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.lendDate = lendDate;
		this.extensionStatus = extensionStatus;
		this.extensionAvailabilityCnt = extensionAvailabilityCnt;
	}

	
	public BookLendDTO(String lendNo, String userNo, String bookNo, Timestamp lendDate, String extensionStatus,
			int extensionAvailabilityCnt, Timestamp expectedReturnDate, String returnStatus, String returnNo,
			Timestamp returnDate, String bookLendingAvailability, String bookReservationAvailability,
			int bookLendingCnt, String bookIsbn, int rank, String title, String author, String pubDate,
			String description, String isbn, String cover, String categoryName, String publisher, int bookCnt,
			int bookTotalLendingCnt, Timestamp userRegistrationDate, String overDueStatus, int overDueCnt,
			int currentLendingCnt, int currentReservationCnt, int totalLendingCnt, String userName, String userID,
			String userPassword, String userEmail, String userEmailHash, String userEmailChecked, String userAddress,
			String userTel) {
		super();
		this.lendNo = lendNo;
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.lendDate = lendDate;
		this.extensionStatus = extensionStatus;
		this.extensionAvailabilityCnt = extensionAvailabilityCnt;
		this.expectedReturnDate = expectedReturnDate;
		this.returnStatus = returnStatus;
		this.returnNo = returnNo;
		this.returnDate = returnDate;
		this.bookLendingAvailability = bookLendingAvailability;
		this.bookReservationAvailability = bookReservationAvailability;
		this.bookLendingCnt = bookLendingCnt;
		this.bookIsbn = bookIsbn;
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
		this.userRegistrationDate = userRegistrationDate;
		this.overDueStatus = overDueStatus;
		this.overDueCnt = overDueCnt;
		this.currentLendingCnt = currentLendingCnt;
		this.currentReservationCnt = currentReservationCnt;
		this.totalLendingCnt = totalLendingCnt;
		this.userName = userName;
		this.userID = userID;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userEmailHash = userEmailHash;
		this.userEmailChecked = userEmailChecked;
		this.userAddress = userAddress;
		this.userTel = userTel;
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

	public int getExtensionAvailabilityCnt() {
		return extensionAvailabilityCnt;
	}

	public void setExtensionAvailabilityCnt(int extensionAvailabilityCnt) {
		this.extensionAvailabilityCnt = extensionAvailabilityCnt;
	}
	
	public String getReturnNo() {
		return returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	public Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
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

	public String getOverDueStatus() {
		return overDueStatus;
	}

	public void setOverDueStatus(String overDueStatus) {
		this.overDueStatus = overDueStatus;
	}

	public int getOverDueCnt() {
		return overDueCnt;
	}

	public void setOverDueCnt(int overDueCnt) {
		this.overDueCnt = overDueCnt;
	}

	public int getCurrentLendingCnt() {
		return currentLendingCnt;
	}

	public void setCurrentLendingCnt(int currentLendingCnt) {
		this.currentLendingCnt = currentLendingCnt;
	}

	public int getCurrentReservationCnt() {
		return currentReservationCnt;
	}

	public void setCurrentReservationCnt(int currentReservationCnt) {
		this.currentReservationCnt = currentReservationCnt;
	}

	public int getTotalLendingCnt() {
		return totalLendingCnt;
	}

	public void setTotalLendingCnt(int totalLendingCnt) {
		this.totalLendingCnt = totalLendingCnt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmailHash() {
		return userEmailHash;
	}

	public void setUserEmailHash(String userEmailHash) {
		this.userEmailHash = userEmailHash;
	}

	public String getUserEmailChecked() {
		return userEmailChecked;
	}

	public void setUserEmailChecked(String userEmailChecked) {
		this.userEmailChecked = userEmailChecked;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
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

	public Timestamp getUserRegistrationDate() {
		return userRegistrationDate;
	}

	public void setUserRegistrationDate(Timestamp userRegistrationDate) {
		this.userRegistrationDate = userRegistrationDate;
	}

	
	
}
