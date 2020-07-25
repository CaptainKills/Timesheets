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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import timesheets.Employee;
import timesheets.logging.Logger;

public class Database {
	private static final Logger logger = new Logger(Database.class.toString());

	private final Path database_path = Paths.get("data" + File.separator + "Test.db").toAbsolutePath();
	private final Path backup_path = Paths.get("data" + File.separator + "Test_backup.db").toAbsolutePath();

	public static Map<Integer, Employee> EmployeeList = new HashMap<Integer, Employee>();
	public static Map<String, String> settings = new LinkedHashMap<String, String>();

	// @formatter:off
		private final String employees_table = "CREATE TABLE IF NOT EXISTS employees (\n"
				+ "	id INTEGER PRIMARY KEY,\n"
				+ "	name TEXT NOT NULL,\n"
				+ "	age INTEGER NOT NULL,\n"
				+ " salary INTEGER NOT NULL,\n"
				+ " admin BOOLEAN NOT NULL DEFAULT false\n" + ") WITHOUT ROWID;";

		private final String timedata_table = "CREATE TABLE IF NOT EXISTS timedata (\n"
				+ " id INTEGER NOT NULL,\n"
				+ " date DATE NOT NULL,\n"
				+ " start TIME NOT NULL,\n"
				+ " end TIME NOT NULL,\n"
				+ " break TIME NOT NULL,\n"
				+ " total TIME NOT NULL,\n"
				+ " PRIMARY KEY (id,date),\n"
				+ " FOREIGN KEY (id) REFERENCES employees(id)" + ") WITHOUT ROWID;";
		// @formatter:on

	private Connection connect() {
		Connection conn = null;

		try {
			checkDirectory(database_path);

			String url = "jdbc:sqlite:" + database_path;

			logger.info("Establishing connection to SQLite Database...");
			conn = DriverManager.getConnection(url);
			if (conn != null) {
				logger.info("Connection to SQLite Database has been established.");
				checkDatabase();
			} else {
				logger.warn("Connection to SQLite Database failed.");
			}
		} catch (SQLException e) {
			logger.error("SQL CONNECTION ERROR: " + e);
		}

		return conn;
	}

	private void checkDirectory(Path p) {
		try {
			File f = p.toFile();
			if (!f.getParentFile().exists()) {
				logger.info("Parent Directory of Database does not exist.");
				f.getParentFile().mkdir();
				logger.info("Parent Directory of Database created.");
			}
		} catch (SecurityException e) {
			logger.error("COULD NOT CREATE PARENT DIRECTORY: " + e);
		}
	}

