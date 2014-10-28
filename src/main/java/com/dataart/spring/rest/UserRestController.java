package com.dataart.spring.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dataart.spring.dao.UserDAO;
import com.dataart.spring.model.User;

/**
 * @author vmeshcheryakov
 *
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public User get(@PathVariable("login") String login) {
		return userDAO.selectByLogin(login);
	}

	@RequestMapping(method = RequestMethod.GET)
	public User get(HttpSession session) {
		return (User) session.getAttribute("account");
	}

}
