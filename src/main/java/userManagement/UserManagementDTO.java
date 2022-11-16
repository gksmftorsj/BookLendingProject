package userManagement;

public class UserManagementDTO {
	String userNo;
	String overDueStatus;
	int overDueCnt;
	int currentLendingCnt;
	int currentReservationCnt;
	int totalLendingCnt;
	
	private String userName;
	private String userID;
	private String userPassword;
	private String userEmail;
	private String userEmailHash;
	private String userEmailChecked;
	private String userAddress;
	private String userTel;

	
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
	
	public UserManagementDTO(String userName, String userID, String userPassword, String userEmail,
			String userEmailHash, String userEmailChecked, String userAddress, String userTel, String userNo, String overDueStatus, int overDueCnt, int currentLendingCnt,
			int currentReservationCnt, int totalLendingCnt) {
		super();
		this.userNo = userNo;
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
	
	
}
