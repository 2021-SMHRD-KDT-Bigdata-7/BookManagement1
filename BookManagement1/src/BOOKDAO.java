import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BOOKDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int result; // ��ȯ��

	// �����ͺ��̽��� �����ϴ� �޼ҵ� getConnect()

	// �����ͺ��̽��� ������� �޼ҵ� Close()

	// ȸ������ �޼ҵ�

	// insert insert()

	// select select()
	// select selectAll()

	// update update()

	// delete delete()

	public void getConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) { // ��� ������ �߻��� ĳġ���ִ� ��Ȱ
			System.out.println("���� ����");
			e.printStackTrace();
		}
	}

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
			result = psmt.executeUpdate();// ������Ʈ �ټ�
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