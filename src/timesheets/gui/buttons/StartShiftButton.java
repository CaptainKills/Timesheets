package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class StartShiftButton extends JButton{
	private static final long serialVersionUID = -3218652731101113395L;
	
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
