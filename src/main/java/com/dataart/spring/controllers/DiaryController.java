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
@RequestMapping(value = "/diary")
public class DiaryController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String diary(Model model) {
		model.addAttribute("diaryActive", "active");
		return "diary";
	}


}
