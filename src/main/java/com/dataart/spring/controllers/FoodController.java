package com.dataart.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataart.spring.dao.FoodDAO;
import com.dataart.spring.model.Food;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/food")
public class FoodController {

	@Autowired
	private FoodDAO foodDAO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String food(Model model) {
		model.addAttribute("foodActive", "active");
		return "food";
	}

	@RequestMapping(value="/raw", method = RequestMethod.GET)
	@ResponseBody
	public List<Food> getData(
			@RequestParam(value = "query", required = false) String query) {
		if (query != null) {
			return foodDAO.selectByName(query);
		}
		return foodDAO.selectAll();
	}

}
