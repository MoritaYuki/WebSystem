package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Application;
import model.Common;

public class ApplicationDao extends Common{

	// 全申込情報の一覧を取得する
	public List<Application> findAllApplication() {
		Connection con = null;
		//ユーザ情報保管用のリストを準備
		List<Application> applicationList = new ArrayList<Application>();
		try {
			con = DBManager.getConnection();

			String tables = "FROM application AS a "
						+ "INNER JOIN user AS u "
						+ "ON a.user_id = u.user_id";
			String sql = "SELECT * " + tables;

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
				int applicationNo = rs.getInt("application_no");
				int userId = rs.getInt("user_id");
				String loginId = rs.getString("login_id");
				int grade = rs.getInt("grade");
				String userName = rs.getString("user_name");
                String appDate = getFormatDate(rs.getTimestamp("app_date"), "yyyy/MM/dd HH:mm");
                int appAmount = rs.getInt("app_amount");
                String payDate = getFormatDate(rs.getTimestamp("pay_date"), "yyyy/MM/dd HH:mm");
                int payAmount = rs.getInt("app_amount");
                int payFg = rs.getInt("pay_fg");
                Application application = new Application(applicationNo, userId, loginId, grade, userName, appDate, appAmount,
                											payDate, payAmount, payFg);
                applicationList.add(application);
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
		return applicationList;
	}

	// 申込番号から申込・入金情報を取得
	public Application findApplicationByApplicationNo(String sApplicationNo) {
		Connection con = null;
		try {
			con = DBManager.getConnection();

			String where = "WHERE application_no = ?";
			String sql = "SELECT * FROM application " + where;

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, sApplicationNo);
			ResultSet rs = stmt.executeQuery();

			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			if (!rs.next()) {
				return null;
			}

			int applicationNo = rs.getInt("application_no");
			int userId = rs.getInt("user_id");
            String appDate = getFormatDate(rs.getTimestamp("app_date"), "yyyy/MM/dd HH:mm");
            int appAmount = rs.getInt("app_amount");
            String payDate = getFormatDate(rs.getTimestamp("pay_date"), "yyyy/MM/dd HH:mm");
			int payAmount = rs.getInt("pay_amount");
            int payFg = rs.getInt("pay_fg");
			Application application = new Application(applicationNo, userId, appDate, appAmount, payDate,
														payAmount, payFg);
			return application;

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
	}

	// ユーザごとに申込・入金の履歴情報を取得
	public List<Application> findApplicationByUserId(int sUserId) {
		Connection con = null;
		//講座情報保管用のリストを準備
		List<Application> applicationList = new ArrayList<Application>();
		try {
			con = DBManager.getConnection();

			String where = "WHERE user_id = ?";
			String sql = "SELECT * FROM application " + where;

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, sUserId);
			ResultSet rs = stmt.executeQuery();

			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
                int applicationNo = rs.getInt("application_no");
                int userId = rs.getInt("user_id");
                String appDate = getFormatDate(rs.getTimestamp("app_date"), "yyyy/MM/dd HH:mm");
                int appAmount = rs.getInt("app_amount");
                String payDate = getFormatDate(rs.getTimestamp("pay_date"), "yyyy/MM/dd HH:mm");
                int payAmount = rs.getInt("pay_amount");
                int payFg = rs.getInt("pay_fg");
                Application application = new Application(applicationNo, userId, appDate, appAmount, payDate,
                											payAmount, payFg);
                applicationList.add(application);
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
		return applicationList;
	}

	// 申込情報を挿入する
	public int applicationInsert(Application application) {
		Connection con = null;
		try {
			con = DBManager.getConnection();
			int applicationNo = 0;

			String columns = "(user_id, app_date, app_amount) ";
			String values = "VALUES(?, NOW(), ?)";

			String sql = "INSERT INTO application" + columns + values;

			//ステートメントの準備
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// 各値の代入
			stmt.setLong(1, application.getUserId());
			stmt.setLong(2, application.getAppAmount());

			System.out.println("申込情報書込み数：" + stmt.executeUpdate() + "件");

			// 自動採番した申込番号を取得し、戻り値として返す
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				applicationNo = rs.getInt(1);
			}
			return applicationNo;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			// データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
	}

	// 入力情報から申込一覧を検索
	public List<Application> search(String sGrade, String sUserName, String sLoginId, String sStart, String sEnd,
								String sApplicationNo) {
		// コネクションを取得
		Connection conn = null;

		//申込情報保管用のリストを準備
		List<Application> applicationList = new ArrayList<Application>();

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備(ログインIDと申込番号は空欄でない場合のみ挿入する)
			String logId = "";
			String appNo = "";

			if(!strCheck(sLoginId)) {
				logId = "AND login_id = ? ";
			}
			if(!strCheck(sApplicationNo)) {
				appNo = "AND applicationNo = ?";
			}

			String where = "WHERE (u.grade LIKE ?) "
						+ "AND (u.user_name LIKE BINARY ?) "
						+ "AND (a.app_date BETWEEN ? AND ?) "
						+ logId
						+ appNo;
			String tables = "application AS a "
						+ "INNER JOIN user AS u "
						+ "ON a.user_id = u.user_id ";
			String sql = "SELECT * FROM " + tables + where;

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入(フォームが空欄の場合はワイルドカードを代入)

			if (strCheck(sGrade)) {
				stmt.setString(1, "%");
			} else {
				stmt.setString(1, sGrade);
			}

			if (strCheck(sUserName)) {
				stmt.setString(2, "%");
			} else {
				stmt.setString(2, "%" + sUserName + "%");
			}

			if (strCheck(sStart)) {
				stmt.setString(3, "0000-00-00");
			} else {
				stmt.setString(3, sStart);
			}

			if (strCheck(sEnd)) {
				stmt.setString(4, "9999-12-31");
			} else {
				stmt.setString(4, sEnd);
			}

			if(!strCheck(sLoginId)) {
				stmt.setString(5, sLoginId);
			}

			if(!strCheck(sApplicationNo)) {
				if(!strCheck(sLoginId)) {
					stmt.setString(6, sApplicationNo);
				}else {
					stmt.setString(5, sApplicationNo);
				}
			}
			ResultSet rs = stmt.executeQuery();

			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
				int applicationNo = rs.getInt("application_no");
				int userId = rs.getInt("user_id");
				String loginId = rs.getString("login_id");
				int grade = rs.getInt("grade");
				String userName = rs.getString("user_name");
                String appDate = getFormatDate(rs.getTimestamp("app_date"), "yyyy/MM/dd HH:mm");
                int appAmount = rs.getInt("app_amount");
                String payDate = getFormatDate(rs.getTimestamp("pay_date"), "yyyy/MM/dd HH:mm");
                int payAmount = rs.getInt("app_amount");
                int payFg = rs.getInt("pay_fg");
                Application application = new Application(applicationNo, userId, loginId, grade, userName, appDate, appAmount,
                											payDate, payAmount, payFg);
                applicationList.add(application);
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
		return applicationList;
	}

	public void updatePayment(Application application, int calPayment) {
		// コネクション取得
		Connection conn = null;

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String sql = "UPDATE application SET pay_date = NOW(), pay_amount = ?, pay_fg = ? WHERE application_no = ?";

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
			int totalPayament = application.getPayAmount()+calPayment;
			stmt.setInt(1, totalPayament);
			if(totalPayament < application.getAppAmount()) {
				stmt.setInt(2, 3);
			}else if(totalPayament > application.getAppAmount()){
				stmt.setInt(2, 2);
			}
			else if(totalPayament == application.getAppAmount()){
				stmt.setInt(2, 1);
			}
			stmt.setInt(3, application.getApplicationNo());


			// 更新したレコードの数を返す
			System.out.println("入金登録数：" + stmt.executeUpdate() + "件");
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
}
