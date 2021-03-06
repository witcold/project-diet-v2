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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dataart.spring.dao.GoalDAO;
import com.dataart.spring.dao.UserDAO;
import com.dataart.spring.dao.WeightDAO;
import com.dataart.spring.dto.SignUpDTO;
import com.dataart.spring.model.Gender;
import com.dataart.spring.model.Goal;
import com.dataart.spring.model.User;
import com.dataart.spring.model.Weight;
import com.dataart.spring.validators.SignUpValidator;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/signup")
public class SignUpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private WeightDAO weightDAO;

	@Autowired
	private GoalDAO goalDAO;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy.MM.dd"), true));
	}

	@InitBinder("signUpDTO")
	public void initUserBinder(WebDataBinder binder) {
		binder.setValidator(new SignUpValidator());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String greeting(Model model) {
		model.addAttribute("genders", Gender.values());
		return "signup";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String signUp(
			@Valid SignUpDTO signUpDTO,
			BindingResult result,
			Model model,
			HttpSession session) {
		LOGGER.debug("Adding: \"{}\"", signUpDTO);
		if (!result.hasErrors()) {
			if (userDAO.selectByLogin(signUpDTO.getLogin()) != null) {
				result.rejectValue("login", "login.existed", "Login already registered, please choose another one");
				return "signup";
			} else {
				User user = signUpDTO.getUser();
				userDAO.insert(user);
				session.setAttribute("account", user);

				Weight currentWeight = new Weight();
				currentWeight.setUserId(user.getId());
				currentWeight.setDate(new Date());
				currentWeight.setWeight(signUpDTO.getWeight());
				weightDAO.insert(currentWeight);

				Goal currentGoal = new Goal();
				currentGoal.setUserId(user.getId());
				currentGoal.setDate(new Date());
				currentGoal.setWeight(signUpDTO.getWeight());
				goalDAO.insert(currentGoal);

				return "redirect:/";
			}
		} else {
			result.reject("notvalid");
			model.addAttribute("genders", Gender.values());
			return "signup";
		}
	}

}
