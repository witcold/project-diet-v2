/**
 * 
 */
package com.dataart.spring.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vmeshcheryakov
 *
 */
@WebFilter({"/dashboard", "/weight", "/diary"})
public class SessionFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		LOGGER.debug(filterConfig.toString());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		LOGGER.debug(request.toString());
		LOGGER.debug(response.toString());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
