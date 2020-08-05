package java_board.article.model;

public class Reply {

	private int id; // 댓글 번호
	private int parent_id; // 부모글 번호
	private String body; // 내용
	private String writer; // 작성자
	private String regDate; // 작성일
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Reply(int id, int parent_id, String body, String writer, String regDate) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.body = body;
		this.writer = writer;
		this.regDate = regDate;
	}
	
}