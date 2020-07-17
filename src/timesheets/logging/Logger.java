package timesheets.logging;

import java.time.LocalTime;
import java.time.LocalDate;

public class Logger {
	private String className;
	
	public Logger(String className) {
		this.className = className;
	}
	
	public void info(String msg) {
		String log = formatMessage("INFO", msg);
		LogManager.writeLog(log);
	}
	
	public void debug(String msg) {
		String log = formatMessage("DEBUG", msg);
		LogManager.writeLog(log);
	}
	
	public void error(String msg) {
		String log = formatMessage("ERROR", msg);
		LogManager.writeLog(log);
	}
	
	public void warn(String msg) {
		String log = formatMessage("FATAL", msg);
		LogManager.writeLog(log);
	}
	
	private String formatMessage(String level, String msg) {
		return String.format("%s %s [%s] %s -> %s", LocalDate.now(), LocalTime.now(), level, className, msg); 
	}

}

/*
 * Example log Script: 
 * 22:26:51.523 [AWT-EventQueue-0] ERROR timesheets.logging.LoggingTest - This is an error message!
 * 22:26:51.526 [AWT-EventQueue-0] FATAL timesheets.logging.LoggingTest - This is a fatal message! Program will terminate!
 */
