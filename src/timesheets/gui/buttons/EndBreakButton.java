package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;

import timesheets.Employee;
import timesheets.TimeHandler;
import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.DisplayList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class EndBreakButton extends JButton{
	private static final long serialVersionUID = -7484190806366816954L;
	private static final Logger logger = new Logger(EndBreakButton.class);

	private TimeHandler time = new TimeHandler();
	
	private LocalTime currentTime, differenceTime, newTime;

	public EndBreakButton() {
		super("End Break");
		setPreferredSize(DimensionList.buttonSize_large);
		setFont(FontList.buttonFont);
		setEnabled(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int id = Integer.parseInt(TextFieldList.inputField.getText());
				Employee activeEmployee = Database.EmployeeList.get(id);
				
				currentTime = time.roundOffTime(time.getCurrentTime());
				activeEmployee.setBreakEnded(currentTime);
				logger.info("Employee " + activeEmployee.getID_String() + " has ended their break at: " + currentTime);
				
				differenceTime = time.calculateDifference(activeEmployee.getBreakStarted(), activeEmployee.getBreakEnded());

				if (activeEmployee.getTimePaused() != null) {
					newTime = time.addUp(activeEmployee.getTimePaused(), differenceTime);
				} else {
					newTime = differenceTime;
				}
				DisplayList.loginTextArea.updateInfoText("Total break taken this shift: " + differenceTime);

				activeEmployee.setTimePaused(newTime);
				activeEmployee.setBreakStarted(null);
				activeEmployee.setBreakEnded(null);
				ExtendedHandler.enableShiftButtons(false, true, true, false);
			}
		});
		logger.debug("EndBreakButton initialised.");
	}

}
