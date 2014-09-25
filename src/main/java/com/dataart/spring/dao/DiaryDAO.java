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

import com.dataart.spring.dto.CaloriesDTO;
import com.dataart.spring.model.Diary;
import com.dataart.spring.utils.DateUtils;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
public class DiaryDAO {

	private JdbcTemplate template;

	private static final class DiaryRowMapper implements RowMapper<Diary> {
		public DiaryRowMapper() {
		}

		@Override
		public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
			return getDiary(rs);
		}
	}

	private static final class DiaryResultSetExtractor implements ResultSetExtractor<Diary> {
		public DiaryResultSetExtractor() {
		}

		@Override
		public Diary extractData(ResultSet rs) throws SQLException, DataAccessException {
			if (rs.next()) {
				return getDiary(rs);
			}
			return null;
		}
		
	}

	private class CaloriesRowMapper implements RowMapper<CaloriesDTO> {
		public CaloriesRowMapper() {
		}

		@Override
		public CaloriesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CaloriesDTO caloriesDTO = new CaloriesDTO();
			caloriesDTO.setDate(rs.getDate(1));
			caloriesDTO.setCalories(rs.getInt(2));
			return caloriesDTO;
		}
	}

	public boolean exists(Diary diary) {
		String sql = "SELECT COUNT(*)"
				+ " FROM diaries"
				+ " WHERE (user_id = ?) AND (food_id = ?) AND (timestamp = ?);";
		int result = template.queryForObject(sql, Integer.class, diary.getUserId(), diary.getFoodId(), diary.getTimestamp());
		return result == 1;
	}

	public Diary selectOne(long userId, long foodId, Date timestamp) {
		String sql = "SELECT user_id, food_id, timestamp, weight"
				+ " FROM diaries"
				+ " WHERE (user_id = ?) AND (food_id = ?) AND (timestamp = ?);";
	return template.query(sql, new DiaryResultSetExtractor(), userId, foodId, timestamp);
	}

	public boolean insert(Diary diary) {
		String sql = "INSERT INTO diaries (user_id, food_id, timestamp, weight)"
					+ " VALUES (?, ?, ?, ?);";
		int result = template.update(sql, diary.getUserId(), diary.getFoodId(),
				diary.getTimestamp(), diary.getWeight());
		return result == 1;
	}

	public boolean update(Diary diary) {
		String sql = "UPDATE diaries"
					+ " SET weight = ?"
					+ " WHERE (user_id = ?) AND (food_id = ?) AND (timestamp = ?);";
		int result = template.update(sql, diary.getWeight(), diary.getUserId(),
				diary.getFoodId(), diary.getTimestamp());
		return result == 1;
	}

	public void insertOrUpdate (Diary diary) {
		if (exists(diary)) {
			update(diary);
		} else {
			insert(diary);
		}
	}

	public boolean delete(Diary diary) {
		String sql = "DELETE FROM diaries"
					+ " WHERE (user_id = ?) AND (food_id = ?) AND (timestamp = ?);";
		int result = template.update(sql, diary.getUserId(), diary.getFoodId(),
				diary.getTimestamp());
		return result == 1;
	}

	private List<Diary> selectByUserIdForInterval(long userId, Date from,
			Date to) {
		String sql = "SELECT user_id, food_id, timestamp, weight"
					+ " FROM diaries"
					+ " WHERE (user_id = ?) AND (timestamp BETWEEN ? AND ?);";
		return template.query(sql, new DiaryRowMapper(), userId, from, to);
	}

	public List<Diary> selectByUserIdWithRange(long userId, Date from, Date to) {
		from = DateUtils.getDayStart(from);
		to = DateUtils.getDayEnd(to);
		return selectByUserIdForInterval(userId, from, to);
	}

	public List<Diary> selectByUserIdForDate(long userId, Date date) {
		Date from = DateUtils.getDayStart(date);
		Date to = DateUtils.getDayEnd(date);
		return selectByUserIdForInterval(userId, from, to);
	}

	public List<CaloriesDTO> getAggregatedInfo(long userId, Date from, Date to) {
		String sql = "SELECT timestamp::date as date, SUM(weight*calories*10)"
					+ " FROM diaries d"
					+ " JOIN foods f ON (d.food_id = f.food_id)"
					+ " WHERE (user_id = ?) AND (timestamp BETWEEN ? AND ?)"
					+ " GROUP BY user_id, date"
					+ " ORDER BY 1 ASC;";
		return template.query(sql, new CaloriesRowMapper(), userId, from, to);
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

	static Diary getDiary(ResultSet rs) throws SQLException {
		Diary diary = new Diary();
		diary.setUserId(rs.getLong("user_id"));
		diary.setFoodId(rs.getLong("food_id"));
		diary.setTimestamp(rs.getTimestamp("timestamp"));
		diary.setWeight(rs.getFloat("weight"));
		return diary;
	}

}
