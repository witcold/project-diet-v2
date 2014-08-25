/**
 * 
 */
package com.dataart.spring.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dataart.spring.model.User;
import com.dataart.spring.utils.PasswordHashing;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
public class UserDAO {

	private JdbcTemplate template;

	public boolean insert(User user) {
		int result = template.update(
				"INSERT INTO users (login, password, first_name, last_name) VALUES (?, ?, ?, ?)",
				new Object[] { user.getLogin(), PasswordHashing.encode(user.getPassword()), user.getFirstName(), user.getLastName() });
		return result == 1;
	}

	public boolean authenticate(User user) {
		String passwordHash = null;
		try {
			passwordHash = template.queryForObject(
					"SELECT password FROM users WHERE login = ?",
					new Object[] { user.getLogin() }, String.class);
		} catch (Exception e) {
		}
		return passwordHash != null && passwordHash.equals(PasswordHashing.encode(user.getPassword()));
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

}
