package nl.triantis.timesheets.report.excel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import nl.triantis.timesheets.Employee;
import nl.triantis.timesheets.TimeHandler;
import nl.triantis.timesheets.gui.lists.TextFieldList;

public class ExcelFormatter {
	
	private static TimeHandler time = new TimeHandler();
	
	public static void buildToday(Row row, Employee emp, Map.Entry<LocalDate, LocalTime[]> entry) {
		LocalDate entryDate = entry.getKey();
		LocalDate currentDate = time.getCurrentDate();
		
		if(entryDate.isEqual(currentDate)) {
			buildEntry(row, emp, entry);
		}
	}
	
	public static void buildWeek(Row row, Employee emp, Map.Entry<LocalDate, LocalTime[]> entry) {
		LocalDate entryDate = entry.getKey();
		LocalDate weekStart = time.getWeekStart().minusDays(1);
		LocalDate weekEnd = time.getWeekEnd().plusDays(1);
		
		if (entryDate.isAfter(weekStart) && entryDate.isBefore(weekEnd)) {
			buildEntry(row, emp, entry);
		}
	}
	
	public static void buildMonth(Row row, Employee emp, Map.Entry<LocalDate, LocalTime[]> entry) {
		LocalDate entryDate = entry.getKey();
		LocalDate currentDate = time.getCurrentDate();
		
		if (entryDate.getMonth() == currentDate.getMonth() && entryDate.getYear() == currentDate.getYear()) {
			buildEntry(row, emp, entry);
		}
	}
	
	public static void buildSpecific(Row row, Employee emp, Map.Entry<LocalDate, LocalTime[]> entry) {
		LocalDate entryDate = entry.getKey();
		LocalDate beginDate = ((LocalDate) TextFieldList.startingDateInput.getValue()).minusDays(1);
		LocalDate endDate = ((LocalDate) TextFieldList.endingDateInput.getValue()).plusDays(1);
		
		if (entryDate.isAfter(beginDate) && entryDate.isBefore(endDate)) {
			buildEntry(row, emp, entry);
		}
	}
	
	private static void buildEntry(Row row, Employee emp, Map.Entry<LocalDate, LocalTime[]> entry) {
		LocalTime[] workedTime = entry.getValue();
		
		row.createCell(0).setCellValue(emp.getID_String());
		row.createCell(1).setCellValue(emp.getFirstName());
		row.createCell(2).setCellValue(emp.getLastName());
		
		row.createCell(3).setCellValue(entry.getKey().toString());
		row.createCell(4).setCellValue(workedTime[0].toString());
		row.createCell(5).setCellValue(workedTime[1].toString());
		row.createCell(6).setCellValue(workedTime[2].toString());
		row.createCell(7).setCellValue(workedTime[3].toString());
	}

}
