package com.dataart.spring.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dataart.spring.dao.DiaryDAO;
import com.dataart.spring.dto.DiaryDTO;
import com.dataart.spring.model.Diary;
import com.dataart.spring.model.User;

/**
 * @author vmeshcheryakov
 *
 */
@RestController
@RequestMapping("/diaries")
public class DiaryRestController {

	@Autowired
	private DiaryDAO diaryDAO;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(Date.class, "timestamp", new CustomDateEditor(new SimpleDateFormat("yyyy.MM.dd HH:mm"), false));
	}

	@RequestMapping(value = "/{date}", method = RequestMethod.GET)
	public List<DiaryDTO> get(@PathVariable("date") Date date, Locale locale, HttpSession session) {
		User user = (User) session.getAttribute("account");
		List<Diary> diaries = diaryDAO.selectByUserIdForDate(user.getId(), date);
		List<DiaryDTO> result = new ArrayList<DiaryDTO>(diaries.size());
		for (Diary diary : diaries) {
			DiaryDTO dto = new DiaryDTO(diary, locale.getLanguage());
			result.add(dto);
		}
		return result;
		
	}

	@RequestMapping(value="/add",  method = RequestMethod.POST)
	public String add(Diary diary, HttpSession session) {
		User user = (User) session.getAttribute("account");
		diary.setUserId(user.getId());
		diaryDAO.insertOrUpdate(diary);
		return "ok";
	}

	@RequestMapping(value="/update",  method = RequestMethod.POST)
	public String update(Diary diary, HttpSession session) {
		User user = (User) session.getAttribute("account");
		diary.setUserId(user.getId());
		diaryDAO.update(diary);
		return "ok";
	}

	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(Diary diary, HttpSession session) {
		User user = (User) session.getAttribute("account");
		diary.setUserId(user.getId());
		diaryDAO.delete(diary);
		return "ok";
	}

}
