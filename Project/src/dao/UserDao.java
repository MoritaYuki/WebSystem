package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

public class UserDao extends CommonDao{

	//ログインIDとパスワードで検索をかける。
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

	// 全ユーザの情報を取得する
	public List<User> findAll() {

		Connection con = null;
		//ユーザ情報保管用のリストを準備
		List<User> userList = new ArrayList<User>();
		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM user WHERE user_id != 1";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			//取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
                int userId = rs.getInt("user_id");
                String loginId = rs.getString("login_id");
                String password = rs.getString("password");
                int grade = rs.getInt("grade");
                String userName = rs.getString("user_name");
                String userNamePhonetic = rs.getString("user_name_phonetic");
                String sex = rs.getString("sex");
                Date birthday = rs.getDate("birthday");
                String contactInfo = rs.getString("contact_info");
                String address = rs.getString("address");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(userId, loginId, password, grade, userNamePhonetic, userName, sex,  birthday,
                					contactInfo, address, createDate, updateDate);

                userList.add(user);
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
		return userList;
	}

	// ユーザIDからアカウントを検索
	public User searchByUserId(String sUserId){

			// コネクションを取得
			Connection conn = null;

			try {
				//DBに接続
				conn = DBManager.getConnection();
				//SELECT文準備
				String where = "WHERE user_id = ?";

				String sql = "SELECT * FROM user " + where;

				//ステートメントの準備
				PreparedStatement stmt = conn.prepareStatement(sql);
				//それぞれの入力項目を代入(フォームが空欄の場合はワイルドカードを代入)
				stmt.setString(1, sUserId);
		        ResultSet rs = stmt.executeQuery();

		      //取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
				if (!rs.next()) {
					return null;
				}

				int userId = rs.getInt("user_id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				int grade = rs.getInt("grade");
				String userName = rs.getString("user_name");
				String userNamePhonetic = rs.getString("user_name_phonetic");
				String sex = rs.getString("sex");
				Date birthday = rs.getDate("birthday");
				String contactInfo = rs.getString("contact_info");
				String address = rs.getString("address");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(userId, loginId, password, grade, userNamePhonetic, userName, sex, birthday,
									contactInfo, address, createDate, updateDate);
				return user;

			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}finally {
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

	// 入力情報からアカウントを検索
	public List<User> search(String sLoginId, String sUserName, String sGrade, String sAddress){

		// コネクションを取得
		Connection conn = null;

		//ユーザ情報保管用のリストを準備
		List<User> userList = new ArrayList<User>();

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String where;

			// ログインIDが空欄なら、ログインIDの抽出文を消す。（完全一致の場合はワイルドカードがないため）
			if(!strCheck(sLoginId)) {
				where = "((user_name LIKE BINARY ?) OR (user_name_phonetic LIKE BINARY ?))"
						+ "AND (grade LIKE ?)"
						+ "AND (address LIKE BINARY ?)"
						+ "AND (login_id = BINARY ?)"
						+ "AND (user_id != 1)";
			} else {
				where = "((user_name LIKE BINARY ?) OR (user_name_phonetic LIKE BINARY ?))"
						+ "AND (grade LIKE ?)"
						+ "AND (address LIKE BINARY ?)"
						+ "AND (user_id != 1)";
			}

			String sql = "SELECT * FROM user WHERE" + where;

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入(フォームが空欄の場合はワイルドカードを代入)

	        if(strCheck(sUserName)) {
	        	stmt.setString(1, "%");
	        	stmt.setString(2, "%");
	        }else {
	        	stmt.setString(1, "%" + sUserName + "%");
	        	stmt.setString(2, "%" + sUserName + "%");
	        }

	        if(strCheck(sGrade)) {
	        	stmt.setString(3, "%");
	        }else {
	        	stmt.setString(3, sGrade);
	        }

	        if(strCheck(sAddress)) {
	        	stmt.setString(4, "%");
	        }else {
	        	stmt.setString(4, "%" + sAddress + "%");
	        }

	        if(!strCheck(sLoginId)) {
	        	stmt.setString(5, sLoginId);
	        }

	        ResultSet rs = stmt.executeQuery();

	      //取得したユーザデータの表から１レコードずつ値を取得して、リストに代入していく
			while (rs.next()) {
				int userId = rs.getInt("user_id");
                String loginId = rs.getString("login_id");
                String password = rs.getString("password");
                int grade = rs.getInt("grade");
                String userName = rs.getString("user_name");
                String userNamePhonetic = rs.getString("user_name_phonetic");
                String sex = rs.getString("sex");
                Date birthday = rs.getDate("birthday");
                String contactInfo = rs.getString("contact_info");
                String address = rs.getString("address");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(userId, loginId, password, grade, userNamePhonetic, userName, sex,  birthday,
                					contactInfo, address, createDate, updateDate);

                userList.add(user);
            }
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally {
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
		return userList;
	}

	// フォームからの情報を新規ユーザとして登録
	public int signup(String[] userData) {
		//コネクション取得
		Connection conn = null;

		// ハッシュ生成前にバイト配列へ置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;

		// ハッシュ生成アルゴリズム
		String algorithm = "MD5";

		try {
			//ハッシュ生成処理（passwordはuserData[1]に配置）
			byte[] bytes = MessageDigest.getInstance(algorithm).digest(userData[1].getBytes(charset));
			userData[1] = DatatypeConverter.printHexBinary(bytes);

			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String column = "(login_id, password, grade, user_name_phonetic, user_name, sex, birthday, contact_info, address, create_date)";
			String value = "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, now())";

			String sql = "INSERT INTO user" + column + value;

			/*
			 * ・createStatement()は入力した文章をそのままステートメントにするだけだから、最後の
			 * executeQuery()の引数にsql分を指定する。
			 * ・preparedStatement()を用いる場合は、最初に？で代入部分を置き換えてから、再度setメソッド
			 * にて代入する。代入した際にインスタンスstmtには既に代入後のsql分が保存されているため、最後
			 * のexecuteQuery()で引数を指定する必要はなし
			 */

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//それぞれの入力項目を代入
			for(int i=0; i<userData.length; i++) {
		        stmt.setString(i+1, userData[i]);
			}

	        // 追加したレコードの数を表示
	        System.out.println(stmt.executeUpdate() + "件の新規アカウントを登録");

	     // 自動採番したユーザIDを取得し、戻り値として返す
			ResultSet rs = stmt.getGeneratedKeys();
			int userId = 0;
			if (rs.next()) {
				userId = rs.getInt(1);
			}
			return userId;
		}catch(SQLException | NoSuchAlgorithmException e){
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

	public void userUpdate(String[] userData) {
		//コネクション取得
		Connection conn = null;
		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String password;
			if(strCheck(userData[2])) {
				password = "";
			}else {
				// ハッシュ生成前にバイト配列へ置き換える際のCharset
				Charset charset = StandardCharsets.UTF_8;
				// ハッシュ生成アルゴリズム
				String algorithm = "MD5";

				//ハッシュ生成処理（passwordはuserData[1]に配置）
				byte[] bytes = MessageDigest.getInstance(algorithm).digest(userData[2].getBytes(charset));
				userData[2] = DatatypeConverter.printHexBinary(bytes);
				password = " password = ?,";
			}

			String set = "SET"
					+ password
					+ " grade = ?,"
					+ " user_name_phonetic = ?,"
					+ " user_name = ?,"
					+ " sex = ?,"
					+ " birthday = ?,"
					+ " contact_info = ?,"
					+ " address = ? ";
			String where = "WHERE user_id = ?";

			String sql = "UPDATE user " + set + where;

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入
			for(int i=2; i<userData.length; i++) {
		        stmt.setString(i-1, userData[i]);
			}
			if(strCheck(userData[2])) {
				for(int i=3; i<userData.length; i++) {
			        stmt.setString(i-2, userData[i]);
				}
			}else {
				for(int i=2; i<userData.length; i++) {
			        stmt.setString(i-1, userData[i]);
				}
			}
			// 追加したレコードの数を返す
			stmt.executeUpdate();
			System.out.println("ユーザID：" + userData[0] + "の情報を更新");
		} catch (SQLException | NoSuchAlgorithmException e) {
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

	public void userDelete(String userId) {
		// コネクション取得
		Connection conn = null;
		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String sql = "DELETE FROM user WHERE user_id = ?";

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//それぞれの入力項目を代入
			stmt.setString(1, userId);
			System.out.println("アカウント削除件数：" + stmt.executeUpdate() + "件");

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

	// ユーザIDの最大値を取得
	public int getMaxUserId() {
		//コネクション取得
		Connection conn = null;

		try {
			//DBに接続
			conn = DBManager.getConnection();
			//SELECT文準備
			String sql = "SELECT MAX(user_id) FROM user";

			//ステートメントの準備
			PreparedStatement stmt = conn.prepareStatement(sql);
			//ステートメントの中身をResultSet型の変数に代入
			ResultSet rs = stmt.executeQuery();

			//検索したレコードは1件だけだから繰り返し文は不要
			if (!rs.next()) {
				return 0;
			}

			//取得したIDとパスワードを返す
			int maxUserId = rs.getInt("max(user_id)");

			return maxUserId;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					//例外時は値を取得しない
					return 0;
				}
			}
		}
	}

	// 入力フォームの不備をチェック
	public boolean formCheck(String[] userData, String passwordRe) {

		// 同じログインID(userData[0]に配置)がないか判定
		List<User> userList = findAll();
		for(User user: userList) {
			if(!strCheck(userData[0]) && user.getLoginId().equals(userData[0])) {
				return true;
			}
		}

		// 入力フォームに空欄があるか(loginIDはログインIDの一意性を調べた際に確認済) 判定
		if(strCheck(passwordRe)) {
			return true;
		}
		for(String x: userData) {
			if( strCheck(x) ) {
				return true;
			}
		}

		// パスワード(userData[1]に配置)が確認用のもの(passwordRe)と一致しているか判定
		if(!userData[1].equals(passwordRe)) {
			return true;
		}
		return false;
	}

	public boolean updateFormCheck(String[] userData, String passwordRe) {

		// 入力フォームに空欄があるか(loginIDはログインIDの一意性を調べた際に確認済) 判定
		for(int i=0; i<userData.length; i++) {
			if(i != 2 && strCheck(userData[i])) {
				return true;
			}
		}

		// パスワード(userData[1]に配置)が確認用のもの(passwordRe)と一致しているか判定
		if(!strCheck(userData[2]) && !userData[2].equals(passwordRe)) {
			return true;
		}
		return false;
	}
}
