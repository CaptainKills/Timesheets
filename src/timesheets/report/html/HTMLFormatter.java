package timesheets.report.html;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import timesheets.Employee;
import timesheets.TimeHandler;
import timesheets.logging.Logger;
import timesheets.report.ReportOutputType;
import timesheets.resources.LanguageManager;
import timesheets.sql.Database;

public class HTMLFormatter {

	private static final Logger logger = new Logger(HTMLFormatter.class);
	private static TimeHandler time = new TimeHandler();
	private static Map<String, String> lang = LanguageManager.language;

	public static String build(ReportOutputType t) {
		StringBuilder builder = new StringBuilder();

		logger.info("Building Text - " + t);
		for (Employee emp : Database.EmployeeList.values()) {
			String emp_text = buildEmployee(emp);
			builder.append(emp_text);

			String time_text = "";
			if (!emp.getWorkedTime().isEmpty()) {
				switch (t) {
				case TODAY:
					time_text = buildToday(emp.getWorkedTime());
					builder.append(time_text);
					break;
				case WEEK:
					time_text = buildWeek(emp.getWorkedTime());
					builder.append(time_text);
					break;
				case MONTH:
					time_text = buildMonth(emp.getWorkedTime());
					builder.append(time_text);
					break;
				default:
					logger.info("Default Setting Used.");
					time_text = buildToday(emp.getWorkedTime());
					builder.append(time_text);
					break;
				}
			}

			String footer_text = buildFooter();
			builder.append(footer_text);
		}

		return builder.toString();
	}

	public static String build(LocalDate beginDate, LocalDate endDate) {
		StringBuilder builder = new StringBuilder();

		for (Employee emp : Database.EmployeeList.values()) {
			String emp_text = buildEmployee(emp);
			builder.append(emp_text);

			if (!emp.getWorkedTime().isEmpty()) {
				logger.info("Building Text - Specific");
				
				String time_text = buildSpecific(emp.getWorkedTime(), beginDate, endDate);
				builder.append(time_text);
			}
			
			String table_end = buildFooter();
			builder.append(table_end);
		}
		
		return builder.toString();
	}

	private static String buildEmployee(Employee emp) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("\t\t<p>\n");
		builder.append("\t\t\t<table style=\"table-layout:fixed; width:50%\">\n");
		builder.append("\t\t\t\t<caption><b>");
		builder.append(emp.getName() + " (" + emp.getID_String() + ")");
		builder.append("</b></caption>\n");
		
		builder.append("\t\t\t\t<tr>\n");
		builder.append("\t\t\t\t\t<th>"+ lang.get("table_date") + "</th>\n");
		builder.append("\t\t\t\t\t<th>"+ lang.get("table_start_time") + "</th>\n");
		builder.append("\t\t\t\t\t<th>" + lang.get("table_end_time") + "</th>\n");
		builder.append("\t\t\t\t\t<th>" + lang.get("table_break_time") + "</th>\n");
		builder.append("\t\t\t\t\t<th>" + lang.get("table_total_time") + "</th>\n");
		builder.append("\t\t\t\t</tr>\n");

		return builder.toString();
	}

	private static String buildEntry(Map.Entry<LocalDate, LocalTime[]> entry) {
		StringBuilder builder = new StringBuilder();

		builder.append("\t\t\t\t<tr>\n");
		builder.append("\t\t\t\t\t<td><i>");
		builder.append(entry.getKey());
		builder.append("</i></td>\n");
		
		for(int i = 0; i < 4; i++) {
			builder.append("\t\t\t\t\t<td>");
			builder.append(entry.getValue()[i]);
			builder.append("</td>\n");
		}
		
		builder.append("\t\t\t\t</tr>\n");

		return builder.toString();
	}
	
	private static String buildFooter() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("\t\t\t</table>\n");
		builder.append("\t\t</p><br>\n\n");
		
		return builder.toString();
	}

	private static String buildToday(Map<LocalDate, LocalTime[]> timeMap) {
		StringBuilder builder = new StringBuilder();

		for (Map.Entry<LocalDate, LocalTime[]> entry : timeMap.entrySet()) {
			if (entry.getKey().isEqual(time.getCurrentDate())) {
				builder.append(buildEntry(entry));
			} else {
				continue;
			}
		}

		return builder.toString();
	}

	private static String buildWeek(Map<LocalDate, LocalTime[]> timeMap) {
		StringBuilder builder = new StringBuilder();

		for (Map.Entry<LocalDate, LocalTime[]> entry : timeMap.entrySet()) {
			if (entry.getKey().isAfter(time.getWeekStart().minusDays(1))
					&& entry.getKey().isBefore(time.getWeekEnd().plusDays(1))) {

				builder.append(buildEntry(entry));
			} else {
				continue;
			}
		}

		return builder.toString();
	}

	private static String buildMonth(Map<LocalDate, LocalTime[]> timeMap) {
		StringBuilder builder = new StringBuilder();

		for (Map.Entry<LocalDate, LocalTime[]> entry : timeMap.entrySet()) {
			if (entry.getKey().getMonth() == time.getCurrentDate().getMonth()
					&& entry.getKey().getYear() == time.getCurrentDate().getYear()) {
				builder.append(buildEntry(entry));
			} else {
				continue;
			}
		}

		return builder.toString();
	}

	private static String buildSpecific(Map<LocalDate, LocalTime[]> timeMap, LocalDate beginDate, LocalDate endDate) {
		StringBuilder builder = new StringBuilder();

		for (Map.Entry<LocalDate, LocalTime[]> entry : timeMap.entrySet()) {
			if (entry.getKey().isAfter(beginDate.minusDays(1)) && entry.getKey().isBefore(endDate.plusDays(1))) {
				builder.append(buildEntry(entry));
			} else {
				continue;
			}
		}

		return builder.toString();
	}

}
