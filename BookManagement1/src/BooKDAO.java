import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BooKDAO {
	
	// �ڹٶ� DB�� ���� ����Ǵ� ��� ���� �κ�!!
	
	
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	ArrayList<BookVO> list = new ArrayList<>();
	
	
	int result = 0; // ��ȯ��

	String sql;
	
	// ���̺� ���� �޼ҵ�
	public void table() {
		// 1. �����ͺ��̽� ����
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
	// �����ͺ��̽��� �����ϴ� �޼ҵ� getConnect()

	// �����ͺ��̽��� ������� �޼ҵ� Close()

	// ȸ������ �޼ҵ�

	// insert insert()
	// : å ������ �����ϴ� �޼ҵ� 
	// ���ø� �μ�Ʈ��� �ϴ� �� �޼ҵ�� �� ȣ���Ҷ� �Ű��������� ������ �־�� �ؿ�
	// �Ű������� Ÿ���� BooKVO ��� �ϴ� Ÿ������ ���� �ϴµ� 
	// �׷� �� �κе��� ������ ����� �մϴ�.
	   public int insert(BookVO b) {

		      getConnect();
		      
		      try {
		         String sql = "insert into book values(?,?,?,?,?)";
		         psmt = conn.prepareStatement(sql);
		         psmt.setString(1, b.getB_id());
		         psmt.setString(2, b.getB_title());
		         psmt.setString(3, b.getB_author());
		         psmt.setString(4, b.getB_publisher());
		         psmt.setInt(5, b.getB_price());
		         result = psmt.executeUpdate();// ������Ʈ �ټ�
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         Close();
		      }
		      return result;
		   }
	   
	// select select()
	   public void select(String b_id) {
		      getConnect();
		      sql = "select * from book where b_id = ?";
		      try {
		         psmt = conn.prepareStatement(sql);
		         
		         psmt.setString(1, b_id);
		      
		         rs = psmt.executeQuery();
		         while (rs.next()) {
		            String getB_id = rs.getString(1);
		            String getB_title = rs.getString(2);
		            String getB_author = rs.getString(3);
		            String getB_publisher = rs.getString(4);
		            int getB_price = rs.getInt(5);
		            System.out.println(getB_id + "/" + getB_title + "/" + getB_author + "/" + getB_publisher + "/" + getB_price);
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         Close();
		      }
		   }
//		   ���� ������ ���� �ϳ��� �����!!
//		 ....!!!! ��0��!!!!!   
	   
	   // select selectAll()
	   public ArrayList<BookVO> selectAll() {
		   
		   // ��.. ��... ArrayList�� �Ἥ ������ ������ 
//		    �ϴ� ArrayList�� ���� �Ǿ�� ����?
		         getConnect();
		         
		         try {
		        	 String sql = "select * from book";
		            psmt = conn.prepareStatement(sql);         
		            rs = psmt.executeQuery();
		            while (rs.next()) {
		               String getB_id = rs.getString(1);
		               String getB_title = rs.getString(2);
		               String getB_author = rs.getString(3);
		               String getB_publisher = rs.getString(4);
		               int getB_price = rs.getInt(5);
//		               System.out.println(getB_id + "/" + getB_title + "/" + getB_author + "/" + getB_publisher + "/" + getB_price);
		        
		            // ���̺� �˻����� �����͸� �ϳ��� ������ �ö�
//		              �������� ���������� ���� ����Ʈ�� ��� �����°ſ���
		               list.add(new BookVO(getB_id, getB_title, getB_author, getB_publisher, getB_price));
		            
		            }
		         } catch (SQLException e) {
		            e.printStackTrace();
		         } finally {
		            Close();
		         }
		    
		         // �׸��� ȣ��� �޼ҵ��� ��ġ�� ��ȯ������ ArrayList�� �����ִ°���
		         return list;
		         
		   }
	   
	// update update()
	public int update(String id, int price) {        
        getConnect();           
        String sql = "update book set b_price=? where b_id=?";
        try {
           psmt = conn.prepareStatement(sql);
           psmt.setInt(1, price);
           psmt.setString(2, id);
           result = psmt.executeUpdate();      
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           Close();
        }    
        return result;
     }
	
	
	// delete delete()
	public int delete(String id) {

	    getConnect();

	    try {

	       String sql = "delete from book where b_id=?";

	       psmt = conn.prepareStatement(sql);

	       psmt.setString(1, id);

	       result = psmt.executeUpdate();

	    } catch (SQLException e) {

	       e.printStackTrace();

	    } finally {

	       Close();

	    }

	    return result;

	 }
	
	
	// �����ͺ��̽��� �����ϴ� �޼ҵ� getConnect()
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
	// �����ͺ��̽��� ������� �޼ҵ� Close()
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

	

}