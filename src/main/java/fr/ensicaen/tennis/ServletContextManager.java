package fr.ensicaen.tennis;

import fr.ensicaen.tennis.persistence.Database;
import fr.ensicaen.tennis.util.Logger;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class ServletContextManager implements ServletContextListener {

	public static final String DATABASE_OBJECT = "DatabaseObject";
	private static final Logger logger = new Logger(ServletContextManager.class.getSimpleName());

	/**
	 * This event occurs at application startup
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// Every message below this LogLevel will be ignored
		Logger.setMinimumLogLevel(Logger.LogLevel.valueOf(ApplicationProperties.get("log_level")));
		// Display some properties
		logger.info("Starting -> "+ApplicationProperties.get("application_name"));
		// Start database
		ServletContext context = servletContextEvent.getServletContext();
		logger.debug("Context path = "+context.getContextPath());
		String realPath = context.getRealPath(".");
		logger.debug("Realpath for db : "+realPath);
		context.setAttribute(DATABASE_OBJECT, Database.getInstance(realPath));
	}

	/**
	 * This event occurs at application shutdown.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		logger.info("Stopping -> "+ApplicationProperties.get("application_name"));
		// Close database
		ServletContext context = servletContextEvent.getServletContext();
		Database d = (Database)context.getAttribute(DATABASE_OBJECT);
		if (d != null) {
			logger.info("Close database : " + d);
			d.close();
		}

		// Now unregister JDBC drivers in this context's ClassLoader:
		// Get the webapp's ClassLoader
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		// Loop through all drivers
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			if (driver.getClass().getClassLoader() == cl) {
				// This driver was registered by the webapp's ClassLoader, so unregister it:
				try {
					logger.info("Unregistering JDBC driver (" + driver + ")");
					DriverManager.deregisterDriver(driver);
				} catch (SQLException ex) {
					logger.error("Error deregistering JDBC driver (" + ex + ")");
				}
			} else {
				// driver was not registered by the webapp's ClassLoader and may be in use elsewhere
				logger.warning("Not unregistering JDBC driver {} as it does not belong to this webapp's ClassLoader" + driver);
			}
		}
	}
}
