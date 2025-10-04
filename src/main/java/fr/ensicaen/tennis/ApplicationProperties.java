package fr.ensicaen.tennis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
	private static ApplicationProperties instance;
	public static ApplicationProperties getInstance() {
		if (instance == null) instance = new ApplicationProperties();
		return instance;
	}

	private Properties properties;

	protected ApplicationProperties() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream in = classLoader.getResourceAsStream("META-INF/tennis.properties");
		try {
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get the value of a property.<br/>
	 * @param name the property name
	 * @return the property value
	 * @see ApplicationProperties#get(String)
	 */
	public String getProperty(String name) {
		return properties.getProperty(name);
	}

	/**
	 * Shortcut to {@link #getProperty(String) getProperty}
	 */
	public static String get(String name) {
		return getInstance().getProperty(name);
	}
}
