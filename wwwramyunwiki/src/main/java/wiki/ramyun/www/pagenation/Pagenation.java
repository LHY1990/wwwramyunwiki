package wiki.ramyun.www.pagenation;

import org.springframework.stereotype.Component;

@Component
public class Pagenation {
	private int listSize=3;			//한 페이지당 보여질 리스트의 갯수
    private int rangeSize=10; 		//한 페이지 범위에 보여질 페이지 갯수
	private int page; 				//현재 페이지 번호 
	private int range;			    //각 페이지별 시작번호
	private int listCnt; 			//전체 게시글의 갯수
	private int pageCnt;				//전체 페이지 갯수
	private int startPage; 			//각 페이지 범위 시작 번호
	private int startList;   		//게시판 시작번호
	private int endPage; 			//각 페이지 범위 끝 번호
	private boolean prev;			//이전 페이지 여부
	private boolean next;			//다음 페이지 여부
	
	public int getListSize() {
		return listSize;
	}
	public int getRangeSize() {
		return rangeSize;
	}
	public int getPage() {
		return page;
	}
	public int getRange() {
		return range;
	}
	public int getListCnt() {
		return listCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getStartList() {
		return startList;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public void setStartList(int startList) {
		this.startList=startList;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	public void pageInfo(int page, int range, int listCnt) {
		this.page=page;
		this.range=range;
		this.listCnt=listCnt;
		
		//전체 페이지 갯수. 123개면 각 페이지 별로 10개씩일때 12.3을 올림한다. 13페이지가 된다
		this.pageCnt=(int)Math.ceil(listCnt/listSize);
		
		//시작페이지는 2번째장에서 첫번째라면 (2-1)*10+1 즉 21이다. 1~10이라면 (1-1)*10+1 즉 1
		this.startPage=(range-1)*rangeSize+1;
		
		//끝페이지. range는 현재있는 구간이라고 보면될듯 21~30이면 3에 있는거지 기본이 1이고 
		this.endPage=range*rangeSize;
		
		//게시판 시작번호. 10개가 기본인 경우 2페이지면 10이 시작번호 기본이 0<=기준<10 이다 왜냐면 sql문이 그렇다. limit 0,10 이기때문
		this.startList=(page-1)*listSize;
		
		//이전 버튼 상태. 첫페이지면 꺼지고 아니면 켜지고
		this.prev=(range==1?false : true);
		
		//다음버튼 상태. 마지막 페이지가 페이지 갯수보다 작다면 없음
		this.next =(endPage>pageCnt?false:true);
		
		if(this.endPage>this.pageCnt) {
			//만약에 마지막 페이지가 페이지 갯수랑 같다면 다음은 없다
			this.endPage=this.pageCnt;
			this.next=false;
		}
		
	}
	
}
