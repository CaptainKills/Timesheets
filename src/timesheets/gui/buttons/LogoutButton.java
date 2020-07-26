package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.TextAreaList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;

public class LogoutButton extends JButton{
	private static final long serialVersionUID = -6786090739017183076L;
	private static final Logger logger = new Logger(LogoutButton.class.toString());

	public LogoutButton() {
		super("Logout");
		setPreferredSize(DimensionList.buttonSize_medium);
		setFont(FontList.buttonFont);
		setEnabled(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				TextFieldList.inputField.setText("");
				TextAreaList.loginTextArea.updateInfoText("Please enter ID to log in...");
				ButtonList.switchMenuButton.setText(">>");
				ButtonList.switchMenuButton.setEnabled(false);

				ButtonList.loginButton.setEnabled(true);
				
				displayTimeButtons(true);
				displayAdminButtons(false);
				ExtendedHandler.enableShiftButtons(false, false, false, false);
				setEnabled(false);

				PanelList.numpadPanel.setVisible(true);
				PanelList.editPanel.setVisible(false);
				PanelList.timesheetPanel.setVisible(false);
				
				logger.info("Employee has logged out.");
			}
		});
		
		logger.debug("LogoutButton initialised.");
	}
	
	private void displayTimeButtons(Boolean bool) {
		ButtonList.startShiftButton.setVisible(bool);
		ButtonList.endShiftButton.setVisible(bool);
		ButtonList.startBreakButton.setVisible(bool);
		ButtonList.endBreakButton.setVisible(bool);
	}

	private void displayAdminButtons(Boolean bool) {
		ButtonList.addEmployeeButton.setVisible(bool);
		ButtonList.removeEmployeeButton.setVisible(bool);
		ButtonList.editEmployeeButton.setVisible(bool);
		ButtonList.printSheetsButton.setVisible(bool);
		ButtonList.editSheetsButton.setVisible(bool);
		ButtonList.exitButton.setVisible(bool);
	}
}
