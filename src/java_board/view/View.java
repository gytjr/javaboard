package java_board.view;

import java.util.Scanner;

import java_board.common.Request;

abstract public class View {

	protected Scanner sc;
	protected Request request;
	
	public View(Scanner sc, Request request) {
		this.sc = sc;
		this.request = request;
	}
	
	public abstract void render();
}