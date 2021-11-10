package wiki.ramyun.www.search;

import java.time.LocalDateTime;

public class SearchVO {
	private String name;
	private LocalDateTime updateDate;
	
	public SearchVO() {
		super();
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
}
