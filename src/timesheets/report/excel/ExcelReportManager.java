package timesheets.report.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.apache.poi.ss.usermodel.Workbook;

import timesheets.logging.Logger;
import timesheets.report.ReportOutputType;
import timesheets.resources.LanguageManager;
import timesheets.resources.ResourceHandler;

public class ExcelReportManager {
	private static final Logger logger = new Logger(ExcelReportManager.class);
	
	private static String reportNamePrefix = LanguageManager.language.get("report_name_prefix");
	private static String reportNamePostfix = ".xlsx";
	
	private static final Path reportDirectory = ResourceHandler.report_directory_path;
	private static Path reportPath;
	
	public static String writeToFile(Workbook wb, ReportOutputType type) {
		String fileName = initialiseFileDirectory(type);
		
		try (OutputStream fileOut = new FileOutputStream(reportPath.toString())) {
			logger.debug("Writing workbook to file.");
		    wb.write(fileOut);
		    wb.close();
		    logger.debug("Finished writing workbook to file.");
		} catch(IOException e) {
			logger.error("COULD NOT WRITE TO REPORT FILE!", e);
		}
		
		return fileName;
	}
	
	private static String initialiseFileDirectory(ReportOutputType type) {
		String fileName = reportNamePrefix + " " + LocalDate.now() + " " + type.toString() + reportNamePostfix;
		
		try {
			logger.info("Initialising Report Directory.");
			
			reportPath = Paths.get(reportDirectory + File.separator + fileName).toAbsolutePath();
			File reportFile = reportPath.toFile();
			
			if(!reportFile.getParentFile().exists()) {
				logger.debug("Parent directory of report file does not exist.");
				reportFile.getParentFile().mkdirs();
			}
			
		} catch (SecurityException e) {
			logger.error("COULD NOT CREATE REPORT DIRECTORY!", e);
		}
		
		logger.debug("Report directory successfully initialised.");
		return fileName;
	}

}
