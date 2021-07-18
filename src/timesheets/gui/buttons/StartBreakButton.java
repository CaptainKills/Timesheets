package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;

import timesheets.Employee;
import timesheets.TimeHandler;
import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.DisplayList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;
import timesheets.sql.Database;

public class StartBreakButton extends JButton {
	private static final long serialVersionUID = 8689997860170245346L;
	private static final Logger logger = new Logger(StartBreakButton.class);

	private TimeHandler time = new TimeHandler();
	private static String buttonText = LanguageManager.language.get("start_break_button");

	public StartBreakButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_large);
		setFont(FontList.buttonFont);
		setEnabled(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int id = Integer.parseInt(TextFieldList.inputField.getText());
				Employee activeEmployee = Database.EmployeeList.get(id);

				LocalTime currentTime = time.getCurrentTime();
				DisplayList.loginTextArea.updateInfoText("Break has been started at: " + currentTime);
				logger.info("Employee " + activeEmployee.getID_String() + " has started their break at " + currentTime);

				activeEmployee.setBreakStarted(currentTime);
				ExtendedHandler.enableShiftButtons(false, false, false, true);
			}
		});

		logger.debug("StartBreakButton initialised.");
	}

}
