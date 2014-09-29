package com.dataart.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataart.spring.model.Category;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
@Transactional
public class CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Category selectById(long categoryId) {
		Session session = sessionFactory.getCurrentSession();
		Category category = (Category) session.createCriteria(Category.class)
				.add(Restrictions.eq("id", categoryId))
				.uniqueResult();
		return category;
	}

	public List<Category> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Category.class)
				.add(Restrictions.gt("id", 0L))
				.list();
	}

}
