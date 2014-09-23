/**
 * 
 */
package com.dataart.spring.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dataart.spring.dao.UserDAO;
import com.dataart.spring.model.Gender;
import com.dataart.spring.model.User;
import com.dataart.spring.validators.SignUpValidator;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
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
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy.MM.dd"), true));
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String greeting(Model model) {
		model.addAttribute("genders", Gender.values());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(
			@Valid User user,
			BindingResult result,
			HttpSession session) {
		LOGGER.debug("Adding: \"{}\"", user);
		if (!result.hasErrors()) {
			if (userDAO.selectByLogin(user.getLogin()) != null) {
				result.rejectValue("login", "login.existed", "Login already registered, please choose another one");
				return "signup";
			} else if (userDAO.insert(user)) {
				session.setAttribute("account", user);
			}
			return "redirect:/";
		} else {
			result.reject("notvalid", "Please ensure your data is valid");
			return "signup";
		}
	}

}
