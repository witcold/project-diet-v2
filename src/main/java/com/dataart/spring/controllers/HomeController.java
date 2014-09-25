package com.dataart.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dataart.spring.model.User;

/**
 * Handles requests for the application home page.
 * @author vmeshcheryakov
 */
@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@ModelAttribute("user")
	public User getUserBean() {
		return new User();
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpSession session) {
		LOGGER.debug("Hi there! The client came in: {}:{}:{} ({})!",
				request.getRemoteHost(), request.getRemotePort(),
				request.getHeader("Accept-Language"), request.getHeader("User-Agent"));
		if (session.getAttribute("account") != null) {
			return "redirect:/dashboard";
		}
		return "home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "redirect:/";
	}

}
