package java_board;

import java.util.HashMap;

class Article {

	private int id;
	private String title;
	private String body;
	private String regDate;
	private int hit;
	private HashMap<String, Integer> likesAndHates; // 1. 좋아요, 2. 싫어요

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public HashMap<String, Integer> getLikesAndHates() {
		return likesAndHates;
	}

	public void setLikesAndHates(HashMap<String, Integer> likesAndHates) {
		this.likesAndHates = likesAndHates;
	}

	// 기본
	Article() {

	}

	Article(int id, String title, String body, String regDate, int hit, HashMap<String, Integer> likesAndHates) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
		this.likesAndHates = likesAndHates;
	}
	
	void set_likes_and_hates(String userId, int likeOrHate) {
		if(likesAndHates == null) {
			likesAndHates = new HashMap<String, Integer>();
		}
//		if(likesAndHates.get(userId) == null) {
//			
//		}
		
		if(likesAndHates.containsKey(userId)) {
			if(likesAndHates.get(userId) == likeOrHate) {
				likesAndHates.remove(userId);
			} else {
				likesAndHates.put(userId, likeOrHate);				
			}
		} else {
			likesAndHates.put(userId, likeOrHate);			
		}
		
	}
	
	HashMap<String, Integer> get_likes_and_hates() {
		int likeCnt = 0;
		int hateCnt = 0;
		
		for(int value : likesAndHates.values()) {
			if(value == 1) {
				likeCnt++;
			}
		}
		hateCnt = likesAndHates.size() - likeCnt; 
		
		HashMap<String, Integer> resultMap = new HashMap<>();
		resultMap.put("like", likeCnt);
		resultMap.put("hate", hateCnt);
		
		return resultMap;
	}
	
	
	String getPropertyByType(int type) {
		String rst = "";
		
		if(type == 1) {
			rst = this.title;
		} else if(type == 2) {
			rst = this.body;
		}
		
		return rst;
	}

}