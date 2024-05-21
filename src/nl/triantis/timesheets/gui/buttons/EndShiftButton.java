package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;

import nl.triantis.timesheets.Employee;
import nl.triantis.timesheets.TimeHandler;
import nl.triantis.timesheets.gui.ExtendedHandler;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.DisplayList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.lists.TextFieldList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;
import nl.triantis.timesheets.sql.Database;

public class EndShiftButton extends JButton {
	private static final long serialVersionUID = 1690125966086841320L;
	private static final Logger logger = new Logger(EndShiftButton.class);

	private Database database = new Database();
	private TimeHandler time = new TimeHandler();
	private LocalTime[] newShift;
	
	private static String buttonText = LanguageManager.language.get("end_shift_button");

	public EndShiftButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_large);
		setFont(FontList.buttonFont);
		setEnabled(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int id = Integer.parseInt(TextFieldList.inputField.getText());
				Employee activeEmployee = Database.EmployeeList.get(id);

				LocalDate currentDate = time.getCurrentDate();
				LocalTime currentTime = time.getCurrentTime();
				activeEmployee.setTimeEnded(currentTime);

				LocalTime newWorkedTime = time.calculateDifference(activeEmployee.getTimeStarted(),
						activeEmployee.getTimeEnded(), activeEmployee.getTimePaused());

				if (activeEmployee.getTimePaused() != null) {
					DisplayList.loginTextArea.updateInfoText("Total time worked this shift: " + newWorkedTime + ", and " + activeEmployee.getTimePaused() + " of breaktime.");
				} else if (activeEmployee.getTimePaused() == null) {
					DisplayList.loginTextArea.updateInfoText("Total time worked this shift: " + newWorkedTime + ".");
				}
				
				logger.info("Employee " + activeEmployee.getID_String() + " ended their shift at: " + currentTime);
				
				

				if (activeEmployee.getWorkedTime().containsKey(currentDate)) {
					logger.info("Employee already worked on this date.");
					LocalTime[] previousShift = activeEmployee.getWorkedTime().get(currentDate);
					LocalTime prevStartTime = previousShift[0];
					LocalTime prevEndTime = previousShift[1];
					LocalTime prevBreakTime = previousShift[2];
					LocalTime prevTotalTime = previousShift[3];
					
					LocalTime newTotalTime = time.addUp(prevTotalTime, newWorkedTime);
					logger.info("Prev Total Time: " + prevTotalTime + ", Next Total Time: " + newWorkedTime + ", New Total Time: " + newTotalTime);

					LocalTime gapBetweenShifts = time.calculateDifference(prevEndTime, activeEmployee.getTimeStarted());
					logger.info("Prev End Time: " + prevEndTime + ", Current Start Time: " + activeEmployee.getTimeStarted() + ", Gap Inbetween: " + gapBetweenShifts);
					
					LocalTime totalBreakTime = time.addUp(prevBreakTime, activeEmployee.getTimePaused());
					logger.info("Prev Break Time: " + prevBreakTime + ", New Break Time: " + activeEmployee.getTimePaused() + ", Total Break Time: " + totalBreakTime);
					
					LocalTime totalPausedTime = time.addUp(totalBreakTime, gapBetweenShifts);
					logger.info("Total Break Time: " + totalBreakTime + ", Additional Gap Time: " + gapBetweenShifts + ", Total Paused Time: " + totalPausedTime);

					newShift = formatShift(prevStartTime, activeEmployee.getTimeEnded(), totalPausedTime, newTotalTime);
				} else {
					logger.info("Employee has not worked on this date.");
					newShift = formatShift(activeEmployee.getTimeStarted(), activeEmployee.getTimeEnded(), activeEmployee.getTimePaused(), newWorkedTime);
					
				}
				
				logger.info("New Shift: ST=" + newShift[0] + ", ET=" + newShift[1] + ", BT=" + newShift[2] + ", TT=" + newShift[3]);
				activeEmployee.setWorkedTime(currentDate, newShift);
				activeEmployee.resetTime();
				database.insertTime(id, currentDate, newShift);

				ExtendedHandler.enableShiftButtons(true, false, false, false);
			}
		});

		logger.debug("EndShiftButton initialised.");
	}

	private LocalTime[] formatShift(LocalTime start, LocalTime end, LocalTime paused, LocalTime difference) {
		LocalTime[] newShift = new LocalTime[4];
		newShift[0] = start;
		newShift[1] = end;

		if (paused == null) {
			newShift[2] = LocalTime.of(0, 0);
		} else {
			newShift[2] = paused;
		}
		newShift[3] = difference;
		
		return newShift;
	}

}
