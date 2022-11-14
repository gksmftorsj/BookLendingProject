package userManagement;

public class UserManagementDTO {
	String userNo;
	String overDueStatus;
	int overDueCnt;
	int currentLendingCnt;
	int currentReservationCnt;
	int totalLendingCnt;
	
	public UserManagementDTO() {

	}
	
	public UserManagementDTO(String userNo, String overDueStatus, int overDueCnt, int currentLendingCnt,
			int currentReservationCnt, int totalLendingCnt) {
		super();
		this.userNo = userNo;
		this.overDueStatus = overDueStatus;
		this.overDueCnt = overDueCnt;
		this.currentLendingCnt = currentLendingCnt;
		this.currentReservationCnt = currentReservationCnt;
		this.totalLendingCnt = totalLendingCnt;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
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
	
	
	
	
}
