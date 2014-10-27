package com.dataart.spring.controllers;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dataart.spring.config.LocalizationMessageSource;

/**
 * @author vmeshcheryakov
 *
 */
@Controller
@RequestMapping(value = "/messages")
public class MessagesController {

	@Autowired
	private LocalizationMessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, Locale locale) throws IOException {
		model.addAttribute("keys", messageSource.getKeys(locale));
		return "messages";
	}

}
