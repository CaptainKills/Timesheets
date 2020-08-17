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

public class StartBreakButton extends JButton{
	private static final long serialVersionUID = 8689997860170245346L;
	private static final Logger logger = new Logger(StartBreakButton.class.toString());
	
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
				Employee activeEmployee = Database.EmployeeList.get(id);
				
				LocalTime currentTime = time.roundOffTime(time.getCurrentTime());
				DisplayList.loginTextArea.updateInfoText("Break has been started at: " + currentTime);
				logger.info("Employee " + activeEmployee.getID_String() + " has started their break at " + currentTime);
				
				activeEmployee.setBreakStarted(currentTime);
				ExtendedHandler.enableShiftButtons(false, false, false, true);
			}
		});
		
		logger.debug("StartBreakButton initialised.");
	}

}
