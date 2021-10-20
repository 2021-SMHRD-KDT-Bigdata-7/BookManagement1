
public class BookVO {

	private String b_id;
	private String b_title;
	private String b_author;
	private String b_publisher;

	public BookVO() {}
	
	public BookVO(String b_id, String b_title, String b_author, String b_publisher, int b_price) {
		this.b_id = b_id;
		this.b_title = b_title;
		this.b_author = b_author;
		this.b_publisher = b_publisher;
		this.b_price = b_price;
	}
	
	public String getB_id() {
		return b_id;
	}

	public String getB_title() {
		return b_title;
	}

	public String getB_author() {
		return b_author;
	}

	public String getB_publisher() {
		return b_publisher;
	}

	public int getB_price() {
		return b_price;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public void setB_author(String b_author) {
		this.b_author = b_author;
	}

	public void setB_publisher(String b_publisher) {
		this.b_publisher = b_publisher;
	}

	public void setB_price(int b_price) {
		this.b_price = b_price;
	}

	private int b_price;


	public String toString() {
		return "Book { [일련번호 : "+this.b_id+"] [책 제목 : "+this.b_title+"] [저자 : "+this.b_author+"] [출판사 : "+this.b_publisher+"] [가격 : "+this.b_price+"]}";
	}
}
