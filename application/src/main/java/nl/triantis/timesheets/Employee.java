package nl.triantis.timesheets;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private double salary;
	private boolean admin;
	private TreeMap<LocalDate, LocalTime[]> workedTime = new TreeMap<LocalDate, LocalTime[]>();

	private LocalTime TimeStarted = null;
	private LocalTime TimeEnded = null;
	private LocalTime TimePaused = null;
	private LocalTime BreakStarted = null;
	private LocalTime BreakEnded = null;

	public Employee(int id, String firstName, String lastName, int age, double salary, boolean admin,
			TreeMap<LocalDate, LocalTime[]> timeMap) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.salary = salary;
		this.admin = admin;
		this.workedTime = timeMap;
	}

	@Override
	public String toString() {
		return getID_String() + "/" + getName() + "/" + age + "/" + salary + "/" + admin;
	}

	public int getID() {
		return this.id;
	}

	public String getID_String() {
		return String.format("%05d", id);
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Map<LocalDate, LocalTime[]> getWorkedTime() {
		return this.workedTime;
	}

	public void setWorkedTime(LocalDate date, LocalTime[] time) {
		this.workedTime.put(date, time);
	}

	public LocalTime getTimeStarted() {
		return this.TimeStarted;
	}

	public void setTimeStarted(LocalTime time) {
		this.TimeStarted = time;
	}

	public LocalTime getTimeEnded() {
		return this.TimeEnded;
	}

	public void setTimeEnded(LocalTime time) {
		this.TimeEnded = time;
	}

	public LocalTime getTimePaused() {
		return this.TimePaused;
	}

	public void setTimePaused(LocalTime time) {
		this.TimePaused = time;
	}

	public LocalTime getBreakStarted() {
		return this.BreakStarted;
	}

	public void setBreakStarted(LocalTime time) {
		this.BreakStarted = time;
	}

	public LocalTime getBreakEnded() {
		return this.BreakEnded;
	}

	public void setBreakEnded(LocalTime time) {
		this.BreakEnded = time;
	}

	public void resetTime() {
		setTimeStarted(null);
		setTimeEnded(null);
		setTimePaused(null);
	}
}
