package com.dataart.spring.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataart.spring.model.Weight;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
@Transactional
public class WeightDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void insert(Weight weight) {
		Session session = sessionFactory.getCurrentSession();
		session.save(weight);
	}

	public void update(Weight weight) {
		Session session = sessionFactory.getCurrentSession();
		session.update(weight);
	}

	public void insertOrUpdate (Weight weight) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(weight);
	}

	public void delete(Weight weight) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(weight);
	}

	public List<Weight> selectByUserIdWithRange(long userId, Date from, Date to) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Weight.class)
				.add(Restrictions.eq("userId", userId))
				.add(Restrictions.between("date", from, to))
				.addOrder(Order.asc("date"))
				.list();
	}

	public Weight selectLastByUserId(long userId) {
		Session session = sessionFactory.getCurrentSession();
		return (Weight) session.createCriteria(Weight.class)
				.add(Restrictions.eq("userId", userId))
				.addOrder(Order.desc("date"))
				.setMaxResults(1)
				.uniqueResult();
	}

}
