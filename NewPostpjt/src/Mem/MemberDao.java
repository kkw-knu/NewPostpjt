package Mem;
import java.sql.*;
import java.util.*;

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
		String sql = "insert into member2 values(?,?,?,?,?,sysdate,'n')";
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
	
	public int logincheck(String id, String password) {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet res = null;
		String sql = "select password from member2 where id=? and del !='y'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			res = pstmt.executeQuery();
			if(res.next()) {
				String pw = res.getString("password");
				if(pw.equals(password)) result = 1; //로그인 성공
				else result = 0; //비밀번호 틀림
			}else result = -1; //없는 아이디
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(res!=null) res.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	
	public Member select(String id) {
		Member member = new Member();
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet res = null;
		String sql = "select * from member2 where id=? and del !='y'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			res = pstmt.executeQuery();
			if(res.next()) {
				member.setId(id);
				member.setPassword(res.getString("password"));
				member.setAddress(res.getString("address"));
				member.setName(res.getString("name"));
				member.setTel(res.getString("tel"));
				member.setReg_date(res.getDate("reg_date"));
				member.setDel(res.getString("del"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(res!=null) res.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return member;
	}
	
	public int update(Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "update member2 set password=?, address=?, name=?, tel=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getAddress());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getTel());
			pstmt.setString(5, member.getId());
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
	
	public int delete(String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
//		String sql = "delete from member2 where id=?";
		String sql = "update member2 set del = 'y' where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
	
	public List<Member> list() {
		List<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		String sql = "select * from member2 order by reg_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setAddress(rs.getString("address"));
				member.setName(rs.getString("name"));
				member.setTel(rs.getString("tel"));
				member.setReg_date(rs.getDate("reg_date"));
				member.setDel(rs.getString("del"));
				list.add(member);
			}
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
		return list;
	}
}
