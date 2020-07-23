package timesheets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timesheets.logging.Logger;

public class DataHandler {
	private static final Logger logger = new Logger(DataHandler.class.toString());

	private final Path path_EmployeeData = Paths.get("data" + File.separator + "EmployeeData.dat").toAbsolutePath();
	private final Path path_TimeData = Paths.get("data" + File.separator + "TimeData.dat").toAbsolutePath();
	private final Path path_Settings = Paths.get("data" + File.separator + "Settings.dat").toAbsolutePath();
	private File file_EmployeeData = path_EmployeeData.toFile();
	private File file_TimeData = path_TimeData.toFile();
	private File file_Settings = path_Settings.toFile();

	private static final String date_REGEX = "(?<=[@]|[,])[^=]+";
	private static final String time_REGEX = "(?<=[=]|[|]).....";

	public static Map<Integer, Employee> EmployeeList = new HashMap<Integer, Employee>();
	public static Map<String, String> settings = new LinkedHashMap<String, String>();

	private final String defaultSettings = "" + "fontsize=10\n" + "width=1000\n" + "height=360\n" + "" + "" + "" + ""
			+ "";

	private final String defaultEmployeeData = "12345/Admin/1/0.0/true";
	private final String defaultTimeData = "12345@";

	public void loadDataFromFiles() {
		checkFiles();

		logger.info("Loading Data From Files.");
		try (Scanner employeeReader = new Scanner(file_EmployeeData);
				Scanner timeReader = new Scanner(file_TimeData);
				BufferedReader settingsReader = new BufferedReader(new FileReader(file_Settings))) {
			
			logger.info("Loading Employee Data from File.");
			while (employeeReader.hasNext() & timeReader.hasNext()) {
				String employeeImport = employeeReader.nextLine();
				String timeImport = timeReader.nextLine();
				assignDataToEmployee(employeeImport, timeImport);
			}

			logger.info("Loading Settings from File.");
			String newline;
			while ((newline = settingsReader.readLine()) != null) {
				extractSettings(newline);
			}
		} catch (IOException e) {
			logger.error("COULD NOT LOAD DATA FROM FILES: " + e);
		}

		logger.info("Loading Data From Files Complete.");
	}

	private void checkFiles() {
		if (!file_EmployeeData.exists()) {
			logger.info("EmplopyeeData file does not exist.");
			createFile(file_EmployeeData);
		} else if (file_EmployeeData.length() == 0) {
			logger.info("EmployeeData File is empty: Length=" + file_EmployeeData.length());
			writeDefaultsToFile(file_EmployeeData);
		} else {
			logger.info("EmplopyeeData file exists.");
		}
		
		if (!file_TimeData.exists()) {
			logger.info("TimeData file does not exist.");
			createFile(file_TimeData);
		} else if (file_TimeData.length() == 0) {
			logger.info("TimeData File is empty: Length=" + file_TimeData.length());
			writeDefaultsToFile(file_TimeData);
		} else {
			logger.info("TimeData file exists.");
		}
		
		if (!file_Settings.exists()) {
			logger.info("Settings file does not exist.");
			createFile(file_Settings);
		} else if (file_Settings.length() == 0) {
			logger.info("Settings File is empty: Length=" + file_Settings.length());
			writeDefaultsToFile(file_Settings);
		} else {
			logger.info("Settings file exists.");
		}
	}

	private void createFile(File file) {
		try {
			if (!file.getParentFile().exists()) {
				logger.info("Parent Directory of files does not exist.");
				file.getParentFile().mkdirs();
				logger.info("Parent Directory of files created.");
			}
		} catch (Exception e) {
			logger.error("COULD NOT CREATE PARENT DIRECTORY: " + e);
		}
		
		try (PrintWriter writer = new PrintWriter(file)) {
			file.createNewFile();
			logger.info("New File created at " + file.toString());
			writeDefaultsToFile(file);
			logger.info("File creation succesfully completed.");
		} catch (IOException e) {
			logger.error("COULD NOT CREATE FILE: " + e);
		}
	}
	
