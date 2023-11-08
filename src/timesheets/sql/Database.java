package timesheets.sql;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import timesheets.Employee;
import timesheets.Settings;
import timesheets.TimeHandler;
import timesheets.gui.other.CustomOptionPane;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;
import timesheets.resources.ResourceHandler;

public class Database {
	private static final Logger logger = new Logger(Database.class);
	private static TimeHandler time = new TimeHandler();
	private static final String enc_key = "HWEupmmPvjfwlUk6";

	private static final File database_file = ResourceHandler.database_path.toFile();
	private static final File encrypted_file = ResourceHandler.encrypted_path.toFile();
	private static final File directory = ResourceHandler.data_directory_path.toFile();

	public static Map<Integer, Employee> EmployeeList = new TreeMap<Integer, Employee>();
	private static Map<String, String> lang = LanguageManager.language;
	
	private static String dialogTitleSuccess = lang.get("jop_db_title_success");
	private static String dialogMsgSuccess = lang.get("jop_db_msg_success");
	private static String dialogTitleFail = lang.get("jop_db_title_fail");
	private static String dialogMsgFail = lang.get("jop_db_msg_fail");

	public void setup() {
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(SQLQuery.queryCreateEmployeeTable);
			ResultSet rs = stmt.executeQuery(SQLQuery.queryCountEmployees);
			
			int numberOfEmployeeEntries = rs.getInt(1);
			logger.debug("SQL Table \"employees\" has " + numberOfEmployeeEntries + " entries.");
			
			if(numberOfEmployeeEntries == 0) {
				logger.debug("No entries. Adding Administrator.");
				PreparedStatement pstmt = conn.prepareStatement(SQLQuery.queryAddEmployee);
				
				pstmt.setInt(1, 12345);
				pstmt.setString(2, "Administrator");
				pstmt.setString(3, " ");
				pstmt.setInt(4, 0);
				pstmt.setDouble(5, 0.0);
				pstmt.setBoolean(6, true);
				
				pstmt.execute();
				pstmt.close();
			}
			
			stmt.execute(SQLQuery.queryCreateTimeTable);
			rs = stmt.executeQuery(SQLQuery.queryCountTime);
			
			int numberOfTimeEntries = rs.getInt(1);
			logger.debug("SQL Table \"timedata\" has " + numberOfTimeEntries + " entries.");
			
			rs.close();
		} catch (SQLException e) {
			logger.error("COULD NOT SET UP SQLite DATABASE!", e);
		}
	}

	private Connection connect() throws SQLException {
		Encryption.decrypt(enc_key, encrypted_file, database_file);
		String url = "jdbc:sqlite:" + database_file;

		logger.info("Establishing connection to SQLite Database...");
		Connection conn = DriverManager.getConnection(url);
		
		if (conn != null) {
			logger.info("Connection to SQLite Database has been established.");
		} else {
			logger.warn("Connection to SQLite Database failed.");
		}

		return conn;
	}

	public void load() {
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQLQuery.querySelectAllEmployees);) {
			logger.info("Loading Database in program.");
			
			while (rs.next()) {
				int employeeID = rs.getInt("id");
				TreeMap<LocalDate, LocalTime[]> timeMap = loadTimeData(conn, employeeID);
				
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				int age = rs.getInt("age");
				double salary = rs.getDouble("salary");
				boolean admin = rs.getBoolean("admin");
				
				Employee employee = new Employee(employeeID, firstName, lastName, age, salary, admin, timeMap);
				EmployeeList.put(employeeID, employee);
			}
			
			logger.info("Loading Database Complete.");
		} catch (SQLException e) {
			logger.error("COULD NOT LOAD EMPLOYEES FROM DATABASE!", e);
		} finally {
			Encryption.encrypt(enc_key, database_file, encrypted_file);
		}
	}

	private TreeMap<LocalDate, LocalTime[]> loadTimeData(Connection c, int id) throws SQLException {
		TreeMap<LocalDate, LocalTime[]> timemap = new TreeMap<LocalDate, LocalTime[]>();
		
		PreparedStatement pstmt = c.prepareStatement(SQLQuery.querySelectTime);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			LocalDate date = rs.getDate("date").toLocalDate();
			LocalTime startTime = rs.getTime("start").toLocalTime();
			LocalTime endTime = rs.getTime("end").toLocalTime();
			LocalTime breakTime = rs.getTime("break").toLocalTime();
			LocalTime totalTime = rs.getTime("total").toLocalTime();
					
			LocalTime[] workedHours = {startTime, endTime, breakTime, totalTime};
			timemap.put(date, workedHours);
		}
		
		pstmt.close();

		return timemap;
	}

	public static void backupDatabase() {
		try {
			logger.debug("Creating Backup of SQLite Database.");
			String fileName = "Timesheets Backup " + time.getCurrentDate();
			String directory = ResourceHandler.data_directory_path.toString();
			String postfix = ResourceHandler.encrypted_postfix;

			Path backup_path = Paths.get(directory + File.separator + fileName + postfix).toAbsolutePath();
			File backup = backup_path.toFile();

			if (backup.exists()) {
				logger.debug("Current Backup File will be deleted.");
				backup.delete();
			}

			Files.copy(encrypted_file.toPath(), backup_path, StandardCopyOption.COPY_ATTRIBUTES);
			logger.debug("Backup of SQLite Database Created.");
		} catch (IOException e) {
			logger.error("COULD NOT CREATE BACKUP!", e);
		}
	}

	public static void revertBackup(String backupName) {
		try {
			backupDatabase();
			logger.debug("Reverting Backup of SQLite Database.");
			String postfix = ResourceHandler.encrypted_postfix;

			Path backup_path = Paths.get(directory + File.separator + backupName + postfix).toAbsolutePath();

			Files.copy(backup_path, encrypted_file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			logger.info("Backup of SQLite Database Reverted.");
			
			CustomOptionPane cop = new CustomOptionPane("DB Backup Success");
			cop.setText(dialogTitleSuccess, dialogMsgSuccess);
			cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			cop.showDialog();
		} catch (IOException e) {
			logger.error("COULD NOT REVERT BACKUP!", e);
			
			CustomOptionPane cop = new CustomOptionPane("DB Backup Fail");
			cop.setText(dialogTitleFail, dialogMsgFail);
			cop.setConfig(JOptionPane.ERROR_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			cop.showDialog();
		}
	}

	public void cleanDirectory() {
		int number_of_backups = Integer.parseInt(Settings.settings.get("number_of_backups"));
		Path directory = ResourceHandler.data_directory_path;
		String[] directoryFiles = directory.toFile().list();

		if (directoryFiles != null) {
			logger.info("Backup files in Directory: " + directoryFiles.length + ", # of Files allowed: " + number_of_backups);
			int difference = directoryFiles.length - number_of_backups - 1; // -1 because the original file is not included.
			
			if (difference > 0) {
				logger.debug("There are currently " + difference + "  backups too many.");
				
				for (int i = 0; i < difference; i++) {
					if (directoryFiles[i].contains("Backup")) {
						logger.debug("Removing " + directoryFiles[i]);
						File f = new File(directory + File.separator + directoryFiles[i]);
						f.delete();
					}
				}
				
				logger.info("Backup Directory Clean finished.");
			} else {
				logger.info("Backup Limit has not been reached yet. Cleaning Backup Directory skipped.");
			}
		} else {
			logger.debug("Backup files in Directory: 0, directory is empty.");
		}
	}

	public void insertEmployee(Employee emp) {
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(SQLQuery.queryAddEmployee)) {
			pstmt.setInt(1, emp.getID());
			pstmt.setString(2, emp.getFirstName());
			pstmt.setString(3, emp.getLastName());
			pstmt.setInt(4, emp.getAge());
			pstmt.setDouble(5, emp.getSalary());
			pstmt.setBoolean(6, emp.getAdmin());

			pstmt.executeUpdate();
			logger.info("Added Employee to Database: " + emp.getID());
		} catch (SQLException e) {
			logger.error("COULD NOT INSERT EMPLOYEE INTO DATABASE!", e);
		} finally {
			Encryption.encrypt(enc_key, database_file, encrypted_file);
		}
	}

	public void deleteEmployee(int id) {
		try (Connection conn = this.connect();
				PreparedStatement pstmt_emp = conn.prepareStatement(SQLQuery.queryDeleteEmployee);
				PreparedStatement pstmt_time = conn.prepareStatement(SQLQuery.queryDeleteTimeMap)) {
			pstmt_emp.setInt(1, id);
			pstmt_time.setInt(1, id);
			
			pstmt_emp.executeUpdate();
			pstmt_time.executeUpdate();

			logger.info("Deleted Employee from Database: " + id);
		} catch (SQLException e) {
			logger.error("COULD NOT DELETE EMPLOYEE FROM DATABASE!", e);
		} finally {
			Encryption.encrypt(enc_key, database_file, encrypted_file);
		}
	}

	public void updateEmployee(int oldID, Employee emp) {
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(SQLQuery.queryUpdateEmployee)) {
			pstmt.setInt(1, emp.getID());
			pstmt.setString(2, emp.getFirstName());
			pstmt.setString(3, emp.getLastName());
			pstmt.setInt(4, emp.getAge());
			pstmt.setDouble(5, emp.getSalary());
			pstmt.setBoolean(6, emp.getAdmin());
			pstmt.setInt(7, oldID);

			pstmt.executeUpdate();
			logger.info("Updated Employee in Database: " + oldID + " -> " + emp.getID());
		} catch (SQLException e) {
			logger.error("COULD NOT UPDATE EMPLOYEE IN DATABASE!", e);
		} finally {
			Encryption.encrypt(enc_key, database_file, encrypted_file);
		}
	}

	public void insertTime(int id, LocalDate date, LocalTime[] shift) {
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(SQLQuery.queryAddTime)) {
			pstmt.setInt(1, id);
			pstmt.setDate(2, java.sql.Date.valueOf(date));
			pstmt.setTime(3, java.sql.Time.valueOf(shift[0]));
			pstmt.setTime(4, java.sql.Time.valueOf(shift[1]));
			pstmt.setTime(5, java.sql.Time.valueOf(shift[2]));
			pstmt.setTime(6, java.sql.Time.valueOf(shift[3]));

			pstmt.setTime(7, java.sql.Time.valueOf(shift[0]));
			pstmt.setTime(8, java.sql.Time.valueOf(shift[1]));
			pstmt.setTime(9, java.sql.Time.valueOf(shift[2]));
			pstmt.setTime(10, java.sql.Time.valueOf(shift[3]));
			pstmt.setInt(11, id);
			pstmt.setDate(12, java.sql.Date.valueOf(date));

			pstmt.executeUpdate();
			logger.debug("Succesfully logged Time: " + id + " - " + date);
		} catch (SQLException e) {
			logger.error("COULD NOT INSERT TIME INTO DATABASE!", e);
		} finally {
			Encryption.encrypt(enc_key, database_file, encrypted_file);
		}
	}
	
	public void deleteTime(int id, LocalDate date) {
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(SQLQuery.queryDeleteTimeEntry)) {
			pstmt.setInt(1, id);
			pstmt.setDate(2, java.sql.Date.valueOf(date));

			pstmt.executeUpdate();
			logger.debug("Succesfully deleted time: " + id + " - " + date);
		} catch (SQLException e) {
			logger.error("COULD NOT DELETE TIME FROM DATABASE!", e);
		} finally {
			Encryption.encrypt(enc_key, database_file, encrypted_file);
		}
	}

}
