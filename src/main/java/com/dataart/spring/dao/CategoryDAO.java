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

import com.dataart.spring.model.Category;

/**
 * @author vmeshcheryakov
 *
 */
@Repository
public class CategoryDAO {

	private JdbcTemplate template;

	public List<Category> selectAll() {
		String sql = "SELECT category_id, parent_id, name FROM categories WHERE (category_id > 0);";
		List<Category> list = template.query(sql, new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category category = new Category();
				category.setId(rs.getLong(1));
				category.setParentId(rs.getLong(2));
				category.setName(rs.getString(3));
				return category;
			}
		});
		return list;
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

}
