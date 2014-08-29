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
@RequestMapping(value = "/weight")
@SuppressWarnings("static-method")
public class WeightController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView weight() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("weight");
		mav.addObject("weight_active", "active");
		return mav;
	}

}
