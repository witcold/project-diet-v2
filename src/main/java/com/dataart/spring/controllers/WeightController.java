/**
 * 
 */
package com.dataart.spring.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dataart.spring.dao.WeightDAO;
import com.dataart.spring.model.User;
import com.dataart.spring.model.Weight;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@SuppressWarnings("static-method")
public class WeightController {

	@Autowired
	private WeightDAO weightDAO;

	@ModelAttribute("weight")
	public Weight getWeight() {
		return new Weight();
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/weight", method = RequestMethod.GET)
	public String weight(Model model, HttpSession session, Locale locale) {
		model.addAttribute("weight_active", "active");
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM YYYY", locale);
		model.addAttribute("currentMonth", sdf.format(new Date()));
		User user = (User) session.getAttribute("account");
		model.addAttribute("weightList", weightDAO.selectByUserId(user.getId()));
		return "weight";
	}

	@RequestMapping(value="/addweight",  method = RequestMethod.POST)
	public String add(Weight weight, HttpSession session) {
		User user = (User) session.getAttribute("account");
		weight.setUserId(user.getId());
		weightDAO.insert(weight);
		return "redirect:/weight";
	}

}
