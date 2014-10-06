package com.dataart.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String dashboard(Model model, HttpSession session) {
		model.addAttribute("dashboardActive", "active");
		return "dashboard";
	}

}
