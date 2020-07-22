package java_board;

import java.util.ArrayList;
import java.util.Scanner;

import util.Util;

public class BoardApp1 {
	ArrayList<Article> articles = new ArrayList<Article>();
	
	void start() {

		Scanner sc = new Scanner(System.in);
		String cmd = "";
//		String store = null; // "" -> 없는 데이터, null
		
		int id = 1;
		
		//테스트 데이터 만들기
		
		Article article1 = new Article();
		article1.id = 1;
		article1.title = "테스트 데이터 제목";
		article1.body = "테스트 데이터 내용";
		article1.writer = "테스트 데이터 내용";
		article1.regDate = Util.getCurrentDate();
		article1.hit = 20;
		
		
		Article article2 = new Article(2, "제목2", "내용2", Util.getCurrentDate(), 30);
		Article article3 = new Article(3, "제목3", "내용3", Util.getCurrentDate(), 0);
		
		articles.add(article1);		
		articles.add(article2);
		articles.add(article3);
		
		while(true) {
			
			System.out.print("명령어를 입력해주세요 : ");
			cmd = sc.nextLine();
			if(cmd.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
			if(cmd.equals("help")) {
				System.out.println("add : 데이터 저장");
				System.out.println("list : 데이터 목록 조회");
				System.out.println("detail : 데이터 상세 조회");
				System.out.println("update : 데이터 수정");
				System.out.println("delete : 데이터 삭제");
				System.out.println("search : 데이터 검색");
			}
			
			if(cmd.equals("add")) {
				
				Article article = new Article();
				article.id = id;
				id++;
				
				System.out.println("제목을 입력해주세요");
				String title = sc.nextLine();
				article.title = title;
				
				article.regDate = Util.getCurrentDate();
				
				
				
//				System.out.println("내용을 입력해주세요");
//				String body = sc.nextLine();
//				article.body = body;
//				
//				System.out.println("작성자를 입력해주세요");
//				String writer = sc.nextLine();
//				article.writer = writer;
//				
				articles.add(article);
				System.out.println("게시물이 등록되었습니다.");

			}
			if(cmd.equals("list")) {
				print_articles(articles);
			}
			if(cmd.equals("update")) {
				System.out.println("어떤 게시물을 수정하시겠습니까? : ");
				int targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_id(targetNo);
				
				if(targetArticle != null) {
					System.out.println("수정할 제목을 입력해주세요 : ");
					String updated_title = sc.nextLine();
					targetArticle.title = updated_title;
				}else {
					System.out.println("없는 게시물 번호입니다.");
				}
				
			}
			if(cmd.equals("delete")) {
				System.out.println("어떤 게시물을 삭제하시겠습니까?");
				int targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_id(targetNo);
				
				if(targetArticle != null) {
					articles.remove(targetArticle);
					System.out.println("게시물이 삭제되었습니다.");
				}else {
					System.out.println("없는 게시물 번호입니다.");
				}
				
			}
			if(cmd.equals("search")) {
				System.out.println("검색 항목을 선택해주세요. : 1. 제목 2. 내용");
				int searchFlag = Integer.parseInt(sc.nextLine());
				
				
				System.out.println("검색어를 입력해주세요");
				String keyword = sc.nextLine();
				
				ArrayList<Article> searchedArticles = new ArrayList<>();
				
				if(searchFlag == 1) {
					for(int i = 0; i < articles.size(); i++) {
						if(articles.get(i).title.contains(keyword)) {
							searchedArticles.add(articles.get(i));
						}
					}
				}
				
				if(searchFlag == 2) {
					for(int i = 0; i < articles.size(); i++) {
						if(articles.get(i).body.contains(keyword)) {
							searchedArticles.add(articles.get(i));
						}
					}
				}
				
				print_articles(searchedArticles);
			}
			if(cmd.equals("detail")) {
				System.out.println("게시물 번호를 입력해주세요.");
				int articleId = Integer.parseInt(sc.nextLine());
				Article article = get_article_by_id(articleId);
				
				if(article == null) {
					System.out.println("없는 게시물입니다.");
				}else {
					article.hit++;
					print_article(article);
				}
			}
		}
	}
	public void print_article(Article article) {
		System.out.println("======== 게시물 목록 ========");
		System.out.println("번호 : " + article.id);
		System.out.println("제목 : " + article.title);
		System.out.println("내용 : " + article.body);
		System.out.println("조회수 : " + article.hit);
	}

	public Article get_article_by_id(int id) {
		Article article = null;
		for(int i = 0; i <articles.size(); i++) {
			
			Article target = articles.get(i);
			
			if(target.id == id) {
				article = target;
				break;
			}
			
		}
		return article;
	}

	public void print_articles(ArrayList<Article> articles) {
		System.out.println("======== 게시물 목록 ========");
		for(int i = 0; i < articles.size() ; i++) {
			System.out.println("번호 : " + articles.get(i).id);
			System.out.println("제목 : " + articles.get(i).title);
			
			String str = articles.get(i).regDate;
			String[] arr = str.split(" ");
			
			System.out.println("작성일 : " + arr[0]);
			System.out.println("조회수 : " + articles.get(i).hit);
		}
	}
	
}

