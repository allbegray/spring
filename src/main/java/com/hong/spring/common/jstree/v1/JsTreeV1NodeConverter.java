package com.hong.spring.common.jstree.v1;

import java.util.List;

interface JsTreeV1NodeConverter<S> {
	
	JsTreeV1Node convert(S source);

	List<JsTreeV1Node> convert(List<S> source);

}
