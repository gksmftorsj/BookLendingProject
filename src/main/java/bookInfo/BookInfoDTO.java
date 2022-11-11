package bookInfo;

public class BookInfoDTO {
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
	
	public BookInfoDTO() {
		
	}

	public BookInfoDTO(int rank, String title, String author, String pubDate, String description, String isbn,
			String cover, String categoryName, String publisher, int bookCnt) {
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

	
	
}
