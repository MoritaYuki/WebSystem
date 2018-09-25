package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.DatatypeConverter;

import model.User;

public class UserDao {

	//ログインIDとパスワードでwebpro内のレコードに検索をかける。
	public User findByLoginInfo(String loginId, String password) {
		//コネクション取得
		Connection conn = null;

		// ハッシュ生成前にバイト配列へ置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;

		// ハッシュ生成アルゴリズム
		String algorithm = "MD5";

		try {
			//ハッシュ生成処理
			byte[] bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
			String encPassword = DatatypeConverter.printHexBinary(bytes);

			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";
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
	        stmt.setString(1, loginId);
	        stmt.setString(2, encPassword);
	        //ステートメントの中身をResultSet型の変数に代入
	        ResultSet rs = stmt.executeQuery();

	        //検索したレコードは1件だけだから繰り返し文は不要
	        if(!rs.next()){
	        	return null;
	        }

	        //取得したIDとパスワードを返す
	        int userIdData = Integer.parseInt(rs.getString("user_id"));
	        String loginIdData = rs.getString("login_id");
	        String userNameData = rs.getString("user_name");

	        return new User(userIdData,loginIdData, userNameData);

		}catch(SQLException | NoSuchAlgorithmException e){
			e.printStackTrace();
            return null;
		}finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                  //例外時は値を取得しない
                    return null;
                }
            }
        }
	}
}
