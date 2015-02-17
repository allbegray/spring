package com.hong.spring.modules.board.support;

import com.hong.spring.common.web.support.SearchContext;

public class BoardSearchContext extends SearchContext {

	private String select_type;
	private String keyword;

	public String getSelect_type() {
		return select_type;
	}

	public void setSelect_type(String select_type) {
		this.select_type = select_type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
