package login;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class MemberDao {
	// singleton
	private static MemberDao instance = new MemberDao();
	private MemberDao() {};
	public static MemberDao getinstance() {
		return instance;
	}
	
	//database connection pool
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("db���� ���� : "+e.getMessage());
		}
		return conn;
	}
	
	public int confirm(String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //select�� ����Ҷ��� Result�� ����Ѵ�.
		Connection conn = getConnection();
		String sql = "select id from member2 where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) result = 1; //���� �Է��� id�� �ִٴ� ���̴�.
			else result = 0; //�ش��ϴ� id�� ���ٴ� ���̴�.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	
	public int insert(Member member) { //insert�� resultset�� �ʿ����
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "insert into member2 values(?,?,?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getTel());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
}
