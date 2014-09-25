package com.dataart.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dataart.spring.model.Goal;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
public class GoalDAO {

	private JdbcTemplate template;

	private static final class GoalRowMapper implements RowMapper<Goal> {
		public GoalRowMapper() {
		}

		@Override
		public Goal mapRow(ResultSet rs, int rowNum) throws SQLException {
			return getGoal(rs);
		}
	}

	private static final class GoalResultSetExtractor implements ResultSetExtractor<Goal> {
		public GoalResultSetExtractor() {
		}

		@Override
		public Goal extractData(ResultSet rs) throws SQLException, DataAccessException {
			if (rs.next()) {
				return getGoal(rs);
			}
			return null;
		}
		
	}

	public boolean exists(Goal goal) {
		String sql = "SELECT COUNT(*)"
				+ " FROM goals"
				+ " WHERE (user_id = ?) AND (date = ?);";
		int result = template.queryForObject(sql, int.class, goal.getUserId(), goal.getDate());
		return result == 1;
	}

	public Goal selectOne(long userId, Date date) {
		String sql = "SELECT user_id, date, weight"
					+ " FROM goals"
					+ " WHERE (user_id = ?) AND (date = ?);";
		return template.query(sql, new GoalResultSetExtractor(), userId, date);
	}

	public boolean insert(Goal goal) {
		String sql = "INSERT INTO goals (user_id, date, weight)"
					+ " VALUES (?, ?, ?);";
		int result = template.update(sql, goal.getUserId(), goal.getDate(), goal.getWeight());
		return result == 1;
	}

	public boolean update(Goal goal) {
		String sql = "UPDATE goals SET weight = ?"
					+ " WHERE (user_id = ?) AND (date = ?);";
		int result = template.update(sql, goal.getWeight(), goal.getUserId(), goal.getDate());
		return result == 1;
	}

	public void insertOrUpdate (Goal goal) {
		if (exists(goal)) {
			update(goal);
		} else {
			insert(goal);
		}
	}

	public boolean delete(Goal goal) {
		String sql = "DELETE FROM goals"
					+ " WHERE (user_id = ?) AND (date = ?);";
		int result = template.update(sql, goal.getUserId(), goal.getDate());
		return result == 1;
	}

	public List<Goal> selectAllByUserId(long userId) {
		String sql = "SELECT user_id, date, weight"
					+ " FROM goals"
					+ " WHERE (user_id = ?)"
					+ " ORDER BY date ASC;";
		return template.query(sql, new GoalRowMapper(), userId);
	}

	public Goal selectLastByUserId(long userId) {
		String sql = "SELECT user_id, date, weight"
					+ " FROM goals"
					+ " WHERE (user_id = ?)"
					+ " ORDER BY date DESC"
					+ " LIMIT 1;";
		return template.query(sql, new GoalResultSetExtractor(), userId);
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

	static Goal getGoal(ResultSet rs) throws SQLException {
		Goal goal = new Goal();
		goal.setUserId(rs.getLong(1));
		goal.setDate(rs.getDate(2));
		goal.setWeight(rs.getFloat(3));
		return goal;
	}

}
