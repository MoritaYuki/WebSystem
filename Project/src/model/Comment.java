package model;

//JavaBeans作成条件（java.io.Serializableのインポートと実装）
import java.io.Serializable;

public class Comment implements Serializable{

	private int userId;
	private int grade;
	private String comment;

	//JavaBeans作成条件（引数を持たないコンストラクタ）
	public Comment(){
	}

	public Comment(int userId, int grade, String comment) {
		super();
		this.userId = userId;
		this.grade = grade;
		this.comment = comment;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
