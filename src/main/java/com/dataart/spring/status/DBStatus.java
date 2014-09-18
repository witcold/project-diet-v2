/**
 * 
 */
package com.dataart.spring.status;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
public class DBStatus {

	private JdbcTemplate template;

	public boolean getDBStatus() {
		Date result = template.queryForObject("SELECT now()", Date.class);
		return result != null;
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

}
