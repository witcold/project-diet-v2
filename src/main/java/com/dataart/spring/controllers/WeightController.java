/**
 * 
 */
package com.dataart.spring.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping(value = "/weight")
public class WeightController {

	@Autowired
	private WeightDAO weightDAO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String weight(Model model, HttpSession session) {
		model.addAttribute("weight_active", "active");
		User user = (User) session.getAttribute("account");
		model.addAttribute("weight_list", weightDAO.selectByUserId(user.getId()));
		return "weight";
	}

}
