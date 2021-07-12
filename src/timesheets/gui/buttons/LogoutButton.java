package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.DisplayList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class LogoutButton extends JButton {
	private static final long serialVersionUID = -6786090739017183076L;
	private static final Logger logger = new Logger(LogoutButton.class);
	
	private static String buttonText = LanguageManager.language.get("logout_button");
	private static String enterIdText = LanguageManager.language.get("enter_id_text");

	public LogoutButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_medium);
		setFont(FontList.buttonFont);
		setEnabled(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				TextFieldList.inputField.setText("");
				DisplayList.loginTextArea.updateInfoText(enterIdText);
				ButtonList.switchMenuButton.setText(">>");
				ButtonList.switchMenuButton.setEnabled(false);

				ButtonList.loginButton.setEnabled(true);

				ExtendedHandler.enableShiftButtons(false, false, false, false);
				ExtendedHandler.displayPanels(true, true, false, false, false, false);

				setEnabled(false);
				logger.info("Employee has logged out.");
			}
		});

		logger.debug("LogoutButton initialised.");
	}

}
