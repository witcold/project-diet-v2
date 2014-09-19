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
import com.dataart.spring.utils.Gender;
import com.dataart.spring.utils.PasswordHashing;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
public class UserDAO {

	private JdbcTemplate template;

	private class UserResultSetExtractor implements ResultSetExtractor<User> {
		public UserResultSetExtractor() {
		}

		@Override
		public User extractData(ResultSet rs) throws SQLException, DataAccessException {
			User user = null;
			if (rs.next()) {
				user = new User();
				user.setLogin(rs.getString("login"));
				user.setId(rs.getLong("user_id"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setGender(Gender.get(rs.getString("gender").charAt(0)));
				user.setBirthDate(rs.getDate("birth_date"));
				user.setCountryId(rs.getString("country_id"));
				user.setHeight(rs.getInt("height"));
				user.setActivityLevel(rs.getFloat("activity_level"));
			}
			return user;
		}
	}

	public boolean insert(final User user) {
		final String sql = "INSERT INTO users (login, password, first_name, last_name, gender, birth_date, country_id, height, activity_level)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		KeyHolder holder = new GeneratedKeyHolder();
		int result = template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"user_id"});
				ps.setString(1, user.getLogin());
				ps.setString(2, PasswordHashing.encode(user.getPassword()));
				ps.setString(3, user.getFirstName());
				ps.setString(4, user.getLastName());
				ps.setString(5, "" + user.getGender().getValue());
				ps.setDate(6, new java.sql.Date(user.getBirthDate().getTime()));
				ps.setString(7, user.getCountryId());
				ps.setInt(8, user.getHeight());
				ps.setFloat(9, user.getActivityLevel());
				return ps;
			}
		}, holder);
		user.setId(holder.getKey().longValue());
		return result == 1;
	}

	public User selectByLogin(String login) {
		String sql = "SELECT login, user_id, password, first_name, last_name, gender, birth_date, country_id, height, activity_level"
					+ " FROM users"
					+ " WHERE (login = ?);";
		return template.query(sql, new UserResultSetExtractor(), login);
	}

	public boolean authenticate(User user) {
		User dbUser = selectByLogin(user.getLogin());
		if (dbUser != null
				&& dbUser.getPassword().equals(PasswordHashing.encode(user.getPassword()))) {
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