	private void writeDefaultsToFile(File file) {
		try(PrintWriter writer = new PrintWriter(file)){
			if (file.getAbsolutePath() == path_Settings.toString()) {
				logger.debug("Writing default settings to Settings file.");
				writer.print(defaultSettings);
			} else if (file.getAbsolutePath() == path_EmployeeData.toString()) {
				logger.debug("Writing default employee data to Settings file.");
				writer.print(defaultEmployeeData);
			} else if (file.getAbsolutePath() == path_TimeData.toString()) {
				logger.debug("Writing default settings to Settings file.");
				writer.print(defaultTimeData);
			}
		} catch (IOException e){
			logger.error("COULD NOT WRITE DEFAULTS TO FILE: " + e);
		}
	}

	private void assignDataToEmployee(String employee, String time) {
		String[] EmployeeData = employee.split("/");
		int import_ID = Integer.parseInt(EmployeeData[0]);
		String import_Name = EmployeeData[1];
		int import_Age = Integer.parseInt(EmployeeData[2]);
		double import_Salary = Double.parseDouble(EmployeeData[3]);
		boolean import_Admin = Boolean.parseBoolean(EmployeeData[4]);
		TreeMap<LocalDate, LocalTime[]> import_WorkedTime;

		if (time.length() > 6) {
			import_WorkedTime = getSavedTimeMap(time);
		} else {
			import_WorkedTime = new TreeMap<LocalDate, LocalTime[]>();
		}

		Employee dummy_employee = new Employee(import_ID, import_Name, import_Age, import_Salary, import_Admin,
				import_WorkedTime);
		EmployeeList.put(import_ID, dummy_employee);
		logger.info("Employee Loaded: " + dummy_employee.getID_String());
	}

	private TreeMap<LocalDate, LocalTime[]> getSavedTimeMap(String time) {
		LinkedList<String> dates = extractREGEX(date_REGEX, time);
		LinkedList<String> times = extractREGEX(time_REGEX, time);

		LinkedList<LocalDate> datesList = new LinkedList<LocalDate>();
		for (int index = 0; index < dates.size(); index++) {
			datesList.add(LocalDate.parse(dates.get(index)));
		}

		LinkedList<LocalTime> timeList = new LinkedList<LocalTime>();
		for (int index = 0; index < times.size(); index++) {
			timeList.add(LocalTime.parse(times.get(index)));
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

	private LinkedList<String> extractREGEX(String regex, String inputString) {
		Pattern regexPattern = Pattern.compile(regex);
		Matcher regexMatcher = regexPattern.matcher(inputString);

		LinkedList<String> groupList = new LinkedList<String>();
		while (regexMatcher.find()) {
			String currentGroup = regexMatcher.group();
			groupList.add(currentGroup);
		}
		return groupList;
	}

	private void extractSettings(String line) {
		String[] segments = line.split("=");
		String setting = segments[0];
		String value = segments[1];

		settings.put(setting, value);
		logger.info("Added Setting " + setting + ", Value: " + value);
	}

	public void saveDataToFiles() {
		checkFiles();

		logger.info("Saving Data to Files.");
		try (PrintWriter employeeWriter = new PrintWriter(file_EmployeeData);
				PrintWriter timeWriter = new PrintWriter(file_TimeData);) {

			writeDataToFile(employeeWriter, timeWriter);
			logger.info("Succesfully saved data to files.");
		} catch (IOException e) {
			logger.error("COULD NOT WRITE TO FILE: " + e);
		}
	}

	private void writeDataToFile(PrintWriter employeeWriter, PrintWriter timeWriter) {
		logger.debug("Writing employee data to file.");

		for (Map.Entry<Integer, Employee> entry : EmployeeList.entrySet()) {
			Employee employee = entry.getValue();
			employeeWriter.println(employee.toString());
			timeWriter.println(employee.getID_String() + "@" + employee.timeMapToString());
		}
	}

	public int generateNewID() {
		int newID = new Random().nextInt(100000); // Generate number from 00000 to 99999 - a five digit ID

		if (isIdUsed(newID)) {
			return generateNewID();
		} else {
			logger.debug("Generated new ID: " + newID);
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
