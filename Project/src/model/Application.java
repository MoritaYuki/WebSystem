package model;

//JavaBeans作成条件（java.io.Serializableのインポートと実装）
import java.io.Serializable;
import java.util.Date;

public class Application implements Serializable {
	private int applicationNo;
	private int userId;
	private Date appDate;
	private int appAmount;
	private int payAmount;
	private boolean payFg;
	private String[] payFgList = {"未", "済"};

	//JavaBeans作成条件（引数を持たないコンストラクタ）
	public Application(){
	}

	// 	申込登録時のコンストラクタ
	public Application(int userId, int appAmount) {
		super();
		this.userId = userId;
		this.appAmount = appAmount;
	}

	public Application(int applicationNo, int userId, Date appDate, int appAmount, int payAmount, boolean payFg) {
		super();
		this.applicationNo = applicationNo;
		this.userId = userId;
		this.appDate = appDate;
		this.appAmount = appAmount;
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

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
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

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public boolean isPayFg() {
		return payFg;
	}

	public void setPayFg(boolean payFg) {
		this.payFg = payFg;
	}

	public String[] getPayFgList() {
		return payFgList;
	}
}
