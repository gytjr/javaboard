package java_board.view;

import java.util.ArrayList;
import java.util.HashMap;

import java_board.article.model.Article;
import java_board.article.model.Pagination;
import java_board.article.model.Reply;

public class ArticleView {
	public void print_replies(ArrayList<Reply> replies) {
		System.out.println("======== 댓글 ========");
		for (int i = 0; i < replies.size(); i++) {
			System.out.println("내용   : " + replies.get(i).getBody());
			System.out.println("작성자 : " + replies.get(i).getWriter());
			System.out.println("등록일 : " + replies.get(i).getRegDate());
		}
	}

	public void print_article(Article article) {
		System.out.println("======== 게시물 상세 ========");
		System.out.println("번호     : " + article.getId());
		System.out.println("제목     : " + article.getTitle());
		System.out.println("내용     : " + article.getBody());
		System.out.println("조회수   : " + article.getHit());
		System.out.println("등록날짜 : " + article.getRegDate());

		HashMap<String, Integer> resultMap = article.get_likes_and_hates();
		System.out.println("좋아요   : " + resultMap.get("like"));
		System.out.println("싫어요   : " + resultMap.get("hate"));
	}

	public void print_articles(ArrayList<Article> articles, Pagination page) {
		System.out.println("======== 게시물 목록 ========");
		int startIndex = (page.getCurrentPageNo() - 1) * page.getArticlesPerPage();
		int endIndex = startIndex + page.getArticlesPerPage();
		for (int i = startIndex; i < endIndex; i++) {
			System.out.println("번호   : " + articles.get(i).getId());
			System.out.println("제목   : " + articles.get(i).getTitle());

			String str = articles.get(i).getRegDate();
			String[] arr = str.split(" ");
			System.out.println("작성일 : " + arr[0]);
			System.out.println("조회수 : " + articles.get(i).getHit());
		}
		System.out.println("===== 페이지 =====");
		System.out.print(1 + " ... ");
		for (int i = page.getStartPageInBlock(page.getCurrentPageNo()); i < page
				.getEndPageInBlock(page.getCurrentPageNo()); i++) {
			if (i == page.getCurrentPageNo()) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print(i + " ");
			}
		}
		System.out.print("... " + page.getLastPage() + "\n");
	}
}