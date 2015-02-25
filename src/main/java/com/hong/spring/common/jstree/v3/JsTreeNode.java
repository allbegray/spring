package com.hong.spring.common.jstree.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JsTreeNode {

	private Serializable id;
	private Serializable parent;
	private String text;
	private String icon;
	private JsTreeNodeState state;
	private List<JsTreeNode> children;

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	public Serializable getParent() {
		return parent;
	}

	public void setParent(Serializable parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public JsTreeNodeState getState() {
		if (state == null) {
			this.setState(new JsTreeNodeState());
		}
		return state;
	}

	private void setState(JsTreeNodeState state) {
		this.state = state;
	}

	public List<JsTreeNode> getChildren() {
		if (children == null) {
			this.setChildren(new ArrayList<JsTreeNode>());
		}
		return children;
	}

	private void setChildren(List<JsTreeNode> children) {
		this.children = children;
	}

}
