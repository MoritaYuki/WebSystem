package model;

//JavaBeans作成条件（java.io.Serializableのインポートと実装）
import java.io.Serializable;

public class Course implements Serializable{

	//インスタンスフィールド
	private int course_id;
	private int grade;
	private String courseName;
	private String teacher;
	private int term;
	private int price;
	private String courseDetail;
	private String createDate;
	private String updateDate;

	//JavaBeans作成条件（引数を持たないコンストラクタ）
	public Course(){
	}

	public Course(int course_id, int grade, String courseName, String teacher, int term, int price, String courseDetail,
			String createDate, String updateDate) {
		super();
		this.course_id = course_id;
		this.grade = grade;
		this.courseName = courseName;
		this.teacher = teacher;
		this.term = term;
		this.price = price;
		this.courseDetail = courseDetail;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
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

}
