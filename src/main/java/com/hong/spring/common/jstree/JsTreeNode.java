package com.hong.spring.common.jstree;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hong.spring.common.serializer.JsTreeStateSerializer;

public class JsTreeNode {

	private String data;
	private JsTreeState state = JsTreeState.CLOSED;
	private List<JsTreeNode> children;
	private JsTreeAttributes attributes;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
		this.getAttributes().setName(data);
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
