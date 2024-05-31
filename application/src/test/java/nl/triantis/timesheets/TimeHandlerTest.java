package nl.triantis.timesheets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TimeHandlerTest {

	private static TimeHandler time;

	@BeforeAll
	static void setup() {
		time = new TimeHandler();
	}

	@Test
	void testCalcuteDifference() {
		LocalTime start = LocalTime.of(8, 0);
		LocalTime end = LocalTime.of(16, 0);
		LocalTime paused = LocalTime.of(0, 30);

		LocalTime actualDifference = time.calculateDifference(start, end);
		LocalTime expectedDifference = LocalTime.of(8, 0);

		assertEquals(expectedDifference, actualDifference);

		actualDifference = time.calculateDifference(start, end, paused);
		expectedDifference = LocalTime.of(7, 30);

		assertEquals(expectedDifference, actualDifference);
	}

	@Test
	void testAddUpTime() {
		LocalTime initial = LocalTime.of(8, 0);
		LocalTime timeToAdd = LocalTime.of(8, 0);

		LocalTime actualNewTime = time.addUp(initial, timeToAdd);
		LocalTime expectedNewTime = LocalTime.of(16, 0);

		assertEquals(expectedNewTime, actualNewTime);
	}
}
