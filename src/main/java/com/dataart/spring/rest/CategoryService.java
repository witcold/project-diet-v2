package com.dataart.spring.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dataart.spring.dao.CategoryDAO;
import com.dataart.spring.dto.CategoryDTO;
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
	//@CookieValue("lang") String language
	public List<CategoryDTO> get(Locale locale) {
		List<Category> categories = categoryDAO.selectById(0).getSubcategories();
		List<CategoryDTO> result = new ArrayList<CategoryDTO>(categories.size());
		for (Category category : categories) {
			CategoryDTO dto = new CategoryDTO(category, locale.getLanguage());
			result.add(dto);
		}
		return result;
	}
}
