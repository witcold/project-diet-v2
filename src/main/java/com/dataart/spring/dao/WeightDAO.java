/**
 * 
 */
package com.dataart.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

	public boolean insert(final Weight weight) {
		final String sql = "INSERT INTO weights (user_id, date, weight) VALUES (?, ?, ?);";
		int result = template.update(sql);
		return result == 1;
	}

	public List<Weight> selectByUserId(final long userId) {
		final String sql = "SELECT user_id, date, weight FROM weights WHERE user_id = ?;";
		List<Weight> list = template.query(sql, new RowMapper<Weight>() {
			@Override
			public Weight mapRow(ResultSet rs, int rowNum) throws SQLException {
				Weight weight = new Weight();
				weight.setUserId(rs.getLong(1));
				weight.setDate(rs.getDate(2));
				weight.setWeight(rs.getFloat(3));
				return weight;
			}
		}, userId);
		return list;
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

}
