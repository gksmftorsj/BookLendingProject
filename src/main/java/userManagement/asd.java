package userManagement;

import java.util.List;

import bookReservation.BookReservationDAO;
import bookReservation.BookReservationDTO;

public class asd {

	public static void main(String[] args) {
		BookReservationDAO bookReservationDao = new BookReservationDAO();
		List<BookReservationDTO> bookReservationList = null;
		bookReservationList = bookReservationDao.selectAdminNotLendBookReservationDetailByUserNo("20221120URN0008");
		for(BookReservationDTO bookReservation : bookReservationList){
			System.out.println(bookReservation.getBookNo());
		}
	}

}
