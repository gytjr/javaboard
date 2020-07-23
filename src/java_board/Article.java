package java_board;

public class Article {

	int id;
	String title;
	String body;
	String writer;
	String regDate;
	int hit;
	
	//기본 생성자 다른거있으면 안만들어줌
	Article(){
		
	}
	Article(int id, String title, String body, String regDate, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
	}
	
	String getPropertyByType(int type) {
		String rst = "";
		if(type == 1) {
			rst = this.title;
		}else if(type == 2) {
			rst = this.body;
		}
		return rst;
	}
}
