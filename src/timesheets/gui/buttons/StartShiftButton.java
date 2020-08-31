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

public class StartShiftButton extends JButton {
	private static final long serialVersionUID = -3218652731101113395L;
	private static final Logger logger = new Logger(StartShiftButton.class);

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
				Employee activeEmployee = Database.EmployeeList.get(id);

				LocalTime currentTime = time.roundOffTime(time.getCurrentTime());
				DisplayList.loginTextArea.updateInfoText("Workshift has been started at: " + currentTime);
				logger.info("Employee " + activeEmployee.getID_String() + " has started their shift at " + currentTime);

				activeEmployee.setTimeStarted(currentTime);
				ExtendedHandler.enableShiftButtons(false, true, true, false);
			}
		});

		logger.debug("StartShiftButton initialised.");
	}

}
