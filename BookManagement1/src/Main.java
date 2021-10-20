import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
//		BookManagement bm = new BookManagement();
		
		// DAO�� �ִ� �� �޼ҵ忡 �����Ϸ��� ��ü�� �־�� ����? -> DAO ��ü
		BooKDAO dao = new BooKDAO();
		
		// BooKVO �� �����ϱ� ���� ��ü ���� �ʿ�
		 BookVO  vo = new  BookVO();

		 // ���ɿ��� �� �����ô�
		System.out.println("[Java �������� ���α׷�]");
		
		while (true) { //�߳� bookvo�� �ҹ��ڿ���.. �˼��ؿ�...
			System.out.println();
			System.out.print("[1]������� [2]������ȸ [3]�������� [4]�������� [5]���� >> ");
			int menu = sc.nextInt();
 
			int cnt;

			
			// �̳༮���� book�� ���� ���赵 �̴ϱ� VO�� �Ű� ����������!
//			String id;
//			String title;
//			String author;
//			String publisher;
//			int price;

			if (menu == 1) {
				
				
				System.out.print("�������̵� : ");
//				vo.setB_id(sc.next());
				String id = sc.next();
				
				System.out.print("������ : ");
				String title = sc.next();
//				vo.setB_title(sc.next());
				
				System.out.print("���� : ");
//				vo.setB_author(sc.next());
				String author = sc.next(); 
				
				System.out.print("���ǻ� : ");
//				vo.setB_publisher(sc.next());
				String publisher  = sc.next();
				
				System.out.print("���� : ");
//				vo.setB_price(sc.nextInt());
				int price = sc.nextInt();
				
				/// �� ����ڰ� ������ ���忡 �ʿ��� �������� �Է� �ϸ�
				// �� ������� sql �� �����ؼ� ������ ���̽� ���̺� insert�� ������ �ٲ�����
				// �׷��� ���ؼ��� DB�� java�� ��� ���� ����ɼ� �յ��� 
				// DAO ��� ������ ����� ���ϴ�. 
				
				vo = new BookVO(id, title, author, publisher, price);
				
				cnt = dao.insert(vo);
				
				if(cnt > 0) {
					System.out.println("����Ϸ�!!");
				}else {
					System.out.println("�������..");
				}
				
			
			} else if (menu == 2) {

				System.out.print("[1]Ư��������ȸ [2]��ü��ȸ >> ");
				int m = sc.nextInt();

				if (m == 1) {
					System.out.print("�������̵� : ");
					String id = sc.next();
					dao.select(id);
				} else if (m == 2) {
					
					// �׷� �޼ҵ带 ȣ���� �κ��� ArrayList�� ���� ����
					
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
				
				System.out.print("������ �������̵� : ");
				String id = sc.next();
				System.out.print("���� : ");
				int price = sc.nextInt();
				
				cnt = dao.update(id, price);
				
				if(cnt > 0) {
					System.out.println("�����Ϸ�!!");
				}else {
					System.out.println("��������..");
				}

			} else if (menu == 4) {
				System.out.print("������ �������̵� : ");
				String id = sc.next();
				
				cnt = dao.delete(id);
				
				if(cnt > 0) {
					System.out.println("�����Ϸ�!!");
				}else {
					System.out.println("��������..");
				}
				 
//				���� arraylist ������
//				���� ��ü ��ȸ ���� Ư�� ���� ��ȸ�� �ǳ���??�׳׳�
				
				// �����̶� ���� ����� �� �Ƴ���? �׳� �� �ƴµ� 
				// all �� arraylist ���� �ٸ�������� �߾��
				// �ѹ� ��������~
			} else {
				System.out.println("Java �������� ���α׷��� ����˴ϴ�.");
				break;
			}
		}

	}

}
