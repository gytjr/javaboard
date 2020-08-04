package java_board;

public class Pagination {
	private int currentPageNo;//현재 페이지
	private int totalCountOfArticle;//전체 게시물의 개수
	private int articlePerPage;//페이지당 게시물의개수
	private int startPage; //시작페이지
	private int pageCount;//페이지블록에 보여줄 페이지 개개수
	
	private int lastPage = totalCountOfArticle / articlePerPage +1; //마지막페이지
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getTotalCountOfArticle() {
		return totalCountOfArticle;
	}
	public void setTotalCountOfArticle(int totalCountOfArticle) {
		this.totalCountOfArticle = totalCountOfArticle;
	}
	public int getArticlePerPage() {
		return articlePerPage;
	}
	public void setArticlePerPage(int articlePerPage) {
		this.articlePerPage = articlePerPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public Pagination(int totalCountOfArticle, int articlePerPage, int pageCount){
		this.currentPageNo = 1;
		this.startPage = 1;
		this.totalCountOfArticle = totalCountOfArticle;
		this.articlePerPage = articlePerPage;
		this.pageCount = pageCount;
		
		int startPageInBlock = currentPageNo - 2;
		int endPageInBlock = startPageInBlock + pageCount;
		
		if(startPageInBlock > lastPage) {
			startPageInBlock = startPage;
		}
		
	}
	int getLastPage() {
		return totalCountOfArticle / articlePerPage +1;
	}
	int getStartPageInBlock(int CurrentPageNo) {
		int startPageInBlock = CurrentPageNo - pageCount /2;
		if(startPageInBlock < startPage) {
			startPageInBlock = startPage;
		}
		return startPageInBlock;
	}
	
	int getEndPageInBlock(int CurrentPageNo) {
		int startPageInBlock = getStartPageInBlock(CurrentPageNo);
		int endPageInBlock = startPageInBlock * pageCount;
		int lastPage = getLastPage();
	}
}
