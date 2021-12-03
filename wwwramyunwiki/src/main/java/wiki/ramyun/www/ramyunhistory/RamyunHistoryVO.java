package wiki.ramyun.www.ramyunhistory;

import wiki.ramyun.www.ramyun.RamyunVO;

public class RamyunHistoryVO extends RamyunVO {
	//추가된 부분
	private String writer;
	private int id;
	private int writerMemberNumber;

	public int getWriterMemberNumber() {
		return writerMemberNumber;
	}
	public void setWriterMemberNumber(int writerMemberNumber) {
		this.writerMemberNumber = writerMemberNumber;
	}
	public RamyunHistoryVO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	

}
