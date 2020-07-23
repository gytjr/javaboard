package java_board;

public class Reply {
	int id; // 댓글 번호
	int parent_id;
	String body;
	String writer;
	String regDate;
	
	
	public Reply(int id, int parent_id, String body, String writer, String regDate) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.body = body;
		this.writer = writer;
		this.regDate = regDate;
	}
}
