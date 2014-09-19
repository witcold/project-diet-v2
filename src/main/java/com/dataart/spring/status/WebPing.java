/**
 * 
 */
package com.dataart.spring.status;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author vmeshcheryakov
 *
 */
@WebServlet("/status")
@SuppressWarnings("serial")
public class WebPing extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		DBStatus dbStatus = context.getBean(DBStatus.class);
		response.getWriter().println(dbStatus.getDBStatus());
	}

}
