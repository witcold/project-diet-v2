/**
 * 
 */
package com.dataart.spring.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dataart.spring.dao.CategoryDAO;
import com.dataart.spring.dao.FoodDAO;
import com.dataart.spring.model.Category;
import com.dataart.spring.model.Food;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/food")
public class FoodController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FoodController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private FoodDAO foodDAO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String dashboard(Model model) {
		LOGGER.info("Get categories list");
		List<Category> categories = categoryDAO.selectAll();
		model.addAttribute("categoryList", categories);

		LOGGER.info("Get foods list");
		List<Food> foods = foodDAO.selectAll();
		model.addAttribute("foodList", foods);

		model.addAttribute("foodActive", "active");
		return "food";
	}

}
