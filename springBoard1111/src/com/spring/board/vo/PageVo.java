package com.spring.board.vo;

public class PageVo {

	private int pageNo;//���� ������ ��ȣ
	private int pageSize;//�˻� �Խñ۰���
	private int totalCnt;//�ѱۼ�
	private int blockSize;//���� �˻��� ����������
	
	private int totalPage;
	private int startRow;
	private int endRow;

	private int startPage;
	private int endPage;
	private int prevPage;
	private int nextPage;

	public PageVo() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PageVo(int pageNo, int pageSize, int totalCnt, int blockSize) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCnt = totalCnt;
		this.blockSize = blockSize;
		
		calcPager();
	}

	
	private void calcPager() {
		
		//�������� ���� �ѰԽñ� ���� ������ �������� ��°���
		totalPage=(totalCnt+pageSize-1)/pageSize;
		
		//���࿡ ������������ 0�����۰ų����� ������������ȣ�� ������������ ũ�� ��������ȣ��1
		if(pageNo < 1) pageNo=1;
		if(pageNo > totalPage) pageNo=totalPage;
		
		//�Խñ��� �������� ���������� -1 ���ϱ� ����������°��� +1
		startRow = (pageNo-1) * pageSize + 1;
		System.out.println((pageNo-1) * pageSize + 1);
		//���������� ��������ȣ ���ϱ� ����������°���
		endRow = pageNo * pageSize;
		System.out.println("pageNo = "+pageNo+", pageSize = "+pageSize);
		System.out.println(pageNo * pageSize);
		//���࿡ ���������� �������� �������� ������ ����������=������������
		if(endRow>totalCnt) {
			endRow=totalCnt;
		}
		
		//�������������� = ����������-1 ������ ��µɺ����� ���ϱ� ������+1
		startPage = (pageNo-1) / blockSize * blockSize + 1;
		
		//���������� ������ ���ϱ� ��µɺ�����
		endPage = startPage + blockSize - 1;
		
		//���࿡ ���������� ���������������� ������ ���������� ������������
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		
		prevPage=startPage-blockSize;
		nextPage=startPage+blockSize;
	}


	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

}





















