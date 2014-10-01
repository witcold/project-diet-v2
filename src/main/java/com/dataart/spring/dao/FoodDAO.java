package com.dataart.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataart.spring.model.Food;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
@Transactional
public class FoodDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Map<Long, Food> selectByIds(List<Long> ids) {
		Map<Long, Food> map = new HashMap<Long, Food>();
		if (ids.size() > 0) {
			Session session = sessionFactory.getCurrentSession();
			List<Food> list = session.createCriteria(Food.class)
					.add(Restrictions.in("id", ids))
					.list();
			for (Food food : list) {
				map.put(food.getId(), food);
			}
		}
		return map;
	}

	public List<Food> selectByCategoryId(Long categoryId) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Food.class)
				.add(Restrictions.eq("categoryId", categoryId))
				.list();
	}

	public List<Food> selectByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Food.class)
				.add(Restrictions.or(
						Restrictions.ilike("nameEn", name, MatchMode.ANYWHERE),
						Restrictions.ilike("nameRu", name, MatchMode.ANYWHERE)))
				.list();
	}

	public List<Food> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Food.class)
				.list();
	}

}
