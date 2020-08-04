package java_board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

import java_board.util.Util;

public class BoardApp1 {
	//public, default, protected, private
	//public 모든 객체가 접근가능.
	//protected 같은 패키지 객체 + 상속관계.
	//default 같은 패키지 객체.
	//private 자기 자신만
	private ArrayList<Article> articles = new ArrayList<Article>();
	private ArrayList<Reply> replies = new ArrayList<Reply>();
	private ArrayList<User> users = new ArrayList<User>();
	private int lastArticleId = 0; // 게시물 번호 관리용
	private int lastReplyId = 0; // 댓글 번호 관리용

	void start() {
		Scanner sc = new Scanner(System.in);
		String cmd = "";

		make_test_data();
		Pagination pagination = new Pagination(articles.size(), 3, 5);
		print_articles(articles, pagination);

		while (true) {

			System.out.print("명령어를 입력해주세요 :");
			cmd = sc.nextLine();
			if (cmd.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
			if (cmd.equals("help")) {
				System.out.println("add : 게시물 저장");
				System.out.println("list : 게시물 목록 조회");
				System.out.println("detail : 게시물 상세 조회");
				System.out.println("update : 게시물 수정");
				System.out.println("delete : 게시물 삭제");
				System.out.println("search : 게시물 검색");
				System.out.println("sort : 게시물 정렬 조회");
				System.out.println("page : 페이지");
				System.out.println("join : 회원가입");
			}

			if (cmd.equals("add")) {

				Article article = new Article();
				article.setId(lastArticleId);
				lastArticleId++;

				System.out.println("제목을 입력해주세요");
				String title = sc.nextLine();
				article.setTitle(title);

				article.setRegDate(Util.getCurrentDate());

//				System.out.println("내용을 입력해주세요");
//				String body = sc.nextLine();
//				bodies.add(body);
//				article.body = body;

				articles.add(article);
				System.out.println("게시물이 등록되었습니다.");

			}
			if (cmd.equals("list")) {
				print_articles(articles, pagination);
			}
			if (cmd.equals("update")) {
				System.out.println("어떤 게시물을 수정하시겠습니까? : ");
				int targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_id(targetNo);

				if (targetArticle != null) {

					System.out.println("수정할 제목을 입력해주세요 : ");
					String updated_title = sc.nextLine();
					targetArticle.setTitle(updated_title);

					System.out.println("수정이 완료되었습니다.");
				} else {
					System.out.println("없는 게시물 번호입니다.");
				}

			}
			if (cmd.equals("delete")) {
				System.out.println("어떤 게시물을 삭제하시겠습니까? :");

				int targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_id(targetNo);

				if (targetArticle != null) {
					articles.remove(targetArticle);
					System.out.println("게시물이 삭제되었습니다.");
				} else {
					System.out.println("없는 게시물 번호입니다.");
				}
			}
			if (cmd.equals("search")) {
				System.out.println("검색 항목을 선택해주세요. : 1. 제목, 2. 내용");
				int searchFlag = Integer.parseInt(sc.nextLine());
				System.out.println("검색어를 입력해주세요");
				String keyword = sc.nextLine();
				ArrayList<Article> searchedArticles = new ArrayList<>();

				for (int i = 0; i < articles.size(); i++) {
					if (articles.get(i).getPropertyByType(searchFlag).contains(keyword)) {
						searchedArticles.add(articles.get(i));
					}
				}

				print_articles(searchedArticles, pagination);
			}
			if (cmd.equals("detail")) {
				System.out.println("게시물 번호를 입력해주세요.");
				int articleId = Integer.parseInt(sc.nextLine());
				Article article = get_article_by_id(articleId);

				if (article == null) {
					System.out.println("없는 게시물입니다.");
				} else {

					article.hit++;
					print_article(article);
					ArrayList<Reply> replies = get_replies_by_parent_id(articleId);
					print_replies(replies);

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

							print_article(article);
							ArrayList<Reply> replies2 = get_replies_by_parent_id(articleId);
							print_replies(replies2);

						} else if (detailCmd == 2) {
							System.out.println("1. 좋아요  2. 싫어요");
							int likeOrHate = Integer.parseInt(sc.nextLine());
							article.set_likes_and_hates("chacha1", likeOrHate);
							print_article(article);

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

				sortArticles(articles, fieldFlag, sortFlag);
				print_articles(articles, pagination);
			} else if (cmd.equals("page")) {
				while (true) {
					print_articles(articles, pagination);
					System.out.println("1. 이전   2. 다음  3. 선택  4. 뒤로가기");
					int pageCmd = Integer.parseInt(sc.nextLine());

					if (pageCmd == 1) {
						if (pagination.getCurrentPageNo() <= pagination.startPage) {
							System.out.println("첫 페이지입니다.");
							continue;
						}
						pagination.currentPageNo--;
					} else if (pageCmd == 2) {
						if (pagination.currentPageNo >= pagination.getLastPage()) {
							System.out.println("마지막 페이지입니다.");
							continue;
						}
						pagination.currentPageNo++;
					} else if (pageCmd == 3) {
						System.out.println("페이지를 선택해주세요.");
						int selectedPageNo = Integer.parseInt(sc.nextLine());
						pagination.currentPageNo = selectedPageNo;
					} else if (pageCmd == 4) {
						break;
					}
					print_articles(articles, pagination);
				}
			} else if (cmd.equals("join")) {
				System.out.println("id 입력 : ");
				String userId = sc.nextLine();
				System.out.println("비밀번호 입력 : ");
				String userPw = sc.nextLine();
				System.out.println("이름 입력 : ");
				String userName = sc.nextLine();
				
				User user = new User(userId, userPw, userName);
				User target = getUserById(userId);
				
				
				if(target == null) {
					users.add(user);
					System.out.println("가입이 완료되었습니다.");
				}else {
					System.out.println("중복된 아이디입니다.");
				}
				
				
			}
		}
	}
	
	public User getUserById(String userId) {
		
		for(int i = 0; i < users.size(); i++) {
			if(userId.equals(users.get(i).userId)) {
				return users.get(i);
			}
		}
		
	}

	public ArrayList<Reply> get_replies_by_parent_id(int parent_id) {

		ArrayList<Reply> result = new ArrayList<Reply>();

		for (int i = 0; i < this.replies.size(); i++) {
			if (this.replies.get(i).parent_id == parent_id) {
				result.add(this.replies.get(i));
			}
		}

		return result;

	}

	public void print_replies(ArrayList<Reply> replies) {
		System.out.println("======== 댓글 ========");
		for (int i = 0; i < replies.size(); i++) {
			System.out.println("내용   : " + replies.get(i).body);
			System.out.println("작성자 : " + replies.get(i).writer);
			System.out.println("등록일 : " + replies.get(i).regDate);
		}
	}

	public void print_article(Article article) {
		System.out.println("======== 게시물 상세 ========");
		System.out.println("번호     : " + article.id);
		System.out.println("제목     : " + article.title);
		System.out.println("내용     : " + article.body);
		System.out.println("조회수   : " + article.hit);
		System.out.println("등록날짜 : " + article.regDate);

		HashMap<String, Integer> resultMap = article.get_likes_and_hates();
		System.out.println("좋아요   : " + resultMap.get("like"));
		System.out.println("싫어요   : " + resultMap.get("hate"));
	}

	public void print_articles(ArrayList<Article> articles, Pagination page) {
		System.out.println("======== 게시물 목록 ========");
		int startIndex = (page.currentPageNo - 1) * page.articlesPerPage;
		int endIndex = startIndex + page.articlesPerPage;
		for (int i = startIndex; i < endIndex; i++) {
			System.out.println("번호   : " + articles.get(i).id);
			System.out.println("제목   : " + articles.get(i).title);

			String str = articles.get(i).regDate;
			String[] arr = str.split(" ");
			System.out.println("작성일 : " + arr[0]);
			System.out.println("조회수 : " + articles.get(i).hit);
		}
		System.out.println("===== 페이지 =====");
		System.out.print(1 + " ... ");
		for (int i = page.getStartPageInBlock(page.currentPageNo); i < page
				.getEndPageInBlock(page.currentPageNo); i++) {
			if (i == page.currentPageNo) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print(i + " ");
			}
		}
		System.out.print("... " + page.getLastPage() + "\n");
	}

	public Article get_article_by_id(int id) {
		Article article = null;
		for (int i = 0; i < articles.size(); i++) {

			Article target = articles.get(i);

			if (target.id == id) {
				article = target;
				break;
			}
		}
		return article;
	}

	public void sortArticles(ArrayList<Article> articles, int type, int sort) {

		Collections.sort(articles, new Comparator<Article>() {

			@Override
			public int compare(Article a1, Article a2) {
				int rst = -1;
				if (type == 1) {
					int c1 = a1.get_likes_and_hates().get("like");
					int c2 = a2.get_likes_and_hates().get("like");

					rst = c1 > c2 ? 1 : -1;

				} else if (type == 2) {
					String c1 = a1.regDate;
					String c2 = a2.regDate;

					rst = c1.compareTo(c2) > 0 ? 1 : -1;
				}

				if (sort == 2) {
					rst *= -1;
				}
				return rst;
			}
		});
	}

	public void make_test_data() {

		HashMap<String, Integer> likes1 = new HashMap<>();
		likes1.put("chacha1", 1);
		likes1.put("chacha2", 2);
		likes1.put("chacha3", 1);

		HashMap<String, Integer> likes2 = new HashMap<>();
		likes2.put("chacha1", 2);
		likes2.put("chacha2", 2);
		likes2.put("chacha3", 2);

		HashMap<String, Integer> likes3 = new HashMap<>();
		likes3.put("chacha1", 1);
		likes3.put("chacha2", 1);
		likes3.put("chacha3", 1);

		Article article1 = new Article();
		article1.id = 1;
		article1.title = "테스트 데이터 제목1";
		article1.body = "테스트 데이터 내용1";
		article1.regDate = "2020-07-28 09:49:00";
		article1.hit = 20;
		article1.likesAndHates = likes1;

		Article article2 = new Article(2, "제목2", "내용2", "2020-07-29 09:49:00", 30, likes2);
		Article article3 = new Article(3, "제목3", "내용3", "2020-07-30 09:49:00", 5, likes3);

		articles.add(article1);
		articles.add(article2);
		articles.add(article3);

		lastArticleId = 4;

		for (int i = 4; i <= 50; i++) {
			Article a = new Article(i, "제목" + i, "내용" + i, "2020-07-29 09:49:00", 30, likes2);
			articles.add(a);
		}

		Reply r1 = new Reply(1, 1, "댓글1", "작성자1", Util.getCurrentDate());
		Reply r2 = new Reply(2, 1, "댓글2", "작성자2", Util.getCurrentDate());
		Reply r3 = new Reply(3, 2, "댓글3", "작성자3", Util.getCurrentDate());

		lastReplyId = 4;

		replies.add(r1);
		replies.add(r2);
		replies.add(r3);
	}
}