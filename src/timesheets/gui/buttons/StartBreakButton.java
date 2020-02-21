package timesheets.gui.buttons;

import java.awt.event.*;
import java.time.LocalTime;

import javax.swing.JButton;

import timesheets.*;
import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.*;

public class StartBreakButton extends JButton{
	private TimeHandler time = new TimeHandler();

	public StartBreakButton() {
		super("Start Break");
		setPreferredSize(DimensionList.buttonSize_large);
		setFont(FontList.buttonFont);
		setEnabled(false);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int id = Integer.parseInt(TextFieldList.inputField.getText());
				Employee activeEmployee = DataHandler.EmployeeList.get(id);
				
				LocalTime currentTime = time.roundOffTime(time.getCurrentTime());
				TextAreaList.loginTextArea.updateInfoText("Break has been started at: " + currentTime);
				activeEmployee.setBreakStarted(currentTime);
				ExtendedHandler.enableShiftButtons(false, false, false, true);
				//pack();
			}
		});
	}

}
