/**
 * 
 */
package com.dataart.spring.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataart.spring.dao.GoalDAO;
import com.dataart.spring.model.Goal;
import com.dataart.spring.model.User;
import com.dataart.spring.utils.DateUtils;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/goal")
public class GoalController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoalController.class);

	@Autowired
	private GoalDAO goalDAO;

	@ModelAttribute("goal")
	public Goal getGoal() {
		return new Goal();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy.MM.dd"), true));
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String goal(Date from, Date to, Model model, HttpSession session) {
		from = DateUtils.getFirstDayOfMonth(from);
		model.addAttribute("currentDate", from);

		if (to == null) {
			to = DateUtils.getLastDayOfMonth(from);
		}
		User user = (User) session.getAttribute("account");
		LOGGER.debug("Get goal list for user {} ({} - {})", user.getId(), from, to);
		List<Goal> list = goalDAO.selectByUserIdWithRange(user.getId(), from, to);
		model.addAttribute("goalList", list);

		Date prev = DateUtils.getPreviousMonth(from);
		model.addAttribute("prevDate", prev);

		if(!DateUtils.isCurrentMonth(from)) {
			Date next = DateUtils.getNextMonth(from);
			model.addAttribute("nextDate", next);
		}

		model.addAttribute("goalActive", "active");
		return "goal";
	}

	@RequestMapping(value="/add",  method = RequestMethod.POST)
	public String add(Goal goal, HttpSession session) {
		User user = (User) session.getAttribute("account");
		goal.setUserId(user.getId());
		goalDAO.insert(goal);
		return "redirect:/goal";
	}

	@RequestMapping(value="/update",  method = RequestMethod.POST)
	public String update(Goal goal, HttpSession session) {
		User user = (User) session.getAttribute("account");
		goal.setUserId(user.getId());
		goalDAO.update(goal);
		return "redirect:/goal";
	}

	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(Date date, HttpSession session) {
		User user = (User) session.getAttribute("account");
		Goal goal = new Goal();
		goal.setUserId(user.getId());
		goal.setDate(date);
		goalDAO.delete(goal);
		return "redirect:/goal";
	}

	@RequestMapping(value="/raw", method = RequestMethod.GET)
	@ResponseBody
	public List<Goal> getData(Date from, Date to, HttpSession session) {
		User user = (User) session.getAttribute("account");
		if (from == null) {
			from = DateUtils.getFirstDayOfMonth(null);
		}
		if (to == null) {
			to = DateUtils.getLastDayOfMonth(from);
		}
		return goalDAO.selectByUserIdWithRange(user.getId(), from, to);
	}

}
