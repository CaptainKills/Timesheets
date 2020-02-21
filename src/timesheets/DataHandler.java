package timesheets;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.*;
import java.time.*;

public class DataHandler {
	private final String filePath_EmployeeData = "EmployeeData.dat";
	private final String filePath_TimeData = "TimeData.dat";
	private final String filePath_Settings = "Settings.dat";
	private File file_EmployeeData = new File(filePath_EmployeeData);
	private File file_TimeData = new File(filePath_TimeData);
	private File file_Settings = new File(filePath_Settings);

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

	private int import_ID;
	private String import_Name;
	private int import_Age;
	private double import_Salary;
	private Boolean import_Admin;

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
	
	private LinkedList<String> EmployeeData;
	private TreeMap<LocalDate, LocalTime[]> import_WorkedTime;

	private Random rand = new Random();
	private String idString;
	private int[] idNumbers = { 0, 0, 0, 0, 0 };
	private int newID;

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
			System.out.print("Could not read from file: " + e);
		} finally {
			try {
				employeeReader.close();
				timeReader.close();
				settingsReader.close();
			} catch (Exception e) {
				System.out.print("Could not close reader: " + e);
			}
		}
	}
	
	private void checkFiles() {
		if (!file_EmployeeData.exists()) {
			tryToCreateFile(filePath_EmployeeData, file_EmployeeData, employeeWriter);
		}
		if (!file_TimeData.exists()) {
			tryToCreateFile(filePath_TimeData, file_TimeData, timeWriter);
		}
		if (!file_Settings.exists()) {
			tryToCreateFile(filePath_Settings, file_Settings, settingsWriter);
		}
	}
	
	private void tryToCreateFile(String filepath, File file, PrintWriter writer) {
		try {
			final Formatter formatter = new Formatter(filepath);
			writer = new PrintWriter(file);
			
			if(filepath.equals(filePath_Settings)) {
				writer.print(defaultSettings);
			}
			
			writer.flush();
			writer.close();
			formatter.close();
		} catch (Exception e) {
			System.out.println("The file could not be created: " + e);
		}
	}
	
	private void assignDataToEmployee(String employee, String time) {
		EmployeeData = extractDataViaRegex(emp_REGEX, employee);
		import_ID = Integer.parseInt(EmployeeData.get(0));
		import_Name = EmployeeData.get(1);
		import_Age = Integer.parseInt(EmployeeData.get(2));
		import_Salary = Double.parseDouble(EmployeeData.get(3));
		import_Admin = Boolean.parseBoolean(EmployeeData.get(4));
		
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
			employeeWriter = new PrintWriter(filePath_EmployeeData);
			timeWriter = new PrintWriter(filePath_TimeData);
			settingsWriter = new PrintWriter(filePath_Settings);
			
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
			time.println(dummy_employee.getID() + "@" + dummy_employee.timeMapToString());
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
		for (int i = 0; i < 5; i++) {
			idNumbers[i] = rand.nextInt(9);
		}
		newID = convertIdArrayToInt(idNumbers);

		if (isIdUsed(newID)) {
			return generateNewID();
		} else {
			return newID;
		}
	}
	
	private int convertIdArrayToInt(int[] generatedId) {
		if (idNumbers[0] == 0) {
			idString = Integer.toString(generatedId[0] + 1) + Integer.toString(generatedId[1])
					+ Integer.toString(generatedId[2]) + Integer.toString(generatedId[3]) + Integer.toString(generatedId[4]);
		} else {
			idString = Integer.toString(generatedId[0]) + Integer.toString(generatedId[1]) + Integer.toString(generatedId[2])
					+ Integer.toString(generatedId[3]) + Integer.toString(generatedId[4]);
		}
		return Integer.parseInt(idString);
	}
	
	public Boolean isIdUsed(int id) {
		if (EmployeeList.get(id) != null) {
			return true;
		} else {
			return false;
		}
	}
}
