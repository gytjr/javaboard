package java_board.controller;

import java.util.ArrayList;
import java.util.Scanner;

import java_board.article.model.Article;
import java_board.article.model.ArticleDao;
import java_board.article.model.Pagination;
import java_board.article.model.Reply;
import java_board.common.Request;
import java_board.user.model.User;
import java_board.user.model.UserDao;
import java_board.util.Util;
import java_board.view.ArticleAddView;
import java_board.view.ArticleView;
import java_board.view.HelpView;
import java_board.view.ListView;
import java_board.view.MainView;
import java_board.view.View;

public class BoardController {

	// public, default, protected, private

	// public 모든 객체가 접근 가능
	// protected 같은 패키지 객체 + 상속관계 접근 가능
	// default 같은 패키지 객체
	// private 자기 자신만

	private ArticleDao articleDao = new ArticleDao();
	private UserDao userDao = new UserDao();

	private ArticleView articleView = new ArticleView();
	private View responseView;

	private ArrayList<Reply> replies = new ArrayList<Reply>();
	private int lastReplyId = 0; // 댓글 번호 관리용

	ArrayList<Article> articles;
	ArrayList<User> users;
	Scanner sc = new Scanner(System.in);
	
	public void start(String cmd, Request request) {

		articleDao.make_test_data();
		articles = articleDao.getArticles();
		users = userDao.getUsers();

		Pagination pagination = new Pagination(articles.size(), 3, 5);
		//articleView.print_articles(articles, pagination);

		if (cmd.equals("main")) {
			responseView = new MainView(sc, request);
			responseView.render();
		} else if (cmd.equals("exit")) {
			System.out.println("프로그램 종료");
		} else if (cmd.equals("help")) {
			responseView = new HelpView(sc, request);
			responseView.render();
		}

		else if (cmd.equals("add")) {
			
			responseView = new ArticleAddView(sc, request);
			responseView.render();
			
		} else if(cmd.equals("doAdd")) {
			
			String title = (String)request.getParameters("title");
			String body = (String)request.getParameters("body");
			
			Article article = new Article();
			article.setTitle(title);
			article.setBody(body);

			articleDao.insertArticle(article);
			System.out.println("게시물이 등록되었습니다.");
			responseView = new MainView(sc, request);
			responseView.render();
			
		} else if (cmd.equals("list")) {
			
			ArrayList<Article> list = articleDao.getArticles();
			
			request.setAttribute("articles", list);
			Pagination pagination2 = new Pagination(articles.size(), 3, 5);
			request.setAttribute("pagination", pagination2);

			responseView = new ListView(sc, request);
			responseView.render();
			
			articleView.print_articles(articles, pagination);
		} else if (cmd.equals("update")) {
			System.out.println("어떤 게시물을 수정하시겠습니까? : ");
			int targetNo = Integer.parseInt(sc.nextLine());

			Article article = articleDao.getArticleById(targetNo);

			if (article == null) {
				System.out.println("없는 게시물 번호입니다.");
			}
			System.out.println("수정할 제목을 입력해주세요.");
			String updated_title = sc.nextLine();
			boolean result = articleDao.updateArticle(targetNo, updated_title);

			if (result) {
				System.out.println("수정이 완료되었습니다.");
			} else {
				System.out.println("없는 게시물 번호입니다.");
			}

		} else if (cmd.equals("delete")) {
			System.out.println("어떤 게시물을 삭제하시겠습니까? :");

			int targetNo = Integer.parseInt(sc.nextLine());

			boolean result = articleDao.deleteArticle(targetNo);

			if (result) {
				System.out.println("게시물이 삭제되었습니다.");
			} else {
				System.out.println("없는 게시물 번호입니다.");
			}
		} else if (cmd.equals("search")) {
			System.out.println("검색 항목을 선택해주세요. : 1. 제목, 2. 내용");
			int searchFlag = Integer.parseInt(sc.nextLine());
			System.out.println("검색어를 입력해주세요");
			String keyword = sc.nextLine();
			ArrayList<Article> searchedArticles = articleDao.getSearchedArticles(searchFlag, keyword);

			articleView.print_articles(searchedArticles, pagination);
		} else if (cmd.equals("detail")) {
			System.out.println("게시물 번호를 입력해주세요.");
			int articleId = Integer.parseInt(sc.nextLine());
			Article article = articleDao.getArticleById(articleId);

			if (article == null) {
				System.out.println("없는 게시물입니다.");
			} else {

				article.setHit(article.getHit() + 1);
				articleView.print_article(article);
				ArrayList<Reply> replies = articleDao.getRepliesByParentId(articleId);
				articleView.print_replies(replies);

				while (true) {
					System.out.println("1. 댓글  2. 좋아요  3. 수정  4. 삭제  5. 뒤로가기");
					int detailCmd = Integer.parseInt(sc.nextLine());

					if (detailCmd == 1) {

						int replyId = lastReplyId;
						lastReplyId++;

						System.out.println("댓글 내용을 입력해주세요.");
						String replyBody = sc.nextLine();
						String writer = "익명";
						String regDate = Util.getCurrentDate();

						Reply new_reply = new Reply(replyId, articleId, replyBody, writer, regDate);
						this.replies.add(new_reply);
						System.out.println("댓글이 성공적으로 등록되었습니다.");

						articleView.print_article(article);
						ArrayList<Reply> replies2 = articleDao.getRepliesByParentId(articleId);
						articleView.print_replies(replies2);

					} else if (detailCmd == 2) {
						System.out.println("1. 좋아요  2. 싫어요");
						int likeOrHate = Integer.parseInt(sc.nextLine());
						articleDao.setLikeAndHates(articleId, "chacha1", likeOrHate);
						articleView.print_article(article);

					} else if (detailCmd == 5) {
						break;
					}
				}

			}
		} else if (cmd.equals("sort")) {
			System.out.println("정렬 대상을 선택해주세요 : 1. 좋아요,  2. 작성일");
			int fieldFlag = Integer.parseInt(sc.nextLine());
			System.out.println("정렬 방법을 선택해주세요 : 1. 오름차순,  2. 내림차순");
			int sortFlag = Integer.parseInt(sc.nextLine());

			articleDao.sortArticles(articles, fieldFlag, sortFlag);
			articleView.print_articles(articles, pagination);
		} else if (cmd.equals("page")) {
			while (true) {
				articleView.print_articles(articles, pagination);
				System.out.println("1. 이전   2. 다음  3. 선택  4. 뒤로가기");
				int pageCmd = Integer.parseInt(sc.nextLine());

				if (pageCmd == 1) {
					if (pagination.getCurrentPageNo() <= pagination.getStartPage()) {
						System.out.println("첫 페이지입니다.");
						continue;
					}
					pagination.setCurrentPageNo(pagination.getCurrentPageNo() - 1);
				} else if (pageCmd == 2) {
					if (pagination.getCurrentPageNo() >= pagination.getLastPage()) {
						System.out.println("마지막 페이지입니다.");
						continue;
					}
					pagination.setCurrentPageNo(pagination.getCurrentPageNo() + 1);
				} else if (pageCmd == 3) {
					System.out.println("페이지를 선택해주세요.");
					int selectedPageNo = Integer.parseInt(sc.nextLine());
					pagination.setCurrentPageNo(selectedPageNo);
				} else if (pageCmd == 4) {
					break;
				}
				articleView.print_articles(articles, pagination);
			}
		} else if (cmd.equals("join")) {
			System.out.println("아이디   : ");
			String userId = sc.nextLine();
			System.out.println("비밀번호 : ");
			String userPw = sc.nextLine();
			System.out.println("이름     : ");
			String userName = sc.nextLine();

			User u = new User(userId, userPw, userName);
			User target = userDao.getUserById(userId);

			if (target == null) {
				users.add(u);
			} else {
				System.out.println("이미 등록된 아이디입니다.");
			}
		}
	}
}