/**
 * 
 */
package com.dataart.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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

	public User selectByLogin(final String login) {
		return template.query(
				"SELECT password, first_name, last_name FROM users WHERE login = ?",
				new Object[] { login }, new ResultSetExtractor<User>() {
					@Override
					public User extractData(ResultSet rs) throws SQLException, DataAccessException {
						User user = new User();
						if (rs.next()) {
							user.setLogin(login);
							user.setPassword(rs.getString(1));
							user.setFirstName(rs.getString(2));
							user.setLastName(rs.getString(3));
						}
						return user;
					}
				});
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

}
