package java_board.article.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java_board.util.Util;

public class ArticleDao {
	private ArrayList<Article> articles = new ArrayList<>();
	private ArrayList<Reply> replies = new ArrayList<>();
	
	private int lastArticleId = 0; // 게시물 번호 관리용
	private int lastReplyId = 0;

	public ArrayList<Article> getArticles() {
		return this.articles;
	}
	
	public void insertArticle(Article article) {

		article.setId(lastArticleId);
		lastArticleId++;
		article.setRegDate(Util.getCurrentDate());

		articles.add(article);
	}
	
	public Article getArticleById(int id) {
		Article article = null;
		for (int i = 0; i < articles.size(); i++) {

			Article target = articles.get(i);

			if (target.getId() == id) {
				article = target;
				break;
			}
		}
		return article;
	}

	public boolean updateArticle(int targetNo, String updated_title) {

		Article targetArticle = getArticleById(targetNo);
		
		if(targetArticle == null) {
			return false;
		}
		
		targetArticle.setTitle(updated_title);
		return true;
	}

	public boolean deleteArticle(int targetNo) {
		Article targetArticle = getArticleById(targetNo);
		
		if (targetArticle != null) {
			articles.remove(targetArticle);
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Article> getSearchedArticles(int searchFlag, String keyword) {
		
		ArrayList<Article> searchedArticles = new ArrayList<>();
		
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).getPropertyByType(searchFlag).contains(keyword)) {
				searchedArticles.add(articles.get(i));
			}
		}
		return searchedArticles;
	}

	public void setLikeAndHates(int articleId, String string, int likeOrHate) {
		Article article = getArticleById(articleId);
		article.set_likes_and_hates("chacha1", likeOrHate);
	}
	
	public void sortArticles(ArrayList<Article> articles, int type, int sort) {

		Collections.sort(articles, new Comparator<Article>() {

			@Override
			public int compare(Article a1, Article a2) {
				int rst = -1;
				if (type == 1) {
					int c1 = a1.get_likes_and_hates().get("like");
					int c2 = a2.get_likes_and_hates().get("like");

					rst = c1 > c2 ? 1 : -1;

				} else if (type == 2) {
					String c1 = a1.getRegDate();
					String c2 = a2.getRegDate();

					rst = c1.compareTo(c2) > 0 ? 1 : -1;
				}

				if (sort == 2) {
					rst *= -1;
				}
				return rst;
			}
		});
	}
	public ArrayList<Reply> getRepliesByParentId(int parentId) {

		ArrayList<Reply> result = new ArrayList<Reply>();

		for (int i = 0; i < this.replies.size(); i++) {
			if (this.replies.get(i).getParent_id() == parentId) {
				result.add(this.replies.get(i));
			}
		}

		return result;

	}
	public void make_test_data() {

		HashMap<String, Integer> likes1 = new HashMap<>();
		likes1.put("chacha1", 1);
		likes1.put("chacha2", 2);
		likes1.put("chacha3", 1);

		HashMap<String, Integer> likes2 = new HashMap<>();
		likes2.put("chacha1", 2);
		likes2.put("chacha2", 2);
		likes2.put("chacha3", 2);

		HashMap<String, Integer> likes3 = new HashMap<>();
		likes3.put("chacha1", 1);
		likes3.put("chacha2", 1);
		likes3.put("chacha3", 1);

		Article article1 = new Article();
		article1.setId(1);
		article1.setTitle("테스트 데이터 제목1");
		article1.setBody("테스트 데이터 내용1");
		article1.setRegDate("2020-07-28 09:49:00");
		article1.setHit(20);
		article1.setLikesAndHates(likes1);
		
		Article article2 = new Article(2, "제목2", "내용2", "2020-07-29 09:49:00", 30, likes2);
		Article article3 = new Article(3, "제목3", "내용3", "2020-07-30 09:49:00", 5, likes3);

		articles.add(article1);
		articles.add(article2);
		articles.add(article3);

		for (int i = 4; i <= 50; i++) {
			Article a = new Article(i, "제목" + i, "내용" + i, "2020-07-29 09:49:00", 30, likes2);
			articles.add(a);
		}

		Reply r1 = new Reply(1, 1, "댓글1", "작성자1", Util.getCurrentDate());
		Reply r2 = new Reply(2, 1, "댓글2", "작성자2", Util.getCurrentDate());
		Reply r3 = new Reply(3, 2, "댓글3", "작성자3", Util.getCurrentDate());

		lastReplyId = 4;

		replies.add(r1);
		replies.add(r2);
		replies.add(r3);
	}
}