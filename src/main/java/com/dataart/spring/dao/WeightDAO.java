/**
 * 
 */
package com.dataart.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
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
		int result = template.update(sql, weight.getUserId(), weight.getDate(), weight.getWeight());
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

	public List<Weight> selectByUserIdWithRange(final long userId, final Date from, final Date to) {
		final String sql = "SELECT user_id, date, weight FROM weights WHERE (user_id = ?) AND (date BETWEEN ? AND ?);";
//		List<Weight> list = template.query(sql,
//				new Object[] { userId, from, to },
//				new int[] { Types.BIGINT, Types.DATE, Types.DATE },
//				new RowMapper<Weight>() {
//					@Override
//					public Weight mapRow(ResultSet rs, int rowNum)
//							throws SQLException {
//						Weight weight = new Weight();
//						weight.setUserId(rs.getLong(1));
//						weight.setDate(rs.getDate(2));
//						weight.setWeight(rs.getFloat(3));
//						return weight;
//					}
//				});
		List<Weight> list = template.query(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setLong(1, userId);
						ps.setDate(2, new java.sql.Date(from.getTime()));
						ps.setDate(3, new java.sql.Date(to.getTime()));
						return ps;
					}
				},
				new RowMapper<Weight>() {
					@Override
					public Weight mapRow(ResultSet rs, int rowNum) throws SQLException {
						Weight weight = new Weight();
						weight.setUserId(rs.getLong(1));
						weight.setDate(rs.getDate(2));
						weight.setWeight(rs.getFloat(3));
						return weight;
					}
				}
		);
		return list;
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

}
