import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BooKDAO {
	
	// 자바랑 DB가 서로 연결되는 기능 정리 부분!!
	
	
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	ArrayList<BookVO> list = new ArrayList<>();
	
	
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
	// : 책 정보들 저장하는 메소드 
	// 보시면 인설트라고 하는 이 메소드는 꼭 호출할때 매개변수들을 가지고 있어야 해요
	// 매개변수의 타입은 BooKVO 라고 하는 타입으로 들어가야 하는데 
	// 그럼 이 부분들을 수정을 해줘야 합니다.
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
		         result = psmt.executeUpdate();// 업데이트 줄수
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
//		   저희 데이터 아직 하나도 없어요!!
//		 ....!!!! ㅇ0ㅇ!!!!!   
	   
	   // select selectAll()
	   public ArrayList<BookVO> selectAll() {
		   
		   // 자.. 움... ArrayList를 써서 가지고 오려면 
//		    일단 ArrayList가 생성 되어야 겠죵?
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
		        
		            // 테이블 검색으로 데이터를 하나씩 가지고 올때
//		              마지막에 최종적으로 가변 리스트에 담아 버리는거에요
		               list.add(new BookVO(getB_id, getB_title, getB_author, getB_publisher, getB_price));
		            
		            }
		         } catch (SQLException e) {
		            e.printStackTrace();
		         } finally {
		            Close();
		         }
		    
		         // 그리고 호출된 메소드의 위치에 반환값으로 ArrayList를 돌려주는거죵
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

	

}