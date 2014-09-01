/**
 * 
 */
package com.dataart.spring;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author vmeshcheryakov
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.setConfigLocation("com.dataart.spring.config.RootContext");
		servletContext.addListener(new ContextLoaderListener(rootContext));

		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", new DispatcherServlet(appContext));
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");

		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", filter);
		encodingFilter.addMappingForUrlPatterns(null, false, "/*"); //TODO there is another method,  may be it is better?
	}

	

}
