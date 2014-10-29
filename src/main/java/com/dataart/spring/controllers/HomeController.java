package com.dataart.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application pages.
 * @author vmeshcheryakov
 */
@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

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

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model) {
		model.addAttribute("dashboardActive", "active");
		return "dashboard";
	}

	@RequestMapping(value = "/weight", method = RequestMethod.GET)
	public String weight(Model model) {
		model.addAttribute("weightActive", "active");
		return "weight";
	}

	@RequestMapping(value = "/food", method = RequestMethod.GET)
	public String food(Model model) {
		model.addAttribute("foodActive", "active");
		return "food";
	}

	@RequestMapping(value = "/diary", method = RequestMethod.GET)
	public String diary(Model model) {
		model.addAttribute("diaryActive", "active");
		return "diary";
	}

	@RequestMapping(value = "/goal", method = RequestMethod.GET)
	public String goal(Model model) {
		model.addAttribute("goalActive", "active");
		return "goal";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		session.invalidate();
		return "redirect:/";
	}

}
