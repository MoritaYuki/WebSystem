package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.ApplicationDetail;

public class ApplicationDetailDao {
	// 申込詳細情報を挿入する
	public void applicationDetailInsert(ApplicationDetail applicationDetail) {
		Connection con = null;
		try {
			con = DBManager.getConnection();

			String columns = "(application_no, course_id) ";
			String values = "VALUES(?, ?)";

			String sql = "INSERT INTO application_detail" + columns + values;

			//ステートメントの準備
			PreparedStatement stmt = con.prepareStatement(sql);

			// 各値の代入
			stmt.setLong(1, applicationDetail.getApplicationNo());
			stmt.setLong(2, applicationDetail.getCourseId());

			System.out.println("申込詳細情報書込み数：" + stmt.executeUpdate() + "件");
		} catch (SQLException e) {
			e.printStackTrace();
			return ;
		} finally {
			// データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return ;
				}
			}
		}
	}
}
