import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
//		BookManagement bm = new BookManagement();
		
		// DAO에 있는 각 메소드에 접근하려면 객체가 있어야 겠죠? -> DAO 객체
		BooKDAO dao = new BooKDAO();
		
		// BooKVO 를 접근하기 위한 객체 생성 필요
		 BookVO  vo = new  BookVO();

		 // 오케오케 쭉 가봅시당
		System.out.println("[Java 도서관리 프로그램]");
		
		while (true) { //넹넹 bookvo는 소문자에여.. 죄송해요...
			System.out.println();
			System.out.print("[1]도서등록 [2]도서조회 [3]정보수정 [4]도서삭제 [5]종료 >> ");
			int menu = sc.nextInt();
 
			int cnt;

			
			// 이녀석들은 book에 관한 설계도 이니까 VO로 옮겨 버릴꺼에요!
//			String id;
//			String title;
//			String author;
//			String publisher;
//			int price;

			if (menu == 1) {
				
				
				System.out.print("도서아이디 : ");
//				vo.setB_id(sc.next());
				String id = sc.next();
				
				System.out.print("도서명 : ");
				String title = sc.next();
//				vo.setB_title(sc.next());
				
				System.out.print("저자 : ");
//				vo.setB_author(sc.next());
				String author = sc.next(); 
				
				System.out.print("출판사 : ");
//				vo.setB_publisher(sc.next());
				String publisher  = sc.next();
				
				System.out.print("가격 : ");
//				vo.setB_price(sc.nextInt());
				int price = sc.nextInt();
				
				/// 자 사용자가 데이터 저장에 필요한 정보들을 입력 하면
				// 이 내용들을 sql 로 연결해서 데이터 베이스 테이블에 insert를 진행해 줄꺼에요
				// 그러기 위해서는 DB랑 java가 기능 끼리 연결될수 잇도록 
				// DAO 라는 파일을 사용할 껍니다. 
				
				vo = new BookVO(id, title, author, publisher, price);
				
				cnt = dao.insert(vo);
				
				if(cnt > 0) {
					System.out.println("저장완료!!");
				}else {
					System.out.println("저장실패..");
				}
				
			
			} else if (menu == 2) {

				System.out.print("[1]특정도서조회 [2]전체조회 >> ");
				int m = sc.nextInt();

				if (m == 1) {
					System.out.print("도서아이디 : ");
					String id = sc.next();
					dao.select(id);
				} else if (m == 2) {
					
					// 그럼 메소드를 호출한 부분은 ArrayList로 돌아 오니
					
					ArrayList<BookVO> list = new ArrayList<>(dao.selectAll());
					
					
					for(int i = 0; i < list.size(); i++) {
						
						System.out.print(list.get(i).getB_id() + "\t");
						System.out.print(list.get(i).getB_title() + "\t");
						System.out.print(list.get(i).getB_author() + "\t");
						System.out.print(list.get(i).getB_publisher() + "\t");
						System.out.print(list.get(i).getB_price() + "\t");
						
						System.out.println();
					}
					
				}

			} else if (menu == 3) {
				
				System.out.print("수정할 도서아이디 : ");
				String id = sc.next();
				System.out.print("가격 : ");
				int price = sc.nextInt();
				
				cnt = dao.update(id, price);
				
				if(cnt > 0) {
					System.out.println("수정완료!!");
				}else {
					System.out.println("수정실패..");
				}

			} else if (menu == 4) {
				System.out.print("삭제할 도서아이디 : ");
				String id = sc.next();
				
				cnt = dao.delete(id);
				
				if(cnt > 0) {
					System.out.println("삭제완료!!");
				}else {
					System.out.println("삭제실패..");
				}
				 
//				저희 arraylist 못썼어용
//				구롬 전체 조회 말고 특정 도서 조회는 되나용??네네네
				
				// 수정이랑 삭제 기능은 다 됐나용? 네네 다 됐는데 
				// all 만 arraylist 말고 다른방법으로 했어용
				// 한번 봐볼께뇬~
			} else {
				System.out.println("Java 도서관리 프로그램이 종료됩니다.");
				break;
			}
		}

	}

}
