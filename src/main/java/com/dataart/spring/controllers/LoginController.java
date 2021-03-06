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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dataart.spring.dao.UserDAO;
import com.dataart.spring.model.User;
import com.dataart.spring.validators.LoginValidator;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserDAO userDAO;

	@InitBinder("user")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new LoginValidator());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(
			@Valid User user,
			BindingResult result,
			HttpSession session) {
		LOGGER.debug("Login: \"{}\"", user.getLogin());
		if (!result.hasErrors()) {
			User account = userDAO.authenticate(user);
			if (account != null) {
				session.setAttribute("account", account);
				return "redirect:/";
			}
		}
		result.reject("notvalid");
		return "login";
	}

}
