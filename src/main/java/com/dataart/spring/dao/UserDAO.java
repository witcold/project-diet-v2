package com.dataart.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataart.spring.model.User;
import com.dataart.spring.utils.PasswordHashing;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
@Transactional
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean insert(final User user) {
		//long id = (long) template.save(user);
		//user.setId(id);
		return true;
	}

	public User selectByLogin(String login) {
//		String sql = "SELECT login, user_id, password, first_name, last_name, gender, birth_date, country_id, height, activity_level"
//					+ " FROM users"
//					+ " WHERE (login = ?)";
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
		return user;
	}

	public boolean authenticate(User user) {
		User dbUser = selectByLogin(user.getLogin());
		if (dbUser != null
				&& dbUser.getPassword().equals(PasswordHashing.encode(user.getPassword()))) {
			user.clone(dbUser);
			return true;
		}
		return false;
	}

}
