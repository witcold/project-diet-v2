/**
 * 
 */
package com.dataart.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.dataart.spring.status.DBStatus;

/**
 * @author vmeshcheryakov
 *
 */
@Configuration
@PropertySource(value = { "classpath:db.properties" })
public class RootContext {

	@Autowired
	private Environment env;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("driverClassName"));
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setUsername(env.getProperty("user.login"));
		dataSource.setPassword(env.getProperty("user.password"));
		return dataSource;
	}

	@Bean(name = "dbStatus")
	public DBStatus getDBStatus() {
		return new DBStatus();
	}

}
