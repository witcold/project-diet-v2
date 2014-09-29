package com.dataart.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataart.spring.dto.CaloriesDTO;
import com.dataart.spring.model.Diary;
import com.dataart.spring.utils.DateUtils;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
@Transactional
public class DiaryDAO {

	@Autowired
	private SessionFactory sessionFactory;

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

	public void insert(Diary diary) {
		Session session = sessionFactory.getCurrentSession();
		session.save(diary);
	}

	public void update(Diary diary) {
		Session session = sessionFactory.getCurrentSession();
		session.update(diary);
	}

	public void insertOrUpdate (Diary diary) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(diary);
	}

	public void delete(Diary diary) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(diary);
	}

	private List<Diary> selectByUserIdForInterval(long userId, Date from, Date to) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Diary.class)
				.add(Restrictions.eq("id", userId))
				.add(Restrictions.between("timestamp", from, to))
				.list();
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
		String sql = "SELECT DATE(timestamp) as date, SUM(weight*calories*10)"
					+ " FROM diaries d"
					+ " JOIN foods f ON (d.food_id = f.food_id)"
					+ " WHERE (user_id = :userId) AND (timestamp BETWEEN :from AND :to)"
					+ " GROUP BY user_id, date"
					+ " ORDER BY date ASC;";
		Session session = sessionFactory.getCurrentSession();
		return session.createSQLQuery(sql)
				.setLong("userId", userId)
				.setTimestamp("from", from)
				.setTimestamp("to", to)
				.list();
	}

}
