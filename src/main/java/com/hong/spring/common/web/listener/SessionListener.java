package com.hong.spring.common.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setMaxInactiveInterval(5 * 60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

	}

}
