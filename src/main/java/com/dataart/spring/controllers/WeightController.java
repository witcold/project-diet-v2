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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dataart.spring.dao.WeightDAO;
import com.dataart.spring.model.User;
import com.dataart.spring.model.Weight;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@SuppressWarnings("static-method")
public class WeightController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeightController.class);

	@Autowired
	private WeightDAO weightDAO;

	@ModelAttribute("weight")
	public Weight getWeight() {
		return new Weight();
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/weight", method = RequestMethod.GET)
	public String weight(@RequestParam(value = "from", required = false) String date, Model model, HttpSession session, Locale locale) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Calendar calendar = Calendar.getInstance(locale);
		Date from;
		try {
			from = sdf.parse(date);
			calendar.setTime(from);
		} catch (Exception e) {
			LOGGER.warn("Error parsing date \"{}\", using current", e.getLocalizedMessage());
			from = new Date();
			calendar.setTime(from);
			calendar.set(Calendar.DATE, 1);
			from = calendar.getTime();
		}
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date to = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date next = calendar.getTime();
		model.addAttribute("nextMonth", sdf.format(next));
		calendar.add(Calendar.MONTH, -2);
		Date prev = calendar.getTime();
		model.addAttribute("prevMonth", sdf.format(prev));
		LOGGER.info("From {} To {}. Prev = {}, Next = {}", sdf.format(from), sdf.format(to), sdf.format(prev), sdf.format(next));
		model.addAttribute("weight_active", "active");
		sdf = new SimpleDateFormat("MMMM YYYY", locale);
		model.addAttribute("currentMonth", sdf.format(from));
		User user = (User) session.getAttribute("account");
		model.addAttribute("weightList", weightDAO.selectByUserIdWithRange(user.getId(), from, to));
		return "weight";
	}

	@RequestMapping(value="/addweight",  method = RequestMethod.POST)
	public String add(Weight weight, HttpSession session) {
		User user = (User) session.getAttribute("account");
		weight.setUserId(user.getId());
		weightDAO.insert(weight);
		return "redirect:/weight";
	}

}
