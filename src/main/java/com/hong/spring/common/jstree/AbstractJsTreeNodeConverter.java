package com.hong.spring.common.jstree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJsTreeNodeConverter<S> implements JsTreeNodeConverter<S> {

	@Override
	public abstract JsTreeNode convert(S source);

	@Override
	public List<JsTreeNode> convert(List<S> source) {
		List<JsTreeNode> nodes = new ArrayList<JsTreeNode>(source.size());
		for (S s : source) {
			nodes.add(this.convert(s));
		}
		return nodes;
	}

}
