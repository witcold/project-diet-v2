package com.dataart.spring.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
				.add(Restrictions.eq("userId", userId))
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
		//new com.dataart.spring.dto.CaloriesDTO( ... )
		String hql = "SELECT day(d.timestamp) as date, SUM(d.weight*d.food.calories*10)"
					+ " FROM Diary as d"
					+ " WHERE (d.id = :userId) AND (d.timestamp BETWEEN :from AND :to)"
					+ " GROUP BY date"
					+ " ORDER BY date ASC";
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql)
				.setLong("userId", userId)
				.setDate("from", from)
				.setDate("to", to)
				.list();
	}

}
