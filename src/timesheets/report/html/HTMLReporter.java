package timesheets.report.html;

import java.time.LocalDate;

import timesheets.report.ReportOutputType;

public class HTMLReporter {
	
	public static String createReport(String body, ReportOutputType type) {
		StringBuilder report = new StringBuilder();
		
		report.append(getReportHeader(type));
		report.append(body);
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
		builder.append("\t\t<h1>Company Name: </h1>\n");
		builder.append("\t\t<p>Timesheets Report " + LocalDate.now() + " for time period: " + type.toString() + "</p><br>\n");
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
