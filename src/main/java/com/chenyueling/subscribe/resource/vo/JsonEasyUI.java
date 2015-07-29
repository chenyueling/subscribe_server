package com.chenyueling.subscribe.resource.vo;

import java.util.List;

public class JsonEasyUI<T> {
	private Long total;
	private List<T> rows;
	public JsonEasyUI(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public JsonEasyUI() {
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}


}
