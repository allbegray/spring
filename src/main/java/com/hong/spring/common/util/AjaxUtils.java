package com.hong.spring.common.util;

import javax.servlet.http.HttpServletRequest;

public class AjaxUtils {

	private AjaxUtils() {
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

}
