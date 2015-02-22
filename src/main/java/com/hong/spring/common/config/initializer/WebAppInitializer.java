package com.hong.spring.common.config.initializer;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.hong.spring.common.config.PersistenceConfig;
import com.hong.spring.common.config.RootConfig;
import com.hong.spring.common.config.SecurityConfig;
import com.hong.spring.common.config.WebMvcConfig;
import com.hong.spring.common.config.sitemesh.MyConfigurableSiteMeshFilter;

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
		return new Filter[] { encodingFilter, new HiddenHttpMethodFilter(), new MyConfigurableSiteMeshFilter() };
	}

}