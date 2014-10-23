package com.dataart.spring.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dataart.spring.dao.WeightDAO;
import com.dataart.spring.model.User;
import com.dataart.spring.model.Weight;

/**
 * @author vmeshcheryakov
 *
 */
@RestController
@RequestMapping("/weights")
public class WeightRestController {

	@Autowired
	private WeightDAO weightDAO;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@RequestMapping(value = "/{fromdate}/{todate}", method = RequestMethod.GET)
	public List<Weight> get(
			@PathVariable("fromdate") Date from,
			@PathVariable("todate") Date to,
			HttpSession session) {
		User user = (User) session.getAttribute("account");
		return weightDAO.selectByUserIdWithRange(user.getId(), from, to);
	}

	@RequestMapping(value="/add",  method = RequestMethod.POST)
	public String add(Weight weight, HttpSession session) {
		User user = (User) session.getAttribute("account");
		weight.setUserId(user.getId());
		weightDAO.insertOrUpdate(weight);
		return "ok";
	}

	@RequestMapping(value="/update",  method = RequestMethod.POST)
	public String update(Weight weight, HttpSession session) {
		User user = (User) session.getAttribute("account");
		weight.setUserId(user.getId());
		weightDAO.update(weight);
		return "ok";
	}

	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(Date date, HttpSession session) {
		User user = (User) session.getAttribute("account");
		Weight weight = new Weight();
		weight.setUserId(user.getId());
		weight.setDate(date);
		weightDAO.delete(weight);
		return "ok";
	}

}
