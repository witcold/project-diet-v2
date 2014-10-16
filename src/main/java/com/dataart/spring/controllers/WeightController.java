package com.dataart.spring.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataart.spring.dao.WeightDAO;
import com.dataart.spring.dto.CaloriesDTO;
import com.dataart.spring.model.Gender;
import com.dataart.spring.model.User;
import com.dataart.spring.model.Weight;
import com.dataart.spring.utils.BMR;
import com.dataart.spring.utils.DateUtils;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/weight")
public class WeightController {

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
	public String weight(Date from, Date to, Model model, HttpSession session) {
		from = DateUtils.getFirstDayOfMonth(from);
		model.addAttribute("currentDate", from);

		Date prev = DateUtils.getPreviousMonth(from);
		model.addAttribute("prevDate", prev);

		if(!DateUtils.isCurrentMonth(from)) {
			Date next = DateUtils.getNextMonth(from);
			model.addAttribute("nextDate", next);
		}

		model.addAttribute("weightActive", "active");
		return "weight";
	}

	@RequestMapping(value="/add",  method = RequestMethod.POST)
	public String add(Weight weight, HttpSession session) {
		User user = (User) session.getAttribute("account");
		weight.setUserId(user.getId());
		weightDAO.insertOrUpdate(weight);
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

	@RequestMapping(value="/raw", method = RequestMethod.GET)
	@ResponseBody
	public List<Weight> getData(
			@RequestParam(value = "from", required = false) Date from,
			@RequestParam(value = "to", required = false) Date to,
			HttpSession session) {
		User user = (User) session.getAttribute("account");
		if (from == null) {
			from = DateUtils.getFirstDayOfMonth(null);
		}
		if (to == null) {
			to = DateUtils.getLastDayOfMonth(from);
		}
		return weightDAO.selectByUserIdWithRange(user.getId(), from, to);
	}

	@RequestMapping(value = "/bmr", method = RequestMethod.GET)
	@ResponseBody
	public List<CaloriesDTO> getBmr(
			@RequestParam(value = "from", required = false) Date from,
			@RequestParam(value = "to", required = false) Date to,
			HttpSession session) {
		User user = (User) session.getAttribute("account");
		
		Gender gender = user.getGender();
		int age = Years.yearsBetween(new DateTime(user.getBirthDate().getTime()), new DateTime()).getYears();
		int height = user.getHeight();

		if (from == null) {
			from = DateUtils.getFirstDayOfMonth(null);
		}
		if (to == null) {
			to = DateUtils.getLastDayOfMonth(from);
		}

		List<Weight> list = weightDAO.selectByUserIdWithRange(user.getId(), from, to);
		List<CaloriesDTO> bmrList = new ArrayList<CaloriesDTO>();
		if (!list.isEmpty()) {
			for (Weight weight : list) {
				CaloriesDTO bmr = new CaloriesDTO();
				bmr.setDate(weight.getDate());
				bmr.setCalories((int) (user.getActivityLevel()*BMR.calculate(gender, age, height, weight.getWeight())));
				bmrList.add(bmr);
			}
		}
		return bmrList;
	}

}
