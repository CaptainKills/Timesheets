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
import java.util.Map;
import java.util.TreeMap;

import timesheets.Employee;
import timesheets.logging.Logger;

public class Database {
	private static final Logger logger = new Logger(Database.class.toString());
	private static final String enc_key = "HWEupmmPvjfwlUk6";

	private final Path database_path = Paths.get("data" + File.separator + "Timesheets.db").toAbsolutePath();
	private final Path encrypted_path = Paths.get("data" + File.separator + "Timesheets.encrypted").toAbsolutePath();
	private final Path backup_path = Paths.get("data" + File.separator + "Timesheets_backup.encrypted").toAbsolutePath();

	public static Map<Integer, Employee> EmployeeList = new HashMap<Integer, Employee>();

	// @formatter:off
		private final String employees_table = "CREATE TABLE IF NOT EXISTS employees (\n"
				+ "	id INTEGER PRIMARY KEY UNIQUE,\n"
				+ "	name TEXT NOT NULL,\n"
				+ "	age INTEGER NOT NULL,\n"
				+ " salary INTEGER NOT NULL,\n"
				+ " admin BOOLEAN DEFAULT false\n" + ") WITHOUT ROWID;";

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

	public void setupDatabase() {
		String query_checkAdmin = "SELECT id FROM employees WHERE id=12345;";
		String query_addAdmin = "INSERT INTO employees(id,name,age,salary,admin) VALUES(12345,\"Administrator\",20,0.0,true);";
		
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(employees_table);
			stmt.execute(timedata_table);
			logger.debug("Checked SQLite Database file for missing Tables.");

			ResultSet rs = stmt.executeQuery(query_checkAdmin);
			if (rs.next() == false) { // If there is no initial entry of administrator, add it.
				stmt.executeUpdate(query_addAdmin);
				logger.debug("Tables were new: Created Administrator account.");
			}

			rs.close();
		} catch (SQLException e) {
			logger.error("COULD NOT SET UP SQLite DATABASE: " + e);
		}
	}

	private Connection connect() {
		Connection conn = null;

		try {
			checkDirectory();
			Encryption.decrypt(enc_key, encrypted_path, database_path);
			String url = "jdbc:sqlite:" + database_path;

			logger.info("Establishing connection to SQLite Database...");
			conn = DriverManager.getConnection(url);
			if (conn != null) {
				logger.info("Connection to SQLite Database has been established.");
			} else {
				logger.warn("Connection to SQLite Database failed.");
			}
		} catch (SQLException e) {
			logger.error("SQL CONNECTION ERROR: " + e);
		}
		
		return conn;
	}

	private void checkDirectory() {
		try {
			File f = database_path.toFile();
			if (!f.getParentFile().exists()) {
				logger.info("Parent Directory of Database does not exist.");
				f.getParentFile().mkdir();
				logger.info("Parent Directory of Database created.");
			}
		} catch (SecurityException e) {
			logger.error("COULD NOT CREATE PARENT DIRECTORY: " + e);
		}
	}

	public void loadDatabase() {
		String query = "SELECT * FROM employees;";

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {

			logger.info("Loading Database in program.");
			while (rs.next()) {
				int employeeID = rs.getInt("id");
				logger.info("Loading Employee " + employeeID);

				TreeMap<LocalDate, LocalTime[]> timeMap = loadTimeData(conn, employeeID);
				Employee employee = new Employee(employeeID, rs.getString("name"), rs.getInt("age"),
						rs.getDouble("salary"), rs.getBoolean("admin"), timeMap);

				EmployeeList.put(employeeID, employee);
			}
			logger.info("Loading Database Complete.");
		} catch (SQLException e) {
			logger.error("SQL DATABASE ERROR: " + e);
		} finally {
			Encryption.encrypt(enc_key, database_path, encrypted_path);
		}
	}

	private TreeMap<LocalDate, LocalTime[]> loadTimeData(Connection c, int id) {
		String query = "SELECT * FROM timedata WHERE id = " + id + ";";
		TreeMap<LocalDate, LocalTime[]> timemap = new TreeMap<LocalDate, LocalTime[]>();

		try (Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				LocalTime[] workedHours = { rs.getTime("start").toLocalTime(), rs.getTime("end").toLocalTime(),
						rs.getTime("break").toLocalTime(), rs.getTime("total").toLocalTime() };

				logger.info("Loaded shift: " + workedHours[0] + ", " + workedHours[1] + ", " + workedHours[2] + ", "
						+ workedHours[3] + "|" + rs.getDate("date").toLocalDate());

				timemap.put(rs.getDate("date").toLocalDate(), workedHours);
			}
		} catch (SQLException e) {
			logger.error("COULD NOT EXTRACT TIMEDATA FROM DATABASE: " + e);
		}
		
		return timemap;
	}

	public void backupDatabase() {
		try {
			logger.info("Creating Backup of SQLite Database.");
			File backup = backup_path.toFile();
			if (backup.exists()) {
				logger.info("Current Backup File will be deleted.");
				backup.delete();
			}

			Files.copy(encrypted_path, backup_path, StandardCopyOption.COPY_ATTRIBUTES);
			logger.info("Backup of SQLite Database Created.");
		} catch (IOException e) {
			logger.error("COULD NOT CREATE BACKUP: " + e);
		}
	}

	public void insertEmployee(Employee emp) {
		String query = "INSERT INTO employees(id,name,age,salary,admin) VALUES(?,?,?,?,?);";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, emp.getID());
			pstmt.setString(2, emp.getName());
			pstmt.setInt(3, emp.getAge());
			pstmt.setDouble(4, emp.getSalary());
			pstmt.setBoolean(5, emp.getAdmin());

			pstmt.executeUpdate();
			logger.info("Added Employee to Database: " + emp.getID());
		} catch (SQLException e) {
			logger.error("COULD NOT INSERT EMPLOYEE INTO DATABASE: " + e);
		} finally {
			Encryption.encrypt(enc_key, database_path, encrypted_path);
		}
	}

	public void deleteEmployee(int id) {
		String query_emp = "DELETE FROM employees\nWHERE id = ?;";
		String query_time = "DELETE FROM timedata\nWHERE id = ?;";

		try (Connection conn = this.connect();
				PreparedStatement pstmt_emp = conn.prepareStatement(query_emp);
				PreparedStatement pstmt_time = conn.prepareStatement(query_time)) {
			pstmt_emp.setInt(1, id);
			pstmt_emp.executeUpdate();

			pstmt_time.setInt(1, id);
			pstmt_time.executeUpdate();

			logger.info("Deleted Employee from Database: " + id);
		} catch (SQLException e) {
			logger.error("COULD NOT DELETE EMPLOYEE FROM DATABASE: " + e);
		} finally {
			Encryption.encrypt(enc_key, database_path, encrypted_path);
		}
	}

	public void updateEmployee(int oldID, Employee new_emp) {
		String query = "UPDATE employees SET id = ?, name = ?, age = ?, salary = ?, admin = ? WHERE id = ?;";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, new_emp.getID());
			pstmt.setString(2, new_emp.getName());
			pstmt.setInt(3, new_emp.getAge());
			pstmt.setDouble(4, new_emp.getSalary());
			pstmt.setBoolean(5, new_emp.getAdmin());
			pstmt.setInt(6, oldID);

			pstmt.executeUpdate();
			logger.info("Updated Employee in Database: " + oldID + " -> " + new_emp.getID());
		} catch (SQLException e) {
			logger.error("COULD NOT UPDATE EMPLOYEE IN DATABASE: " + e);
		} finally {
			Encryption.encrypt(enc_key, database_path, encrypted_path);
		}
	}

	public void insertTime(int id, LocalDate date, LocalTime[] shift) {
		String query = "INSERT INTO timedata(id,date,start,end,break,total) VALUES(?,?,?,?,?,?)\n"
				+ "ON CONFLICT(id,date) DO UPDATE SET start = ?, end = ?, break = ?, total = ?\n"
				+ "WHERE id = ? AND date = ?;";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
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
			logger.info("Succesfully logged Time: " + id + " - " + date);
		} catch (SQLException e) {
			logger.error("COULD NOT INSERT TIME INTO DATABASE: " + e);
		} finally {
			Encryption.encrypt(enc_key, database_path, encrypted_path);
		}
	}

}
