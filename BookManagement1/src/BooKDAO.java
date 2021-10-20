import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooKDAO {
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	//createStatement stmt = null;
	
	int result = 0; // 반환값

	String sql;
	
	// 테이블 생성 메소드
	public void table() {
		// 1. 데이터베이스 연결
		getConnect();
		
		sql = "create table book"
				+ "( b_id varchar(10),"
				+ "  b_title varchar2(50) not null,"
				+ "  b_author varchar2(50) not null,"
				+ "  b_publisher varchar2(30) not null,"
				+ "  b_price number not null,"
				+ "  constraint book_bid_pk primary key(b_id)"
				+ ")";
		
		try {
			
			conn.createStatement();
			
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Close();
		}
	}
	// 데이터베이스를 연결하는 메소드 getConnect()

	// 데이터베이스를 연결끊는 메소드 Close()

	// 회원정보 메소드

	// insert insert()

	// select select()
	
	// select selectAll()

	// update update()

	// delete delete()

	// 데이터베이스를 연결하는 메소드 getConnect()
	public void getConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) { // 어떠한 오류라도 발생시 캐치해주는 역활
			System.out.println("연결 오류");
			e.printStackTrace();
		}
	}
	// 데이터베이스를 연결끊는 메소드 Close()
	public void Close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insert(String id, String pw, String nick) {

		getConnect();
		try {
			String sql = "insert into member values(?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, nick);
			result = psmt.executeUpdate();// 업데이트 줄수
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close();
		}
		return result;
	}

	public void select(String book) {
		getConnect();
		String sql = "select * from member";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String getID = rs.getString(1);
				String getPW = rs.getString(2);
				String getNICK = rs.getString(3);
				System.out.println(getID + " / " + getPW + " / " + getNICK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close();
		}
	}
	
	public void selectAll() {
		getConnect();
		String sql = "select * from member";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String getID = rs.getString(1);
				String getPW = rs.getString(2);
				String getNICK = rs.getString(3);
				System.out.println(getID + " / " + getPW + " / " + getNICK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close();
		}
	}

	public int update(String set, String where) {
		getConnect();
		try {
			String sql = "update member set pw = ? where id =? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, set);
			psmt.setString(2, where);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close();
		}
		return result;

	}

	public int delete(String id, String pw) {
		getConnect();
		try {
			String sql = "delete from member where id=? and pw =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close();
		}
		return result;
	}

}