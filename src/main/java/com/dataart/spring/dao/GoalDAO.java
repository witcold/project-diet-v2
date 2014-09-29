package com.dataart.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataart.spring.model.Goal;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
@Transactional
public class GoalDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void insert(Goal goal) {
		Session session = sessionFactory.getCurrentSession();
		session.save(goal);
	}

	public void update(Goal goal) {
		Session session = sessionFactory.getCurrentSession();
		session.update(goal);
	}

	public void insertOrUpdate (Goal goal) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(goal);
	}

	public void delete(Goal goal) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(goal);
	}

	public List<Goal> selectAllByUserId(long userId) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Goal.class)
				.add(Restrictions.eq("userId", userId))
				.addOrder(Order.asc("date"))
				.list();
	}

	public Goal selectLastByUserId(long userId) {
		Session session = sessionFactory.getCurrentSession();
		return (Goal) session.createCriteria(Goal.class)
				.add(Restrictions.eq("userId", userId))
				.addOrder(Order.asc("date"))
				.setMaxResults(1)
				.uniqueResult();
	}

}
