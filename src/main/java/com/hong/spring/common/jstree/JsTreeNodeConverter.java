package com.hong.spring.common.jstree;

import java.util.List;

interface JsTreeNodeConverter<S> {
	
	JsTreeNode convert(S source);

	List<JsTreeNode> convert(List<S> source);

}
