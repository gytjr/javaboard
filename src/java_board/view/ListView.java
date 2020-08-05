package java_board.view;

import java.util.ArrayList;
import java.util.Scanner;

import java_board.article.model.Article;
import java_board.article.model.Pagination;
import java_board.common.Request;
import java_board.controller.BoardController;

public class ListView extends View {
	private BoardController app = new BoardController();

	public ListView(Scanner sc, Request request) {
		super(sc, request);
	}

	@Override
	public void render() {
		
		ArrayList<Article> articles = (ArrayList<Article>)request.getAttribute("articles");
		Pagination page = (Pagination)request.getAttribute("pagination");
		
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
		
		app.start("main", request);
	}
}