/**
 * 
 */
package com.dataart.spring.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeightController.class);

	@Autowired
	private WeightDAO weightDAO;

	@ModelAttribute("weight")
	public Weight getWeight() {
		return new Weight();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy.MM.dd"), true));
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String weight(Date from, Date to, Model model, HttpSession session, Locale locale) {
		from = getFirstDayOfMonth(from);
		model.addAttribute("currentDate", from);

		if (to == null) {
			to = getLastDayOfMonth(from);
		}
		User user = (User) session.getAttribute("account");
		LOGGER.info("Get weight list for user {} ({} - {})", user.getId(), from, to);
		model.addAttribute("weightList", weightDAO.selectByUserIdWithRange(user.getId(), from, to));

		Date prev = getPreviousMonth(from);
		model.addAttribute("prevDate", prev);

		Date next = getNextMonth(from);
		model.addAttribute("nextDate", next);

		model.addAttribute("weightActive", "active");
		return "weight";
	}

	@RequestMapping(value="/add",  method = RequestMethod.POST)
	public String add(Weight weight, HttpSession session) {
		User user = (User) session.getAttribute("account");
		weight.setUserId(user.getId());
		weightDAO.insert(weight);
		return "redirect:/weight";
	}

	@RequestMapping(value="/update",  method = RequestMethod.POST)
	public String update(Weight weight, HttpSession session) {
		User user = (User) session.getAttribute("account");
		weight.setUserId(user.getId());
		weightDAO.update(weight);
		return "redirect:/weight";
	}

	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(Date date, HttpSession session) {
		User user = (User) session.getAttribute("account");
		Weight weight = new Weight();
		weight.setUserId(user.getId());
		weight.setDate(date);
		weightDAO.delete(weight);
		return "redirect:/weight";
	}

	private Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	private Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	private Date getPreviousMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	private Date getNextMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

}
