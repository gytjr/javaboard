package java_board.view;

import java.util.Scanner;

import java_board.common.Request;
import java_board.controller.BoardController;

public class ArticleAddView extends View {
	
	private BoardController app = new BoardController();
	
	public ArticleAddView(Scanner sc, Request request) {
		super(sc, request);
	}
	
	public void render() {
		System.out.println("===== 게시물 등록 화면 =====");
		System.out.println("제목을 입력해주세요");
		String title = sc.nextLine();
		System.out.println("내용을 입력해주세요");
		String body = sc.nextLine();
		
		Request request = new Request();
		request.setParameters("title", title);
		request.setParameters("body", body);
		
		app.start("doAdd", request);
	}
}