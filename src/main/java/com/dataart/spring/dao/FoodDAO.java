/**
 * 
 */
package com.dataart.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dataart.spring.model.Food;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
public class FoodDAO {

	private NamedParameterJdbcTemplate template;

	private static RowMapper<Food> rowMapper = new RowMapper<Food>() {
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
	};

	public Map<Long, Food> selectByIds(List<Long> ids) {
		Map<Long, Food> map = new HashMap<Long, Food>();
		if (ids.size() > 0) {
			String sql = "SELECT food_id, category_id, name, calories, proteins, fats, carbohydrates FROM foods WHERE food_id IN (:ids);";
			List<Food> list = template.query(sql, new MapSqlParameterSource("ids", ids), rowMapper);
			for (Food food : list) {
				map.put(food.getId(), food);
			}
		}
		return map;
	}

	public List<Food> selectByCategoryId(Long categoryId) {
		String sql = "SELECT food_id, category_id, name, calories, proteins, fats, carbohydrates FROM foods WHERE (category_id = :categoryId);";
		return template.query(sql, new MapSqlParameterSource("categoryId", categoryId), rowMapper);
	}

	public List<Food> selectByName(String name) {
		String sql = "SELECT food_id, category_id, name, calories, proteins, fats,carbohydrates FROM foods WHERE (name ILIKE :name);";
		return template.query(sql, new MapSqlParameterSource("name", '%' + name + '%'), rowMapper);
	}

	public List<Food> selectAll() {
		String sql = "SELECT food_id, category_id, name, calories, proteins, fats, carbohydrates FROM foods ORDER BY 3 ASC;";
		return template.query(sql, rowMapper);
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new NamedParameterJdbcTemplate(ds);
	}

}
