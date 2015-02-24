package com.hong.spring.common.config.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.hong.spring.common.config.PersistenceConfig;
import com.hong.spring.common.config.RootConfig;
import com.hong.spring.common.config.SecurityConfig;
import com.hong.spring.common.config.WebMvcConfig;
import com.hong.spring.common.config.sitemesh.MyConfigurableSiteMeshFilter;
import com.hong.spring.common.web.filter.CrossScriptingFilter;
import com.hong.spring.common.web.listener.SessionListener;

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
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[] { encodingFilter, new HiddenHttpMethodFilter(), new CrossScriptingFilter(), new MyConfigurableSiteMeshFilter() };
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