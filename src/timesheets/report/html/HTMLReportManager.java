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
import timesheets.resources.LanguageManager;
import timesheets.resources.ResourceHandler;

public class HTMLReportManager {
	private static final Logger logger = new Logger(HTMLReportManager.class);
	
	private static String reportNamePrefix = LanguageManager.language.get("report_name_prefix");
	private static String reportNamePostfix = ".html";

	private static final Path reportDirectory = ResourceHandler.report_directory_path;
	private static final Path reportStylePath = ResourceHandler.report_style_path;
	private static Path reportPath;
	
	public static String writeToFile(String report, ReportOutputType type) {
		String fileName = initialiseFile(type);
		
		try (FileWriter fw = new FileWriter(reportPath.toFile(), false);
				PrintWriter pw = new PrintWriter(fw)) {
			logger.debug("Writing to Report file.");
			pw.println(report);
			logger.debug("Finished writing to Report file.");
		} catch (IOException e) {
			logger.error("COULD NOT WRITE TO REPORT FILE!", e);
		}
		
		return fileName;
	}
	
	private static String initialiseFile(ReportOutputType type) {
		String fileName = reportNamePrefix + " " + LocalDate.now() + " " + type.toString() + reportNamePostfix;
		
		try {
			logger.info("Initialising Report File.");
			
			reportPath = Paths.get(reportDirectory + File.separator + fileName).toAbsolutePath();
			File reportFile = reportPath.toFile();
			
			if (!reportFile.getParentFile().exists()) {
				logger.debug("Parent directory of report file does not exist.");
				reportFile.getParentFile().mkdirs();
			}
			
			if(!reportStylePath.toFile().exists() || reportStylePath.toFile().length() == 0) {
				File report_style_file = reportStylePath.toFile();
				report_style_file.createNewFile();
				CSSFormatter.initFile(report_style_file);
			}

			logger.info("Creating Report File.");
			reportFile.createNewFile();
		} catch (IOException e) {
			logger.error("COULD NOT CREATE REPORT DIRECTORY!", e);
		}

		logger.info("Report directory successfully initialised.");
		return fileName;
	}

}
