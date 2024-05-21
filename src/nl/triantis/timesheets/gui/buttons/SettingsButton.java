package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;

import nl.triantis.timesheets.Settings;
import nl.triantis.timesheets.gui.ExtendedHandler;
import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.lists.TextFieldList;
import nl.triantis.timesheets.gui.lists.UnusualsList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class SettingsButton extends JButton {
	private static final long serialVersionUID = -3562889450672410185L;
	private static final Logger logger = new Logger(SettingsButton.class);

	private static Map<String, String> settings = Settings.settings;
	private static String buttonText = LanguageManager.language.get("settings_button");

	public SettingsButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				ExtendedHandler.displayPanels(false, false, true, false, false, true);
				loadSettingsFields();
			}
		});

		logger.debug("SettingsButton initialised.");
	}

	private void loadSettingsFields() {
		boolean deleteLogs = Boolean.parseBoolean(settings.get("delete_logs"));

		if (deleteLogs == true) {
			ButtonList.deleteLogEnabledButton.setSelected(true);
			TextFieldList.logCountInput.setEnabled(true);
		} else {
			ButtonList.deleteLogDisabledButton.setSelected(true);
			TextFieldList.logCountInput.setEnabled(false);
		}

		UnusualsList.backupBox.loadBackupsInBox();
	}

}
