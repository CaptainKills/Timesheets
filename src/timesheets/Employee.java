package timesheets;

import java.util.*;
import java.time.*;

public class Employee {	
	private int ID;
	private String Name;
	private int Age;
	private double Salary;
	private boolean Admin;
	private TreeMap<LocalDate, LocalTime[]> WorkedTime = new TreeMap<LocalDate, LocalTime[]>();
	
	private LocalTime TimeStarted = null;
	private LocalTime TimeEnded = null;
	private LocalTime TimePaused = null;
	private LocalTime BreakStarted = null;
	private LocalTime BreakEnded = null;
	
	public Employee(int id, String name, int age, double salary, boolean admin, TreeMap<LocalDate, LocalTime[]> timeMap) {
		this.ID = id;
		this.Name = name;
		this.Age = age;
		this.Salary = salary;
		this.Admin = admin;
		this.WorkedTime = timeMap;
	}
	
	@Override
	public String toString() {
		return getID_String() + "/" + Name + "/" + Age + "/" + Salary + "/" + Admin;
	}

	public int getID() {
		return ID;
	}
	
	public String getID_String() {
		return String.format("%05d", ID);
	}

	public void setID(int id) {
		this.ID = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		this.Age = age;
	}
	
	public double getSalary() {
		return Salary;
	}
	
	public void setSalary(double salary) {
		this.Salary = salary;
	}
	
	public boolean getAdmin() {
		return Admin;
	}
	
	public void setAdmin(boolean admin) {
		this.Admin = admin;
	}
	
	public Map<LocalDate, LocalTime[]> getWorkedTime(){
		return WorkedTime;
	}

	public void setWorkedTime(LocalDate date, LocalTime[] time) {
		WorkedTime.put(date, time);
	}
	
	public LocalTime getTimeStarted() {
		return TimeStarted;
	}

	public void setTimeStarted(LocalTime time) {
		this.TimeStarted = time;
	}

	public LocalTime getTimeEnded() {
		return TimeEnded;
	}

	public void setTimeEnded(LocalTime time) {
		this.TimeEnded = time;
	}

	public LocalTime getTimePaused() {
		return TimePaused;
	}

	public void setTimePaused(LocalTime time) {
		this.TimePaused = time;
	}

	public LocalTime getBreakStarted() {
		return BreakStarted;
	}

	public void setBreakStarted(LocalTime time) {
		this.BreakStarted = time;
	}

	public LocalTime getBreakEnded() {
		return BreakEnded;
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
