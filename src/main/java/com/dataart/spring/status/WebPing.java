package com.dataart.spring.status;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author vmeshcheryakov
 *
 */
@WebServlet("/status")
public class WebPing extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DBStatus dbStatus = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(DBStatus.class);
		response.getWriter().println(dbStatus.getDBStatus());
	}

}
