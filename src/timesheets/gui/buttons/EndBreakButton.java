package timesheets.gui.buttons;

import java.awt.event.*;

import timesheets.*;
import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.*;
import java.time.*;
import javax.swing.JButton;

public class EndBreakButton extends JButton{
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
				Employee activeEmployee = DataHandler.EmployeeList.get(id);
				
				currentTime = time.roundOffTime(time.getCurrentTime());
				TextAreaList.loginTextArea.updateInfoText("Break has been ended at: " + currentTime);
				activeEmployee.setBreakEnded(currentTime);
				differenceTime = time.calculateDifference(activeEmployee.getBreakStarted(), activeEmployee.getBreakEnded());

				if (activeEmployee.getTimePaused() != null) {
					newTime = time.addUp(activeEmployee.getTimePaused(), differenceTime);
				} else {
					newTime = differenceTime;
				}
				TextAreaList.loginTextArea.updateInfoText("Total break taken this shift: " + differenceTime);

				activeEmployee.setTimePaused(newTime);
				activeEmployee.setBreakStarted(null);
				activeEmployee.setBreakEnded(null);
				ExtendedHandler.enableShiftButtons(false, true, true, false);
				//pack();
			}
		});
	}

}
