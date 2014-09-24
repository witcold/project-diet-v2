/**
 * 
 */
package com.dataart.spring.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vmeshcheryakov
 *
 */
@WebFilter(filterName = "SessionFilter", urlPatterns = {"/dashboard", "/weight", "/diary"}, dispatcherTypes = {DispatcherType.REQUEST})
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
		LOGGER.debug("Session filter PRE-FILTER");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session.getAttribute("account") == null) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("/login");
		}
		chain.doFilter(request, response);
		LOGGER.debug("Session filter POST-FILTER");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
