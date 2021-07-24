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
	
	public static String writeToFile(Workbook wb, ReportOutputType type) {
		String fileName = reportNamePrefix + " " + LocalDate.now() + " " + type.toString() + reportNamePostfix;
		Path reportPath = Paths.get(reportDirectory + File.separator + fileName).toAbsolutePath();
		
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

}
