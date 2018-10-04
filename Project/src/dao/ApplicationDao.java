package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Application;

public class ApplicationDao {

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
				Date appDate = rs.getDate("app_date");
                int appAmount = rs.getInt("app_amount");
                Date payDate = rs.getDate("pay_date");
                int payAmount = rs.getInt("app_amount");
                boolean payFg = rs.getBoolean("pay_fg");
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
			Date appDate = rs.getTimestamp("app_date");
			int appAmount = rs.getInt("app_amount");
			Date payDate = rs.getTimestamp("pay_date");
			int payAmount = rs.getInt("pay_amount");
			boolean payFg = rs.getBoolean("pay_fg");
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
                Date appDate = rs.getTimestamp("app_date");
                int appAmount = rs.getInt("app_amount");
                Date payDate = rs.getTimestamp("pay_date");
                int payAmount = rs.getInt("pay_amount");
                boolean payFg = rs.getBoolean("pay_fg");
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
}
