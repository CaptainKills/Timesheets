package timesheets.runnable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import timesheets.Employee;
import timesheets.TimeHandler;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class Editor implements Runnable {
	
	private static final Logger logger = new Logger(Editor.class);
	
	private static Scanner input = new Scanner(System.in);
	private TimeHandler timeHandler = new TimeHandler();
	private Database database = new Database();
	
	private static Map<Integer, Employee> employeeList = Database.EmployeeList;

	@Override
	public void run() {
		logger.info("Initialising Timesheets Editor.");
		System.out.println("Timesheets Editor!");
		
		boolean exit = false;
		String cmd;
		
		while(!exit) {
			System.out.print("Enter Command: ");
			cmd = input.next().trim().toLowerCase();
			
			if(cmd.equals("q") || cmd.equals("exit") || cmd.equals("quit")) {
				exit = true;
			}
			
			else if(cmd.equals("add") || cmd.equals("a") || cmd.equals("+")) {
				addTime();
			}
			
			else if(cmd.equals("remove") || cmd.equals("r") || cmd.equals("-")) {
				removeTime();
			}
			
			else if(cmd.equals("edit") || cmd.equals("e") || cmd.equals("~")) {
				editTime();
			}
			
			else if(cmd.equals("random") || cmd.equals("rand") || cmd.equals("r")) {
				randomise();
			}
			
			else if(cmd.equals("list") || cmd.equals("ls")) {
				listEntries(true);
			}
			
			else if(cmd.equals("employees") || cmd.equals("emp")) {
				listEntries(false);
			}
			
			
			else {
				System.out.println("Invalid Command!");
			}
		}
		
		input.close();
		System.out.println("Exit Timesheets Editor...");
		System.exit(0);
	}
	
	private void addTime() {
		/*
		 * Select Employee
		 * Select Date
		 * 		- Check if date already exists
		 * 
		 * Insert Start Time
		 * Insert End Time
		 * 		- Check if end time is after start time
		 * Insert Break Time
		 * 
		 * Calculate Total Time
		 * Push to DB
		 */
		System.out.print("Employee ID: ");
		int id = input.nextInt();
		
		while(employeeList.get(id) == null) {
			System.out.println("No employee with this ID. Please retry.");
			System.out.print("Employee ID: ");
			id = input.nextInt();
		}
		
		Employee employee = employeeList.get(id);
		System.out.println("Selected Employee: " + employee.getName());
		
		
		LocalDate date;
		while(true) {
			System.out.print("Date (YYYY-MM-DD): ");
			String inputDate = input.next();
			
			try {
				date = LocalDate.parse(inputDate);
				
				if(!employee.getWorkedTime().containsKey(date)) {
					break;
				}
				
				System.out.println("Date already exists.");
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please retry.");
			}
		}
		
		LocalTime[] shift = new LocalTime[4];
		String[] timeNames = {"Start", "End", "Break"};
		
		for(int i = 0; i < timeNames.length; i++) {
			while(true) {
				System.out.print(timeNames[i] + " Time (HH:MM): ");
				String inputTime = input.next();
				
				try {
					LocalTime time = LocalTime.parse(inputTime);
					shift[i] = time;
					break;
				} catch (DateTimeParseException e) {
					System.out.println("Invalid time format. Please retry.");
				}
			}
		}
		
		LocalTime totalTime = timeHandler.calculateDifference(shift[0], shift[1], shift[2]);
		shift[3] = totalTime;
		System.out.println(date + ": " + shift[0] + ", " + shift[1] + ", " + shift[2] + ", " + shift[3]);
		
		employee.getWorkedTime().put(date, shift);
		database.insertTime(id, date, shift);
		System.out.println("Shift successfully added.");
	}
	
	private void removeTime() {
		/*
		 * Select Employee
		 * Select Date
		 * 		- Check if date exists
		 * Remove from DB
		 */
		System.out.print("Employee ID: ");
		int id = input.nextInt();
		
		while(employeeList.get(id) == null) {
			System.out.println("No employee with this ID. Please retry.");
			System.out.print("Employee ID: ");
			id = input.nextInt();
		}
		
		Employee employee = employeeList.get(id);
		System.out.println("Selected Employee: " + employee.getName());
		
		
		LocalDate date;
		while(true) {
			System.out.print("Date (YYYY-MM-DD): ");
			String inputDate = input.next();
			
			try {
				date = LocalDate.parse(inputDate);
				
				if(employee.getWorkedTime().containsKey(date)) {
					break;
				}
				
				System.out.println("Date does not exist.");
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please retry.");
			}
		}
		
		employee.getWorkedTime().remove(date);
		database.deleteTime(id, date);
		System.out.println("Shift successfully deleted.");
	}
	
	private void editTime() {
		/*
		 * Select Employee
		 * Select Date
		 * 		- Check if date already exists
		 * 
		 * (press ENTER if not changing)
		 * Edit Start Time
		 * Edit End Time
		 * Edit Break Time
		 * 
		 * Calculate Total Time
		 * Push to DB
		 */
		
		System.out.print("Employee ID: ");
		int id = input.nextInt();
		
		while(employeeList.get(id) == null) {
			System.out.println("No employee with this ID. Please retry.");
			System.out.print("Employee ID: ");
			id = input.nextInt();
		}
		
		Employee employee = employeeList.get(id);
		System.out.println("Selected Employee: " + employee.getName());
		
		
		LocalDate date;
		while(true) {
			System.out.print("Date (YYYY-MM-DD): ");
			String inputDate = input.next();
			
			try {
				date = LocalDate.parse(inputDate);
				
				if(employee.getWorkedTime().containsKey(date)) {
					break;
				}
				
				System.out.println("Date does not exist.");
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please retry.");
			}
		}
		
		String[] timeNames = {"Start", "End", "Break"};
		LocalTime[] shift = employee.getWorkedTime().get(date);
		System.out.println("Current shift (" + date + "): Start=" + shift[0] + ", End=" + shift[1] + ", Break=" + shift[2] + ", Total=" + shift[3]);
		
		int i = 0;
		while(true) {
			try {
				System.out.print("Change " + timeNames[i] + "time into: ");
				LocalTime time = LocalTime.parse(input.next());
				shift[i] = time;
				i++;
			} catch(DateTimeParseException e) {
				System.out.println("Invalid Time Format!");
			}
			
			if(i == timeNames.length) {
				break;
			}
		}
		
		LocalTime totalTime = timeHandler.calculateDifference(shift[0], shift[1], shift[2]);
		shift[3] = totalTime;
		System.out.println("New Shift (" + date + "): " + shift[0] + ", " + shift[1] + ", " + shift[2] + ", " + shift[3]);
		
		employee.getWorkedTime().put(date, shift);
		database.insertTime(id, date, shift);
		System.out.println("Shift successfully added.");
		
	}
	
	private void randomise() {
		System.out.print("Select date range (today/week/month): ");
		String range = input.next();
		
		LocalDate startDate;
		LocalDate endDate;
		
		while(true) {
			if(range.equals("today") || range.equals("t")) {
				startDate = timeHandler.getCurrentDate();
				endDate = timeHandler.getCurrentDate();
				break;
			} else if(range.equals("week") || range.equals("w")) {
				startDate = timeHandler.getWeekStart();
				endDate = timeHandler.getWeekEnd();
				break;
			} else if(range.equals("month") || range.equals("m")) {
				startDate = timeHandler.getCurrentDate().withDayOfMonth(1);
				endDate = timeHandler.getCurrentDate().withDayOfMonth(31);
				break;
			} else {
				System.out.println("Invalid range!");
				System.out.print("Select date range (today/week/month): ");
				range = input.next();
			}
		}
		
		System.out.print("Percentage of randomisation (%): ");
		int percentage = Integer.parseInt(input.next());
		while(percentage <= 0 || percentage > 100) {
			System.out.println("Percentage not between 0% and 100%!");
			System.out.print("Percentage of randomisation: ");
			percentage = Integer.parseInt(input.next());
		}
		
		
		System.out.println("Start Date: " + startDate.toString());
		System.out.println("End Date: " + endDate.toString());
		
		Random rand = new Random();
		
		for(Employee emp : employeeList.values()) {
			for(LocalDate i = startDate; i.isBefore(endDate.plusDays(1)); i = i.plusDays(1)) {
				if(!emp.getWorkedTime().containsKey(i) && getCertainPercentageUsed(percentage)) {
					LocalTime startTime = LocalTime.of(rand.nextInt(5)+10, 0);
					LocalTime endTime = LocalTime.of(rand.nextInt(5)+17, 0);
					LocalTime breakTime = LocalTime.of(0, rand.nextInt(3)*15);
					LocalTime totalTime = timeHandler.calculateDifference(startTime, endTime, breakTime);
					LocalTime[] shift = {startTime, endTime, breakTime, totalTime};
					
					emp.getWorkedTime().put(i, shift);
					database.insertTime(emp.getID(), i, shift);
				}
			}
		}
		listEntries(true);
		
	}
	
	private void listEntries(boolean showTime) {
		for(Employee emp : employeeList.values()) {
			System.out.println(emp.getName() + " (" + emp.getID_String() + "): ");
			
			if(!showTime) {
				continue;
			}
			
			for(Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
				System.out.print(entry.getKey() +": ");
				System.out.print(entry.getValue()[0]);
				System.out.print(", ");
				System.out.print(entry.getValue()[1]);
				System.out.print(", ");
				System.out.print(entry.getValue()[2]);
				System.out.print(", ");
				System.out.println(entry.getValue()[3]);
			}
		}
	}
	
	private Boolean getCertainPercentageUsed(int percentage) {
		Random rand = new Random();
		
		int randomInt = rand.nextInt(101);
		
		if(randomInt <= percentage) {
			return true;
		}
		
		return false;
	}

}
