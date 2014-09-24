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

import com.dataart.spring.model.User;

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
		String url = req.getRequestURL().toString();
		String query = req.getQueryString();
		if (query != null) {
			url += "?" + URLDecoder.decode(query, "UTF-8");
		}
		LOGGER.debug("PRE-FILTER {}", url);
		HttpSession session = req.getSession();
		if (session.getAttribute("account") == null) {
			HttpServletResponse res = (HttpServletResponse) response;
			LOGGER.debug("Redirecting...");
			res.sendRedirect(req.getContextPath() + "/login");
		} else {
			User user = (User) session.getAttribute("account");
			LOGGER.debug("User: {}", user.getLogin());
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
