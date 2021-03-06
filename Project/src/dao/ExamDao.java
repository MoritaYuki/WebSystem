package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Common;
import model.Exam;

public class ExamDao extends Common {

	// 全テスト結果情報を取得する
	public List<Exam> findAll() {
		Connection con = null;
		//講座情報保管用のリストを準備
		List<Exam> examList = new ArrayList<Exam>();
		try {
			con = DBManager.getConnection();

			String join = "INNER JOIN user AS u "
					+ "ON s.user_id = u.user_id "
					+ "INNER JOIN comment AS c "
					+ "ON s.user_id = c.user_id AND s.grade = c.grade";
			String sql = "SELECT * FROM score AS s " + join;

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

                Exam exam = new Exam(userId, loginId, userNamePhonetic, userName, sex, year, grade, term, japanese, math,
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

	public List<Exam> findByUserId(String sUserId){
		// コネクションを取得
		Connection conn = null;

		//ユーザ情報保管用のリストを準備
		List<Exam> examList = new ArrayList<Exam>();

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String join = "INNER JOIN user AS u "
						+ "ON s.user_id = u.user_id "
						+ "INNER JOIN comment AS c "
						+ "ON s.user_id = c.user_id AND s.grade = c.grade ";
			String sql = "SELECT * FROM score AS s " + join + "WHERE s.user_id = ?";

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, sUserId);
			ResultSet rs = stmt.executeQuery();

			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String loginId = rs.getString("login_id");
				String userNamePhinetic = rs.getString("user_name_phonetic");
				String userName = rs.getString("user_name");
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
				Exam exam = new Exam(userId, loginId, userNamePhinetic, userName, sex, year, grade, term, japanese,
									math, english, science, social, comment, createDate, updateDate);

				// 各教科の得点を配列に格納し、全て0点の場合はexamListに入れない
				int[] scoreList = {japanese,
									math,
									english,
									science,
									social
									};
				if(Exam.scoreCheck(scoreList)) {
					continue;
				}
				examList.add(exam);
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
		return examList;
	}

	// テスト結果を検索
	public List<Exam> search(String sYear) {
		// コネクションを取得
		Connection conn = null;

		//ユーザ情報保管用のリストを準備
		List<Exam> examList = new ArrayList<Exam>();

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String sql = "SELECT * FROM score AS s "
						+ "INNER JOIN user AS u "
						+ "ON s.user_id = u.user_id "
						+ "INNER JOIN comment AS c "
						+ "ON s.user_id = c.user_id AND s.grade = c.grade "
						+ "WHERE year >= ? AND year <= ?";

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入(フォームが空欄の場合はワイルドカードを代入)

			if (strCheck(sYear)) {
				stmt.setString(1, "0");
				stmt.setString(2, "5000");
			} else {
				stmt.setString(1, sYear);
				stmt.setString(2, sYear);
			}

			ResultSet rs = stmt.executeQuery();

			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String loginId = rs.getString("login_id");
				String userNamePhinetic = rs.getString("user_name_phonetic");
				String userName = rs.getString("user_name");
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
				Exam exam = new Exam(userId, loginId, userNamePhinetic, userName, sex, year, grade, term, japanese,
									math, english, science, social, comment, createDate, updateDate);
				examList.add(exam);
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
		return examList;
	}

	public void signup(int uGrade, int userId) {
		//コネクション取得
		Connection conn = null;
		//SELECT文準備
		for (int grade = 1; grade <= 3; grade++) {
			try {
				//DBに接続
				conn = DBManager.getConnection();
				// commentテーブルのレコード作成
				String intoComment = "INTO comment "
									+ "(user_id, grade) "
									+ "VALUES (?, ?)";
				String sqlComment = "INSERT " + intoComment;
				PreparedStatement stmtComment = conn.prepareStatement(sqlComment);
				stmtComment.setInt(1, userId);
				stmtComment.setInt(2, grade);
				stmtComment.executeUpdate();
				// scoreテーブルのレコード作成
				for (int term = 1; term <= 3; term++) {
					String intoScore = "INTO score(user_id, year, grade, term, create_date) "
									+ "VALUES (?, ?, ?, ?, now()) ";
					String sqlScore = "INSERT " + intoScore;
					//ステートメントの準備
					PreparedStatement stmtScore = conn.prepareStatement(sqlScore);
					//それぞれの入力項目を代入
					stmtScore.setInt(1, userId);
					stmtScore.setInt(2, Exam.getYearNow() + (grade - uGrade));
					stmtScore.setInt(3, grade);
					stmtScore.setInt(4, term);
					// 追加したレコードの数を表示
					System.out.println(stmtScore.executeUpdate() + "件の新規テスト結果マスタを登録");
				}
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
	}

	// 入力情報からテスト結果を更新
	public void updateScore(List<Exam> examList) {
		//コネクション取得
		Connection conn = null;

		try {
			//DBに接続
			conn = DBManager.getConnection();
			for(Exam exam: examList) {
				//SELECT文準備
				String set = "SET japanese = ?,"
							+ " math = ?,"
							+ " english = ?,"
							+ " science = ?,"
							+ " social = ? ";
				String where = "WHERE user_id = ? AND year = ? AND grade = ? AND term = ?";

				String sql = "UPDATE score " + set + where;

				//ステートメントの準備
				PreparedStatement stmt = conn.prepareStatement(sql);
				//それぞれの入力項目を代入
				stmt.setInt(1, exam.getJapanese());
				stmt.setInt(2, exam.getMath());
				stmt.setInt(3, exam.getEnglish());
				stmt.setInt(4, exam.getScience());
				stmt.setInt(5, exam.getSocial());
				stmt.setInt(6, exam.getUserId());
				stmt.setInt(7, exam.getYear());
				stmt.setInt(8, exam.getGrade());
				stmt.setInt(9, exam.getTerm());
				// 追加したレコードの数を返す
				stmt.executeUpdate();
				System.out.println("ユーザID：" + exam.getUserId() + "のスコアを更新");
			}
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

	public void examDelete(String userId) {
		// コネクション取得
		Connection conn = null;
		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String sql = "DELETE FROM score WHERE user_id = ?";

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入
			stmt.setString(1, userId);
			System.out.println("テスト結果マスタ削除件数：" + stmt.executeUpdate() + "件");
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

	public void updateComment(String userId, String grade, String comment) {
		//コネクション取得
		Connection conn = null;

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String set = "SET comment = ? ";
			String where = "WHERE user_id = ? AND grade = ?";

			String sql = "UPDATE comment " + set + where;

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入
			stmt.setString(1, comment);
			stmt.setString(2, userId);
			stmt.setString(3, grade);
			// 追加したレコードの数を返す
			stmt.executeUpdate();
			System.out.println("ユーザID：" + userId + "　　　学年：" + grade + "のコメントを更新");
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

	// フォーム入力に不備がないかを判定
	public boolean formCheck(String[] examData) {

		// 入力フォームに空欄があるか判定
		for (String c : examData) {
			if (strCheck(c)) {
				return true;
			}
		}
		// 0点未満、100点より大きい数がないか判定
		for (int i = 0; i < examData.length; i++) {
			if (Integer.parseInt(examData[i]) < 0 || 100 < Integer.parseInt(examData[i])) {
				return true;
			}
		}
		return false;
	}

	// テストのコメントリストを取得する
	public List<String> getCommentList(String sUserId) {
		Connection con = null;
		//講座情報保管用のリストを準備
		List<String> commentList = new ArrayList<String>();
		try {
			con = DBManager.getConnection();
			String sql = "SELECT * FROM comment WHERE user_id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, sUserId);
			ResultSet rs = stmt.executeQuery();

			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
                String comment = rs.getString("comment");
                commentList.add(comment);
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
		return commentList;
	}

//	public void insertScore(Exam exam) {
//	//コネクション取得
//	Connection conn = null;
//
//	try {
//		//DBに接続
//		conn = DBManager.getConnection();
//		//SELECT文準備
//		String column = "(user_id, year, grade, term, japanese, math, english, science, social, comment, create_date)";
//		String value = "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
//
//		String sql = "INSERT INTO exam" + column + value;
//
//		/*
//		 * ・createStatement()は入力した文章をそのままステートメントにするだけだから、最後の
//		 * executeQuery()の引数にsql分を指定する。
//		 * ・preparedStatement()を用いる場合は、最初に？で代入部分を置き換えてから、再度setメソッド
//		 * にて代入する。代入した際にインスタンスstmtには既に代入後のsql分が保存されているため、最後
//		 * のexecuteQuery()で引数を指定する必要はなし
//		 */
//
//		//ステートメントの準備
//		PreparedStatement stmt = conn.prepareStatement(sql);
//
//		//それぞれの入力項目を代入
//		stmt.setInt(1, exam.getUserId());
//		stmt.setInt(1, exam.getYear());
//		stmt.setInt(1, exam.getGrade());
//		stmt.setInt(1, exam.getTerm());
//		stmt.setInt(1, exam.getJapanese());
//		stmt.setInt(1, exam.getMath());
//		stmt.setInt(1, exam.getEnglish());
//		stmt.setInt(1, exam.getScience());
//		stmt.setInt(1, exam.getSocial());
//		stmt.setString(1, exam.getComment());
//
//        // 追加したレコードの数を返す
//        stmt.executeUpdate();
//
//	}catch(SQLException e){
//		e.printStackTrace();
//		return;
//	}finally {
//		// データベース切断
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//                return;
//            }
//		}
//	}
//}
}
