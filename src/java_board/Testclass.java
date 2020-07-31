package java_board;

import java.util.ArrayList;
import java.util.Scanner;

import util.Util;



public class Testclass {

	public static void main(String[] args) {
		ArrayList<Article> testList = new ArrayList<>();
		for(int i = 0; i <100; i++) {
			Article article = new Article(i, "제목2", "내용2", Util.getCurrentDate(), 30, null);
			testList.add(article);
		}
		
		int currentPageNo = 1;//현재 페이지
		int totalCountOfArticle = testList.size();//전체 게시물의 개수
		int articlePerPage = 3;//페이지당 게시물의개수
		int pageCount = 10;//페이지블록에 보여줄 페이지 개수
		int startPage = 1; //시작페이지
		int lastPage = totalCountOfArticle / articlePerPage +1; //마지막페이지
		
		
		int startIndex = (currentPageNo - 1) * articlePerPage;  //내가보여줄게시물
		int endIndex = startIndex + articlePerPage; // 보여줄 게시물의 마지막 인덱스
		
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			//리스트출력
			for(int  i = startIndex; i< endIndex; i++) {
				System.out.println("번호 : " + testList.get(i).id);
				System.out.println("제목 : " + testList.get(i).title);
				System.out.println("내용 : " + testList.get(i).body);
			}
			
			//페이지
			int startPageInBlock = currentPageNo - 2;
			int endPageInBlock = startPageInBlock + pageCount;
			if(startPageInBlock < startPage) {
				startPageInBlock = startPage;
			}
			if(startPageInBlock > lastPage) {
				startPageInBlock = startPage;
			}
			System.out.print(1 + " ... ");
			for(int i = startPageInBlock; i < endPageInBlock; i++) {
				if(i == currentPageNo) {
					System.out.print("[" + i + "] ");
				} else {
					System.out.print(i + " ");
				}
			}
			System.out.print(" ... " + lastPage + "\n");
		
		
			System.out.println("1. next  2.prev");
			int cmd = Integer.parseInt(sc.nextLine());
			if(cmd==1) {
				currentPageNo ++;
			}else if (cmd==2) {
				currentPageNo --;
			}
		}
		
		
		
	}

}
