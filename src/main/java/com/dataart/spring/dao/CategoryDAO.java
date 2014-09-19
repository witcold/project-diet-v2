package com.dataart.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dataart.spring.model.Category;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
public class CategoryDAO {

	private JdbcTemplate template;

	private class CategoryRowMapper implements RowMapper<Category> {
		public CategoryRowMapper() {
		}

		@Override
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			return getCategory(rs);
		}
	}

	private class CategoryResultSetExtractor implements ResultSetExtractor<Category> {
		public CategoryResultSetExtractor() {
		}

		@Override
		public Category extractData(ResultSet rs) throws SQLException, DataAccessException {
			if (rs.next()) {
				return getCategory(rs);
			}
			return null;
		}
	}

	public Category selectById(long categoryId) {
		String sql = "SELECT category_id, parent_id, name_en, name_ru"
					+ " FROM categories"
					+ " WHERE (category_id = ?);";
		return template.query(sql, new CategoryResultSetExtractor(), categoryId);
	}

	public List<Category> selectAll() {
		String sql = "SELECT category_id, parent_id, name"
					+ " FROM categories"
					+ " WHERE (category_id > 0);";
		return template.query(sql, new CategoryRowMapper());
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

	Category getCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getLong("category_id"));
		category.setParentId(rs.getLong("parent_id"));
		category.setNameEn(rs.getString("name_en"));
		category.setNameRu(rs.getString("name_ru"));
		return category;
	}

}
