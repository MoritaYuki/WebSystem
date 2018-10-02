package model;

//JavaBeans作成条件（java.io.Serializableのインポートと実装）
import java.io.Serializable;

public class ApplicationDetail implements Serializable {
	private int applicationNo;
	private int courseId;


	//JavaBeans作成条件（引数を持たないコンストラクタ）
	public ApplicationDetail(){
	}


	public ApplicationDetail(int applicationNo, int courseId) {
		super();
		this.applicationNo = applicationNo;
		this.courseId = courseId;
	}


	public int getApplicationNo() {
		return applicationNo;
	}


	public int getCourseId() {
		return courseId;
	}
}