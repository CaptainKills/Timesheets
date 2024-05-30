package nl.triantis.timesheets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.triantis.timesheets.Employee;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;


class EmployeeTest {
	
	private Employee employee;

	@BeforeEach
	void setup() {
		int id = 20433;
		String firstName = "Danick";
		String lastName = "Triantis";
		int age = 23;
		double salary = 13.95;
		boolean admin = true;
		TreeMap<LocalDate, LocalTime[]> workedTime = new TreeMap<LocalDate, LocalTime[]>();

		this.employee = new Employee(id, firstName, lastName, age, salary, admin, workedTime);
	}

	@Test
	void testIdStringPadding() {
		this.employee.setID(1);
		String idString = employee.getID_String();

		assertTrue(idString.equals("00001"), () -> "Employee: ID String 00001");
	}

	@Test
	void testWorkedTime() {
		this.employee.setTimeStarted(LocalTime.of(8,0));
		this.employee.setTimeEnded(LocalTime.of(18,0));
		this.employee.setTimePaused(LocalTime.of(0,30));

		assertTrue(this.employee.getTimeStarted() != null, () -> "Employee: Time Started equal to null");
		assertTrue(this.employee.getTimeEnded() != null, () -> "Employee: Time Ended equal to null");
		assertTrue(this.employee.getTimePaused() != null, () -> "Employee: Time Paused equal to null");

		this.employee.resetTime();

		assertTrue(this.employee.getTimeStarted() == null, () -> "Employee: Time Started not equal to null");
		assertTrue(this.employee.getTimeEnded() == null, () -> "Employee: Time Ended not equal to null");
		assertTrue(this.employee.getTimePaused() == null, () -> "Employee: Time Paused not equal to null");
	}
}

