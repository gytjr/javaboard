package java_board.test;

import java_board.common.Request;
import java_board.controller.BoardController;

public class Test {
	
	public static void main(String[] args) {

		BoardController board1 = new BoardController();
		Request request = new Request();
		board1.start("main", request);
	}
	
	

}