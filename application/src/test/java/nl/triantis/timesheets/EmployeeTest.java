package nl.triantis.timesheets;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
