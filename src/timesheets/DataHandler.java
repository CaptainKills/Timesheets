package timesheets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataHandler {
	private final Path path_EmployeeData = Paths.get("data" + File.separator + "EmployeeData.dat").toAbsolutePath();
	private final Path path_TimeData = Paths.get("data" + File.separator + "TimeData.dat").toAbsolutePath();
	private final Path path_Settings = Paths.get("data" + File.separator + "Settings.dat").toAbsolutePath();
	private File file_EmployeeData = path_EmployeeData.toFile();
	private File file_TimeData = path_TimeData.toFile();
	private File file_Settings = path_Settings.toFile();

	private PrintWriter employeeWriter;
	private PrintWriter timeWriter;
	private PrintWriter settingsWriter;
	private Scanner employeeReader;
	private Scanner timeReader;
	private Scanner settingsReader;

	private static final String emp_REGEX = "[^\\/]+";
	private static final String year_REGEX = "(?<=[@]|[,])\\d+";
	private static final String month_REGEX = "(?<=[-])\\d+(?=[-])";
	private static final String day_REGEX = "\\d+(?=[=])";
	private static final String hour_REGEX = "\\d+(?=[:])";
	private static final String minutes_REGEX = "(?<=[:])\\d+";
	
	private static final String setting_REGEX = "\\S+(?=[=])";
	private static final String value_REGEX = "(?<=[=])\\S+";

	public static Map<Integer, Employee> EmployeeList = new HashMap<Integer, Employee>();
	public static Map<String, String> settings = new LinkedHashMap<String, String>();
	
	private final String defaultSettings = ""
			+ "fontsize=10\n"
			+ "width=1000\n"
			+ "height=360\n"
			+ ""
			+ ""
			+ ""
			+ ""
			+ "";
	
	private final String defaultEmployeeData = "12345/Admin/1/0.0/true";
	private final String defaultTimeData = "12345@";

	public void loadDataFromFiles() {
		checkFiles();
		
		try {
			employeeReader = new Scanner(file_EmployeeData);
			timeReader = new Scanner(file_TimeData);
			settingsReader = new Scanner(file_Settings);
			
			while (employeeReader.hasNext() & timeReader.hasNext()) {
				String employeeImport = employeeReader.nextLine();
				String timeImport = timeReader.nextLine();
				assignDataToEmployee(employeeImport, timeImport);
			}
			
			while(settingsReader.hasNext()) {
				String newline = settingsReader.nextLine();
				extractSettings(newline);
			}
		} catch (IOException e) {
			System.out.println("Could not read from file: " + e);
		} finally {
			try {
				employeeReader.close();
				timeReader.close();
				settingsReader.close();
			} catch (Exception e) {
				System.out.println("Could not close reader: " + e);
			}
		}
	}
	
	private void checkFiles() {
		if (!file_EmployeeData.exists()) {
			tryToCreateFile(file_EmployeeData, employeeWriter);
		}
		if (!file_TimeData.exists()) {
			tryToCreateFile(file_TimeData, timeWriter);
		}
		if (!file_Settings.exists()) {
			tryToCreateFile(file_Settings, settingsWriter);
		}
	}
	
	private void tryToCreateFile(File file, PrintWriter writer) {
		try {
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			
			file.createNewFile();
			writer = new PrintWriter(file);
			
			if(file.getAbsolutePath() == path_Settings.toString()) {
				writer.print(defaultSettings);
			} else if(file.getAbsolutePath() == path_EmployeeData.toString()) {
				writer.print(defaultEmployeeData);
			} else if(file.getAbsolutePath() == path_TimeData.toString()) {
				writer.print(defaultTimeData);
			}
			
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("The file could not be created: " + e);
		}
	}
	
	private void assignDataToEmployee(String employee, String time) {
		LinkedList<String> EmployeeData = extractDataViaRegex(emp_REGEX, employee);
		int import_ID = Integer.parseInt(EmployeeData.get(0));
		String import_Name = EmployeeData.get(1);
		int import_Age = Integer.parseInt(EmployeeData.get(2));
		double import_Salary = Double.parseDouble(EmployeeData.get(3));
		boolean import_Admin = Boolean.parseBoolean(EmployeeData.get(4));
		TreeMap<LocalDate, LocalTime[]> import_WorkedTime;
		
		if (time.length() > 6) {
			import_WorkedTime = getSavedTimeMap(time);
		} else {
			import_WorkedTime = new TreeMap<LocalDate, LocalTime[]>();
		}

		Employee dummy_employee = new Employee(import_ID, import_Name, import_Age, import_Salary, import_Admin, import_WorkedTime);
		EmployeeList.put(import_ID, dummy_employee);
	}
	
	private LinkedList<String> extractDataViaRegex(String regex, String inputString) {
		Pattern regexPattern = Pattern.compile(regex);
		Matcher regexMatcher = regexPattern.matcher(inputString);

		LinkedList<String> groupList = new LinkedList<String>();
		while (regexMatcher.find()) {
			String currentGroup = regexMatcher.group();
			groupList.add(currentGroup);
		}
		return groupList;
	}

	private TreeMap<LocalDate, LocalTime[]> getSavedTimeMap(String time) {
		LinkedList<String> yearList = extractDataViaRegex(year_REGEX, time);
		LinkedList<String> monthList = extractDataViaRegex(month_REGEX, time);
		LinkedList<String> dayList = extractDataViaRegex(day_REGEX, time);
		LinkedList<String> hourList = extractDataViaRegex(hour_REGEX, time);
		LinkedList<String> minutesList = extractDataViaRegex(minutes_REGEX, time);

		LinkedList<LocalDate> datesList = new LinkedList<LocalDate>();
		for (int index = 0; index < yearList.size(); index++) {
			datesList.add(LocalDate.of(Integer.parseInt(yearList.get(index)), Integer.parseInt(monthList.get(index)),
					Integer.parseInt(dayList.get(index))));
		}

		LinkedList<LocalTime> timeList = new LinkedList<LocalTime>();
		for (int index = 0; index < hourList.size(); index++) {
			timeList.add(LocalTime.of(Integer.parseInt(hourList.get(index)), Integer.parseInt(minutesList.get(index))));
		}

		TreeMap<LocalDate, LocalTime[]> shiftsMap = new TreeMap<LocalDate, LocalTime[]>();
		int index = 0;
		for (LocalDate date : datesList) {
			LocalTime[] workedHours = { timeList.get(4 * index), timeList.get((4 * index) + 1),
					timeList.get((4 * index) + 2), timeList.get((4 * index) + 3) };
			shiftsMap.put(date, workedHours);
			index++;
		}
		return shiftsMap;
	}
	
	private void extractSettings(String line) {
		Pattern settingPattern = Pattern.compile(setting_REGEX);
		Matcher settingMatcher = settingPattern.matcher(line);
		
		Pattern valuePattern = Pattern.compile(value_REGEX);
		Matcher valueMatcher = valuePattern.matcher(line);
		
		while(settingMatcher.find() & valueMatcher.find()) {
			String setting = settingMatcher.group();
			String value = valueMatcher.group();
			
			settings.put(setting, value);
		}
	}

	public void saveDataToFiles() {
		checkFiles();
		
		try {
			employeeWriter = new PrintWriter(file_EmployeeData);
			timeWriter = new PrintWriter(file_TimeData);
			settingsWriter = new PrintWriter(file_Settings);
			
			writeDataToFile(employeeWriter, timeWriter);
			writeSettingsToFile(settingsWriter);
		} catch (Exception e) {
			System.out.println("Could not write to the file: " + e);
		} finally {
			employeeWriter.flush();
			employeeWriter.close();
			timeWriter.flush();
			timeWriter.close();
			settingsWriter.flush();
			settingsWriter.close();
		}
	}
	
	private void writeDataToFile(PrintWriter employee, PrintWriter time) {
		for (Map.Entry<Integer, Employee> entry : EmployeeList.entrySet()) {
			Employee dummy_employee = entry.getValue();
			employee.println(dummy_employee.toString());
			time.println(dummy_employee.getID_String() + "@" + dummy_employee.timeMapToString());
		}
	}
	
	private void writeSettingsToFile(PrintWriter writer) {
		for (Entry<String, String> entry : settings.entrySet()) {
			String setting = entry.getKey();
			String value = entry.getValue();
			
			writer.println(setting + "=" + value);
		}
	}

	public int generateNewID() {
		int newID = new Random().nextInt(100000);

		if (isIdUsed(newID)) {
			return generateNewID();
		} else {
			return newID;
		}
	}
	
	public Boolean isIdUsed(int id) {
		if (EmployeeList.get(id) != null) {
			return true;
		} else {
			return false;
		}
	}
}