	private void checkDatabase() {
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement();) {
			stmt.execute(employees_table);
			stmt.execute(timedata_table);
			logger.debug("Checked SQLite Database file for missing Tables.");

			checkAdministrator();
		} catch (SQLException e) {
			logger.error("SQL DATABASE ERROR: " + e);
		}
	}

	private void checkAdministrator() {
		String query_checkAdmin = "SELECT id FROM employees WHERE id=12345;";
		String query_addAdmin = "INSERT INTO employees(id,name,age,salary,admin) VALUES(12345,\"Administrator\",20,0.0,true);";

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query_checkAdmin)) {

			if (rs.next() == false) { // If there is no initial entry of administrator, add it.
				stmt.executeUpdate(query_addAdmin);
				logger.debug("Tables were new: Created Administrator account.");
			}
		} catch (SQLException e) {
			logger.error("SQL ERROR: " + e);
		}
	}

	public void loadDatabase() {
		String query_emp = "SELECT id, name, age, salary, admin FROM employees;";
		String query_time = "SELECT date,start,end,break,total FROM timedata WHERE id = ?;";

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs_emp = stmt.executeQuery(query_emp);) {

			logger.info("Loading Database in program.");
			while (rs_emp.next()) {
				int employeeID = rs_emp.getInt("id");
				logger.info("Loading Employee: " + employeeID);

				ResultSet rs_time;
				try (PreparedStatement pstmt = conn.prepareStatement(query_time)) {
					pstmt.setInt(1, employeeID);
					rs_time = pstmt.executeQuery(query_time);
				}

				TreeMap<LocalDate, LocalTime[]> shiftsMap = new TreeMap<LocalDate, LocalTime[]>();
				while (rs_time.next()) {
					LocalTime[] workedHours = { rs_time.getTime("start").toLocalTime(),
							rs_time.getTime("end").toLocalTime(), rs_time.getTime("break").toLocalTime(),
							rs_time.getTime("total").toLocalTime() };

					shiftsMap.put(rs_time.getDate("date").toLocalDate(), workedHours);
				}

				Employee employee = new Employee(employeeID, rs_emp.getString("name"), rs_emp.getInt("age"),
						rs_emp.getDouble("salary"), rs_emp.getBoolean("admin"), shiftsMap);
				EmployeeList.put(employeeID, employee);
				logger.info("Employee Loaded: " + employee.getID_String());
			}
			logger.info("Loading Database Complete.");
		} catch (SQLException e) {
			logger.error("SQL DATABASE ERROR: " + e);
		}
	}

	public void backupDatabase() {
		try {
			logger.info("Creating Backup of SQLite Database.");
			Files.copy(database_path, backup_path, StandardCopyOption.COPY_ATTRIBUTES);
			logger.info("Backup of SQLite Database Created.");
		} catch (IOException e) {
			logger.error("COULD NOT CREATE BACKUP: " + e);
		}
	}

	public void insertEmployee(int id, String name, int age, double salary, boolean admin) {
		String sql = "INSERT INTO employees(id,name,age,salary,admin) VALUES(?,?,?,?,?);";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setDouble(4, salary);
			pstmt.setBoolean(5, admin);

			pstmt.executeUpdate();
			logger.info("Added Employee to Database: " + id);
		} catch (SQLException e) {
			logger.error("COULD NOT INSERT EMPLOYEE INTO DATABASE: " + e);
		}
	}

	public void deleteEmployee(int id) {
		String sql = "DELETE FROM employees WHERE id = ?;";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);

			pstmt.executeUpdate();
			logger.info("Deleted Employee from Database: " + id);
		} catch (SQLException e) {
			logger.error("COULD NOT DELETE EMPLOYEE FROM DATABASE: " + e);
		}
	}

	public void updateEmployee(int oldID, int newID, String name, int age, double salary, boolean admin) {
		String sql = "UPDATE employees SET id = ?, name = ?, age = ?, salary = ?, admin = ? WHERE id = ?;";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, newID);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setDouble(4, salary);
			pstmt.setBoolean(5, admin);
			pstmt.setInt(6, oldID);

			pstmt.executeUpdate();
			logger.info("Updated Employee in Database: " + oldID + " -> " + newID);
		} catch (SQLException e) {
			logger.error("COULD NOT UPDATE EMPLOYEE IN DATABASE: " + e);
		}
	}

	public void insertTime(int id, LocalDate date, LocalTime start, LocalTime end, LocalTime br, LocalTime total) {
		String sql = "INSERT INTO timedata(id,date,start,end,break,total) VALUES(?,?,?,?,?,?);";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.setDate(2, java.sql.Date.valueOf(date));
			pstmt.setTime(3, java.sql.Time.valueOf(start));
			pstmt.setTime(4, java.sql.Time.valueOf(end));
			pstmt.setTime(5, java.sql.Time.valueOf(br));
			pstmt.setTime(6, java.sql.Time.valueOf(total));

			pstmt.executeUpdate();
			logger.info("Succesfully logged Time: " + id + " - " + date);
		} catch (SQLException e) {
			logger.error("COULD NOT INSERT TIME INTO DATABASE: " + e);
		}
	}

	public void updateTime(int id, LocalDate date, LocalTime start, LocalTime end, LocalTime br, LocalTime total) {
		String sql = "UPDATE timedata SET start,end,break,total VALUES(?,?,?,?) WHERE id = ? AND date = ?;";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setTime(1, java.sql.Time.valueOf(start));
			pstmt.setTime(2, java.sql.Time.valueOf(end));
			pstmt.setTime(3, java.sql.Time.valueOf(br));
			pstmt.setTime(4, java.sql.Time.valueOf(total));
			pstmt.setInt(5, id);
			pstmt.setDate(6, java.sql.Date.valueOf(date));

			pstmt.executeUpdate();
			logger.info("Succesfully logged Time: " + id + " - " + date);
		} catch (SQLException e) {
			logger.error("COULD NOT INSERT TIME INTO DATABASE: " + e);
		}
	}

}
