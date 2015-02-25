package com.hong.spring.common.jstree;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hong.spring.common.serializer.JsTreeStateSerializer;

public class JsTreeNode {

	private String name;
	private JsTreeState state = JsTreeState.CLOSED;
	private List<JsTreeNode> children;
	private JsTreeAttributes attributes;

	public JsTreeNode() {
	}
	
	public JsTreeNode(String name, String id, String parentId) {
		this(name, id, parentId, null);
	}
	
	public JsTreeNode(String name, String id, String parentId, String type) {
		this.setName(name);
		this.setId(id);
		this.setParentId(parentId);
		this.setType(type);
	}

	@JsonProperty("data")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.getAttributes().setName(name);
	}
	
	public void setId(Serializable id) {
		this.getAttributes().setId(id);
	}
	
	public void setParentId(Serializable parentId) {
		this.getAttributes().setParentId(parentId);
	}
	
	public void setType(String type) {
		this.getAttributes().setType(type);
	}

	@JsonSerialize(using = JsTreeStateSerializer.class)
	public JsTreeState getState() {
		return state;
	}

	public void setState(JsTreeState state) {
		this.state = state;
	}
	
	public void addChild(JsTreeNode node) {
		this.getChildren().add(node);
	}

	public List<JsTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<JsTreeNode> children) {
		this.children = children;
	}

	@JsonProperty("attr")
	public JsTreeAttributes getAttributes() {
		if (attributes == null) {
			this.setAttributes(new JsTreeAttributes());
		}
		return attributes;
	}

	protected void setAttributes(JsTreeAttributes attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
