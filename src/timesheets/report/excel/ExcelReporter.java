package timesheets.report.excel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import timesheets.Employee;
import timesheets.report.ReportOutputType;
import timesheets.resources.LanguageManager;
import timesheets.sql.Database;

public class ExcelReporter {
	
	private static Map<String, String> lang = LanguageManager.language;
	private static String[] headers = {"ID:", lang.get("first_name_label"), lang.get("last_name_label"), lang.get("table_date"),
			lang.get("table_start_time"), lang.get("table_end_time"), lang.get("table_break_time"), lang.get("table_total_time")};
	
	public static String createReport(ReportOutputType type) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Timesheets Report");
		
		makeReportHeader(sheet);
		makeReportBody(sheet, type);
		makeReportStyle(sheet);

		String fileName = ExcelReportManager.writeToFile(workbook, type);
		return fileName;
	}
	
	private static void makeReportHeader(Sheet sheet) {
		Row headerRow = sheet.createRow(0);
		for(int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
		}
	}
	
	private static void makeReportBody(Sheet sheet, ReportOutputType type) {
		int rowId = 1;
		for(Employee emp : Database.EmployeeList.values()) {
			for(Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
				Row row = sheet.createRow(rowId);
				
				switch(type) {
				case TODAY:
					ExcelFormatter.buildToday(row, emp, entry);
					break;
				case WEEK:
					ExcelFormatter.buildWeek(row, emp, entry);
					break;
				case MONTH:
					ExcelFormatter.buildMonth(row, emp, entry);
					break;
				case SPECIFIC:
					ExcelFormatter.buildSpecific(row, emp, entry);
					break;
				default:
					break;
				}
				
				rowId++;
			}
		}
	}
	
	private static void makeReportStyle(Sheet sheet) {
		for(int i = 0; i < headers.length; i++) {
			sheet.autoSizeColumn(i);
		}
	}

}
