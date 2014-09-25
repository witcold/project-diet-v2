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

import com.dataart.spring.model.Weight;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
public class WeightDAO {

	private JdbcTemplate template;

	private static final class WeightRowMapper implements RowMapper<Weight> {
		public WeightRowMapper() {
		}

		@Override
		public Weight mapRow(ResultSet rs, int rowNum) throws SQLException {
			return getWeight(rs);
		}
	}

	private static final class WeightResultSetExtractor implements ResultSetExtractor<Weight> {
		public WeightResultSetExtractor() {
		}

		@Override
		public Weight extractData(ResultSet rs) throws SQLException, DataAccessException {
			if (rs.next()) {
				return getWeight(rs);
			}
			return null;
		}
		
	}

	public boolean exists(Weight weight) {
		String sql = "SELECT COUNT(*)"
				+ " FROM weights"
				+ " WHERE (user_id = ?) AND (date = ?);";
		int result = template.queryForObject(sql, int.class, weight.getUserId(), weight.getDate());
		return result == 1;
	}

	public Weight selectOne(long userId, Date date) {
		String sql = "SELECT user_id, date, weight"
					+ " FROM weights"
					+ " WHERE (user_id = ?) AND (date = ?);";
		return template.query(sql, new WeightResultSetExtractor(), userId, date);
	}

	public boolean insert(Weight weight) {
		String sql = "INSERT INTO weights (user_id, date, weight)"
					+ " VALUES (?, ?, ?);";
		int result = template.update(sql, weight.getUserId(), weight.getDate(), weight.getWeight());
		return result == 1;
	}

	public boolean update(Weight weight) {
		String sql = "UPDATE weights SET weight = ?"
					+ " WHERE (user_id = ?) AND (date = ?);";
		int result = template.update(sql, weight.getWeight(), weight.getUserId(), weight.getDate());
		return result == 1;
	}

	public void insertOrUpdate (Weight weight) {
		if (exists(weight)) {
			update(weight);
		} else {
			insert(weight);
		}
	}

	public boolean delete(Weight weight) {
		String sql = "DELETE FROM weights"
					+ " WHERE (user_id = ?) AND (date = ?);";
		int result = template.update(sql, weight.getUserId(), weight.getDate());
		return result == 1;
	}

	public List<Weight> selectByUserIdWithRange(long userId, Date from, Date to) {
		String sql = "SELECT user_id, date, weight"
					+ " FROM weights"
					+ " WHERE (user_id = ?) AND (date BETWEEN ? AND ?)"
					+ " ORDER BY date ASC;";
		return template.query(sql, new WeightRowMapper(), userId, from, to);
	}

	public Weight selectLastByUserId(long userId) {
		String sql = "SELECT user_id, date, weight"
					+ " FROM weights"
					+ " WHERE (user_id = ?)"
					+ " ORDER BY date DESC"
					+ " LIMIT 1;";
		return template.query(sql, new WeightResultSetExtractor(), userId);
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

	static Weight getWeight(ResultSet rs) throws SQLException {
		Weight weight = new Weight();
		weight.setUserId(rs.getLong(1));
		weight.setDate(rs.getDate(2));
		weight.setWeight(rs.getFloat(3));
		return weight;
	}

}
