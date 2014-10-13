package com.dataart.spring.rest;

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
public class UserService {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public User get(@PathVariable("login") String login) {
		return userDAO.selectByLogin(login);
	}
}
