package com.hong.spring.common.web.support;

import java.util.LinkedHashMap;

import org.springframework.data.domain.Page;

import com.hong.spring.common.jqgrid.JqGrid;

public class AjaxReturn extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	
	public static final String ATTR_NAME_MSG = "msg";
	public static final String ATTR_NAME_RESULT = "result";
	public static final String ATTR_NAME_JQ_GRID = "jqGrid";

	public static final String ATTR_VALUE_SUCCESS = "success";
	public static final String ATTR_VALUE_FAILURE = "failure";

	public void setResult(String result) {
		this.put(ATTR_NAME_RESULT, result);
	}

	public void setMsg(String msg) {
		this.put(ATTR_NAME_MSG, msg);
	}

	private AjaxReturn() {
	}

	public AjaxReturn addAttribute(String attributeName, Object attributeValue) {
		this.put(attributeName, attributeValue);
		return this;
	}

	public static AjaxReturn jsonWithError(String errMessage) {
		AjaxReturn map = new AjaxReturn();
		map.setResult(ATTR_VALUE_FAILURE);
		map.setMsg(errMessage);
		return map;
	}

	public static AjaxReturn jsonWithError(Exception exception) {
		return jsonWithError(exception.getMessage());
	}

	public static AjaxReturn jsonWithSuccessResult() {
		AjaxReturn map = new AjaxReturn();
		map.setResult(ATTR_VALUE_SUCCESS);
		return map;
	}

	public static AjaxReturn jsonWithJqGridAndSuccessResult(Page<?> page) {
		return jsonWithSuccessResult().addAttribute(ATTR_NAME_JQ_GRID, new JqGrid<>(page));
	}

}
