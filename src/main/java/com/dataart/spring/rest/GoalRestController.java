package com.dataart.spring.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dataart.spring.dao.GoalDAO;
import com.dataart.spring.model.Goal;
import com.dataart.spring.model.User;

/**
 * @author vmeshcheryakov
 *
 */
@RestController
@RequestMapping(value = "/goals")
public class GoalRestController {

	@Autowired
	private GoalDAO goalDAO;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy.MM.dd"), true));
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Goal> getData(HttpSession session) {
		User user = (User) session.getAttribute("account");
		return goalDAO.selectAllByUserId(user.getId());
	}

	@RequestMapping(value="/add",  method = RequestMethod.POST)
	public String add(Goal goal, HttpSession session) {
		User user = (User) session.getAttribute("account");
		goal.setUserId(user.getId());
		goalDAO.insertOrUpdate(goal);
		return "ok";
	}

	@RequestMapping(value="/update",  method = RequestMethod.POST)
	public String update(Goal goal, HttpSession session) {
		User user = (User) session.getAttribute("account");
		goal.setUserId(user.getId());
		goalDAO.update(goal);
		return "ok";
	}

	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(
			@RequestParam("date") Date date,
			HttpSession session) {
		User user = (User) session.getAttribute("account");
		Goal goal = new Goal();
		goal.setUserId(user.getId());
		goal.setDate(date);
		goalDAO.delete(goal);
		return "ok";
	}

}
