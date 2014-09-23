/**
 * 
 */
package com.dataart.spring.controllers;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dataart.spring.dao.WeightDAO;
import com.dataart.spring.model.User;
import com.dataart.spring.model.Weight;
import com.dataart.spring.utils.BMR;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private WeightDAO weightDAO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String dashboard(Model model, HttpSession session) {
		User user = (User) session.getAttribute("account");

		Weight weight = weightDAO.selectLastByUserId(user.getId());
		model.addAttribute("lastWeight", weight);

		int age = Years.yearsBetween(new DateTime(user.getBirthDate().getTime()), new DateTime()).getYears();
		model.addAttribute("age", age);

		int bmr = BMR.calculate(user.getGender(), age, user.getHeight(), weight.getWeight());
		model.addAttribute("bmr", bmr);

		LOGGER.debug("DashboardController");
		model.addAttribute("dashboardActive", "active");
		return "dashboard";
	}

}
