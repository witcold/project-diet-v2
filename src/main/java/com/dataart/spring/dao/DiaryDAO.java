package com.dataart.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

	class DiaryRowMapper implements RowMapper<Diary> {
		@Override
		public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
			Diary diary = new Diary();
			diary.setUserId(rs.getLong("user_id"));
			diary.setFoodId(rs.getLong("food_id"));
			diary.setTimestamp(rs.getTimestamp("timestamp"));
			diary.setWeight(rs.getFloat("weight"));
			return diary;
		}
	}

	class CaloriesRowMapper implements RowMapper<CaloriesDTO> {
		@Override
		public CaloriesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CaloriesDTO caloriesDTO = new CaloriesDTO();
			caloriesDTO.setDate(rs.getDate(1));
			caloriesDTO.setCalories(rs.getInt(2));
			return caloriesDTO;
		}
	}

	public boolean insert(Diary diary) {
		String sql = "INSERT INTO diaries (user_id, food_id, timestamp, weight)"
					+ " VALUES (?, ?, ?, ?);";
		int result = template.update(sql, diary.getUserId(), diary.getFoodId(),
				diary.getTimestamp(), diary.getWeight());
		return result == 1;
	}

	public boolean update(Diary diary) {
		String sql = "UPDATE diaries" + " SET weight = ?"
					+ " WHERE (user_id = ?) AND (food_id = ?) AND (timestamp = ?);";
		int result = template.update(sql, diary.getWeight(), diary.getUserId(),
				diary.getFoodId(), diary.getTimestamp());
		return result == 1;
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

}
