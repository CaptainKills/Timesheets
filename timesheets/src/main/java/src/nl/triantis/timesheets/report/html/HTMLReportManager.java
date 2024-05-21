package nl.triantis.timesheets.report.html;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.report.ReportOutputType;
import nl.triantis.timesheets.resources.LanguageManager;
import nl.triantis.timesheets.resources.ResourceHandler;

public class HTMLReportManager {
	private static final Logger logger = new Logger(HTMLReportManager.class);
	
	private static String reportNamePrefix = LanguageManager.language.get("report_name_prefix");
	private static String reportNamePostfix = ".html";

	private static final Path reportDirectory = ResourceHandler.report_directory_path;
	
	public static String writeToFile(String report, ReportOutputType type) {
		String fileName = reportNamePrefix + " " + LocalDate.now() + " " + type.toString() + reportNamePostfix;
		Path reportPath = Paths.get(reportDirectory + File.separator + fileName).toAbsolutePath();
		
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

}
