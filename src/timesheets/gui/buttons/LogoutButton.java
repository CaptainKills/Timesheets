package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.DisplayList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;

public class LogoutButton extends JButton{
	private static final long serialVersionUID = -6786090739017183076L;
	private static final Logger logger = new Logger(LogoutButton.class);

	public LogoutButton() {
		super("Logout");
		setPreferredSize(DimensionList.buttonSize_medium);
		setFont(FontList.buttonFont);
		setEnabled(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				TextFieldList.inputField.setText("");
				DisplayList.loginTextArea.updateInfoText("Please enter ID to log in...");
				ButtonList.switchMenuButton.setText(">>");
				ButtonList.switchMenuButton.setEnabled(false);

				ButtonList.loginButton.setEnabled(true);
				
				ExtendedHandler.displayTimeButtons(true);
				ExtendedHandler.displayAdminButtons(false);
				ExtendedHandler.enableShiftButtons(false, false, false, false);
				ExtendedHandler.displayAdminPanels(true, false, false, false);
				
				setEnabled(false);
				logger.info("Employee has logged out.");
			}
		});
		
		logger.debug("LogoutButton initialised.");
	}
	
	


}
