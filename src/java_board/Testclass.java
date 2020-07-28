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
		
		int currentPageNo = 1;
		int totalCountOfArticle = testList.size();
		int articlePerPage = 3;
		
		int lastPage = totalCountOfArticle / articlePerPage +1;
		
		
		int startIndex = (currentPageNo - 1) * articlePerPage; 
		int endIndex = startIndex + articlePerPage;
		
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			//리스트출력
			for(int  i = startIndex; i< endIndex; i++) {
				System.out.println("번호 : " + testList.get(i).id);
				System.out.println("제목 : " + testList.get(i).title);
				System.out.println("내용 : " + testList.get(i).body);
			}
			
			//페이지
			int startPage = currentPageNo - 2;
			
			if(startPage <=0 ) {
				startPage = 1;
			}
			System.out.print(1 + " ... ");
			for(int i = startPage; i < startPage + 5; i++) {
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
