package timesheets.logging;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;

public class Logger {
	private static LocalTime initial_time = LocalTime.now();
	private static String filename = "Timesheets Log " + initial_time;
	
	private static final Path log_path = Paths.get("logs" + File.separator + filename).toAbsolutePath();
	private static File log_file = log_path.toFile();
	
	private static PrintWriter log_writer;

	public static void initialise() {
		try {
			if(!log_file.getParentFile().exists()) {
				log_file.getParentFile().mkdirs();
			}
			
			log_file.createNewFile();
			writeLog("---Log created---");
		} catch (Exception e) {
			System.out.println("The file could not be created: " + e);
		}
	}
	
	public static void info(String className, String msg) {
		String log = formatMessage("INFO", className, msg);
		writeLog(log);
	}
	
	public static void debug(String className, String msg) {
		String log = formatMessage("DEBUG", className, msg);
		writeLog(log);
	}
	
	public static void error(String className, String msg) {
		String log = formatMessage("ERROR", className, msg);
		writeLog(log);
	}
	
	public static void fatal(String className, String msg) {
		String log = formatMessage("FATAL", className, msg);
		writeLog(log);
	}
	
	private static String formatMessage(String level, String className, String msg) {
		return String.format("%s [%s] %s - %s", LocalTime.now(), level, className, msg); 
	}
	
	private static void writeLog(String log) {
		try {
			log_writer = new PrintWriter(log_file);
			log_writer.write(log);
		} catch (Exception e){
			System.out.println("Could not write to log file: " + e.toString());
		} finally {
			log_writer.flush();
			log_writer.close();
		}
	}

}

/*
 * Example log Script: 
 * 22:26:51.523 [AWT-EventQueue-0] ERROR timesheets.logging.LoggingTest - This is an error message!
 * 22:26:51.526 [AWT-EventQueue-0] FATAL timesheets.logging.LoggingTest - This is a fatal message! Program will terminate!
 */
