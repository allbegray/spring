package com.hong.spring.common.jstree.v1;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractJsTreeV1NodeConverter<S> implements JsTreeV1NodeConverter<S> {

	@Override
	public abstract JsTreeV1Node convert(S source);

	@Override
	public List<JsTreeV1Node> convert(List<S> source) {
		return source.stream().map(s -> this.convert(s)).collect(Collectors.toList());
	}

}
