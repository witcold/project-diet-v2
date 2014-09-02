/**
 * 
 */
package com.dataart.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

	public boolean insert(final User user) {
		final String sql = "INSERT INTO users (login, password, first_name, last_name) VALUES (?, ?, ?, ?);";
		KeyHolder holder = new GeneratedKeyHolder();
		int result = template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"user_id"});
				ps.setString(1, user.getLogin());
				ps.setString(2, PasswordHashing.encode(user.getPassword()));
				ps.setString(3, user.getFirstName());
				ps.setString(4, user.getLastName());
				return ps;
			}
		}, holder);
		user.setId(holder.getKey().longValue());
		return result == 1;
	}

	public User selectByLogin(final String login) {
		return template.query(
				"SELECT user_id, password, first_name, last_name FROM users WHERE login = ?;",
				new Object[] { login }, new ResultSetExtractor<User>() {
					@Override
					public User extractData(ResultSet rs) throws SQLException, DataAccessException {
						User user = null;
						if (rs.next()) {
							user = new User();
							user.setLogin(login);
							user.setId(rs.getLong(1));
							user.setPassword(rs.getString(2));
							user.setFirstName(rs.getString(3));
							user.setLastName(rs.getString(4));
						}
						return user;
					}
				});
	}

	public boolean authenticate(User user) {
		User dbUser = selectByLogin(user.getLogin());
		if (dbUser != null && dbUser.getPassword().equals(PasswordHashing.encode(user.getPassword()))) {
			user.clone(dbUser);
			return true;
		}
		return false;
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

}
