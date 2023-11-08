package timesheets.sql;

public class SQLQuery {
	
	// @formatter:off
	public static final String querySelectAllEmployees = "SELECT * FROM employees;";
	public static final String querySelectTime = "SELECT * FROM timedata WHERE id = ?;";
	
	public static final String queryCountEmployees = "SELECT COUNT(*) FROM employees;";
	public static final String queryCountTime = "SELECT COUNT(*) FROM timedata;";
	
	public static final String queryCreateEmployeeTable = "CREATE TABLE IF NOT EXISTS employees (\n"
			+ "	id INTEGER PRIMARY KEY UNIQUE,\n"
			+ "	firstName TEXT NOT NULL,\n"
			+ " lastName TEXT NOT NULL,\n"
			+ "	age INTEGER NOT NULL,\n"
			+ " salary INTEGER NOT NULL,\n"
			+ " admin BOOLEAN NOT NULL\n"
			+ " ) WITHOUT ROWID;";

	public static final String queryCreateTimeTable = "CREATE TABLE IF NOT EXISTS timedata (\n"
			+ " id INTEGER NOT NULL,\n"
			+ " date DATE NOT NULL,\n"
			+ " start TIME NOT NULL,\n"
			+ " end TIME NOT NULL,\n"
			+ " break TIME NOT NULL,\n"
			+ " total TIME NOT NULL,\n"
			+ " PRIMARY KEY (id, date),\n"
			+ " FOREIGN KEY (id) REFERENCES employees(id)\n"
			+ ") WITHOUT ROWID;";
	
	public static final String queryAddEmployee = "INSERT INTO employees(id, firstName, lastName, age, salary, admin)"
			+ " VALUES(?, ?, ?, ?, ?, ?);";
	
	public static final String queryUpdateEmployee = "UPDATE employees SET id = ?, firstName = ?, lastName = ?,"
			+ " age = ?, salary = ?, admin = ? WHERE id = ?;";
	
	public static final String queryDeleteEmployee = "DELETE FROM employees WHERE id = ?;";
	
	
	public static final String queryAddTime = "INSERT INTO timedata(id, date, start, end, break, total)"
			+ " VALUES(?, ?, ?, ?, ?, ?)\n"
			+ " ON CONFLICT(id, date) DO UPDATE SET start = ?, end = ?, break = ?, total = ?\n"
			+ " WHERE id = ? AND date = ?;";
	
	public static final String queryDeleteTimeMap = "DELETE FROM timedata WHERE id = ?;";
	
	public static final String queryDeleteTimeEntry = "DELETE FROM timedata	WHERE id = ? AND date = ?;";
	// @formatter:on
}
