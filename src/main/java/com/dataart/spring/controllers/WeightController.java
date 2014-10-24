package com.dataart.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/weight")
public class WeightController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String weight(Model model) {
		model.addAttribute("weightActive", "active");
		return "weight";
	}

}
