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

import com.dataart.spring.dao.DiaryDAO;
import com.dataart.spring.model.Diary;
import com.dataart.spring.model.User;
import com.dataart.spring.utils.DateUtils;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/diary")
public class DiaryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DiaryController.class);

	@Autowired
	private DiaryDAO diaryDAO;

	@ModelAttribute("diary")
	public Diary getDiary() {
		return new Diary();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy.MM.dd"), true));
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String weight(Date date, Model model, HttpSession session) {
		if (date == null) {
			date = new Date();
		}
		model.addAttribute("currentDate", date);

		User user = (User) session.getAttribute("account");
		LOGGER.info("Get weight list for user {} on {}", user.getId(), date);
		List<Diary> list = diaryDAO.selectByUserIdForDate(user.getId(), date);
		model.addAttribute("diaryList", list);

		Date prev = DateUtils.getPreviousDay(date);
		model.addAttribute("prevDate", prev);

		Date next = DateUtils.getNextDay(date);
		model.addAttribute("nextDate", next);

		model.addAttribute("diaryActive", "active");
		return "diary";
	}

	@RequestMapping(value="/add",  method = RequestMethod.POST)
	public String add(Diary diary, HttpSession session) {
		User user = (User) session.getAttribute("account");
		diary.setUserId(user.getId());
		diaryDAO.insert(diary);
		return "redirect:/diary";
	}

	@RequestMapping(value="/update",  method = RequestMethod.POST)
	public String update(Diary diary, HttpSession session) {
		User user = (User) session.getAttribute("account");
		diary.setUserId(user.getId());
		diaryDAO.update(diary);
		return "redirect:/diary";
	}

	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(long food_id, Date date, HttpSession session) {
		User user = (User) session.getAttribute("account");
		Diary weight = new Diary();
		weight.setUserId(user.getId());
		weight.setTimestamp(date);
		diaryDAO.delete(weight);
		return "redirect:/diary";
	}

	@RequestMapping(value="/raw", method = RequestMethod.GET)
	@ResponseBody
	public List<Diary> getData(Date date, HttpSession session) {
		User user = (User) session.getAttribute("account");

		return diaryDAO.selectByUserIdForDate(user.getId(), date);
	}

}
