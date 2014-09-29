package com.dataart.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataart.spring.model.User;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
@Transactional
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void insert(final User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	public User selectByLogin(String login) {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.createCriteria(User.class)
				.add(Restrictions.eq("login", login))
				.uniqueResult();
	}

	public User authenticate(User user) {
		User dbUser = selectByLogin(user.getLogin());
		if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
			return dbUser;
		}
		return null;
	}

}
