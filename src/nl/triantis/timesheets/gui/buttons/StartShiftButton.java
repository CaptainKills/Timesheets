package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class StartShiftButton extends JButton {
	private static final long serialVersionUID = -3218652731101113395L;
	private static final Logger logger = new Logger(StartShiftButton.class);

	private TimeHandler time = new TimeHandler();
	private static String buttonText = LanguageManager.language.get("start_shift_button");

	public StartShiftButton() {
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
				DisplayList.loginTextArea.updateInfoText("Workshift has been started at: " + currentTime);
				logger.info("Employee " + activeEmployee.getID_String() + " has started their shift at " + currentTime);

				activeEmployee.setTimeStarted(currentTime);
				ExtendedHandler.enableShiftButtons(false, true, true, false);
			}
		});

		logger.debug("StartShiftButton initialised.");
	}

}
