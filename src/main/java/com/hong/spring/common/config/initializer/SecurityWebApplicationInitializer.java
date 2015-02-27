package com.hong.spring.common.config.initializer;

import javax.servlet.ServletContext;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import com.hong.spring.common.config.sitemesh.MyConfigurableSiteMeshFilter;
import com.hong.spring.common.web.filter.CrossScriptingFilter;

@Order(2)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		insertFilters(servletContext, encodingFilter);

		insertFilters(servletContext, new HiddenHttpMethodFilter());

		MultipartFilter multipartFilter = new MultipartFilter();
		multipartFilter.setMultipartResolverBeanName("multipartResolver");
		insertFilters(servletContext, multipartFilter);
		
		insertFilters(servletContext, new CrossScriptingFilter());
	}

	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		appendFilters(servletContext, new MyConfigurableSiteMeshFilter());
	}

}