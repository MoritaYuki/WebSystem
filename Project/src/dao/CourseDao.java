package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Course;

public class CourseDao extends CommonDao {

	// 全講座情報を取得する
	public List<Course> findAll() {
		Connection con = null;
		//講座情報保管用のリストを準備
		List<Course> courseList = new ArrayList<Course>();
		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM course";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
                int courseId = rs.getInt("course_id");
                int grade = rs.getInt("grade");
                String courseName = rs.getString("course_name");
                String teacher = rs.getString("teacher");
                int term = rs.getInt("term");
                int price = rs.getInt("price");
                String courseDetail = rs.getString("course_detail");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");

                Course course = new Course(courseId, grade, courseName, teacher, term, price, courseDetail, createDate, updateDate);

                courseList.add(course);
            }

		}catch(SQLException e) {
			e.printStackTrace();
            return null;
		}finally{
			// データベース切断
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
		}
		return courseList;
	}

	public int signup(String[] courseData) {
		//コネクション取得
		Connection conn = null;

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String column = "(grade, course_name, teacher, term, price, course_detail, create_date)";
			String value = "VALUE (?, ?, ?, ?, ?, ?, now())";

			String sql = "INSERT INTO course" + column + value;

			/*
			 * ・createStatement()は入力した文章をそのままステートメントにするだけだから、最後の
			 * executeQuery()の引数にsql分を指定する。
			 * ・preparedStatement()を用いる場合は、最初に？で代入部分を置き換えてから、再度setメソッド
			 * にて代入する。代入した際にインスタンスstmtには既に代入後のsql分が保存されているため、最後
			 * のexecuteQuery()で引数を指定する必要はなし
			 */

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入
			for(int i=0; i<courseData.length; i++) {
		        stmt.setString(i+1, courseData[i]);
			}

	        // 追加したレコードの数を返す
	        return stmt.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
	                return 0;
	            }
			}
		}
	}

	public boolean formCheck(String[] courseData) {

		// 同じ講座名(courseData[1]に配置)がないか判定
		List<Course> courseList = findAll();
		for (Course course : courseList) {
			if (!strCheck(courseData[1]) && course.getCourseName().equals(courseData[1])) {
				return true;
			}
		}

		// 入力フォームに空欄があるか(講座名は一意性を調べた際に確認済) 判定
		for (String c : courseData) {
			if (strCheck(c)) {
				return true;
			}
		}
		return false;
	}
}
