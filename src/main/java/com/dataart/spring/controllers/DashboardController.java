/**
 * 
 */
package com.dataart.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/dashboard")
@SuppressWarnings("static-method")
public class DashboardController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard");
		mav.addObject("dashboard_active", "active");
		return mav;
	}

}
