package java_board.view;

import java.util.Scanner;

import java_board.common.Request;
import java_board.controller.BoardController;

public class HelpView extends View {

	BoardController app = new BoardController();
	
	public HelpView(Scanner sc, Request request) {
		super(sc, request);
	}
	
	@Override
	public void render() {
		System.out.println("add : 게시물 저장");
		System.out.println("list : 게시물 목록 조회");
		System.out.println("detail : 게시물 상세 조회");
		System.out.println("update : 게시물 수정");
		System.out.println("delete : 게시물 삭제");
		System.out.println("search : 게시물 검색");
		System.out.println("sort : 게시물 정렬 조회");
		System.out.println("page : 페이지");

		Request request = new Request();

		app.start("main", request);

	}
}