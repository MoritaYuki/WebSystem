package dao;

public class CommonDao {
	// String型のstrが空白文字列や、nullでないかを判定
	public boolean strCheck(String str){
		if(str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}
}
