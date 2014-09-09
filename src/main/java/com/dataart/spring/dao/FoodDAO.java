/**
 * 
 */
package com.dataart.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dataart.spring.model.Food;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
public class FoodDAO {

	private JdbcTemplate template;

	public List<Food> selectAll() {
		String sql = "SELECT food_id, category_id, name, calories, proteins, fats, carbohydrates FROM foods;";
		List<Food> list = template.query(sql, new RowMapper<Food>() {
			@Override
			public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
				Food food = new Food();
				food.setId(rs.getLong(1));
				food.setCategoryId(rs.getLong(2));
				food.setName(rs.getString(3));
				food.setCalories(rs.getInt(4));
				food.setProteins(rs.getInt(5));
				food.setFats(rs.getInt(6));
				food.setCarbohydrates(rs.getInt(7));
				return food;
			}
		});
		return list;
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

}
