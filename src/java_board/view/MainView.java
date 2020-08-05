package java_board.view;

import java.util.Scanner;

import java_board.common.Request;
import java_board.controller.BoardController;

public class MainView extends View {
	
	private BoardController app = new BoardController();
	
	public MainView(Scanner sc, Request request) {
		super(sc, request);
	}
	
	public void render() {
		System.out.println("차태진의 게시판");
		System.out.println("명령어를 입력해주세요. 도움말은 help입니다.");
		String cmd = sc.nextLine();
		Request request = new Request();
		
		
		app.start(cmd, request);
	}
	
}