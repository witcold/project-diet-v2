/**
 * 
 */
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

	public Category selectById(long categoryId) {
		String sql = "SELECT category_id, parent_id, name FROM categories WHERE (category_id = ?);";
		return template.query(sql, new ResultSetExtractor<Category>() {
			@Override
			public Category extractData(ResultSet rs) throws SQLException, DataAccessException {
				Category category = null;
				if (rs.next()) {
					category = new Category();
					category.setId(rs.getLong(1));
					category.setParentId(rs.getLong(2));
					category.setName(rs.getString(3));
				}
				return category;
			}
		}, categoryId);
	}

	public List<Category> selectAll() {
		String sql = "SELECT category_id, parent_id, name FROM categories WHERE (category_id > 0);";
		return template.query(sql, new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category category = new Category();
				category.setId(rs.getLong(1));
				category.setParentId(rs.getLong(2));
				category.setName(rs.getString(3));
				return category;
			}
		});
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

}
