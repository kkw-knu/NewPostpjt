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
			System.out.println("db연결 실패 : "+e.getMessage());
		}
		return conn;
	}
	
	public int confirm(String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //select를 사용할때는 Result를 사용한다.
		Connection conn = getConnection();
		String sql = "select id from member2 where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) result = 1; //현재 입력한 id가 있다는 뜻이다.
			else result = 0; //해당하는 id가 없다는 뜻이다.
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
	
	public int insert(Member member) { //insert는 resultset이 필요없음
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
