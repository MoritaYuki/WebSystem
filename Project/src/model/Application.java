package model;

//JavaBeans作成条件（java.io.Serializableのインポートと実装）
import java.io.Serializable;

public class Application implements Serializable {
	private int applicationNo;
	private int userId;
	private String loginId;
	private int grade;
	private String userName;
	private String appDate;
	private int appAmount;
	private String payDate;
	private int payAmount;
	private int payFg;
	private String[] payFgList = {"未", "済", "過", "不"};

	//JavaBeans作成条件（引数を持たないコンストラクタ）
	public Application(){
	}

	// 	申込登録時のコンストラクタ
	public Application(int userId, int appAmount) {
		super();
		this.userId = userId;
		this.appAmount = appAmount;
	}

	// 申込履歴取得時のコンストラクタ
	public Application(int applicationNo, int userId, String appDate, int appAmount, String payDate, int payAmount, int payFg) {
		super();
		this.applicationNo = applicationNo;
		this.userId = userId;
		this.appDate = appDate;
		this.appAmount = appAmount;
		this.payDate = payDate;
		this.payAmount = payAmount;
		this.payFg = payFg;
	}

	// 申込一覧取得時のコンストラクタ
	public Application(int applicationNo, int userId, String loginId, int grade, String userName, String appDate, int appAmount,
			String payDate, int payAmount, int payFg) {
		super();
		this.applicationNo = applicationNo;
		this.userId = userId;
		this.loginId = loginId;
		this.grade = grade;
		this.userName = userName;
		this.appDate = appDate;
		this.appAmount = appAmount;
		this.payDate = payDate;
		this.payAmount = payAmount;
		this.payFg = payFg;
	}

	public int getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(int applicationNo) {
		this.applicationNo = applicationNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public int getAppAmount() {
		return appAmount;
	}

	public void setAppAmount(int appAmount) {
		this.appAmount = appAmount;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public int getPayFg() {
		return payFg;
	}

	public void setPayFg(int payFg) {
		this.payFg = payFg;
	}

	public String[] getPayFgList() {
		return payFgList;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
