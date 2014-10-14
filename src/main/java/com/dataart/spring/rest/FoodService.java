package com.dataart.spring.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class FoodService {

	@Autowired
	private FoodDAO foodDAO;

	@RequestMapping(value = {"", "/category/0"}, method = RequestMethod.GET)
	public List<FoodDTO> get(Locale locale) {
		List<Food> foods = foodDAO.selectAll();
		List<FoodDTO> result = new ArrayList<FoodDTO>(foods.size());
		for (Food food : foods) {
			FoodDTO dto = new FoodDTO(food, locale.getLanguage());
			result.add(dto);
		}
		return result;
	}

}
