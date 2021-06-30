package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import timesheets.Employee;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.DisplayList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.optionpanes.CustomOptionPane;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;
import timesheets.sql.Database;

public class LoginButton extends JButton {
	private static final long serialVersionUID = 8434083962194467158L;
	private static final Logger logger = new Logger(LoginButton.class);
	private static Map<String, String> lang = LanguageManager.language;

	private int id;
	private static String buttonText = lang.get("login_button");
	private static String welcomeText = lang.get("welcome_text");
	
	private static String dialogTitle = lang.get("jop_lb_title");
	private static String dialogMsg = lang.get("jop_lb_msg");

	public LoginButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_large);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (TextFieldList.inputField.getText().equals("")) {
					return;
				}
				id = Integer.parseInt(TextFieldList.inputField.getText());

				if (Database.EmployeeList.containsKey(id)) {
					Employee activeEmployee = Database.EmployeeList.get(id);

					DisplayList.loginTextArea.updateInfoText(welcomeText + " " + activeEmployee.getName());
					logger.info("Employee " + activeEmployee.getID_String() + " has logged in.");
					setEnabled(false);

					if (activeEmployee.getTimeStarted() == null) {
						ButtonList.startShiftButton.setEnabled(true);
					}

					if (activeEmployee.getTimeStarted() != null && activeEmployee.getTimePaused() != null) {
						ButtonList.endShiftButton.setEnabled(true);
					} else if (activeEmployee.getTimeStarted() != null
							&& (activeEmployee.getTimePaused() == null && activeEmployee.getBreakStarted() == null)) {
						ButtonList.endShiftButton.setEnabled(true);
					}

					if (activeEmployee.getBreakStarted() == null && activeEmployee.getTimeStarted() != null) {
						ButtonList.startBreakButton.setEnabled(true);
					}

					if (activeEmployee.getBreakEnded() == null && activeEmployee.getBreakStarted() != null) {
						ButtonList.endBreakButton.setEnabled(true);
					}

					if (activeEmployee.getAdmin() == true) {
						ButtonList.switchMenuButton.setEnabled(true);
					}

					ButtonList.logoutButton.setEnabled(true);
				} else {
					logger.warn("Someone entered a non-existent ID.");
					
					CustomOptionPane cop = new CustomOptionPane();
					cop.setText(dialogTitle, dialogMsg);
					cop.setOptions(JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
					cop.showDialog();
				}
			}
		});

		logger.debug("LoginButton initialised.");
	}
}
