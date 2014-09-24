package com.dataart.spring.filter;

import java.io.IOException;
import java.net.URLDecoder;

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
@WebFilter(filterName = "SessionFilter", urlPatterns = {"/dashboard/*", "/weight/*", "/food/*", "/diary/*", "/goal/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SessionFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String query = req.getQueryString();
		LOGGER.debug("Session filter PRE-FILTER {}?{}", req.getRequestURL(), URLDecoder.decode(query == null? "" : query, "UTF-8"));
		HttpSession session = req.getSession();
		if (session.getAttribute("account") == null) {
			HttpServletResponse res = (HttpServletResponse) response;
			LOGGER.debug("Redirecting...");
			res.sendRedirect(req.getContextPath() + "/login");
		} else {
			LOGGER.info("Session: {}", session.getAttribute("account"));
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
