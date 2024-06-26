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

public class EndBreakButton extends JButton {
	private static final long serialVersionUID = -7484190806366816954L;
	private static final Logger logger = new Logger(EndBreakButton.class);

	private TimeHandler time = new TimeHandler();
	private LocalTime currentTime, differenceTime, newTime;
	
	private static String buttonText = LanguageManager.language.get("end_break_button");

	public EndBreakButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_large);
		setFont(FontList.buttonFont);
		setEnabled(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int id = Integer.parseInt(TextFieldList.inputField.getText());
				Employee activeEmployee = Database.EmployeeList.get(id);

				currentTime = time.getCurrentTime();
				activeEmployee.setBreakEnded(currentTime);
				logger.info("Employee " + activeEmployee.getID_String() + " has ended their break at: " + currentTime);

				differenceTime = time.calculateDifference(activeEmployee.getBreakStarted(),
						activeEmployee.getBreakEnded());

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
