package com.youpassed.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
//@ComponentScan(basePackages = {"com.youpassed.service"})
public class JpaConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(JpaConfig.class);

	@Bean
	public DataSource dataSource () {
		try {
			EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
			return dbBuilder.setType(EmbeddedDatabaseType.H2)
					.addScripts("classpath:dЬ/schema.sql", "classpath:dЬ/data.sql")
					.build();
		} catch (Exception е) {
			LOGGER.error("Embedded DataSource bean cannot Ье created!", е);
			return null;
		}
	}


}
