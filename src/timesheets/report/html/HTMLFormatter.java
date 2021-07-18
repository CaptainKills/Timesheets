package timesheets.report.html;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import timesheets.Employee;
import timesheets.TimeHandler;
import timesheets.gui.lists.TextFieldList;
import timesheets.resources.LanguageManager;

public class HTMLFormatter {

	private static TimeHandler time = new TimeHandler();

	public static String buildToday(Map.Entry<LocalDate, LocalTime[]> entry) {
		StringBuilder builder = new StringBuilder();

		LocalDate entryDate = entry.getKey();
		LocalDate currentDate = time.getCurrentDate();
		
		if(entryDate.isEqual(currentDate)) {
			builder.append(buildEntry(entry));
		}

		return builder.toString();
	}

	public static String buildWeek(Map.Entry<LocalDate, LocalTime[]> entry) {
		StringBuilder builder = new StringBuilder();

		LocalDate entryDate = entry.getKey();
		LocalDate weekStart = time.getWeekStart().minusDays(1);
		LocalDate weekEnd = time.getWeekEnd().plusDays(1);
		
		if (entryDate.isAfter(weekStart) && entryDate.isBefore(weekEnd)) {
			builder.append(buildEntry(entry));
		}

		return builder.toString();
	}

	public static String buildMonth(Map.Entry<LocalDate, LocalTime[]> entry) {
		StringBuilder builder = new StringBuilder();

		LocalDate entryDate = entry.getKey();
		LocalDate currentDate = time.getCurrentDate();
		
		if (entryDate.getMonth() == currentDate.getMonth() && entryDate.getYear() == currentDate.getYear()) {
			builder.append(buildEntry(entry));
		}

		return builder.toString();
	}

	public static String buildSpecific(Map.Entry<LocalDate, LocalTime[]> entry) {
		StringBuilder builder = new StringBuilder();
		
		LocalDate entryDate = entry.getKey();
		LocalDate beginDate = ((LocalDate) TextFieldList.startingDateInput.getValue()).minusDays(1);
		LocalDate endDate = ((LocalDate) TextFieldList.endingDateInput.getValue()).plusDays(1);
		
		if (entryDate.isAfter(beginDate) && entryDate.isBefore(endDate)) {
			builder.append(buildEntry(entry));
		}

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
	
	public static String buildTableHeader(Employee emp) {
		StringBuilder builder = new StringBuilder();
		Map<String, String> lang = LanguageManager.language;
		
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
	
	public static String buildTableFooter() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("\t\t\t</table>\n");
		builder.append("\t\t</p><br>\n\n");
		
		return builder.toString();
	}

}
