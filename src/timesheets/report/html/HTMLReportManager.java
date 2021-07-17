package timesheets.report.html;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import timesheets.logging.Logger;
import timesheets.report.ReportOutputType;
import timesheets.resources.ResourceHandler;

public class HTMLReportManager {
	private static final Logger logger = new Logger(HTMLReportManager.class);
	
	private static String report_name_prefix = "Timesheets Report ";
	private static String report_name_postfix = ".html";

	private static final Path report_directory = ResourceHandler.report_directory_path;
	private static final Path report_style_path = ResourceHandler.report_style_path;
	private static Path reportPath;
	private static File reportFile;
	
	
	public static String writeToFile(String report, ReportOutputType type) {
		String fileName = initialiseFile(type);
		
		try (FileWriter fw = new FileWriter(reportFile, false);
				PrintWriter pw = new PrintWriter(fw)) {
			logger.debug("Writing to Report file.");
			pw.println(report);
			logger.debug("Finished writing to Report file.");
			
		} catch (IOException e) {
			logger.error("COULD NOT WRITE TO REPORT FILE!", e);
		}
		
		return fileName;
	}
	
	public static String initialiseFile(ReportOutputType type) {
		String fileName = "";
		
		try {
			logger.info("Initialising Report File.");
			
			fileName = report_name_prefix + LocalDate.now() + " " + type.toString() + report_name_postfix;
			reportPath = Paths.get(report_directory + File.separator + fileName).toAbsolutePath();
			reportFile = reportPath.toFile();
			
			if (!reportFile.getParentFile().exists()) {
				logger.debug("Parent Directory of Report File does not exist.");
				reportFile.getParentFile().mkdirs();
			}
			
			if(!report_style_path.toFile().exists() || report_style_path.toFile().length() == 0) {
				File report_style_file = report_style_path.toFile();
				report_style_file.createNewFile();
				CSSFormatter.initFile(report_style_file);
			}

			logger.info("Creating Report File.");
			reportFile.createNewFile();
		} catch (IOException e) {
			logger.error("COULD NOT CREATE REPORT FILE!", e);
		}

		logger.info("Report File Successfully Initialised.");
		return fileName;
	}

}
