package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Common;
import model.Course;

public class CourseDao extends Common {

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

	// 入力情報から講座を検索
	public Course findByCourseId(String sCourseId) {

		// コネクションを取得
		Connection conn = null;

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String sql = "SELECT * FROM course WHERE course_id = ?";

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入(フォームが空欄の場合はワイルドカードを代入)
			stmt.setString(1, sCourseId);

			ResultSet rs = stmt.executeQuery();

			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			if(!rs.next()) {
				return null;
			}

			int courseId = rs.getInt("course_id");
			int grade = rs.getInt("grade");
			String courseName = rs.getString("course_name");
			String teacher = rs.getString("teacher");
			int term = rs.getInt("term");
			int price = rs.getInt("price");
			String courseDetail = rs.getString("course_detail");
			String createDate = rs.getString("create_date");
			String updateDate = rs.getString("update_date");
			Course course = new Course(courseId, grade, courseName, teacher, term, price, courseDetail,
										createDate, updateDate);
			return course;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	// 入力情報から講座一覧を検索
	public List<Course> search(String sGrade, String sCourseName, String sTeacher) {

		// コネクションを取得
		Connection conn = null;

		//講座情報保管用のリストを準備
		List<Course> courseList = new ArrayList<Course>();

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String where = "WHERE (grade LIKE ?)"
					+ "AND (course_name LIKE BINARY ?)"
					+ "AND (teacher LIKE BINARY ?)";

			String sql = "SELECT * FROM course " + where;

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入(フォームが空欄の場合はワイルドカードを代入)

			if (strCheck(sGrade)) {
				stmt.setString(1, "%");
			} else {
				stmt.setString(1, sGrade);
			}

			if (strCheck(sCourseName)) {
				stmt.setString(2, "%");
			} else {
				stmt.setString(2, "%" + sCourseName + "%");
			}

			if (strCheck(sTeacher)) {
				stmt.setString(3, "%");
			} else {
				stmt.setString(3, "%" + sTeacher + "%");
			}

			ResultSet rs = stmt.executeQuery();

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
				Course course = new Course(courseId, grade, courseName, teacher, term, price, courseDetail,
											createDate, updateDate);
				courseList.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return courseList;
	}

	public void signup(String[] courseData) {
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
	        System.out.println("講座マスタ新規登録件数：" + stmt.executeUpdate() + "件");

		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
	            }
			}
		}
	}

	public void courseUpdate(String[] courseData) {
		//コネクション取得
		Connection conn = null;

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String set = "SET grade = ?,"
					+ " course_name = ?,"
					+ " teacher = ?,"
					+ " term = ?,"
					+ " price = ?,"
					+ " course_detail = ? ";
			String where = "WHERE course_id = ?";

			String sql = "UPDATE course " + set + where;

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入
			for(int i=0; i<courseData.length; i++) {
		        stmt.setString(i+1, courseData[i]);
			}
			// 追加したレコードの数を返す
			stmt.executeUpdate();
			System.out.println("講座ID：" + courseData[6] + "の情報を更新");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void courseDelete(String courseId) {
		// コネクション取得
		Connection conn = null;
		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String sql = "DELETE FROM course WHERE course_id = ?";

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入
			stmt.setString(1, courseId);
			System.out.println("講座マスタ削除件数：" + stmt.executeUpdate() + "件");

			return ;

		} catch (SQLException e) {
			e.printStackTrace();
			return ;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return ;
				}
			}
		}
	}

	// 申込番号から申込講座の一覧を取得
	public List<Course> findCourseByApplicationNo(String applicationNo) {
		Connection con = null;
		//講座情報保管用のリストを準備
		List<Course> applicationDetailList = new ArrayList<Course>();
		try {
			con = DBManager.getConnection();
			String where = "WHERE ad.application_no = ?";
			String join = "INNER JOIN course AS c "
						+ "ON ad.course_id = c.course_id ";
			String sql = "SELECT * FROM application_detail AS ad " + join + where;
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, applicationNo);
			ResultSet rs = stmt.executeQuery();
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
				Course course = new Course(courseId, grade, courseName, teacher, term, price, courseDetail,
											createDate, updateDate);
				applicationDetailList.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
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
		return applicationDetailList;
	}

	// ユーザIDから申込講座の一覧を取得
	public ArrayList<Integer> findCourseByUserId(int UserId) {
		Connection con = null;
		//講座情報保管用のリストを準備
		ArrayList<Integer> courseIdList = new ArrayList<Integer>();
		try {
			con = DBManager.getConnection();
			String where = "WHERE a.user_id = ?";
			String join = "INNER JOIN application_detail AS ad "
						+ "ON a.application_no = ad.application_no ";
			String sql = "SELECT (course_id) FROM application AS a " + join + where;

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, UserId);
			ResultSet rs = stmt.executeQuery();
			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
				courseIdList.add(rs.getInt("course_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
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
		return courseIdList;
	}

	public boolean formCheck(String[] courseData, String rootCourseName) {

		// 同じ講座名(courseData[1]に配置)がないか判定
		List<Course> courseList = findAll();
		for (Course course : courseList) {
			String cName = course.getCourseName();
			if (!strCheck(courseData[1]) && cName.equals(courseData[1]) && !(cName.equals(rootCourseName))) {
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
