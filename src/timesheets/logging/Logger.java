package timesheets.logging;

import java.time.LocalTime;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;

public class Logger {
	private String className;
	
	enum LogLevel {
		DEBUG,
		INFO,
		WARN,
		ERROR
	}
	
	@SuppressWarnings("rawtypes")
	public Logger(Class c) {
		this.className = c.getName();
	}
	
	public void debug(String msg) {
		String log = formatMessage(LogLevel.DEBUG, msg);
		LogManager.writeLog(log);
	}
	
	public void info(String msg) {
		String log = formatMessage(LogLevel.INFO, msg);
		LogManager.writeLog(log);
	}
	
	public void warn(String msg) {
		String log = formatMessage(LogLevel.WARN, msg);
		LogManager.writeLog(log);
	}
	
	public void error(String msg, Throwable e) {
		try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw);){
			e.printStackTrace(pw);
			msg += " >>> " + sw.toString(); 
			
			String log = formatMessage(LogLevel.ERROR, msg);
			LogManager.writeLog(log);
		} catch (IOException e1) {
			LogManager.writeLog("COULD NOT LOG STACKTRACE! " + e1);
		}
	}
	
	private String formatMessage(LogLevel level, String msg) {
		return String.format("%s %s\t[%s]\t%s   ->   %s", LocalDate.now(), LocalTime.now(), level, className, msg); 
	}

}