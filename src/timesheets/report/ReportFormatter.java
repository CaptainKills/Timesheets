package timesheets.report;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import timesheets.Employee;
import timesheets.Settings;
import timesheets.TimeHandler;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class ReportFormatter {

	private static final Logger logger = new Logger(ReportFormatter.class);
	private static TimeHandler time = new TimeHandler();

	public enum OutputType {
		TODAY, WEEK, MONTH
	}

	public static String build(OutputType t) {
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

			builder.append("</p>");
		}

		return builder.toString();
	}

	public static String build(LocalDate beginDate, LocalDate endDate) {
		StringBuilder builder = new StringBuilder();

		for (Employee emp : Database.EmployeeList.values()) {
			String emp_text = buildEmployee(emp);
			builder.append(emp_text);

			String time_text = "";
			if (!emp.getWorkedTime().isEmpty()) {
				logger.info("Building Text - Specific");
				time_text = buildSpecific(emp.getWorkedTime(), beginDate, endDate);
				builder.append(time_text);
			}
		}

		builder.append("</p>");
		return builder.toString();
	}

	private static String buildEmployee(Employee emp) {
		StringBuilder builder = new StringBuilder();
		int fontsize = Integer.parseInt(Settings.settings.get("fontsize")) + 1;

		builder.append("<p style=\"");
		builder.append("font-size:" + fontsize + "px;");
		builder.append("font-family:Arial;");
		builder.append("\">");

		builder.append("<b>");
		builder.append(emp.getName() + " (" + emp.getID_String() + ")");
		builder.append("</b><br>");

		return builder.toString();
	}

	private static String buildEntry(Map.Entry<LocalDate, LocalTime[]> entry) {
		StringBuilder builder = new StringBuilder();

		builder.append("<i>");
		builder.append(entry.getKey());
		builder.append(":</i> ");

		String seperator = " | ";
		builder.append(entry.getValue()[0]);
		builder.append(seperator);
		builder.append(entry.getValue()[1]);
		builder.append(seperator);
		builder.append(entry.getValue()[2]);
		builder.append(seperator);
		builder.append(entry.getValue()[3]);

		builder.append("<br>");

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
