package timesheets;

import java.time.*;

public class TimeHandler {

	public LocalTime getCurrentTime() {
		return LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
	}

	public LocalDate getCurrentDate() {
		return LocalDate.now();
	}

	public LocalTime calculateDifference(LocalTime start, LocalTime end) {
		LocalTime newTime = end.minusHours(start.getHour()).minusMinutes(start.getMinute());
		return newTime;
	}

	public LocalTime calculateDifference(LocalTime initialTime, LocalTime finalTime, LocalTime pausedTime) {
		LocalTime newTime;
		
		if (pausedTime != null) {
			newTime = finalTime.minusHours(initialTime.getHour()).minusMinutes(initialTime.getMinute());
			newTime = newTime.minusHours(pausedTime.getHour()).minusMinutes(pausedTime.getMinute());
		} else {
			newTime = finalTime.minusHours(initialTime.getHour()).minusMinutes(initialTime.getMinute());
		}
		
		return newTime;
	}

	public LocalTime addUp(LocalTime initial, LocalTime timeToAdd) {
		LocalTime newTime;
		
		if(timeToAdd != null) {
			newTime = initial.plusHours(timeToAdd.getHour()).plusMinutes(timeToAdd.getMinute());
		} else {
			newTime = initial;
		}
		
		return newTime;
	}

	public LocalTime roundOffTime(LocalTime time) {
		LocalTime newTime = null;
		
		if (time.getMinute() >= 0 && time.getMinute() < 8) {
			newTime = LocalTime.of(time.getHour(), 0);
		} else if (time.getMinute() >= 8 && time.getMinute() < 23) {
			newTime = LocalTime.of(time.getHour(), 15);
		} else if (time.getMinute() >= 23 && time.getMinute() < 38) {
			newTime = LocalTime.of(time.getHour(), 30);
		} else if (time.getMinute() >= 38 && time.getMinute() < 53) {
			newTime = LocalTime.of(time.getHour(), 45);
		} else if (time.getMinute() >= 53 && time.getMinute() < 60) {
			newTime = LocalTime.of(time.getHour() + 1, 0);
		}
		
		return newTime;
	}

	public LocalDate getWeekStart() {
		LocalDate weekStart = getCurrentDate();
		
		while (weekStart.getDayOfWeek() != DayOfWeek.MONDAY) {
			weekStart = weekStart.minusDays(1);
		}
		
		return weekStart;
	}

	public LocalDate getWeekEnd() {
		LocalDate weekEnd = getCurrentDate();
		
		while (weekEnd.getDayOfWeek() != DayOfWeek.SUNDAY) {
			weekEnd = weekEnd.plusDays(1);
		}
		
		return weekEnd;
	}
}
