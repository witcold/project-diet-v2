package com.dataart.spring.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dataart.spring.dao.FoodDAO;
import com.dataart.spring.dto.FoodDTO;
import com.dataart.spring.model.Food;

/**
 * @author vmeshcheryakov
 *
 */
@RestController
@RequestMapping("/foods")
public class FoodRestController {

	@Autowired
	private FoodDAO foodDAO;

	private List<FoodDTO> process(List<Food> list, String language) {
		List<FoodDTO> result = new ArrayList<FoodDTO>(list.size());
		for (Food food : list) {
			FoodDTO dto = new FoodDTO(food, language);
			result.add(dto);
		}
		return result;
	}

	@RequestMapping(value = {"", "/category/0"}, method = RequestMethod.GET)
	public List<FoodDTO> get(Locale locale) {
		List<Food> foods = foodDAO.selectAll();
		return process(foods, locale.getLanguage());
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public List<FoodDTO> get(@PathVariable("id") long categoryId, Locale locale) {
		List<Food> foods = foodDAO.selectByCategoryId(categoryId);
		return process(foods, locale.getLanguage());
	}

	@RequestMapping(value="/search", method = RequestMethod.GET)
	public List<FoodDTO> search(@RequestParam(value = "query", required = false) String query, Locale locale) {
		if (query != null) {
			List<Food> foods = foodDAO.selectByName(query);
			return process(foods, locale.getLanguage());
		}
		return get(locale);
	}

}
