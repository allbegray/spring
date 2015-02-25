package com.hong.spring.common.jstree.v1;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJsTreeV1NodeConverter<S> implements JsTreeV1NodeConverter<S> {

	@Override
	public abstract JsTreeV1Node convert(S source);

	@Override
	public List<JsTreeV1Node> convert(List<S> source) {
		List<JsTreeV1Node> nodes = new ArrayList<JsTreeV1Node>(source.size());
		for (S s : source) {
			nodes.add(this.convert(s));
		}
		return nodes;
	}

}
