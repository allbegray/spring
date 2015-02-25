package com.hong.spring.common.jstree;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class JsTreeAttributes {

	private Serializable id;
	private Serializable parentId;
	private String name;
	
	/**
	 * 유형
	 */
	private String type;

	public JsTreeAttributes() {
	}

	public JsTreeAttributes(Serializable id, Serializable parentId, String name, String type) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.type = type;
	}

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	public Serializable getParentId() {
		return parentId;
	}

	public void setParentId(Serializable parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
