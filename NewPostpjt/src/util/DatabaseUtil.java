package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	public static Connection getConnection() {
		try {
			String URL = "jdbc:mysql://localhost:3306/USER";
			String ID = "root";
			String PW = "root";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(URL, ID, PW);
		}catch(Exception e) {
			e.printStackTrace();
		}return null;
	}
}
//데이터베이스와 연결시켜주는 부분 