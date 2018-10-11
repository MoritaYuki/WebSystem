package model;

//JavaBeans作成条件（java.io.Serializableのインポートと実装）
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable{

	//インスタンスフィールド
	private int userId;
	private String loginId;
	private String password;
	private int grade;
	private String userNamePhonetic;
	private String userName;
	private String sex;
	private Date birthday;
	private String contactInfo;
	private String address;
	private String createDate;
	private String updateDate;

	//JavaBeans作成条件（引数を持たないコンストラクタ）
	public User(){

	}

	// ログイン用コンストラクタ
	public User(int userId, String loginId, String userName) {
		super();
		this.userId = userId;
		this.loginId = loginId;
		this.userName = userName;
	}

	// 全フィールドに代入するコンストラクタ
	public User(int userId, String loginId, String password, int grade, String userNamePhonetic, String userName,
			String sex, Date birthday, String contactInfo, String address, String createDate, String updateDate) {
		super();
		this.userId = userId;
		this.loginId = loginId;
		this.password = password;
		this.grade = grade;
		this.userNamePhonetic = userNamePhonetic;
		this.userName = userName;
		this.sex = sex;
		this.birthday = birthday;
		this.contactInfo = contactInfo;
		this.address = address;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	// ユーザ情報更新時のコンストラクタ
	public User(int userId, String loginId, String password, int grade, String userNamePhonetic, String userName,
			String sex, Date birthday, String contactInfo, String address) {
		super();
		this.userId = userId;
		this.loginId = loginId;
		this.password = password;
		this.grade = grade;
		this.userNamePhonetic = userNamePhonetic;
		this.userName = userName;
		this.sex = sex;
		this.birthday = birthday;
		this.contactInfo = contactInfo;
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
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

	public Date getBirthday() {
		return birthday;
	}

	public String getBirthdayStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(birthday);
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public static Date getFormatBirthday(String birthday) {
		// SimpleDateFormatでString⇒Dateの際は、String値の形式とSimpleDateFormatの引数の形式を合わせる！！！
		// 更に、jspにて表示する際はDate⇒Stringとして好きな表示形式にフォーマットしなおす必要あり。今回は
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fBirthday = sdf.parse(birthday);
			return fBirthday;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
