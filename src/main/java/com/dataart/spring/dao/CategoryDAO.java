package com.dataart.spring.dao;

import org.hibernate.FetchMode;
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
				.setFetchMode("subcategories", FetchMode.JOIN)
				.uniqueResult();
		return category;
	}

}
