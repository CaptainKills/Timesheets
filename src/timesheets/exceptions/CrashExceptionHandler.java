package timesheets.exceptions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import timesheets.logging.Logger;

public class CrashExceptionHandler implements Thread.UncaughtExceptionHandler{

	private static final Logger logger = new Logger(CrashExceptionHandler.class);
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw);){
			e.printStackTrace(pw);
			logger.error(sw.toString());
		} catch (IOException e1) {
			logger.error("COULD NOT WRITE FINAL ERROR!", e1);
		}
	}

}
