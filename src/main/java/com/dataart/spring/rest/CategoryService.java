package com.dataart.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dataart.spring.dao.CategoryDAO;
import com.dataart.spring.model.Category;

/**
 * @author vmeshcheryakov
 *
 */
@RestController
@RequestMapping("/categories")
public class CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(method = RequestMethod.GET)
	public List<Category> get() {
		return categoryDAO.selectById(0).getSubcategories();
	}
}
