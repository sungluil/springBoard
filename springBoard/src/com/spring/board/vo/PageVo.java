package com.spring.board.vo;

public class PageVo {
	
	private int pageNo = 0;

	private int pageSize;
	
	private int startRow;
	private int endRow;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public void calc() {
		
		startRow=(pageNo-1)*pageSize+1;//�������ȣ
		endRow=pageNo*pageSize;//���������ȣ 1-10 11-20 �̷���
	}
}
