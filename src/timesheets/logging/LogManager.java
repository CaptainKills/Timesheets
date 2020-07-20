package timesheets.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogManager {
	private static LocalTime initial_time = LocalTime.now();
	private static LocalDate initial_date = LocalDate.now();
	private static String filename = "Timesheets Log " + initial_date + ".txt";
	
	private static final Path log_path = Paths.get("logs" + File.separator + filename).toAbsolutePath();
	private static File log_file = log_path.toFile();
	
	private static FileWriter file_writer;
	private static PrintWriter log_writer;
	
	private static boolean debugMode = true;

	public static void initialise() {
		try {
			if(!log_file.getParentFile().exists()) {
				log_file.getParentFile().mkdirs();
			}
			
			log_file.createNewFile();
		} catch (IOException e) {
			System.out.println("The file could not be created: " + e);
		}
		writeLog("---Timesheets Log created [" + initial_date + " " + initial_time + "]---\n");
	}
	
	public static void writeLog(String log) {
		try {
			file_writer = new FileWriter(log_file, true);
			log_writer = new PrintWriter(file_writer);
			log_writer.println(log);
			
			if(debugMode) {
				System.out.println(log);
			}
		} catch (IOException e){
			System.out.println("Could not write to log file: " + e);
		} finally {
			log_writer.flush();
			log_writer.close();
		}
	} 

}
