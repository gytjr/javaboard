package java_board;

public class Pagination {
	
	int currentPageNo; // 현재페이지 
	int totalCountOfArticle; // 전체 게시물의 개수
	int articlesPerPage; // 페이지당 게시물의 개수
	int startPage; // 시작 페이지
	int pageCount; // 페이지 블록에 보여줄 페이지 개수
	
	public Pagination(int totalCountOfArticle, int articlesPerPage, int pageCount) {
		this.currentPageNo = 1;
		this.startPage = 1;
		this.totalCountOfArticle = totalCountOfArticle;
		this.articlesPerPage = articlesPerPage;
		this.pageCount = pageCount;			
	}
	
	int getLastPage() {
		return totalCountOfArticle / articlesPerPage + 1;
	}
	
	int getStartPageInBlock(int currentPageNo) {		
		int startPageInBlock = currentPageNo - pageCount / 2; 
		if(startPageInBlock < startPage) { // startPage보다 작으면 안됨
			startPageInBlock = startPage;
		}	
		return startPageInBlock;
	}
	
	int getEndPageInBlock(int currentPageNo) {
		int startPageInBlock = getStartPageInBlock(currentPageNo);
		int endPageInBlock = startPageInBlock + pageCount;
		int lastPage = getLastPage();
		if(endPageInBlock > lastPage) { // lastPage보다 크면 안됨
			endPageInBlock = lastPage;
		}
		return endPageInBlock; 
	}
	
}