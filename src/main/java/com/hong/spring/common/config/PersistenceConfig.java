package com.hong.spring.common.config;

import org.jooq.ConnectionProvider;
import org.jooq.ExecuteListener;
import org.jooq.SQLDialect;
import org.jooq.TransactionProvider;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hong.spring.common.config.jooq.JOOQToSpringExceptionTransformer;
import com.hong.spring.common.config.jooq.SpringTransactionProvider;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class PersistenceConfig {

	@Autowired
	private Environment env;
	
	@Bean(destroyMethod = "shutdown")
	public EmbeddedDatabase dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource());
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setSqlScriptEncoding("UTF-8");
		populator.setIgnoreFailedDrops(true);
		populator.addScript(new ClassPathResource(env.getProperty("db.schema.script")));
		return populator;
	}

	@Bean
	public LazyConnectionDataSourceProxy lazyConnectionDataSource() {
		return new LazyConnectionDataSourceProxy(dataSource());
	}

	@Bean
	public TransactionAwareDataSourceProxy transactionAwareDataSource() {
		return new TransactionAwareDataSourceProxy(lazyConnectionDataSource());
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(lazyConnectionDataSource());
	}

	@Bean
	public ConnectionProvider connectionProvider() {
		return new DataSourceConnectionProvider(transactionAwareDataSource());
	}

	@Bean
	public ExecuteListener jooqToSpringExceptionTransformer() {
		return new JOOQToSpringExceptionTransformer();
	}
	
	@Bean
	public TransactionProvider transactionProvider() {
		return new SpringTransactionProvider();
	}

	@Bean
	public DefaultConfiguration configuration() {
		DefaultConfiguration jooqConfiguration = new DefaultConfiguration();

		jooqConfiguration.setConnectionProvider(connectionProvider());
		jooqConfiguration.setTransactionProvider(transactionProvider());
		jooqConfiguration.setExecuteListenerProvider(new DefaultExecuteListenerProvider(jooqToSpringExceptionTransformer()));

		String sqlDialectName = env.getRequiredProperty("jooq.sql.dialect");
		SQLDialect dialect = SQLDialect.valueOf(sqlDialectName);
		jooqConfiguration.setSQLDialect(dialect);

		return jooqConfiguration;
	}

	@Bean
	public DefaultDSLContext dsl() {
		return new DefaultDSLContext(configuration());
	}

}