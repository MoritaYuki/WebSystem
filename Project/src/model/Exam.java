package model;

//JavaBeans作成条件（java.io.Serializableのインポートと実装）
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Exam implements Serializable{

	//インスタンスフィールド
	private int userId;
	private String loginId;
	private String userNamePhonetic;
	private String userName;
	private String sex;
	private int year;
	private int grade;
	private int term;
	private int japanese;
	private int math;
	private int english;
	private int science;
	private int social;
	private String comment;
	private Date createDate;
	private Date updateDate;

	//JavaBeans作成条件（引数を持たないコンストラクタ）
	public Exam(){
	}

	public Exam(int userId, String loginId, String userNamePhonetic, String userName, String sex, int year, int grade,
			int term, int japanese, int math, int english, int science, int social, String comment, Date createDate,
			Date updateDate) {
		super();
		this.userId = userId;
		this.loginId = loginId;
		this.userNamePhonetic = userNamePhonetic;
		this.userName = userName;
		this.sex = sex;
		this.year = year;
		this.grade = grade;
		this.term = term;
		this.japanese = japanese;
		this.math = math;
		this.english = english;
		this.science = science;
		this.social = social;
		this.comment = comment;
		this.createDate = createDate;
		this.updateDate = updateDate;
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

	public String getUserNamePhonetic() {
		return userNamePhonetic;
	}

	public void setUserNamePhonetic(String userNamePhonetic) {
		this.userNamePhonetic = userNamePhonetic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getJapanese() {
		return japanese;
	}

	public void setJapanese(int japanese) {
		this.japanese = japanese;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getScience() {
		return science;
	}

	public void setScience(int science) {
		this.science = science;
	}

	public int getSocial() {
		return social;
	}

	public void setSocial(int social) {
		this.social = social;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getYearNow() {

		// 年度をてにいれよーーーーーーぜ！！！！
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		return year;
	}

}