package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
	// String型のstrが空白文字列や、nullでないかを判定
	public static boolean strCheck(String str){
		if(str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}

	// Date型の日付を任意の文字列で取得する
	public static String getFormatDate(Date date, String format) {
		String formatDate;
		if(date == null) {
			formatDate = null;
		}else {
			formatDate = new SimpleDateFormat(format).format(date);
		}
		return formatDate;
	}
}
