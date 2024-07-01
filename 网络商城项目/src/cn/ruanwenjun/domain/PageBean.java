package cn.ruanwenjun.domain;

import java.util.List;

public class PageBean<T> {
	private int currentPage;
	private int currentCount;
	private int totalPage;
	private int totalCount;
	private List<T> list;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	public PageBean() {}

	public PageBean(int currentCount,int currentPage, int totalCount,int totalPage, List<T> list) {
		this.currentPage = currentPage;
		this.currentCount = currentCount;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.list = list;
	}
}
