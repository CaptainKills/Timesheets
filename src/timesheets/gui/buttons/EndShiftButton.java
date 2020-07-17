package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;

import timesheets.DataHandler;
import timesheets.Employee;
import timesheets.TimeHandler;
import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextAreaList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;

public class EndShiftButton extends JButton{
	private static final long serialVersionUID = 1690125966086841320L;
	private static final Logger logger = new Logger(EndShiftButton.class.toString());
	
	private DataHandler data = new DataHandler();
	private TimeHandler time = new TimeHandler();
	
	private LocalTime[] previousShift, newShift;
	private LocalTime currentTime, differenceTime, additionalBreakTime, newTime;
	private LocalDate currentDate;

	public EndShiftButton() {
		super("End Shift");
		setPreferredSize(DimensionList.buttonSize_large);
		setFont(FontList.buttonFont);
		setEnabled(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int id = Integer.parseInt(TextFieldList.inputField.getText());
				Employee activeEmployee = DataHandler.EmployeeList.get(id);
				
				currentDate = time.getCurrentDate();
				currentTime = time.roundOffTime(time.getCurrentTime());
				activeEmployee.setTimeEnded(currentTime);
				
				differenceTime = time.calculateDifference(activeEmployee.getTimeStarted(), activeEmployee.getTimeEnded(), activeEmployee.getTimePaused());
				
				if(activeEmployee.getTimePaused() != null) {
					TextAreaList.loginTextArea.updateInfoText("Total time worked this shift: " + differenceTime + ", and " + activeEmployee.getTimePaused() + " of breaktime.");
				} else if(activeEmployee.getTimePaused() == null){
					TextAreaList.loginTextArea.updateInfoText("Total time worked this shift: " + differenceTime + ", without a break.");
				}
				logger.info("Employee " + activeEmployee.getID_String() + " ended their shift at: " + currentTime);
				
				if (activeEmployee.getWorkedTime().containsKey(currentDate)) {
					previousShift = activeEmployee.getWorkedTime().get(currentDate);					
					newTime = time.addUp(previousShift[3], differenceTime);
					
					additionalBreakTime = time.calculateDifference(previousShift[1], activeEmployee.getTimeStarted());
					activeEmployee.setTimePaused(time.addUp(previousShift[2], additionalBreakTime, activeEmployee.getTimePaused()));
					
					addToShift(previousShift[0], activeEmployee.getTimeEnded(), activeEmployee.getTimePaused(), newTime);
				} else {
					newTime = differenceTime;
					addToShift(activeEmployee.getTimeStarted(), activeEmployee.getTimeEnded(), activeEmployee.getTimePaused(), newTime);
				}

				activeEmployee.setWorkedTime(currentDate, newShift);
				activeEmployee.resetTime();
				data.saveDataToFiles();
				ExtendedHandler.enableShiftButtons(true, false, false, false);
				//pack();
			}
		});
		
		logger.debug("EndShiftButton initialised.");
	}
	
	private void addToShift(LocalTime start, LocalTime end, LocalTime paused, LocalTime difference) {
		newShift = new LocalTime[4];
		newShift[0] = start;
		newShift[1] = end;
		
		if(paused == null) {
			newShift[2] = LocalTime.of(0, 0);
		} else {
			newShift[2] = paused;
		}
		newShift[3] = difference;
	}

}
