package com.hong.spring.common.config.sitemesh;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MyConfigurableSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addExcludedPath("/css/*");
        builder.addExcludedPath("/img/*");
        builder.addExcludedPath("/js/*");
        builder.addExcludedPath("/lib/*");

        builder.addDecoratorPath("/*", "/WEB-INF/decorators/default.jsp");
    }

}
