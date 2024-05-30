package nl.triantis.timesheets.report.html;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import nl.triantis.timesheets.Employee;
import nl.triantis.timesheets.report.ReportOutputType;
import nl.triantis.timesheets.resources.LanguageManager;
import nl.triantis.timesheets.sql.Database;

public class HTMLReporter {
	
	private static Map<String, String> lang = LanguageManager.language;
	private static String[] headers = {lang.get("html_company_label"), lang.get("report_name_prefix"),
			lang.get("html_timeperiod_label")};
	
	public static String createReport(ReportOutputType type) {
		StringBuilder report = new StringBuilder();
		
		report.append(getReportHeader(type));
		report.append(getReportBody(type));
		report.append(getReportFooter());
		
		String fileName = HTMLReportManager.writeToFile(report.toString(), type);
		return fileName;
	}
	
	private static String getReportHeader(ReportOutputType type) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("<!DOCTYPE HTML>\n");
		builder.append("<html>\n");
		builder.append("\t<head>\n");
		builder.append("\t\t<link rel=\"stylesheet\" href=\"style.css\" />\n");
		builder.append("\t\t<h1>"+ headers[0] + " </h1>\n");
		builder.append("\t\t<p>" + headers[1] + " " + LocalDate.now() + " " + headers[2] + " " + type.toString() + "</p><br>\n");
		builder.append("\t</head>\n");
		builder.append("\t<body>\n");
		
		return builder.toString();
	}
	
	private static String getReportBody(ReportOutputType type) {
		StringBuilder builder = new StringBuilder();
		
		for(Employee emp : Database.EmployeeList.values()) {
			builder.append(HTMLFormatter.buildTableHeader(emp));
			
			for(Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
				switch(type) {
				case TODAY:
					builder.append(HTMLFormatter.buildToday(entry));
					break;
				case WEEK:
					builder.append(HTMLFormatter.buildWeek(entry));
					break;
				case MONTH:
					builder.append(HTMLFormatter.buildMonth(entry));
					break;
				case SPECIFIC:
					builder.append(HTMLFormatter.buildSpecific(entry));
					break;
				default:
					break;
				}
			}
			
			builder.append(HTMLFormatter.buildTableFooter());
		}
		
		return builder.toString();
	}
	
	private static String getReportFooter() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("\t</body>\n");
		builder.append("</html>\n");
		
		return builder.toString();
	}

}
