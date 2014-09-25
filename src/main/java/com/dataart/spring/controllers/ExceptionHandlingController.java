package com.dataart.spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author vmeshcheryakov
 *
 */
@ControllerAdvice
public class ExceptionHandlingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) {
		LOGGER.error("Request: {} raised {}", request.getRequestURL(), exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("message", exception.getLocalizedMessage());
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("error");
		return mav;
	}

}
