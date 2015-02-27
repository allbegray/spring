package com.hong.spring.common.config.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.hong.spring.common.config.PersistenceConfig;
import com.hong.spring.common.config.RootConfig;
import com.hong.spring.common.config.SecurityConfig;
import com.hong.spring.common.config.WebMvcConfig;
import com.hong.spring.common.web.listener.SessionListener;

@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class, SecurityConfig.class, PersistenceConfig.class, WebMvcConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(new SessionListener());
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
//		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

}