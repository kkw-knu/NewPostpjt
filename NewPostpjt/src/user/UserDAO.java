package user;

import java.sql.*;

/*�����ͺ��̽��� �����Ǵ� �κ�*/
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
	
	public int join(String userID, String userPW) { //���� �߰��ϴ� �Լ�
		String sql = "insert into user values(?,?)";
		try {
			con = DriverManager.getConnection(url, id, pw);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			return pstmt.executeUpdate(); //insert�Լ��� update�� �߰����Ѵ�.
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1;//����������� -1�� ��ȯ�Ѵ�.
	}
	
}
