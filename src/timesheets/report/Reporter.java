package timesheets.report;

import java.time.LocalDate;

import timesheets.report.ReportFormatter.OutputType;

public class Reporter {
	
	public static void createReport(String body, OutputType type) {
		StringBuilder report = new StringBuilder();
		String usedType = ReportManager.typeFormat(type);
		
		report.append(getReportHeader(usedType));
		report.append(body);
		report.append(getReportFooter());
		
		ReportManager.writeToFile(report.toString(), type);
	}
	
	private static String getReportHeader(String type) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("<!DOCTYPE HTML>\n");
		builder.append("<html>\n");
		builder.append("\t<head>\n");
		builder.append("\t\t<link rel=\"stylesheet\" href=\"style.css\" />\n");
		builder.append("\t\t<h1>Company Name: </h1>\n");
		builder.append("\t\t<p>Timesheets Report " + LocalDate.now() + " for OutputType: " + type + "</p><br>\n");
		builder.append("\t</head>\n");
		builder.append("\t<body>\n");
		
		return builder.toString();
	}
	
	private static String getReportFooter() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("\t</body>\n");
		builder.append("</html>\n");
		
		
		return builder.toString();
	}

}
