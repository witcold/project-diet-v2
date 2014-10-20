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

}