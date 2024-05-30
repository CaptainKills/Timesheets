package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nl.triantis.timesheets.gui.ExtendedHandler;
import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.DisplayList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.lists.TextFieldList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

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
