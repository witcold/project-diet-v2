/**
 * 
 */
package com.dataart.spring.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dataart.spring.dao.UserDAO;
import com.dataart.spring.model.User;
import com.dataart.spring.validators.SignUpValidator;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@SuppressWarnings("static-method")
public class SignUpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private UserDAO userDAO;

	@ModelAttribute("user")
	public User getUserBean() {
		return new User();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new SignUpValidator());
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String greeting() {
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(
			@Valid User user,
			BindingResult result,
			HttpSession session) {
		LOGGER.info("Adding: \"{}\"", user);
		if (!result.hasErrors()) {
			if (userDAO.insert(user)) {
				session.setAttribute("account", user);
			}
			return "redirect:/";
		} else {
			result.reject("notvalid", "Please ensure your data is valid");
			return "signup";
		}
	}

}
