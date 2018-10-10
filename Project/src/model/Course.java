package model;

//JavaBeans作成条件（java.io.Serializableのインポートと実装）
import java.io.Serializable;

public class Course implements Serializable{

	//インスタンスフィールド
	private int courseId;
	private int grade;
	private String courseName;
	private String teacher;
	private int term;
	private int price;
	private String courseDetail;
	private String createDate;
	private String updateDate;
	private String[] sCourseList = {"春期","夏期","冬期"};

	//JavaBeans作成条件（引数を持たないコンストラクタ）
	public Course(){
	}

	public Course(int courseId, int grade, String courseName, String teacher, int term, int price, String courseDetail,
			String createDate, String updateDate) {
		super();
		this.courseId = courseId;
		this.grade = grade;
		this.courseName = courseName;
		this.teacher = teacher;
		this.term = term;
		this.price = price;
		this.courseDetail = courseDetail;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	// 講座情報更新時のコンストラクタ
	public Course(int courseId, int grade, String courseName, String teacher, int term, int price, String courseDetail) {
		super();
		this.courseId = courseId;
		this.grade = grade;
		this.courseName = courseName;
		this.teacher = teacher;
		this.term = term;
		this.price = price;
		this.courseDetail = courseDetail;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public String getCourseDetail() {
		return courseDetail;
	}

	public void setCourseDetail(String courseDetail) {
		this.courseDetail = courseDetail;
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

	public String[] getsCourseList() {
		return sCourseList;
	}

}
