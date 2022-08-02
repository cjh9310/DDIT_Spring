package com.jsp.command;

public class Criteria {

	private int page=1;
	private int perPageNum=10;
	
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page<1) {
			this.page = 1;
		}else {
			this.page = page;
		}
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <1) {
			this.perPageNum = 10;
		}else {
			this.perPageNum = perPageNum;
		}
	}
	
	public int getStartRowNum() {
		return (this.page-1)*perPageNum;
	}
	
	
}
