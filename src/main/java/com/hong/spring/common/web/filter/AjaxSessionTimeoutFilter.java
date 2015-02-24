package com.hong.spring.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

public class AjaxSessionTimeoutFilter implements Filter {

	private boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (isAjaxRequest(req)) {
			try {
				chain.doFilter(req, res);
			} catch (AccessDeniedException e) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
			} catch (AuthenticationException e) {
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {
	}

}
