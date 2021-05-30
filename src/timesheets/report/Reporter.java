package timesheets.report;

public class Reporter {
	
	public static void createReport(String body) {
		StringBuilder report = new StringBuilder();
		
		report.append(getReportHeader());
		report.append(body);
		report.append(getReportFooter());
		
		
		ReportManager.writeToFile(report.toString());
	}
	
	private static String getReportHeader() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("<!DOCTYPE HTML>\n");
		builder.append("<html>\n");
		builder.append("\t<head>\n");
		builder.append("\t\t<link rel=\"stylesheet\" href=\"style.css\" />\n");
		builder.append("\t\tCompany Name:\n");
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
