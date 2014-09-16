/**
 * 
 */
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

	public boolean insert(Diary diary) {
		String sql = "INSERT INTO diaries (user_id, food_id, timestamp, weight) VALUES (?, ?, ?, ?);";
		int result = template.update(sql, diary.getUserId(), diary.getFoodId(), diary.getTimestamp(), diary.getWeight());
		return result == 1;
	}

	public boolean update(Diary diary) {
		String sql = "UPDATE diaries SET weight = ? WHERE (user_id = ?) AND (food_id = ?) AND (timestamp = ?);";
		int result = template.update(sql, diary.getWeight(), diary.getUserId(), diary.getFoodId(), diary.getTimestamp());
		return result == 1;
	}

	public boolean delete(Diary diary) {
		String sql = "DELETE FROM diaries WHERE (user_id = ?) AND (food_id = ?) AND (timestamp = ?);";
		int result = template.update(sql, diary.getUserId(), diary.getFoodId(), diary.getTimestamp());
		return result == 1;
	}

	protected List<Diary> selectByUserIdForInterval(long userId, Date from, Date to) {
		String sql = "SELECT user_id, food_id, timestamp, weight FROM diaries WHERE (user_id = ?) AND (timestamp BETWEEN ? AND ?);";
		return template.query(sql, new RowMapper<Diary>() {
			@Override
			public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
				Diary diary = new Diary();
				diary.setUserId(rs.getLong(1));
				diary.setFoodId(rs.getLong(2));
				diary.setTimestamp(rs.getTimestamp(3));
				diary.setWeight(rs.getFloat(4));
				return diary;
			}
		}, userId, from, to);
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
		String sql = "SELECT timestamp::date as date, SUM(weight*calories*10) FROM diaries d JOIN foods f ON (d.food_id = f.food_id) WHERE (user_id = ?) AND (timestamp BETWEEN ? AND ?) GROUP BY user_id, date;";
		return template.query(sql, new RowMapper<CaloriesDTO>() {
			@Override
			public CaloriesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CaloriesDTO dto = new CaloriesDTO();
				dto.setDate(rs.getDate(1));
				dto.setCalories(rs.getInt(2));
				return dto;
			}
		}, userId, from, to);
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

}
