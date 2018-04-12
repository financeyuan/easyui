package com.yuan.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.xml.DOMConfigurator;

public class Log4jInitListener implements ServletContextListener {
	 

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent event) {
		String prefix = event.getServletContext().getRealPath("/");
	    String file = event.getServletContext().getInitParameter("log4jConfigLocation");
	    if (file != null) {
	      DOMConfigurator.configure(prefix + file); 
	    }

	}
}