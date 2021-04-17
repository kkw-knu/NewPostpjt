package user;

import java.sql.*;

/*데이터베이스와 연동되는 부분*/
public class UserDAO {
	
	private Connection con = null;
	private ResultSet res = null;
	
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/newpjt?characterEncoding=utf8&useUnicode=true";
	String id = "root";
	String pw = "root";
	
	public UserDAO() {
		try {
			Class.forName(driver);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int join(String userID, String userPW) { //값을 추가하는 함수
		String sql = "insert into user values(?,?)";
		try {
			con = DriverManager.getConnection(url, id, pw);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			return pstmt.executeUpdate(); //insert함수는 update로 추가만한다.
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1;//오류가생기면 -1을 반환한다.
	}
	
}
