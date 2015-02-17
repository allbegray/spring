package com.hong.spring.common.web.support;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class SearchContext {

	private int rows;
	private int page;
	private String sidx;
	private String sord = Sort.Direction.ASC.toString().toLowerCase();

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public Pageable getPageRequest() {
		Sort sort = null;
		if (StringUtils.hasText(sidx)) {
			sort = new Sort(Sort.Direction.fromString(sord), sidx.toUpperCase());
		}
		return new PageRequest(page - 1, rows, sort);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
