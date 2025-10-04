package fr.ensicaen.tennis.util;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Logger {
	private static final PrintStream out = System.out;
	private static final PrintStream err = System.err;
	private final String name;
	private LogLevel logLevel;
	private static LogLevel defaultLogLevel = LogLevel.VERBOSE;
	public static void setMinimumLogLevel(LogLevel level) {
		defaultLogLevel = level;
	}
	private boolean timestamped;
	public Logger(String name) {
		this.name= name;
		this.timestamped = false;
		this.logLevel = defaultLogLevel;
	}
	public Logger(String name, boolean timestamped) {
		this(name);
		this.timestamped = timestamped;
	}
	public Logger(String name, LogLevel logLevel) {
		this(name);
		this.logLevel = logLevel;
	}
	public Logger(String name, boolean timestamped, LogLevel logLevel) {
		this (name, timestamped);
		this.logLevel = logLevel;
	}
	public void log(LogLevel level, String message) {
		if (level.getLevel() >= logLevel.getLevel()) {
			out.println(timestamp() + level.name() + " ["+name+"]: " + message);
		}
	}
	public void debug(String message) {
		log(LogLevel.DEBUG, message);
	}
	public void config(String message) {
		log(LogLevel.CONFIG, message);
	}
	public void info(String message) {
		log(LogLevel.INFO, message);
	}
	public void warning(String message) {
		log(LogLevel.WARNING, message);
	}
	public void error(String message) {
		log(LogLevel.ERROR, message);
	}
	private String timestamp() {
		return timestamped ? DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDate.now())+" " : "";
	}
	public enum LogLevel {
		VERBOSE(0), DEBUG(1), CONFIG(2),
		INFO(3), WARNING(4), ERROR(5);
		private final int level;
		LogLevel(int level) {
			this.level = level;
		}
		public int getLevel() {
			return level;
		}
	}
}
