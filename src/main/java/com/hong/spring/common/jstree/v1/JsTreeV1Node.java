package com.hong.spring.common.jstree.v1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hong.spring.common.serializer.JsTreeV1StateSerializer;

public class JsTreeV1Node {

	private String name;
	private JsTreeV1State state = JsTreeV1State.CLOSED;
	private List<JsTreeV1Node> children;
	private Map<String, Object> attributes;

	public JsTreeV1Node() {
	}
	
	public JsTreeV1Node(String name, String id) {
		this.setName(name);
		this.setId(id);
	}

	@JsonProperty("data")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(Serializable id) {
		this.getAttributes().put("id", id);
	}
	
	public void setParentId(Serializable parentId) {
		this.getAttributes().put("parentId", parentId);
	}
	
	@JsonSerialize(using = JsTreeV1StateSerializer.class)
	public JsTreeV1State getState() {
		return state;
	}

	public void setState(JsTreeV1State state) {
		this.state = state;
	}
	
	public void addChild(JsTreeV1Node node) {
		this.getChildren().add(node);
	}

	public List<JsTreeV1Node> getChildren() {
		return children;
	}

	public void setChildren(List<JsTreeV1Node> children) {
		this.children = children;
	}

	@JsonProperty("attr")
	public Map<String, Object> getAttributes() {
		if (attributes == null) {
			this.setAttributes(new HashMap<String, Object>());
		}
		return attributes;
	}

	private void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
