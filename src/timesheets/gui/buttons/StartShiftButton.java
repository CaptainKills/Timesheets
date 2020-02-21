package timesheets.gui.buttons;

import java.awt.event.*;

import timesheets.*;
import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.*;
import java.time.*;
import javax.swing.JButton;

public class StartShiftButton extends JButton{
	private TimeHandler time = new TimeHandler();

	public StartShiftButton() {
		super("Start Shift");
		setPreferredSize(DimensionList.buttonSize_large);
		setFont(FontList.buttonFont);
		setEnabled(false);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int id = Integer.parseInt(TextFieldList.inputField.getText());
				Employee activeEmployee = DataHandler.EmployeeList.get(id);
				
				LocalTime currentTime = time.roundOffTime(time.getCurrentTime());
				TextAreaList.loginTextArea.updateInfoText("Workshift has been started at: " + currentTime);
				activeEmployee.setTimeStarted(currentTime);
				ExtendedHandler.enableShiftButtons(false, true, true, false);
				//pack();
			}
		});
	}

}
