package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Exam;

public class ExamDao extends CommonDao {

	// 全テスト結果情報を取得する
	public List<Exam> findAll() {
		Connection con = null;
		//講座情報保管用のリストを準備
		List<Exam> examList = new ArrayList<Exam>();
		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM score INNER JOIN user ON score.user_id = user.user_id";

			ResultSet rs = con.createStatement().executeQuery(sql);

			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
                int userId = rs.getInt("user_id");
                String loginId = rs.getString("login_id");
                String userName = rs.getString("user_name");
                String userNamePhonetic = rs.getString("user_name_phonetic");
                String sex = rs.getString("sex");
                int year = rs.getInt("year");
                int grade = rs.getInt("grade");
                int term = rs.getInt("term");
                int japanese = rs.getInt("japanese");
                int math = rs.getInt("math");
                int english = rs.getInt("english");
                int science = rs.getInt("science");
                int social = rs.getInt("social");
                String comment = rs.getString("comment");
                Date createDate = rs.getDate("create_date");
                Date updateDate = rs.getDate("update_date");

                Exam exam = new Exam(userId, loginId, userName, userNamePhonetic, sex, year, grade, term, japanese, math,
                					english, science, social, comment, createDate, updateDate);
                examList.add(exam);
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
		return examList;
	}

	public List<Exam> search(String year, String term, String grade) {

		List<Exam> examList = new ArrayList<Exam>() ;
		return examList;

	}


}
