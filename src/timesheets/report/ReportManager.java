package timesheets.report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import timesheets.logging.Logger;
import timesheets.resources.ResourceHandler;

public class ReportManager {
	private static final Logger logger = new Logger(ReportManager.class);
	
	private static String report_name_prefix = "Timesheets Report ";
	private static String report_name_postfix = ".html";

	private static final Path report_directory = ResourceHandler.report_directory_path;
	private static Path report_path;
	private static File report_file;
	
	
	public static void writeToFile(String report) {
		initialiseFile();
		
		try (FileWriter file_writer = new FileWriter(report_file, false);
				PrintWriter report_writer = new PrintWriter(file_writer)) {
			logger.debug("Writing to Report file.");
			report_writer.println(report);
			logger.debug("Fnished writing to Report file.");
			
		} catch (IOException e) {
			logger.error("COULD NOT WRITE TO REPORT FILE!", e);
		}
	}
	
	public static void initialiseFile() {
		try {
			logger.info("Initialising Report File.");
			LocalDate initial_date = LocalDate.now();
			
			String reportName = report_name_prefix + initial_date + report_name_postfix;
			report_path = Paths.get(report_directory + File.separator + reportName).toAbsolutePath();
			report_file = report_path.toFile();
			
			if (!report_file.getParentFile().exists()) {
				logger.debug("Parent Directory of Report File does not exist.");
				report_file.getParentFile().mkdirs();
			}

			logger.info("Creating Report File.");
			report_file.createNewFile();
		} catch (IOException e) {
			logger.error("COULD NOT CREATE REPORT FILE!", e);
		}

		logger.info("Report File Successfully Initialised.");
	}
	
	

}
