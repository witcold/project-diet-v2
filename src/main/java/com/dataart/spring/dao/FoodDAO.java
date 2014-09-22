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

	private class FoodRowMapper implements RowMapper<Food> {
		public FoodRowMapper() {
		}

		@Override
		public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
			Food food = new Food();
			food.setId(rs.getLong("food_id"));
			food.setCategoryId(rs.getLong("category_id"));
			food.setNameEn(rs.getString("name_en"));
			food.setNameRu(rs.getString("name_ru"));
			food.setCalories(rs.getInt("calories"));
			food.setProteins(rs.getInt("proteins"));
			food.setFats(rs.getInt("fats"));
			food.setCarbohydrates(rs.getInt("carbohydrates"));
			return food;
		}
	}

	public Map<Long, Food> selectByIds(List<Long> ids) {
		String sql = "SELECT food_id, category_id, name_en, name_ru, calories, proteins, fats, carbohydrates"
					+ " FROM foods"
					+ " WHERE food_id IN (:ids);";
		Map<Long, Food> map = new HashMap<Long, Food>();
		if (ids.size() > 0) {
			List<Food> list = template.query(sql, new MapSqlParameterSource("ids", ids),
					new FoodRowMapper());
			for (Food food : list) {
				map.put(food.getId(), food);
			}
		}
		return map;
	}

	public List<Food> selectByCategoryId(Long categoryId) {
		String sql = "SELECT food_id, category_id, name_en, name_ru, calories, proteins, fats, carbohydrates"
					+ " FROM foods"
					+ " WHERE (category_id = :categoryId);";
		return template.query(sql, new MapSqlParameterSource("categoryId", categoryId),
				new FoodRowMapper());
	}

	public List<Food> selectByName(String name) {
		String sql = "SELECT food_id, category_id, name_en, name_ru, calories, proteins, fats,carbohydrates"
					+ " FROM foods"
					+ " WHERE (name_en ILIKE :name) OR (name_ru ILIKE :name);";
		return template.query(sql, new MapSqlParameterSource("name", '%' + name + '%'),
				new FoodRowMapper());
	}

	public List<Food> selectAll() {
		String sql = "SELECT food_id, category_id, name_en, name_ru, calories, proteins, fats, carbohydrates"
					+ " FROM foods";
		return template.query(sql, new FoodRowMapper());
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new NamedParameterJdbcTemplate(ds);
	}

}
