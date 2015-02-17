package com.hong.spring.common.jqgrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public class JqGrid<T> {

	private long page;
	private long total;
	private long records;
	private List<T> rows;
	private Map<String, Object> userdatas;

	public JqGrid() {
	}

	public JqGrid(Page<T> page) {
		this.setRows(page.getContent());
		this.setRecords(page.getTotalElements());
		this.setTotal(page.getTotalPages());
		this.setPage(page.getNumber() + 1);
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public List<T> getRows() {
		if (rows == null) {
			this.setRows(new ArrayList<T>());
		}
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public void addRow(T t) {
		this.getRows().add(t);
	}

	public Map<String, Object> getUserdatas() {
		if (userdatas == null) {
			this.setUserdatas(new HashMap<>());
		}
		return userdatas;
	}

	public void setUserdatas(Map<String, Object> userdatas) {
		this.userdatas = userdatas;
	}

	public void addUserdata(String key, Object value) {
		this.getUserdatas().put(key, value);
	}

}